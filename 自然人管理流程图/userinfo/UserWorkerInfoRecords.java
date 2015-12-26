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

import com.gmsdtech.datacenter.constant.user.CompanyNatureType;
import com.gmsdtech.datacenter.constant.user.EmployeeCountType;
import com.gmsdtech.datacenter.constant.user.EnterpriseSiteType;
import com.gmsdtech.datacenter.constant.user.EnterpriseType;
import com.gmsdtech.datacenter.constant.user.IndustryType;
import com.gmsdtech.datacenter.constant.user.PositionType;
import com.gmsdtech.datacenter.constant.user.PostType;
import com.gmsdtech.datacenter.constant.user.WageType;

/**
 * 工作信息
 * Created by routine on 15/2/3.
 */
@Entity
@Table(name = "user_workinfo_records")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class UserWorkerInfoRecords implements Serializable, Cloneable {

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
     * 公司名称
     */
    @Column(name = "company_name", length = 128)
    private String companyName;

    /**
     * 公司电话
     */
    @Column(name = "company_tel", length = 32)
    private String companyTel;


    /**
     * 公司地址 - xx省（自治区）xx市xx区（县）xx 街道
     */
    @Column(name = "company_address", length = 256)
    private String companyAddress;

    /**
     * 公司邮编
     */
    @Column(name = "company_address_postalcode", length = 16)
    private String companyAddressPostalcode;

    /**
     * 所属部门
     */
    @Column(name = "department", length = 32)
    private String department;

    /**
     * 公司性质
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "company_nature_type", nullable = false, length = 16)
    private CompanyNatureType companyNatureType;

    /**
     * 月收入
     */
    @Column(name = "monthly_income", columnDefinition = "decimal(16,2) DEFAULT 0", nullable = false)
    private Double montlyIncome;

    /**
     * 发薪日
     */
    @Column(name = "wageday", length = 2)
    private Integer wageDay;

    /**
     * 工资下发类型
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "wage_type", nullable = false)
    private WageType wageType;

    /**
     * 行业
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "industry_type", nullable = false)
    private IndustryType industryType;

    /**
     * 工作年限
     */
    @Column(name = "work_years", nullable = false, length = 2)
    private Integer workYears;

    /**
     * 职务
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "position_type", nullable = false)
    private PositionType positionType;

    /**
     * 岗位类型
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "post_type", nullable = false)
    private PostType postType;

    /**
     * 员工人数
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "employee_count_type", nullable = false)
    private EmployeeCountType employeeCountType;

    /**
     * 当前工作年限
     */
    @Column(name = "user_service_year_scope", nullable = false)
    private Integer userServiceYearScope;

    /**
     * 企业成立年限
     */
    @Column(name = "enterprise_setup_life_scope", nullable = false, length = 2)
    private Integer enterpriseSetupLifeScope;

    /**
     * 企业类型
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "enterprise_type", nullable = false)
    private EnterpriseType enterpriseType;

    /**
     * 注册时间(企业)
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "individual_regist_time")
    private Date individualRegistTime;

    /**
     * 经营场所类型
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "enterprise_site_type", nullable = false)
    private EnterpriseSiteType enterpriseSiteType;

    /**
     * 持股比例
     */
    @Column(name = "position_scale", columnDefinition = "decimal(8,4) DEFAULT 0", nullable = false)
    private Double positionScale;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public CompanyNatureType getCompanyNatureType() {
        return companyNatureType;
    }

    public void setCompanyNatureType(CompanyNatureType companyNatureType) {
        this.companyNatureType = companyNatureType;
    }

    public Double getMontlyIncome() {
        return montlyIncome;
    }

    public void setMontlyIncome(Double montlyIncome) {
        this.montlyIncome = montlyIncome;
    }

    public Integer getWageDay() {
        return wageDay;
    }

    public void setWageDay(Integer wageDay) {
        this.wageDay = wageDay;
    }

    public WageType getWageType() {
        return wageType;
    }

    public void setWageType(WageType wageType) {
        this.wageType = wageType;
    }

    public IndustryType getIndustryType() {
        return industryType;
    }

    public void setIndustryType(IndustryType industryType) {
        this.industryType = industryType;
    }

    public Integer getWorkYears() {
        return workYears;
    }

    public void setWorkYears(Integer workYears) {
        this.workYears = workYears;
    }

    public PositionType getPositionType() {
        return positionType;
    }

    public void setPositionType(PositionType positionType) {
        this.positionType = positionType;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public EmployeeCountType getEmployeeCountType() {
        return employeeCountType;
    }

    public void setEmployeeCountType(EmployeeCountType employeeCountType) {
        this.employeeCountType = employeeCountType;
    }

    public Integer getUserServiceYearScope() {
        return userServiceYearScope;
    }

    public void setUserServiceYearScope(Integer userServiceYearScope) {
        this.userServiceYearScope = userServiceYearScope;
    }

    public Integer getEnterpriseSetupLifeScope() {
        return enterpriseSetupLifeScope;
    }

    public void setEnterpriseSetupLifeScope(Integer enterpriseSetupLifeScope) {
        this.enterpriseSetupLifeScope = enterpriseSetupLifeScope;
    }

    public EnterpriseType getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(EnterpriseType enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public Date getIndividualRegistTime() {
        return individualRegistTime;
    }

    public void setIndividualRegistTime(Date individualRegistTime) {
        this.individualRegistTime = individualRegistTime;
    }

    public EnterpriseSiteType getEnterpriseSiteType() {
        return enterpriseSiteType;
    }

    public void setEnterpriseSiteType(EnterpriseSiteType enterpriseSiteType) {
        this.enterpriseSiteType = enterpriseSiteType;
    }

    public Double getPositionScale() {
        return positionScale;
    }

    public void setPositionScale(Double positionScale) {
        this.positionScale = positionScale;
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

    @Override
    public UserWorkerInfoRecords clone() throws CloneNotSupportedException {
        return (UserWorkerInfoRecords) super.clone();
    }
}
