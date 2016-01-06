package com.note.model.comment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * 评论
 * 
 * @author baixiaozheng
 * @Date 2016年01月04日15:07:12
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5548248758301137699L;

	/**
	 * id
	 */
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	/**
	 * 内容
	 */
	@Column(name = "content", nullable = false)
	private String content;
	
	/**
	 * note的id
	 */
	@Column(name = "note_id", nullable = false)
	private Integer noteId;
	
	/**
	 * 父评论id（若此条评论为回复，被回复的评论的id）
	 */
	@Column(name = "parent_id", columnDefinition = "int default 0")
	private Integer parentId;
	
	/**
	 * 创建者
	 */
	@Column(name = "user_id", nullable = false)
	private Integer userId;
	
	/**
	 * 创建者的ip
	 */
	@Column(name = "ip", nullable = false)
	private String ip;
	
    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;
	
    /**
     * 更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;
    
    /**
     * 是否正常（false为被删除）
     */
    @Column(name = "enable")
    private Boolean enable;

    /**
     * 是否发布（true正常显示，false只在管理后台可见）
     */
    @Column(name = "publish")
    private Boolean publish;
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Boolean getPublish() {
		return publish;
	}

	public void setPublish(Boolean publish) {
		this.publish = publish;
	}
	
}
