package com.hysro.ajkeeping.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 结账情况概述对象 aj_base_check_in_payment_status
 *
 * @author hysro
 * @date 2024-08-18
 */
public class AjBaseCheckInPaymentStatus extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 考勤总表id */
    @Excel(name = "考勤总表id")
    private Long baseCheckInId;

    /** 考勤总表名称 */
    @Excel(name = "考勤总表名称")
    private String baseCheckInName;

    /** 账单状态(0=未生成, 1=部分账单, 2=已生成) */
    @Excel(name = "账单状态(0=未生成, 1=部分账单, 2=已生成)")
    private Integer billStatus;

    /** 支付状态(0=未支付, 1=部分支付, 2=已付清) */
    @Excel(name = "支付状态(0=未支付, 1=部分支付, 2=已付清)")
    private Integer paymentStatus;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setBaseCheckInId(Long baseCheckInId)
    {
        this.baseCheckInId = baseCheckInId;
    }

    public Long getBaseCheckInId()
    {
        return baseCheckInId;
    }
    public void setBaseCheckInName(String baseCheckInName)
    {
        this.baseCheckInName = baseCheckInName;
    }

    public String getBaseCheckInName()
    {
        return baseCheckInName;
    }
    public void setBillStatus(Integer billStatus)
    {
        this.billStatus = billStatus;
    }

    public Integer getBillStatus()
    {
        return billStatus;
    }
    public void setPaymentStatus(Integer paymentStatus)
    {
        this.paymentStatus = paymentStatus;
    }

    public Integer getPaymentStatus()
    {
        return paymentStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("baseCheckInId", getBaseCheckInId())
            .append("baseCheckInName", getBaseCheckInName())
            .append("billStatus", getBillStatus())
            .append("paymentStatus", getPaymentStatus())
            .toString();
    }
}
