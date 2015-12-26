/*
 * *
 *  * Copyright(c) 2010-2016 by GmsdTech Inc.
 *  * All Rights Reserved
 *
 */

package com.gmsdtech.datacenter.model.records.userinfo;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

import com.gmsdtech.datacenter.constant.user.Gender;

/**
 * 用户联系信息记录
 * Created by routine on 15/2/2.
 */
@Entity
@Table(name = "user_info_records")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class UserInfoRecords implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主健ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 用户编号
     */
    @Index(name = "user_serial_index")
    @Column(name = "user_serial", nullable = false, length = 32)
    private String userSerial;

    /**
     * 用户姓名
     */
    @Column(name = "name", length = 32)
    private String name;

    /**
     * 性别
     */
    @Column(name = "gender", length = 8)
    private Gender gender;

    /**
     * 出生日期
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birthday")
    private Date birthDay;

    /**
     * 年龄
     */
    @Column(name = "age", length = 2)
    private Integer age;

    /**
     * 用户手机号
     */
    @Index(name = "mobile_index")
    @Column(name = "mobile", length = 32)
    private String mobile;

    /**
     * 电子邮箱
     */
    @Index(name = "email_index")
    @Column(name = "email", length = 64)
    private String email;

    /**
     * qq
     */
    @Column(name = "qq", length = 16)
    private String qq;

    /**
     * 微信
     */
    @Column(name = "weixin", length = 16)
    private String weixin;

    /**
     * 微博
     */
    @Column(name = "weibo", length = 16)
    private String weibo;

    /**
     * 证件有效期
     */
    @Column(name = "begin_legal_time", length = 16)
    private String beginLegalTime;

    /**
     * 证件有效期
     */
    @Column(name = "end_legal_time", length = 16)
    private String endLegalTime;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /**
     * 身份证号码
     */
    @Index(name = "identity_number_index")
    @Column(name = "identity_number", nullable = false, length = 32)
    private String identityNumber;

    /**
     * 更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    /**
     * 备注
     */
    @Column(name = "comments", length = 128)
    private String comments;

    /**
     * 机构来源编码
     */
    @Index(name = "source_agency_serial_index")
    @Column(name = "source_agency_serial", nullable = false, length = 32)
    private String sourceAgencySerial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserSerial() {
        return userSerial;
    }

    public void setUserSerial(String userSerial) {
        this.userSerial = userSerial;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getBeginLegalTime() {
        return beginLegalTime;
    }

    public void setBeginLegalTime(String beginLegalTime) {
        this.beginLegalTime = beginLegalTime;
    }

    public String getEndLegalTime() {
        return endLegalTime;
    }

    public void setEndLegalTime(String endLegalTime) {
        this.endLegalTime = endLegalTime;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getSourceAgencySerial() {
        return sourceAgencySerial;
    }

    public void setSourceAgencySerial(String sourceAgencySerial) {
        this.sourceAgencySerial = sourceAgencySerial;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public UserInfoRecords clone() throws CloneNotSupportedException {
        return (UserInfoRecords)super.clone();
    }
}
