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

import com.gmsdtech.datacenter.constant.order.OrderInfoStatus;

/**
 * Created by weniaobing on 15-3-4.
 * <p/>
 * 订单表(批次信息纪录)
 */
@Entity
@Table(name = "order_info_record")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class OrderInfoRecord implements Serializable {

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
    @Index(name = "order_info_serial_index")
    @Column(name = "order_info_serial", nullable = false, length = 32)
    private String orderInfoSerial;

    /**
     * 自然人数量
     */
    @Column(name = "order_user_quantity", nullable = false)
    private Integer orderUserQuantity;

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
     * 更新时间
     */
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

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
     * 导入人员-纪录导入人员账号
     */
    @Column(name = "import_staff", nullable = false)
    private String importStaff;

    /**
     * 所属机构编号
     */
    @Index(name = "source_agency_serial_index")
    @Column(name = "source_agency_serial", nullable = false, length = 32)
    private String sourceAgencySerial;

    /**
     * 订单状态
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "order_info_status", length = 2, nullable = false)
    private OrderInfoStatus orderInfoStatus;

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

    public Integer getOrderUserQuantity() {
        return orderUserQuantity;
    }

    public void setOrderUserQuantity(Integer orderUserQuantity) {
        this.orderUserQuantity = orderUserQuantity;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public String getImportStaff() {
        return importStaff;
    }

    public void setImportStaff(String importStaff) {
        this.importStaff = importStaff;
    }

    public String getSourceAgencySerial() {
        return sourceAgencySerial;
    }

    public void setSourceAgencySerial(String sourceAgencySerial) {
        this.sourceAgencySerial = sourceAgencySerial;
    }

    public OrderInfoStatus getOrderInfoStatus() {
        return orderInfoStatus;
    }

    public void setOrderInfoStatus(OrderInfoStatus orderInfoStatus) {
        this.orderInfoStatus = orderInfoStatus;
    }
}
