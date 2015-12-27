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

/**
 * Created by wenxiaobing on 15-3-5.
 */

@Entity
@Table(name = "agency_import_count")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class AgencyUserImportCount implements Serializable {

    private static final long serialVersionUID = 1L;

    // ToDo 字段打上相应备注, String 类型字段需要指定长度, Date类型需要注意格式备注
    // ToDo 此类是做什么用的？？？？
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "agency_serial", nullable = false)
    private String agencySerial;

    @Column(name = "operation_date", nullable = false)
    private Date oprationDate;

    @Column(name = "order_quantity", nullable = false)
    private Integer orderQuantity;

    @Column(name = "user_quantity", nullable = false)
    private Integer userQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgencySerial() {
        return agencySerial;
    }

    public void setAgencySerial(String agencySerial) {
        this.agencySerial = agencySerial;
    }

    public Date getOprationDate() {
        return oprationDate;
    }

    public void setOprationDate(Date oprationDate) {
        this.oprationDate = oprationDate;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Integer getUserQuantity() {
        return userQuantity;
    }

    public void setUserQuantity(Integer userQuantity) {
        this.userQuantity = userQuantity;
    }
}
