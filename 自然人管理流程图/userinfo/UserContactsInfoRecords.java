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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

import com.gmsdtech.datacenter.constant.user.UserContactsRelation;
import com.gmsdtech.datacenter.constant.user.UserContactsType;

/**
 * 用户联系人记录
 * <p/>
 * 联系人信息记录表 Created by routine on 15/2/2.
 */
@Entity
@Table(name = "user_contactinfo_records")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class UserContactsInfoRecords implements Serializable, Cloneable {

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
     * 联系人姓名
     */
    @Column(name = "name", length = 32)
    private String name;

    /**
     * 身份证号码
     */
    @Column(name = "identity_number", length = 32)
    private String identityNumber;

    /**
     * 联系人手机号
     */
    @Index(name = "mobile_index")
    @Column(name = "mobile", length = 32)
    private String mobile;

    /**
     * 联系人电话(非手机号码)
     */
    @Column(name = "phone", length = 32)
    private String phone;

    /**
     * 联系人类型
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_contacts_type", nullable = false, length = 2)
    private UserContactsType userContactsType;

    /**
     * 联系人关系
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_contacts_relation", nullable = false, length = 16)
    private UserContactsRelation userContactsRelation;

    /**
     * 公司名称
     */
    @Column(name = "company_name", length = 128)
    private String companyName;

    /**
     * 公司地址 - xx省（自治区）xx市xx区（县）xx 街道
     */
    @Column(name = "company_address", length = 256)
    private String companyAddress;

    /**
     * 公司地址 - 邮编
     */
    @Column(name = "company_address_postalcode", length = 16)
    private String companyAddressPostalcode;

    /**
     * qq
     */
    @Column(name = "qq", length = 64)
    private String qq;

    /**
     * 微信
     */
    @Column(name = "weixin", length = 64)
    private String weixin;

    /**
     * 微博
     */
    @Column(name = "weibo", length = 64)
    private String weibo;

    /**
     * 邮箱
     */
    @Column(name = "email", length = 64)
    private String email;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false)
    private Date createTime;

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

    /**
     * 联系人信息id
     */
    @Column(name = "orderdetail_userinfo_records_id", nullable = true)
    private Long orderDetailUserInfoRecordsId;
    
    public Long getOrderDetailUserInfoRecordsId() {
        return orderDetailUserInfoRecordsId;
    }

    public void setOrderDetailUserInfoRecordsId(Long orderDetailUserInfoRecordsId) {
        this.orderDetailUserInfoRecordsId = orderDetailUserInfoRecordsId;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserContactsType getUserContactsType() {
        return userContactsType;
    }

    public void setUserContactsType(UserContactsType userContactsType) {
        this.userContactsType = userContactsType;
    }

    public UserContactsRelation getUserContactsRelation() {
        return userContactsRelation;
    }

    public void setUserContactsRelation(UserContactsRelation userContactsRelation) {
        this.userContactsRelation = userContactsRelation;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getCompanyAddressPostalcode() {
        return companyAddressPostalcode;
    }

    public void setCompanyAddressPostalcode(String companyAddressPostalcode) {
        this.companyAddressPostalcode = companyAddressPostalcode;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    @Override
    public UserContactsInfoRecords clone() throws CloneNotSupportedException {
        return (UserContactsInfoRecords)super.clone();
    }
}
