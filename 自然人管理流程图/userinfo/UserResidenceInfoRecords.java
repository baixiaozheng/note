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
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

import com.gmsdtech.datacenter.constant.user.UserResidenceInfoType;
import com.gmsdtech.datacenter.constant.user.UserResidenceTimeType;

/**
 * 住宅信息记录
 * Created by routine on 15/2/2.
 */
@Entity
@Table(name = "user_residenceinfo_records")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class UserResidenceInfoRecords implements Serializable, Cloneable {

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
     * 住宅电话
     */
    @Column(name = "residence_tel", nullable = false, length = 16)
    private String residenceTel;

    /**
     * 本市年份类型
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_residence_time_type", nullable = false, length = 16)
    private UserResidenceTimeType userResidenceTimeType;

    /**
     * 户口所在地 - xx省（自治区）xx市xx区（县）
     */
    @Column(name = "permanent_address", nullable = false, length = 128)
    private String permanentAddress;  
    
    /**
     * 户口所在地 - 邮编
     */
    @Column(name = "permanent_address_postalcode", length = 16)
    private String permanentAddressPostalcode;

    /**
     * 住宅所在地 - xx省（自治区）xx市xx区（县）
     */
    @Column(name = "residence_address", nullable = false, length = 128)
    private String residenceAddress;
    
    /**
     * 住宅所在地 - 邮编
     */
    @Column(name = "residence_address_postalcode", length = 16)
    private String residenceAddressPostalcode;

    /**
	 *
     * 原户籍地址 xxx（省）xxx（市）
     */
    @Column(name = "original_booklet_address", nullable = false, length = 32)
    private String originalBookletAddress;

    /**
     * 现户籍地址 xxx（省）xxx（市）
     */
    @Column(name = "current_booklet_address", nullable = false, length = 32)
    private String currentBookletAddress;

    /**
     * 住宅类型
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_residenceinfo_type", nullable = false, length = 16)
    private UserResidenceInfoType userResidenceInfoType;

    /**
     * 按揭月还款金额
     */
    @Column(name = "monthly_motgage", columnDefinition = "decimal(16,2) DEFAULT 0", nullable = false)
    private Double monthlyMotgage;

    /**
     * 月租金
     */
    @Column(name = "monthly_rental", columnDefinition = "decimal(16,2) DEFAULT 0")
    private Double monthlyRental;

    /**
     * 共同居住信息
     */
    @Lob
    @Column(name = "user_resid_members_types")
    private String userResidMembersTypes;

    /**
     * 家庭月支出
     */
    @Column(name = "monthly_spends", columnDefinition = "decimal(16,2) DEFAULT 0", nullable = false)
    private Double monthlySpends;

    /**
     * 户籍是否在申请所在地
     */
    @Column(name = "is_booklet_match_application_location", nullable = false)
    private Boolean isBookletMatchApplicationLocation;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getResidenceTel() {
        return residenceTel;
    }

    public void setResidenceTel(String residenceTel) {
        this.residenceTel = residenceTel;
    }

    public UserResidenceTimeType getUserResidenceTimeType() {
        return userResidenceTimeType;
    }

    public void setUserResidenceTimeType(UserResidenceTimeType userResidenceTimeType) {
        this.userResidenceTimeType = userResidenceTimeType;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public String getOriginalBookletAddress() {
        return originalBookletAddress;
    }

    public void setOriginalBookletAddress(String originalBookletAddress) {
        this.originalBookletAddress = originalBookletAddress;
    }

    public String getCurrentBookletAddress() {
        return currentBookletAddress;
    }

    public void setCurrentBookletAddress(String currentBookletAddress) {
        this.currentBookletAddress = currentBookletAddress;
    }

    public UserResidenceInfoType getUserResidenceInfoType() {
        return userResidenceInfoType;
    }

    public void setUserResidenceInfoType(UserResidenceInfoType userResidenceInfoType) {
        this.userResidenceInfoType = userResidenceInfoType;
    }

    public Double getMonthlyMotgage() {
        return monthlyMotgage;
    }

    public void setMonthlyMotgage(Double monthlyMotgage) {
        this.monthlyMotgage = monthlyMotgage;
    }

    public Double getMonthlyRental() {
        return monthlyRental;
    }

    public void setMonthlyRental(Double monthlyRental) {
        this.monthlyRental = monthlyRental;
    }

    public String getUserResidMembersTypes() {
        return userResidMembersTypes;
    }

    public void setUserResidMembersTypes(String userResidMembersTypes) {
        this.userResidMembersTypes = userResidMembersTypes;
    }

    public Double getMonthlySpends() {
        return monthlySpends;
    }

    public void setMonthlySpends(Double monthlySpends) {
        this.monthlySpends = monthlySpends;
    }

    public Boolean getIsBookletMatchApplicationLocation() {
        return isBookletMatchApplicationLocation;
    }

    public void setIsBookletMatchApplicationLocation(Boolean isBookletMatchApplicationLocation) {
        this.isBookletMatchApplicationLocation = isBookletMatchApplicationLocation;
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

    public String getPermanentAddressPostalcode() {
        return permanentAddressPostalcode;
    }

    public void setPermanentAddressPostalcode(String permanentAddressPostalcode) {
        this.permanentAddressPostalcode = permanentAddressPostalcode;
    }

    public String getResidenceAddressPostalcode() {
        return residenceAddressPostalcode;
    }

    public void setResidenceAddressPostalcode(String residenceAddressPostalcode) {
        this.residenceAddressPostalcode = residenceAddressPostalcode;
    }

    @Override
    public UserResidenceInfoRecords clone() throws CloneNotSupportedException {
        return (UserResidenceInfoRecords)super.clone();
    }
}
