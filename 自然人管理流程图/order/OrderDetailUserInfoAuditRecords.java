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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

/**
 * 订单详情审核纪录表 Created by routine on 15/3/15.
 */
@Entity
@Table(name = "orderdetail_userinfo_audit_records")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class OrderDetailUserInfoAuditRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID自增
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
     * 订单编号
     */
    @Column(name = "orderinfo_serial", nullable = false, length = 32)
    private String orderInfoSerial;

    /**
     * 所属订单纪录id
     */
    @Index(name = "orderinfo_records_id_index")
    @Column(name = "orderinfo_records_id", nullable = false)
    private Long orderInfoRecordsId;

    /**
     * 用户分类表纪录id
     */
    @Index(name = "orderdetail_userinfo_records_id_index")
    @Column(name = "orderdetail_userinfo_records_id", nullable = false)
    private Long orderDetailUserInfoRecordsId;

    /**
     * 审核时间
     */
    @Column(name = "audit_time", nullable = false)
    private Date auditTime;

    /**
     * 审核人
     */
    @Column(name = "audit_staff")
    private String auditStaff;

    /**
     * 所属机构编号
     */
    @Index(name = "source_agency_serial_index")
    @Column(name = "source_agency_serial", nullable = false, length = 32)
    private String sourceAgencySerial;

    /**
     * 更新时间
     */
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderInfoSerial() {
        return orderInfoSerial;
    }

    public void setOrderInfoSerial(String orderInfoSerial) {
        this.orderInfoSerial = orderInfoSerial;
    }

    public Long getOrderInfoRecordsId() {
        return orderInfoRecordsId;
    }

    public void setOrderInfoRecordsId(Long orderInfoRecordsId) {
        this.orderInfoRecordsId = orderInfoRecordsId;
    }

    public Long getOrderDetailUserInfoRecordsId() {
        return orderDetailUserInfoRecordsId;
    }

    public void setOrderDetailUserInfoRecordsId(Long orderDetailUserInfoRecordsId) {
        this.orderDetailUserInfoRecordsId = orderDetailUserInfoRecordsId;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserSerial() {
        return userSerial;
    }

    public void setUserSerial(String userSerial) {
        this.userSerial = userSerial;
    }
}
