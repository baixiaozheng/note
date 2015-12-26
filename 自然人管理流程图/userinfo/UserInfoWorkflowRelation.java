package com.gmsdtech.datacenter.model.records.userinfo;

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
 * 单独审核订单时, 订单审核工作流记录
 * @author liwenliang
 * @Date 2015年4月13日
 */
@Entity
@Table(name = "user_info_workflow_ralation")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class UserInfoWorkflowRelation implements Serializable {

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
    @Index(name = "userinfo_id_index")
    @Column(name = "userinfo_id", nullable = false)
    private Long userInfoId;

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

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getAppWorkflowSerial() {
        return appWorkflowSerial;
    }

    public void setAppWorkflowSerial(String appWorkflowSerial) {
        this.appWorkflowSerial = appWorkflowSerial;
    }
}
