package com.hysro.ajkeeping.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生账单明细对象 aj_student_bill
 *
 * @author hysro
 * @date 2024-08-05
 */
public class AjStudentBill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 学生id */
    @Excel(name = "学生id")
    private Long studentId;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String studentName;

    /** 考勤统计表id */
    @Excel(name = "考勤统计表id")
    private Long checkInStatisticId;

    /** 考勤开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考勤开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkInBeginDate;

    /** 考勤结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考勤结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkInEndDate;

    /** 考勤次数 */
    @Excel(name = "考勤次数")
    private Integer checkInTimes;

    /** 请假次数 */
    @Excel(name = "请假次数")
    private Integer leaveTimes;

    /** 视同考勤次数 */
    @Excel(name = "视同考勤次数")
    private Integer countAsCheckInTimes;

    /** 每月收费 */
    @Excel(name = "每月收费")
    private BigDecimal perMonthFee;

    /** 每天收费 */
    @Excel(name = "每天收费")
    private BigDecimal perDayFee;

    /** 资料费 */
    @Excel(name = "资料费")
    private BigDecimal textbookFee;

    /** 伙食费 */
    @Excel(name = "伙食费")
    private BigDecimal foodFee;

    /** 账单费用 */
    @Excel(name = "账单费用")
    private BigDecimal billFee;

    /** 预收费 */
    @Excel(name = "预收费")
    private BigDecimal advanceFee;

    /** 优惠金额 */
    @Excel(name = "优惠金额")
    private BigDecimal coupon;

    /** 实际每月收费 */
    @Excel(name = "实际每月收费")
    private BigDecimal actualPerMonthFee;

    /** 实际每日收费 */
    @Excel(name = "实际每日收费")
    private BigDecimal actualPerDayFee;

    /** 实际资料费 */
    @Excel(name = "实际资料费")
    private BigDecimal actualTextbookFee;

    /** 实际伙食费 */
    @Excel(name = "实际伙食费")
    private BigDecimal actualFoodFee;

    /** 实际账单费用 */
    @Excel(name = "实际账单费用")
    private BigDecimal acutalBillFee;

    /** 缴费状态（0未缴费，1已缴费） */
    @Excel(name = "缴费状态", readConverterExp = "0=未缴费,1=已缴费")
    private Integer billStatus;

    /** 缴费方式(0微信, 1支付宝, 2现金, 3其他) */
    @Excel(name = "缴费方式", readConverterExp = "0=微信,1=支付宝,2=现金,3=其他")
    private Integer billMode;

    /** 缴费证明 */
    @Excel(name = "缴费证明")
    private String billEvidence;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setStudentId(Long studentId)
    {
        this.studentId = studentId;
    }

    public Long getStudentId()
    {
        return studentId;
    }
    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    public String getStudentName()
    {
        return studentName;
    }
    public void setCheckInStatisticId(Long checkInStatisticId)
    {
        this.checkInStatisticId = checkInStatisticId;
    }

    public Long getCheckInStatisticId()
    {
        return checkInStatisticId;
    }
    public void setCheckInBeginDate(Date checkInBeginDate)
    {
        this.checkInBeginDate = checkInBeginDate;
    }

    public Date getCheckInBeginDate()
    {
        return checkInBeginDate;
    }
    public void setCheckInEndDate(Date checkInEndDate)
    {
        this.checkInEndDate = checkInEndDate;
    }

    public Date getCheckInEndDate()
    {
        return checkInEndDate;
    }
    public void setCheckInTimes(Integer checkInTimes)
    {
        this.checkInTimes = checkInTimes;
    }

    public Integer getCheckInTimes()
    {
        return checkInTimes;
    }
    public void setLeaveTimes(Integer leaveTimes)
    {
        this.leaveTimes = leaveTimes;
    }

    public Integer getLeaveTimes()
    {
        return leaveTimes;
    }
    public void setCountAsCheckInTimes(Integer countAsCheckInTimes)
    {
        this.countAsCheckInTimes = countAsCheckInTimes;
    }

    public Integer getCountAsCheckInTimes()
    {
        return countAsCheckInTimes;
    }
    public void setPerMonthFee(BigDecimal perMonthFee)
    {
        this.perMonthFee = perMonthFee;
    }

    public BigDecimal getPerMonthFee()
    {
        return perMonthFee;
    }
    public void setPerDayFee(BigDecimal perDayFee)
    {
        this.perDayFee = perDayFee;
    }

    public BigDecimal getPerDayFee()
    {
        return perDayFee;
    }
    public void setTextbookFee(BigDecimal textbookFee)
    {
        this.textbookFee = textbookFee;
    }

    public BigDecimal getTextbookFee()
    {
        return textbookFee;
    }
    public void setFoodFee(BigDecimal foodFee)
    {
        this.foodFee = foodFee;
    }

    public BigDecimal getFoodFee()
    {
        return foodFee;
    }
    public void setBillFee(BigDecimal billFee)
    {
        this.billFee = billFee;
    }

    public BigDecimal getBillFee()
    {
        return billFee;
    }
    public void setAdvanceFee(BigDecimal advanceFee)
    {
        this.advanceFee = advanceFee;
    }

    public BigDecimal getAdvanceFee()
    {
        return advanceFee;
    }
    public void setCoupon(BigDecimal coupon)
    {
        this.coupon = coupon;
    }

    public BigDecimal getCoupon()
    {
        return coupon;
    }
    public void setActualPerMonthFee(BigDecimal actualPerMonthFee)
    {
        this.actualPerMonthFee = actualPerMonthFee;
    }

    public BigDecimal getActualPerMonthFee()
    {
        return actualPerMonthFee;
    }
    public void setActualPerDayFee(BigDecimal actualPerDayFee)
    {
        this.actualPerDayFee = actualPerDayFee;
    }

    public BigDecimal getActualPerDayFee()
    {
        return actualPerDayFee;
    }
    public void setActualTextbookFee(BigDecimal actualTextbookFee)
    {
        this.actualTextbookFee = actualTextbookFee;
    }

    public BigDecimal getActualTextbookFee()
    {
        return actualTextbookFee;
    }
    public void setActualFoodFee(BigDecimal actualFoodFee)
    {
        this.actualFoodFee = actualFoodFee;
    }

    public BigDecimal getActualFoodFee()
    {
        return actualFoodFee;
    }
    public void setAcutalBillFee(BigDecimal acutalBillFee)
    {
        this.acutalBillFee = acutalBillFee;
    }

    public BigDecimal getAcutalBillFee()
    {
        return acutalBillFee;
    }
    public void setBillStatus(Integer billStatus)
    {
        this.billStatus = billStatus;
    }

    public Integer getBillStatus()
    {
        return billStatus;
    }
    public void setBillMode(Integer billMode)
    {
        this.billMode = billMode;
    }

    public Integer getBillMode()
    {
        return billMode;
    }
    public void setBillEvidence(String billEvidence)
    {
        this.billEvidence = billEvidence;
    }

    public String getBillEvidence()
    {
        return billEvidence;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("studentId", getStudentId())
                .append("studentName", getStudentName())
                .append("checkInStatisticId", getCheckInStatisticId())
                .append("checkInBeginDate", getCheckInBeginDate())
                .append("checkInEndDate", getCheckInEndDate())
                .append("checkInTimes", getCheckInTimes())
                .append("leaveTimes", getLeaveTimes())
                .append("countAsCheckInTimes", getCountAsCheckInTimes())
                .append("perMonthFee", getPerMonthFee())
                .append("perDayFee", getPerDayFee())
                .append("textbookFee", getTextbookFee())
                .append("foodFee", getFoodFee())
                .append("billFee", getBillFee())
                .append("advanceFee", getAdvanceFee())
                .append("coupon", getCoupon())
                .append("actualPerMonthFee", getActualPerMonthFee())
                .append("actualPerDayFee", getActualPerDayFee())
                .append("actualTextbookFee", getActualTextbookFee())
                .append("actualFoodFee", getActualFoodFee())
                .append("acutalBillFee", getAcutalBillFee())
                .append("billStatus", getBillStatus())
                .append("billMode", getBillMode())
                .append("billEvidence", getBillEvidence())
                .toString();
    }
}
