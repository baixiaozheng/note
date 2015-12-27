package com.gmsdtech.datacenter.model.records.order;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 用户导入联系人信息 Created by wenxiaobing on 15-3-8.
 */
@Entity
@Table(name = "orderdetail_user_contactinfo_records")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class OrderDetailUserContactInfoRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主健ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 所属订单详情用户信息纪录id
     */
    @Column(name = "orderdetail_userinfo_records_id", nullable = false)
    private Long orderDetailUserInfoRecordsId;

    /**
     * 用户联系人信息表id
     */
    @Column(name = "user_contacts_info_id", nullable = false)
    private Long userContactsInfoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderDetailUserInfoRecordsId() {
        return orderDetailUserInfoRecordsId;
    }

    public void setOrderDetailUserInfoRecordsId(Long orderDetailUserInfoRecordsId) {
        this.orderDetailUserInfoRecordsId = orderDetailUserInfoRecordsId;
    }

    public Long getUserContactsInfoId() {
        return userContactsInfoId;
    }

    public void setUserContactsInfoId(Long userContactsInfoId) {
        this.userContactsInfoId = userContactsInfoId;
    }
}
