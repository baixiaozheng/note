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
 * Created by weniaobing on 15-3-4.
 * <p/>
 * 订单记录与工作流关系表
 */
@Entity
@Table(name = "order_info_workflow_ralation")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class OrderInfoWorkflowRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主健ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 订单纪录
     */
    @Index(name = "orderinfo_id_index")
    @Column(name = "orderinfo_id", nullable = false)
    private Long orderInfoId;

    /**
     * 工作流编号
     */
    @Column(name = "app_workflow_serial", nullable = false)
    private String appWorkflowSerial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(Long orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public String getAppWorkflowSerial() {
        return appWorkflowSerial;
    }

    public void setAppWorkflowSerial(String appWorkflowSerial) {
        this.appWorkflowSerial = appWorkflowSerial;
    }
}
