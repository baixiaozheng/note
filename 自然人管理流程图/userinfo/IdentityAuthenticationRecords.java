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

import com.gmsdtech.datacenter.constant.thirdpart.IdentityAuthenticationResultType;
import com.gmsdtech.datacenter.constant.thirdpart.IdentityAuthenticationSourceType;

/**
 * 身份证认证记录表 Created by routine on 15/2/2.
 */
@Entity
@Table(name = "identity_authentication_records")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class IdentityAuthenticationRecords implements Serializable {

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
    private Long userSerial;

    /**
     * 认证姓名
     */
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    /**
     * 身份证号
     */
    @Column(name = "identity_num", nullable = false, length = 32)
    private String identityNum;

    /**
     * 认证结果类型
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "identity_authentication_result_type", nullable = false, length = 16)
    private IdentityAuthenticationResultType identityAuthenticationResultType;

    /**
     * 提交认证机构编号
     */
    @Index(name = "apply_agency_serial_index")
    @Column(name = "apply_agency_serial", nullable = false, length = 32)
    private String applyAgencySerial;

    /**
     * 认证中心标识
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "identity_authentication_source_type", nullable = false, length = 16)
    private IdentityAuthenticationSourceType identityAuthenticationSourceType;

    /**
     * 认证时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "auth_time", nullable = false)
    private Date authTime;

    /**
     * 更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    /**
     * 返回备注
     */
    @Column(name = "comments", length = 128)
    private String comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserSerial() {
        return userSerial;
    }

    public void setUserSerial(Long userSerial) {
        this.userSerial = userSerial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public IdentityAuthenticationResultType getIdentityAuthenticationResultType() {
        return identityAuthenticationResultType;
    }

    public void setIdentityAuthenticationResultType(IdentityAuthenticationResultType identityAuthenticationResultType) {
        this.identityAuthenticationResultType = identityAuthenticationResultType;
    }

    public String getApplyAgencySerial() {
        return applyAgencySerial;
    }

    public void setApplyAgencySerial(String applyAgencySerial) {
        this.applyAgencySerial = applyAgencySerial;
    }

    public IdentityAuthenticationSourceType getIdentityAuthenticationSourceType() {
        return identityAuthenticationSourceType;
    }

    public void setIdentityAuthenticationSourceType(IdentityAuthenticationSourceType identityAuthenticationSourceType) {
        this.identityAuthenticationSourceType = identityAuthenticationSourceType;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
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
}