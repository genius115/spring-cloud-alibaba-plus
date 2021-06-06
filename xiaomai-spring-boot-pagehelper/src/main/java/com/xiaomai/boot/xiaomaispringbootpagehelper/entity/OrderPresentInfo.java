package com.xiaomai.boot.xiaomaispringbootpagehelper.entity;

/**
 * @author Madison
 * @date 2021/5/25
 */
import java.math.BigDecimal;
import java.util.Date;

public class OrderPresentInfo {
    private Long id;

    private String activityName;

    private Date beginTime;

    private Date endTime;

    private Integer activityStoresSelectType;

    private String activityStoresIds;

    private Integer memberLevelSelectType;

    private String memberLevelIds;

    private BigDecimal activityOrderConsume;

    private String paymentChannelIds;

    private Integer equityType;

    private Long couponId;

    private Long luckyTurningId;

    private Integer activityStatus;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getActivityStoresSelectType() {
        return activityStoresSelectType;
    }

    public void setActivityStoresSelectType(Integer activityStoresSelectType) {
        this.activityStoresSelectType = activityStoresSelectType;
    }

    public String getActivityStoresIds() {
        return activityStoresIds;
    }

    public void setActivityStoresIds(String activityStoresIds) {
        this.activityStoresIds = activityStoresIds == null ? null : activityStoresIds.trim();
    }

    public Integer getMemberLevelSelectType() {
        return memberLevelSelectType;
    }

    public void setMemberLevelSelectType(Integer memberLevelSelectType) {
        this.memberLevelSelectType = memberLevelSelectType;
    }

    public String getMemberLevelIds() {
        return memberLevelIds;
    }

    public void setMemberLevelIds(String memberLevelIds) {
        this.memberLevelIds = memberLevelIds == null ? null : memberLevelIds.trim();
    }

    public BigDecimal getActivityOrderConsume() {
        return activityOrderConsume;
    }

    public void setActivityOrderConsume(BigDecimal activityOrderConsume) {
        this.activityOrderConsume = activityOrderConsume;
    }

    public String getPaymentChannelIds() {
        return paymentChannelIds;
    }

    public void setPaymentChannelIds(String paymentChannelIds) {
        this.paymentChannelIds = paymentChannelIds == null ? null : paymentChannelIds.trim();
    }

    public Integer getEquityType() {
        return equityType;
    }

    public void setEquityType(Integer equityType) {
        this.equityType = equityType;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getLuckyTurningId() {
        return luckyTurningId;
    }

    public void setLuckyTurningId(Long luckyTurningId) {
        this.luckyTurningId = luckyTurningId;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
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
}