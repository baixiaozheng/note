package com.note.util.pdf;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xhtmlrenderer.pdf.ITextFontResolver;

import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;

/**
 * 导出pdf
 * @author WQY
 *
 */

public class PdfExportUtil {

	private static final int MAX_SIZE = 45;
	public static InputStream htmlToPdf(String htmlContent,String picUrl,String topLeft) throws Exception {   

        OutputStream os = new ByteArrayOutputStream();       
        PDFITextRenderer renderer = new PDFITextRenderer();       
        ITextFontResolver fontResolver = renderer.getFontResolver();       
        fontResolver.addFont("/Users/baixiaozheng/Documents/部署文件/runtime_env/font/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        fontResolver.addFont("/Users/baixiaozheng/Documents/部署文件/runtime_env/font/simsumbd.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        StringBuffer html = new StringBuffer();       
        // DOCTYPE 必需写否则类似于 这样的字符解析会出现错误   
    	html.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");       
        html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">").     
             append("<head>")       
            .append("<meta http-equiv=\"Content-Type\" content=\"textml; charset=UTF-8\" />")     
            .append("<style type=\"text/css\">"+
                    "@page {"+
                 //     "size:landscape;"+ // 设置横向打印
                      "margin-top:95pt;"+
                      "@top-center {content: element(header)};"+
                    //  "@bottom-center {content: element(footer)}"+
                      "page-break-before:always"+ //强制换行
//                    "page-break-before:always"+
                      "@bottom-right { content: element(footer-right) };"+
                    "}" +
                   // "div.footer-right {display: none ;}"+
                    "#number:before {content: counter(page);}"+
                    "#count:before { content: counter(pages); }"+
                    "@media print { .footer-right { display: block;  position: running(footer-right);  } } "+
                    ".header1 {position: running(header);color:#cccccc;font-family: SimSun;padding-top:25pt;text-align:right;margin-top:0px;}"+
                    "body {font-family: SimSun;line-height:21px;font-size:14px;}" +
                    //"table{page-break-before:always}" +
                    "table tr (height:30px;)" +
                    "table tr td(border-collapse:collapse; word-break: break-all;)" +
                    "strong { font-weight: bold }"+
                    "h1 { text-align: center}" +
                    ".spanHeader{float:left;}" +
                    ".default{text-align:right;margin:0;border-bottom:1px solid #030303;height:50px;}" +
                    
            	"</style></head>")
                   
            .append("<body ><div style=\"margin-left:20px\">");    
        
        //页眉
        html.append("<div>" +
        		          "<div class='header1' style=''>" +
        		          "<div class='default'>"+
        		          "<span class='spanHeader' >" +//<img src='logo.png' width='84' height='47' />
        		          "<img src='"+picUrl+"' height='47' /></span><div style='float:right;height:20px;padding-top:30px;text-algin:right;color:#000;'>" +
        		          ""+topLeft+"&nbsp;&nbsp;</div></div></div>" +
        		          "</div>");   
        html.append("<div style='margin-top:10px;width:100%;right;'>");
        
        html.append(htmlContent); // 自动换行加<br></br>

        html.append("</div></div>");
        // 页脚
       // html.append("<div class='footer-right' style='display: none;' >第 <span id='number'></span> 页</div>");
        html.append("</body></html>");
     //   System.out.print(html.toString());
        renderer.setDocumentFromString(html.toString());
       
        com.lowagie.text.Document document = new com.lowagie.text.Document(PageSize.A4, 500, 60, 50, 50);
	    /**
	     * HeaderFooter的第2个参数为非false时代表打印页码
	     * 页眉页脚中也可以加入图片，并非只能是文字
	     */
		HeaderFooter header=new HeaderFooter(new Phrase("this"),false);
		
		//设置是否有边框等
		header.setBorder(Rectangle.BOTTOM);
		header.setAlignment(1);
		header.setBorderColor(Color.red);
		document.setHeader(header);
        // 解决图片的相对路径问题       
		//renderer.getSharedContext().setBaseURL(CommonConf.FONT_PATH+"font/");
        renderer.layout();       
        renderer.createPDF(os,true,0,document);  
        ByteArrayOutputStream buffer = (ByteArrayOutputStream) os;
		InputStream excelFile = new ByteArrayInputStream(buffer.toByteArray());
        return excelFile;      
    }  
	
	
	public static String dealHtml(String html){
		// 这是个字符串，检查中文字符串
		html = html.replaceAll("&ldquo;", "“");
		html = html.replaceAll("&rdquo;", "”");
		// 处理文字加粗
		//html = html.replaceAll("<strong>", "<span style='font-weight:700'>");
		//html = html.replaceAll("</strong>", "</span>");
		List<String> htmlList = new ArrayList<String>();
		String[] htmlArray = html.split("<");
		
		for(String htmlA : htmlArray){
			htmlA += "<";
			int length = htmlA.length();
			if(length > MAX_SIZE+1){
				List<Boolean> list = isChineseOrNum(htmlA);
				String newHtmlA = "";
				int count = 0;
				int num = 0;
				int t = 0;
				boolean sign = false;
				boolean isTag = false;
				for(boolean flag : list){
					char c = htmlA.charAt(t);
					
					//判断是否是标签
					if(isTag){
						if(c == '>'){
							isTag = false;
						} else {
							continue;
						}
					} 
					if(c == '<' ){
						isTag = true;
						continue;
					}
					if(flag && !sign ){
						count ++;	
						if(count == MAX_SIZE || count == MAX_SIZE + 1){
							if(count == MAX_SIZE && t == length - 2){
								continue;
							}
							newHtmlA += htmlA.substring(num, t+1)+"<br></br>"; // 标签中不能添加br
							num = t+1;
							count = 0;
						}
					} else {
						if(c == ' ' ){
							count = 0;
						}
						if(sign && c == ';'){
							count ++;
							sign = false;
							if(count == MAX_SIZE || count == MAX_SIZE + 1){
								if(count == MAX_SIZE && t == length - 2){
									continue;
								}
								newHtmlA += htmlA.substring(num, t+1)+"<br></br>";
								num = t+1;
								count = 0;
							} 
						} else if(c == '&') {
							sign = true;
						}
					}
					t++;
				} 
				
				newHtmlA += htmlA.substring(num, htmlA.length());
				htmlList.add(newHtmlA);
				list = null;
			} else {
				htmlList.add(htmlA);
			}
			
		}
		
		String newHtml = "";
		for(String string : htmlList){
			newHtml += string;
		}
		newHtml = newHtml.substring(0, newHtml.length()-1);
		//System.out.println(newHtml);
		return newHtml;
	}

	   // GENERAL_PUNCTUATION 判断中文的“号  
    // CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号  
    // HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号  
    private static final boolean isChinese(char c) {  
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
        
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                ) {  
            return true;  
        }  
            
        return false;  
    }  
  
    /**
     * 判断是否是一个汉字（如果有两个字母或者数字后面一个算一个汉字）
     * @param strName
     * @return
     */
    public static List<Boolean> isChineseOrNum(String strName) {  
    	List<Boolean> list = new ArrayList<Boolean>();
    	char[] ch = strName.toCharArray();  
    	boolean isNum = false;
        for (int i = 0; i < ch.length; i++) {  
            char c = ch[i];  
            if (isChinese(c)) {  
            	list.add(true);
            } else if ( Character.isDigit(c) || isSpecialTag(i, strName)){ // 字母怎么处理
            	if(!isRealSpell(i, strName) ){
            		if(isNum ){
                		isNum = false;
                		list.add(true);
                	} else {
                		isNum = true;
                		list.add(false);
                	}
            	} else {
            		list.add(false);
            	}
            	
            } else {
            	list.add(false);
            }
           
        }  
        return list;  
    } 
    
    /**
     * 判断是否是符号（只占一个字符）
     * @param index
     * @param strName
     * @return
     */
    public static boolean isSpecialTag(int index, String strName){
    	char c = strName.charAt(index);
    	if(index != 0){
    		if(c == '/' ){
        		char pre = strName.charAt(index-1);
        		if(pre != '<'){ // 
        			return true;
        		}
        	}
        	if(c == '%'){
        		char[] ch = strName.toCharArray();
        		boolean flag = false;
            	for (int i = index ; i < ch.length; i++) {  
            		char c1 = ch[i];  
            		if( c1 == '>' ){
            			flag = true;
            			break;
            		}
            	}
            	if(!flag){
            		return true;
            	}
        	}
        	if(c == '¥' || c == '.'){
        		return true;
        	}
    	}
    	return true;
    }
    
    /**
     * 判断是否是标签内的字母
     * @param index
     * @param strName
     * @return
     */
    public static boolean isRealSpell(int index, String strName){ //是否是字母
    	//字母必须不是 标签内的字母才行
    	// 并且不是 标点符号
    	
    	// 怎么判断是否是tag中的字母
    	char[] ch = strName.toCharArray();
    	for (int i = index ; i < ch.length; i++) {  
    		char c = ch[i];  
    		if(c == '<'){
    			return false;
    		} else if( c == '>' ){	
    			return true;
    		}
    	}
    	return false;
    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	String html = "";
    	System.out.println(dealHtml(html));
	}
}
