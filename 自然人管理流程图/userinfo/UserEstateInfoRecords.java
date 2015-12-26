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

import com.gmsdtech.datacenter.constant.user.UserEstateInfoProprietary;
import com.gmsdtech.datacenter.constant.user.UserEstateInfoType;
import com.gmsdtech.datacenter.constant.user.UserEstatePropScaleType;

/**
 * 用户房产信息 Created by routine on 15/2/3.
 */
@Entity
@Table(name = "user_estateinfo_records")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class UserEstateInfoRecords implements Serializable {

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
     * 用户房产类型
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "usesr_estateinfo_type", nullable = false, length = 16)
    private UserEstateInfoType userEstateInfoType;

    /**
     * 用户房产状态
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "usesr_estateinfo_proprietary", nullable = false, length = 16)
    private UserEstateInfoProprietary userEstateInfoProprietary;

    /**
     * 建筑面积 /M²
     */
    @Column(name = "estate_area", columnDefinition = "decimal(6,2) DEFAULT 0", nullable = false)
    private Double estateArea;

    /**
     * 购买单价 元//M²
     */
    @Column(name = "unit_price", columnDefinition = "decimal(16,2) DEFAULT 0", nullable = false)
    private Double unitPrice;

    /**
     * 房产所在地 - xx省（自治区）xx市xx区（县）xx 街道
     */
    @Column(name = "estate_address", nullable = false, length = 128)
    private String estateAddress;

    /**
     * 房产所在地 - 邮编
     */
    @Column(name = "estate_address_postalcode", length = 16)
    private String estateAddressPostalcode;

    /**
     * 产权比例类型
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_estate_propscale_type", nullable = false, length = 16)
    private UserEstatePropScaleType userEstatePropScaleType;

    /**
     * 贷款年限 (限制1-30)
     */
    @Column(name = "loans_year", nullable = false, length = 2)
    private Integer loansYear;

    /**
     * 月还款金额
     */
    @Column(name = "monthly_motgage", columnDefinition = "decimal(16,2) DEFAULT 0", nullable = false)
    private Double monthlyMotgage;

    /**
     * 贷款余额
     */
    @Column(name = "motgage_balance", columnDefinition = "decimal(16,2) DEFAULT 0", nullable = false)
    private Double motgageBalance;

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

    public UserEstateInfoType getUserEstateInfoType() {
        return userEstateInfoType;
    }

    public void setUserEstateInfoType(UserEstateInfoType userEstateInfoType) {
        this.userEstateInfoType = userEstateInfoType;
    }

    public UserEstateInfoProprietary getUserEstateInfoProprietary() {
        return userEstateInfoProprietary;
    }

    public void setUserEstateInfoProprietary(UserEstateInfoProprietary userEstateInfoProprietary) {
        this.userEstateInfoProprietary = userEstateInfoProprietary;
    }

    public Double getEstateArea() {
        return estateArea;
    }

    public void setEstateArea(Double estateArea) {
        this.estateArea = estateArea;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getEstateAddress() {
        return estateAddress;
    }

    public void setEstateAddress(String estateAddress) {
        this.estateAddress = estateAddress;
    }

    public UserEstatePropScaleType getUserEstatePropScaleType() {
        return userEstatePropScaleType;
    }

    public void setUserEstatePropScaleType(UserEstatePropScaleType userEstatePropScaleType) {
        this.userEstatePropScaleType = userEstatePropScaleType;
    }

    public Integer getLoansYear() {
        return loansYear;
    }

    public void setLoansYear(Integer loansYear) {
        this.loansYear = loansYear;
    }

    public Double getMonthlyMotgage() {
        return monthlyMotgage;
    }

    public void setMonthlyMotgage(Double monthlyMotgage) {
        this.monthlyMotgage = monthlyMotgage;
    }

    public Double getMotgageBalance() {
        return motgageBalance;
    }

    public void setMotgageBalance(Double motgageBalance) {
        this.motgageBalance = motgageBalance;
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

    public String getEstateAddressPostalcode() {
        return estateAddressPostalcode;
    }

    public void setEstateAddressPostalcode(String estateAddressPostalcode) {
        this.estateAddressPostalcode = estateAddressPostalcode;
    }

}
