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

import com.gmsdtech.datacenter.constant.rule.scorecard.ScoreCardDecisitionResult;

/**
 * 订单详情审核结果表 Created by routine on 15/3/15.
 */
@Entity
@Table(name = "orderdetail_userinfo_audit_result_records")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class OrderDetailUserInfoAuditResultRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID自增
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 订单编号
     */
    @Column(name = "orderInfoSerial", nullable = false, length = 32)
    private String orderInfoSerial;

    /**
     * 所属订单纪录id
     */
    @Column(name = "orderinfo_records_id", nullable = false)
    private Long orderInfoRecordsId;

    /**
     * 所属订单自然人关系表纪录id
     */
    @Index(name = "orderdetail_userinfo_records_id_index")
    @Column(name = "orderdetail_userinfo_records_id", nullable = false)
    private Long orderDetailUserInfoRecordsId;

    /**
     * 所属订单审核纪录id
     */
    @Index(name = "orderdetail_userinfo_audit_records_id_index")
    @Column(name = "orderdetail_userinfo_audit_records_id", nullable = false)
    private Long orderDetailUserInfoAuditRecordsId;

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
    @Column(name = "source_agency_serial", nullable = false, length = 32)
    private String sourceAgencySerial;

    /**
     * 更新时间
     */
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    /**
     * 黑名单命中信息数量
     */
    @Column(name = "blacklist_hint_quantity", nullable = false)
    private Integer blackListHintQuantity;

    /**
     * 反欺诈命中次数
     */
    @Column(name = "antifraud_hint_quantity", nullable = false)
    private Integer antiFraudHitQuantity;

    /**
     * 所得评分
     */
    @Column(name = "score", columnDefinition = "decimal(16,2) DEFAULT 0", nullable = false)
    private Double score;

    /**
     * 评估建议
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "scorecard_decision_result")
    private ScoreCardDecisitionResult scoreCardDecisitionResult;

    /**
     * 最大决策额度
     */
    @Column(name = "max_decision_position", columnDefinition = "decimal(16,2) DEFAULT 0")
    private Double maxDecisionPosition;

    /**
     * 最小决策额度
     */
    @Column(name = "min_decision_position", columnDefinition = "decimal(16,2) DEFAULT 0")
    private Double minDecisionPosition;

    /**
     * 最大决策费率
     */
    @Column(name = "max_decision_rate", columnDefinition = "decimal(6,2) DEFAULT 0")
    private Double maxDecisionRate;

    /**
     * 最小决策费率
     */
    @Column(name = "min_decision_rate", columnDefinition = "decimal(6,2) DEFAULT 0")
    private Double minDecisionRate;

    /**
     * 最大决策期限
     */
    @Column(name = "max_decision_months", columnDefinition = "int DEFAULT 0")
    private Integer maxDecisionMonths;

    /**
     * 最小决策期限
     */
    @Column(name = "min_decision_months", columnDefinition = "int DEFAULT 0")
    private Integer minDecisionMonths;

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

    public Long getOrderDetailUserInfoAuditRecordsId() {
        return orderDetailUserInfoAuditRecordsId;
    }

    public void setOrderDetailUserInfoAuditRecordsId(Long orderDetailUserInfoAuditRecordsId) {
        this.orderDetailUserInfoAuditRecordsId = orderDetailUserInfoAuditRecordsId;
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

    public Integer getBlackListHintQuantity() {
        return blackListHintQuantity;
    }

    public void setBlackListHintQuantity(Integer blackListHintQuantity) {
        this.blackListHintQuantity = blackListHintQuantity;
    }

    public Integer getAntiFraudHitQuantity() {
        return antiFraudHitQuantity;
    }

    public void setAntiFraudHitQuantity(Integer antiFraudHitQuantity) {
        this.antiFraudHitQuantity = antiFraudHitQuantity;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public ScoreCardDecisitionResult getScoreCardDecisitionResult() {
        return scoreCardDecisitionResult;
    }

    public void setScoreCardDecisitionResult(ScoreCardDecisitionResult scoreCardDecisitionResult) {
        this.scoreCardDecisitionResult = scoreCardDecisitionResult;
    }

    public Double getMaxDecisionPosition() {
        return maxDecisionPosition;
    }

    public void setMaxDecisionPosition(Double maxDecisionPosition) {
        this.maxDecisionPosition = maxDecisionPosition;
    }

    public Double getMinDecisionPosition() {
        return minDecisionPosition;
    }

    public void setMinDecisionPosition(Double minDecisionPosition) {
        this.minDecisionPosition = minDecisionPosition;
    }

    public Double getMaxDecisionRate() {
        return maxDecisionRate;
    }

    public void setMaxDecisionRate(Double maxDecisionRate) {
        this.maxDecisionRate = maxDecisionRate;
    }

    public Double getMinDecisionRate() {
        return minDecisionRate;
    }

    public void setMinDecisionRate(Double minDecisionRate) {
        this.minDecisionRate = minDecisionRate;
    }

    public Integer getMaxDecisionMonths() {
        return maxDecisionMonths;
    }

    public void setMaxDecisionMonths(Integer maxDecisionMonths) {
        this.maxDecisionMonths = maxDecisionMonths;
    }

    public Integer getMinDecisionMonths() {
        return minDecisionMonths;
    }

    public void setMinDecisionMonths(Integer minDecisionMonths) {
        this.minDecisionMonths = minDecisionMonths;
    }
}
