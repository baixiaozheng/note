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
 * Created by weniaobing on 15-3-4.
 * <p/>
 * 用户多表分类信息与批次信息关系表
 */
@Entity
@Table(name = "orderdetail_userinfo_records")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class OrderDetailUserInfoRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主健ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 订单编号
     */
    @Index(name = "orderinfo_serial_index")
    @Column(name = "orderinfo_serial", nullable = false, length = 32)
    private String orderinfoSerial;

    /**
     * 所属订单纪录id
     */
    @Index(name = "orderinfo_records_id_index")
    @Column(name = "orderinfo_records_id", nullable = false)
    private Long orderInfoRecordsId;

    /**
     * 审核次数
     */
    @Column(name = "audit_quantity", nullable = false)
    private Integer auditQuantity;

    /**
     * 录入时间
     */
    @Index(name = "import_time_index")
    @Column(name = "import_time", nullable = false)
    private Date importTime;

    /**
     * 审核时间
     */
    @Column(name = "audit_time")
    private Date auditTime;

    /**
     * 审核人
     */
    @Column(name = "audit_staff")
    private Long auditStaff;

    /**
     * 导入人员-纪录倒入人员账号
     */
    @Column(name = "staff_name", nullable = false, length = 64)
    private String staffName;

    /**
     * 所属机构编号
     */
    @Index(name = "source_agency_serial_index")
    @Column(name = "source_agency_serial", nullable = false, length = 32)
    private String sourceAgencySerial;

    /**
     * 自然人编号
     */
    @Column(name = "user_serial", nullable = false)
    private String userSerial;

    /**
     * 更新时间
     */
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    /**
     * 用户资产信息
     */
    @Column(name = "user_assets_info_id", nullable = false)
    private Long userAssetsInfoId;

    /**
     * 用户学历信息
     */
    @Column(name = "user_education_info_id", nullable = false)
    private Long userEducationInfoId;

    /**
     * 用户房产信息
     */
    @Column(name = "user_estate_info_id", nullable = false)
    private Long userEstateInfoId;

    /**
     * 用户信息
     */
    @Column(name = "user_info_id", nullable = false)
    private Long userInfoId;

    /**
     * 用户婚姻信息
     */
    @Column(name = "user_marriage_info_id", nullable = false)
    private Long userMarriageInfoId;

    /**
     * 用户住宅信息
     */
    @Column(name = "user_residence_info_id", nullable = false)
    private Long userResidenceInfoId;

    /**
     * 用户工作信息
     */
    @Column(name = "user_worker_info_id", nullable = false)
    private Long userWorkerInfoId;

    /**
     * 还款信息
     */
    @Column(name = "user_repay_info_id", nullable = false)
    private Long userRepayInfoId;

    /**
     * 借款信息
     */
    @Column(name = "user_apply_borrow_info_id", nullable = false)
    private Long userApplyBorrowInfoId;

    /**
     * 销售录入债权信息
     */
    @Column(name = "saler_entryloans_descinfo_id", nullable = false)
    private Long salerEntryLoansDecscInfoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderinfoSerial() {
        return orderinfoSerial;
    }

    public void setOrderinfoSerial(String orderinfoSerial) {
        this.orderinfoSerial = orderinfoSerial;
    }

    public Long getOrderInfoRecordsId() {
        return orderInfoRecordsId;
    }

    public void setOrderInfoRecordsId(Long orderInfoRecordsId) {
        this.orderInfoRecordsId = orderInfoRecordsId;
    }

    public Integer getAuditQuantity() {
        return auditQuantity;
    }

    public void setAuditQuantity(Integer auditQuantity) {
        this.auditQuantity = auditQuantity;
    }

    public Date getImportTime() {
        return importTime;
    }

    public void setImportTime(Date importTime) {
        this.importTime = importTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Long getAuditStaff() {
        return auditStaff;
    }

    public void setAuditStaff(Long auditStaff) {
        this.auditStaff = auditStaff;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getSourceAgencySerial() {
        return sourceAgencySerial;
    }

    public void setSourceAgencySerial(String sourceAgencySerial) {
        this.sourceAgencySerial = sourceAgencySerial;
    }

    public String getUserSerial() {
        return userSerial;
    }

    public void setUserSerial(String userSerial) {
        this.userSerial = userSerial;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUserAssetsInfoId() {
        return userAssetsInfoId;
    }

    public void setUserAssetsInfoId(Long userAssetsInfoId) {
        this.userAssetsInfoId = userAssetsInfoId;
    }

    public Long getUserEducationInfoId() {
        return userEducationInfoId;
    }

    public void setUserEducationInfoId(Long userEducationInfoId) {
        this.userEducationInfoId = userEducationInfoId;
    }

    public Long getUserEstateInfoId() {
        return userEstateInfoId;
    }

    public void setUserEstateInfoId(Long userEstateInfoId) {
        this.userEstateInfoId = userEstateInfoId;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public Long getUserMarriageInfoId() {
        return userMarriageInfoId;
    }

    public void setUserMarriageInfoId(Long userMarriageInfoId) {
        this.userMarriageInfoId = userMarriageInfoId;
    }

    public Long getUserResidenceInfoId() {
        return userResidenceInfoId;
    }

    public void setUserResidenceInfoId(Long userResidenceInfoId) {
        this.userResidenceInfoId = userResidenceInfoId;
    }

    public Long getUserWorkerInfoId() {
        return userWorkerInfoId;
    }

    public void setUserWorkerInfoId(Long userWorkerInfoId) {
        this.userWorkerInfoId = userWorkerInfoId;
    }

    public Long getUserRepayInfoId() {
        return userRepayInfoId;
    }

    public void setUserRepayInfoId(Long userRepayInfoId) {
        this.userRepayInfoId = userRepayInfoId;
    }

    public Long getUserApplyBorrowInfoId() {
        return userApplyBorrowInfoId;
    }

    public void setUserApplyBorrowInfoId(Long userApplyBorrowInfoId) {
        this.userApplyBorrowInfoId = userApplyBorrowInfoId;
    }

    public Long getSalerEntryLoansDecscInfoId() {
        return salerEntryLoansDecscInfoId;
    }

    public void setSalerEntryLoansDecscInfoId(Long salerEntryLoansDecscInfoId) {
        this.salerEntryLoansDecscInfoId = salerEntryLoansDecscInfoId;
    }
}
