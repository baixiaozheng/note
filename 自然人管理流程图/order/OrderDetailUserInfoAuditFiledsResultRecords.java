/*
 * *
 *  * Copyright(c) 2010-2016 by GmsdTech Inc.
 *  * All Rights Reserved
 *
 */

package com.gmsdtech.datacenter.model.records.order;

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

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

import com.gmsdtech.datacenter.constant.order.OrderDetailUserInfoFieldTriggerResultType;
import com.gmsdtech.datacenter.constant.user.UserPropertyType;

/**
 * 订单详情用户信息审核字段结果表 Created by routine on 15/3/15.
 */
@Entity
@Table(name = "orderdetail_userinfo_audit_field_result_records")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class OrderDetailUserInfoAuditFiledsResultRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID自增
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 请求内容
     */
    @Column(name = "content", nullable = false, length = 255)
    private String content;

    /**
     * 类型内容
     */
    @Column(name = "content_type", nullable = false, length = 64)
    private String contentType;

    /**
     * 用户编号
     */
    @Index(name = "user_serial_index")
    @Column(name = "user_serial", nullable = false, length = 32)
    private String userSerial;

    /**
     * 结果字段类型
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "orderdetail_userinfo_field_trigger_result_type", nullable = false, length = 32)
    private OrderDetailUserInfoFieldTriggerResultType orderDetailUserInfoFieldTriggerResultType;

    /**
     * 结果
     */
    @Column(name = "result", nullable = false, length = 32)
    private String result;

    /**
     * 所属审核纪录id
     */
    @Index(name = "user_serial_index")
    @Column(name = "orderdetail_userinfo_audit_result_records_id", nullable = false, length = 32)
    private Long orderDetailUserInfoAuditResultRecordsId;

    /**
     * 用户分类表纪录id
     */
    @Index(name = "orderdetail_userinfo_records_id_index")
    @Column(name = "orderdetail_userinfo_records_id", nullable = false)
    private Long orderDetailUserInfoRecordsId;

    /**
     * 用户属性类型
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_property_type", nullable = false, length = 16)
    private UserPropertyType userPropertyType;

    /**
     * 审核时间
     */
    @Column(name = "audit_time", nullable = false)
    private Date auditTime;

    /**
     * 审核人
     */
    @Column(name = "audit_staff", length = 64)
    private String auditStaff;

    /**
     * 所属机构编号
     */
    @Column(name = "source_agency_serial", nullable = false, length = 32)
    private String sourceAgencySerial;

    /**
     * 规则编号
     */
    @Column(name = "serial_number", length = 32)
    private String ruleSerialNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserSerial() {
        return userSerial;
    }

    public void setUserSerial(String userSerial) {
        this.userSerial = userSerial;
    }

    public OrderDetailUserInfoFieldTriggerResultType getOrderDetailUserInfoFieldTriggerResultType() {
        return orderDetailUserInfoFieldTriggerResultType;
    }

    public void setOrderDetailUserInfoFieldTriggerResultType(
            OrderDetailUserInfoFieldTriggerResultType orderDetailUserInfoFieldTriggerResultType) {
        this.orderDetailUserInfoFieldTriggerResultType = orderDetailUserInfoFieldTriggerResultType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getOrderDetailUserInfoAuditResultRecordsId() {
        return orderDetailUserInfoAuditResultRecordsId;
    }

    public void setOrderDetailUserInfoAuditResultRecordsId(Long orderDetailUserInfoAuditResultRecordsId) {
        this.orderDetailUserInfoAuditResultRecordsId = orderDetailUserInfoAuditResultRecordsId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditStaff() {
        return auditStaff;
    }

    public void setAuditStaff(String auditStaff) {
        this.auditStaff = auditStaff;
    }

    public String getSourceAgencySerial() {
        return sourceAgencySerial;
    }

    public void setSourceAgencySerial(String sourceAgencySerial) {
        this.sourceAgencySerial = sourceAgencySerial;
    }

    public UserPropertyType getUserPropertyType() {
        return userPropertyType;
    }

    public void setUserPropertyType(UserPropertyType userPropertyType) {
        this.userPropertyType = userPropertyType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getRuleSerialNumber() {
        return ruleSerialNumber;
    }

    public void setRuleSerialNumber(String ruleSerialNumber) {
        this.ruleSerialNumber = ruleSerialNumber;
    }

    public Long getOrderDetailUserInfoRecordsId() {
        return orderDetailUserInfoRecordsId;
    }

    public void setOrderDetailUserInfoRecordsId(Long orderDetailUserInfoRecordsId) {
        this.orderDetailUserInfoRecordsId = orderDetailUserInfoRecordsId;
    }
    
}
