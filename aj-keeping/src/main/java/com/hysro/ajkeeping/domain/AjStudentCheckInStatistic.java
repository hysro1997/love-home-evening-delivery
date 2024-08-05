package com.hysro.ajkeeping.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考勤统计对象 aj_student_check_in_statistic
 *
 * @author hysro
 * @date 2024-08-04
 */
public class AjStudentCheckInStatistic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 对应考勤总表id */
    @Excel(name = "对应考勤总表id")
    private Long baseCheckInId;

    /** 学生id */
    @Excel(name = "学生id")
    private Long studentId;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String studentName;

    /** 考勤总天数 */
    @Excel(name = "考勤总天数")
    private Integer checkInSumDays;

    /** 考勤次数 */
    @Excel(name = "考勤次数")
    private Integer checkInTimes;

    /** 考勤开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考勤开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkInBeginDate;

    /** 考勤结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考勤结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkInEndDate;

    /** 请假天数 */
    @Excel(name = "请假天数")
    private Integer leaveDays;

    /** 账单状态（0未生成，1已生成） */
    @Excel(name = "账单状态", readConverterExp = "0=未生成,1=已生成")
    private Integer billStatus;

    /** 缴费状态（0未缴费，1已缴费） */
    @Excel(name = "缴费状态", readConverterExp = "0=未缴费,1=已缴费")
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
    public void setCheckInSumDays(Integer checkInSumDays)
    {
        this.checkInSumDays = checkInSumDays;
    }

    public Integer getCheckInSumDays()
    {
        return checkInSumDays;
    }
    public void setCheckInTimes(Integer checkInTimes)
    {
        this.checkInTimes = checkInTimes;
    }

    public Integer getCheckInTimes()
    {
        return checkInTimes;
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
    public void setLeaveDays(Integer leaveDays)
    {
        this.leaveDays = leaveDays;
    }

    public Integer getLeaveDays()
    {
        return leaveDays;
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
            .append("studentId", getStudentId())
            .append("studentName", getStudentName())
            .append("checkInSumDays", getCheckInSumDays())
            .append("checkInTimes", getCheckInTimes())
            .append("checkInBeginDate", getCheckInBeginDate())
            .append("checkInEndDate", getCheckInEndDate())
            .append("leaveDays", getLeaveDays())
            .append("billStatus", getBillStatus())
            .append("paymentStatus", getPaymentStatus())
            .toString();
    }
}
