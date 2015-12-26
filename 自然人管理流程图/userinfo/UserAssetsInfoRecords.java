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

import com.gmsdtech.datacenter.constant.user.CreditCardOverdraftRateType;
import com.gmsdtech.datacenter.constant.user.DueType;

/**
 * 用户资产信息 Created by routine on 15/2/3.
 */
@Entity
@Table(name = "user_assetsinfo_records")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class UserAssetsInfoRecords implements Serializable {

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
     * 年收入
     */
    @Column(name = "annual_income", columnDefinition = "decimal(16,2) DEFAULT 0", nullable = false)
    private Double annualIncome;

    /**
     * 单张信用卡最大额度
     */
    @Column(name = "creditcard_maxamount", columnDefinition = "decimal(16,2) DEFAULT 0", nullable = false)
    private Double creditCardMaxAmount;

    /**
     * 信用卡张数
     */
    @Column(name = "credit_card_count", columnDefinition = "int DEFAULT 0", nullable = false)
    private Integer creditCardCount;

    /**
     * 总信用额度（负债）
     */
    @Column(name = "total_credit_amount", columnDefinition = "decimal(16,2) DEFAULT 0", nullable = false)
    private Double totalCreditAmount;

    /**
     * 信用卡逾期情况
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "credit_card_due_type", nullable = false)
    private DueType creditCardDueType;

    /**
     * 信用卡最长逾期月份
     */
    @Column(name = "credit_card_max_due_month", columnDefinition = "int DEFAULT 0", nullable = false)
    private Integer creditCardMaxDueMonth;

    /**
     * 贷款逾期情况
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "loans_due_type", columnDefinition = "int DEFAULT 0", nullable = false)
    private DueType loansDueType;

    /**
     * 贷款最大逾期期数
     */
    @Column(name = "loans_max_due_count", columnDefinition = "int DEFAULT 0", nullable = false)
    private Integer loansMaxDueCount;

    /**
     * 信用卡透支额度类型
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "creditcard_overdraft_ratetype", nullable = false)
    private CreditCardOverdraftRateType creditCardOverdraftRateType;

    /**
     * 信用卡透支额度比例, 单位%
     */
    @Column(name = "creditcard_overdraft_rate", columnDefinition = "decimal(6, 2) DEFAULT 0", nullable = false)
    private Double creditCardOverdraftRate;

    /**
     * 人行检索次数
     */
    @Column(name = "pbc_search_count_type", columnDefinition = "int DEFAULT 0", nullable = false)
    private Integer pbcSearchCountType;

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

    public Double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public Double getCreditCardMaxAmount() {
        return creditCardMaxAmount;
    }

    public void setCreditCardMaxAmount(Double creditCardMaxAmount) {
        this.creditCardMaxAmount = creditCardMaxAmount;
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

    public Integer getCreditCardCount() {
        return creditCardCount;
    }

    public void setCreditCardCount(Integer creditCardCount) {
        this.creditCardCount = creditCardCount;
    }

    public Double getTotalCreditAmount() {
        return totalCreditAmount;
    }

    public void setTotalCreditAmount(Double totalCreditAmount) {
        this.totalCreditAmount = totalCreditAmount;
    }

    public DueType getCreditCardDueType() {
        return creditCardDueType;
    }

    public void setCreditCardDueType(DueType creditCardDueType) {
        this.creditCardDueType = creditCardDueType;
    }

    public Integer getCreditCardMaxDueMonth() {
        return creditCardMaxDueMonth;
    }

    public void setCreditCardMaxDueMonth(Integer creditCardMaxDueMonth) {
        this.creditCardMaxDueMonth = creditCardMaxDueMonth;
    }

    public String getSourceAgencySerial() {
        return sourceAgencySerial;
    }

    public void setSourceAgencySerial(String sourceAgencySerial) {
        this.sourceAgencySerial = sourceAgencySerial;
    }

    public CreditCardOverdraftRateType getCreditCardOverdraftRateType() {
        return creditCardOverdraftRateType;
    }

    public void setCreditCardOverdraftRateType(CreditCardOverdraftRateType creditCardOverdraftRateType) {
        this.creditCardOverdraftRateType = creditCardOverdraftRateType;
    }

    public void setCreditCardOverdraftRate(Double creditCardOverdraftRate) {
        this.creditCardOverdraftRate = creditCardOverdraftRate;
    }

    public Double getCreditCardOverdraftRate() {
        return creditCardOverdraftRate;
    }

    public DueType getLoansDueType() {
        return loansDueType;
    }

    public void setLoansDueType(DueType loansDueType) {
        this.loansDueType = loansDueType;
    }

    public Integer getLoansMaxDueCount() {
        return loansMaxDueCount;
    }

    public void setLoansMaxDueCount(Integer loansMaxDueCount) {
        this.loansMaxDueCount = loansMaxDueCount;
    }

    public Integer getPbcSearchCountType() {
        return pbcSearchCountType;
    }

    public void setPbcSearchCountType(Integer pbcSearchCountType) {
        this.pbcSearchCountType = pbcSearchCountType;
    }

}
