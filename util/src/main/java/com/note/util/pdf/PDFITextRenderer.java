package com.note.util.pdf;

import java.awt.Dimension;
import java.awt.Shape;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xhtmlrenderer.context.StyleReference;
import org.xhtmlrenderer.extend.NamespaceHandler;
import org.xhtmlrenderer.extend.UserInterface;
import org.xhtmlrenderer.layout.BoxBuilder;
import org.xhtmlrenderer.layout.Layer;
import org.xhtmlrenderer.layout.LayoutContext;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextFontContext;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextOutputDevice;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.ITextReplacedElementFactory;
import org.xhtmlrenderer.pdf.ITextTextRenderer;
import org.xhtmlrenderer.pdf.ITextUserAgent;
import org.xhtmlrenderer.pdf.PDFCreationListener;
import org.xhtmlrenderer.pdf.PDFEncryption;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.render.PageBox;
import org.xhtmlrenderer.render.RenderingContext;
import org.xhtmlrenderer.render.ViewportBox;
import org.xhtmlrenderer.resource.XMLResource;
import org.xhtmlrenderer.simple.extend.XhtmlNamespaceHandler;
import org.xhtmlrenderer.util.Configuration;
import org.xml.sax.InputSource;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;

@SuppressWarnings("rawtypes")
public class PDFITextRenderer extends ITextRenderer {
	@SuppressWarnings("unused")
	private static final float DEFAULT_DOTS_PER_POINT = 26.666666F;
	@SuppressWarnings("unused")
	private static final int DEFAULT_DOTS_PER_PIXEL = 20;
	private final SharedContext _sharedContext;
	private final ITextOutputDevice _outputDevice;
	private org.w3c.dom.Document _doc;
	private BlockBox _root;
	private final float _dotsPerPoint;
	private com.lowagie.text.Document _pdfDoc;
	private PdfWriter _writer;
	private PDFEncryption _pdfEncryption;
	private Character _pdfVersion;
	private final char[] validPdfVersions = { '2', '3', '4', '5', '6', '7' };
	private PDFCreationListener _listener;

	public PDFITextRenderer() {
		this(26.666666F, 20);
	}

	public PDFITextRenderer(float dotsPerPoint, int dotsPerPixel) {
		this._dotsPerPoint = dotsPerPoint;

		this._outputDevice = new ITextOutputDevice(this._dotsPerPoint);

		ITextUserAgent userAgent = new ITextUserAgent(this._outputDevice);
		this._sharedContext = new SharedContext();
		this._sharedContext.setUserAgentCallback(userAgent);
		this._sharedContext.setCss(new StyleReference(userAgent));
		userAgent.setSharedContext(this._sharedContext);
		this._outputDevice.setSharedContext(this._sharedContext);

		ITextFontResolver fontResolver = new ITextFontResolver(
				this._sharedContext);
		this._sharedContext.setFontResolver(fontResolver);

		ITextReplacedElementFactory replacedElementFactory = new ITextReplacedElementFactory(
				this._outputDevice);

		this._sharedContext.setReplacedElementFactory(replacedElementFactory);

		this._sharedContext.setTextRenderer(new ITextTextRenderer());
		this._sharedContext.setDPI(72.0F * this._dotsPerPoint);
		this._sharedContext.setDotsPerPixel(dotsPerPixel);
		this._sharedContext.setPrint(true);
		this._sharedContext.setInteractive(false);
	}

	public ITextFontResolver getFontResolver() {
		return (ITextFontResolver) this._sharedContext.getFontResolver();
	}

	private org.w3c.dom.Document loadDocument(String uri) {
		return this._sharedContext.getUac().getXMLResource(uri).getDocument();
	}

	public void setDocument(String uri) {
		setDocument(loadDocument(uri), uri);
	}

	public void setDocument(org.w3c.dom.Document doc, String url) {
		setDocument(doc, url, new XhtmlNamespaceHandler());
	}

	public void setDocument(File file) throws IOException {
		File parent = file.getAbsoluteFile().getParentFile();
		setDocument(loadDocument(file.toURI().toURL().toExternalForm()),
				parent == null ? "" : parent.toURI().toURL().toExternalForm());
	}

	public void setDocumentFromString(String content) {
		setDocumentFromString(content, null);
	}

	public void setDocumentFromString(String content, String baseUrl) {
		InputSource is = new InputSource(new BufferedReader(new StringReader(
				content)));
		org.w3c.dom.Document dom = XMLResource.load(is).getDocument();

		setDocument(dom, baseUrl);
	}

	public void setDocument(org.w3c.dom.Document doc, String url,
			NamespaceHandler nsh) {
		this._doc = doc;

		getFontResolver().flushFontFaceFonts();

		this._sharedContext.reset();
		if (Configuration.isTrue("xr.cache.stylesheets", true))
			this._sharedContext.getCss().flushStyleSheets();
		else {
			this._sharedContext.getCss().flushAllStyleSheets();
		}
		this._sharedContext.setBaseURL(url);
		this._sharedContext.setNamespaceHandler(nsh);
		// this._sharedContext.getCss().setDocumentContext(this._sharedContext,
		// this._sharedContext.getNamespaceHandler(), doc, new
		// NullUserInterface(null));
		this._sharedContext.getCss().setDocumentContext(this._sharedContext,
				this._sharedContext.getNamespaceHandler(), doc,
				new NullUserInterface());
		getFontResolver().importFontFaces(
				this._sharedContext.getCss().getFontFaceRules());
	}

	public PDFEncryption getPDFEncryption() {
		return this._pdfEncryption;
	}

	public void setPDFEncryption(PDFEncryption pdfEncryption) {
		this._pdfEncryption = pdfEncryption;
	}

	public void setPDFVersion(char _v) {
		for (int i = 0; i < this.validPdfVersions.length; i++) {
			if (_v == this.validPdfVersions[i]) {
				this._pdfVersion = new Character(_v);
				return;
			}
		}
		throw new IllegalArgumentException(
				"Invalid PDF version character; use valid constants from PdfWriter (e.g. PdfWriter.VERSION_1_2)");
	}

	public char getPDFVersion() {
		return this._pdfVersion == null ? '0' : this._pdfVersion.charValue();
	}

	public void layout() {
		LayoutContext c = newLayoutContext();
		BlockBox root = BoxBuilder.createRootBox(c, this._doc);
		root.setContainingBlock(new ViewportBox(getInitialExtents(c)));
		root.layout(c);
		Dimension dim = root.getLayer().getPaintingDimension(c);
		root.getLayer().trimEmptyPages(c, dim.height);
		root.getLayer().layoutPages(c);
		this._root = root;
	}

	private java.awt.Rectangle getInitialExtents(LayoutContext c) {
		PageBox first = Layer.createPageBox(c, "first");

		return new java.awt.Rectangle(0, 0, first.getContentWidth(c), first
				.getContentHeight(c));
	}

	private RenderingContext newRenderingContext() {
		RenderingContext result = this._sharedContext
				.newRenderingContextInstance();
		result.setFontContext(new ITextFontContext());

		result.setOutputDevice(this._outputDevice);

		this._sharedContext.getTextRenderer().setup(result.getFontContext());

		result.setRootLayer(this._root.getLayer());

		return result;
	}

	private LayoutContext newLayoutContext() {
		LayoutContext result = this._sharedContext.newLayoutContextInstance();
		result.setFontContext(new ITextFontContext());

		this._sharedContext.getTextRenderer().setup(result.getFontContext());

		return result;
	}

	public void createPDF(OutputStream os) throws DocumentException {
		createPDF(os, true, 0);
	}

	public void writeNextDocument() throws DocumentException {
		writeNextDocument(0);
	}

	public void writeNextDocument(int initialPageNo) throws DocumentException {
		List pages = this._root.getLayer().getPages();

		RenderingContext c = newRenderingContext();
		c.setInitialPageNo(initialPageNo);
		PageBox firstPage = (PageBox) pages.get(0);
		com.lowagie.text.Rectangle firstPageSize = new com.lowagie.text.Rectangle(
				0.0F, 0.0F, firstPage.getWidth(c) / this._dotsPerPoint,
				firstPage.getHeight(c) / this._dotsPerPoint);

		this._outputDevice.setStartPageNo(this._writer.getPageNumber());

		this._pdfDoc.setPageSize(firstPageSize);
		this._pdfDoc.newPage();

		writePDF(pages, c, firstPageSize, this._pdfDoc, this._writer);
	}

	public void finishPDF() {
		if (this._pdfDoc != null) {
			fireOnClose();
			this._pdfDoc.close();
		}
	}

	public void createPDF(OutputStream os, boolean finish)
			throws DocumentException {
		createPDF(os, finish, 0);
	}

	public void createPDF(OutputStream os, boolean finish, int initialPageNo)
			throws DocumentException {
		List pages = this._root.getLayer().getPages();

		RenderingContext c = newRenderingContext();
		c.setInitialPageNo(initialPageNo);
		PageBox firstPage = (PageBox) pages.get(0);
		com.lowagie.text.Rectangle firstPageSize = new com.lowagie.text.Rectangle(
				0.0F, 0.0F, firstPage.getWidth(c) / this._dotsPerPoint,
				firstPage.getHeight(c) / this._dotsPerPoint);

		com.lowagie.text.Document doc = new com.lowagie.text.Document(
				firstPageSize, 0.0F, 0.0F, 0.0F, 0.0F);

		PdfWriter writer = PdfWriter.getInstance(doc, os);
		if (this._pdfVersion != null) {
			writer.setPdfVersion(this._pdfVersion.charValue());
		}
		if (this._pdfEncryption != null) {
			writer.setEncryption(this._pdfEncryption.getUserPassword(),
					this._pdfEncryption.getOwnerPassword(), this._pdfEncryption
							.getAllowedPrivileges(), this._pdfEncryption
							.getEncryptionType());
		}

		this._pdfDoc = doc;
		this._writer = writer;

		firePreOpen();
		doc.open();

		writePDF(pages, c, firstPageSize, doc, writer);

		if (finish) {
			fireOnClose();
			doc.close();
		}
	}
	
	
	
	public void createPDF(OutputStream os, boolean finish, int initialPageNo,com.lowagie.text.Document doc)
			throws DocumentException {
		
		List pages = this._root.getLayer().getPages();
		
		RenderingContext c = newRenderingContext();
		c.setInitialPageNo(initialPageNo);
		PageBox firstPage = (PageBox) pages.get(0);
		com.lowagie.text.Rectangle firstPageSize = new com.lowagie.text.Rectangle(
				0.0F, 0.0F, firstPage.getWidth(c) / this._dotsPerPoint,
				firstPage.getHeight(c) / this._dotsPerPoint);
//		com.lowagie.text.Document doc = new com.lowagie.text.Document(
//				firstPageSize, 0.0F, 0.0F, 0.0F, 0.0F);
		
		PdfWriter writer = PdfWriter.getInstance(doc, os);
		if (this._pdfVersion != null) {
			writer.setPdfVersion(this._pdfVersion.charValue());
		}
		if (this._pdfEncryption != null) {
			writer.setEncryption(this._pdfEncryption.getUserPassword(),
					this._pdfEncryption.getOwnerPassword(), this._pdfEncryption
							.getAllowedPrivileges(), this._pdfEncryption
							.getEncryptionType());
		}
		
		this._pdfDoc = doc;
		this._writer = writer;
		
		firePreOpen();
		doc.open();
		
		writePDF(pages, c, firstPageSize, doc, writer);
		
		if (finish) {
			fireOnClose();
			doc.close();
		}
	}


	private void firePreOpen() {
		if (this._listener != null)
			this._listener.preOpen(this);
	}

	private void fireOnClose() {
		if (this._listener != null)
			this._listener.onClose(this);
	}

	private void writePDF(List pages, RenderingContext c,
			com.lowagie.text.Rectangle firstPageSize,
			com.lowagie.text.Document doc, PdfWriter writer)
			throws DocumentException {
		this._outputDevice.setRoot(this._root);

		this._outputDevice.start(this._doc);
		this._outputDevice.setWriter(writer);
		this._outputDevice.initializePage(writer.getDirectContent(),
				firstPageSize.getHeight());

		this._root.getLayer().assignPagePaintingPositions(c, (short) 2);

		int pageCount = this._root.getLayer().getPages().size();
		c.setPageCount(pageCount);
		for (int i = 0; i < pageCount; i++) {
			PageBox currentPage = (PageBox) pages.get(i);
			c.setPage(i, currentPage);
			paintPage(c, writer, currentPage);
			this._outputDevice.finishPage();
			if (i != pageCount - 1) {
				PageBox nextPage = (PageBox) pages.get(i + 1);
				com.lowagie.text.Rectangle nextPageSize = new com.lowagie.text.Rectangle(
						0.0F, 0.0F, nextPage.getWidth(c) / this._dotsPerPoint,
						nextPage.getHeight(c) / this._dotsPerPoint);
//				nextPageSize.set
				doc.setPageSize(nextPageSize);
				doc.newPage();
				this._outputDevice.initializePage(writer.getDirectContent(),
						nextPageSize.getHeight());
			}

		}

		this._outputDevice.finish(c, this._root);
	}

	private void paintPage(RenderingContext c, PdfWriter writer, PageBox page) {
		provideMetadataToPage(writer, page);

		page.paintBackground(c, 0, (short) 2);
		page.paintMarginAreas(c, 0, (short) 2);
		page.paintBorder(c, 0, (short) 2);

		Shape working = this._outputDevice.getClip();

		java.awt.Rectangle content = page.getPrintClippingBounds(c);
		this._outputDevice.clip(content);

		int top = -page.getPaintingTop() + page.getMarginBorderPadding(c, 3);

		int left = page.getMarginBorderPadding(c, 1);

		this._outputDevice.translate(left, top);
		this._root.getLayer().paint(c);
		this._outputDevice.translate(-left, -top);

		this._outputDevice.setClip(working);
	}

	private void provideMetadataToPage(PdfWriter writer, PageBox page) {
		byte[] metadata = null;
		if (page.getMetadata() != null) {
			try {
				String metadataBody = stringfyMetadata(page.getMetadata());
				if (metadataBody != null)
					metadata = createXPacket(
							stringfyMetadata(page.getMetadata())).getBytes(
							"UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}

		writer.setPageXmpMetadata(metadata);
	}

	private String stringfyMetadata(Element element) {
		Element target = getFirstChildElement(element);
		if (target == null) {
			return null;
		}
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			transformer.setOutputProperty("omit-xml-declaration", "yes");
			StringWriter output = new StringWriter();
			transformer.transform(new DOMSource(target), new StreamResult(
					output));

			return output.toString();
		} catch (TransformerConfigurationException e) {
			throw new RuntimeException(e);
		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}

	}

	private static Element getFirstChildElement(Element element) {
		Node n = element.getFirstChild();
		while (n != null) {
			if (n.getNodeType() == 1) {
				return (Element) n;
			}
			n = n.getNextSibling();
		}
		return null;
	}

	private String createXPacket(String metadata) {
		StringBuffer result = new StringBuffer(metadata.length() + 50);
		result.append("<?xpacket begin='﻿' id='W5M0MpCehiHzreSzNTczkc9d'?>\n");
		result.append(metadata);
		result.append("\n<?xpacket end='r'?>");

		return result.toString();
	}

	public ITextOutputDevice getOutputDevice() {
		return this._outputDevice;
	}

	public SharedContext getSharedContext() {
		return this._sharedContext;
	}

	public void exportText(Writer writer) throws IOException {
		RenderingContext c = newRenderingContext();
		c.setPageCount(this._root.getLayer().getPages().size());
		this._root.exportText(c, writer);
	}

	public BlockBox getRootBox() {
		return this._root;
	}

	public float getDotsPerPoint() {
		return this._dotsPerPoint;
	}

	public List findPagePositionsByID(Pattern pattern) {
		return this._outputDevice.findPagePositionsByID(newLayoutContext(),
				pattern);
	}

	public PDFCreationListener getListener() {
		return this._listener;
	}

	public void setListener(PDFCreationListener listener) {
		this._listener = listener;
	}

	public PdfWriter getWriter() {
		return this._writer;
	}

	private static final class NullUserInterface implements UserInterface {
		private NullUserInterface() {
		}

		public boolean isHover(Element e) {
			return false;
		}

		public boolean isActive(Element e) {
			return false;
		}

		public boolean isFocus(Element e) {
			return false;
		}

		// NullUserInterface(ITextRenderer.1 x0){
		// this();
		// }
	}
}