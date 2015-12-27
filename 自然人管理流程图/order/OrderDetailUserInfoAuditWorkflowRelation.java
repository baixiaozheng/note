/*
 * *
 *  * Copyright(c) 2010-2016 by GmsdTech Inc.
 *  * All Rights Reserved
 *
 */

package com.gmsdtech.datacenter.model.records.order;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

/**
 * 订单详情审核纪录与工作流关系表 Created by routine on 15/3/15.
 */
@Entity
@Table(name = "orderdetail_userinfo_audit_workflow_relation")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class OrderDetailUserInfoAuditWorkflowRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主健ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 订单审核纪录
     */
    @Index(name = "orderdetail_userinfo_audit_records_id_index")
    @Column(name = "orderdetail_userinfo_audit_records_id", nullable = false)
    private Long orderDetailUserInfoAuditRecordsId;

    /**
     * 工作流编号
     */
    @Index(name = "app_workflow_serial_index")
    @Column(name = "app_workflow_serial", nullable = false)
    private String appWorkflowSerial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderDetailUserInfoAuditRecordsId() {
        return orderDetailUserInfoAuditRecordsId;
    }

    public void setOrderDetailUserInfoAuditRecordsId(Long orderDetailUserInfoAuditRecordsId) {
        this.orderDetailUserInfoAuditRecordsId = orderDetailUserInfoAuditRecordsId;
    }

    public String getAppWorkflowSerial() {
        return appWorkflowSerial;
    }

    public void setAppWorkflowSerial(String appWorkflowSerial) {
        this.appWorkflowSerial = appWorkflowSerial;
    }
}
