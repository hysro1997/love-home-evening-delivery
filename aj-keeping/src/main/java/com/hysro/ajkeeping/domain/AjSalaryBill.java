package com.hysro.ajkeeping.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工资明细对象 aj_salary_bill
 * 
 * @author hysro
 * @date 2024-08-04
 */
public class AjSalaryBill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 教师id */
    @Excel(name = "教师id")
    private Long teacherId;

    /** 教师姓名 */
    @Excel(name = "教师姓名")
    private String teacherName;

    /** 教师考勤统计表id */
    @Excel(name = "教师考勤统计表id")
    private Long teacherCheckInStatistciId;

    /** 考勤开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考勤开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkInBeginDate;

    /** 考勤结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考勤结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkInEndDate;

    /** 考勤总天数 */
    @Excel(name = "考勤总天数")
    private Integer checkInSumDays;

    /** 考勤次数 */
    @Excel(name = "考勤次数")
    private Integer checkInTimes;

    /** 请假天数 */
    @Excel(name = "请假天数")
    private Integer leaveDays;

    /** 值班次数 */
    @Excel(name = "值班次数")
    private Integer onDutyTimes;

    /** 值班补助 */
    @Excel(name = "值班补助")
    private BigDecimal onDutyPension;

    /** 全勤（0全勤，1未全勤） */
    @Excel(name = "全勤", readConverterExp = "0=全勤，1未全勤")
    private Integer fullCheckIn;

    /** 全勤奖 */
    @Excel(name = "全勤奖")
    private BigDecimal fullCheckInPension;

    /** 其他补助 */
    @Excel(name = "其他补助")
    private BigDecimal otherPension;

    /** 工资扣除 */
    @Excel(name = "工资扣除")
    private BigDecimal deduckMoney;

    /** 实际工资 */
    @Excel(name = "实际工资")
    private BigDecimal acutalSalary;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setTeacherName(String teacherName) 
    {
        this.teacherName = teacherName;
    }

    public String getTeacherName() 
    {
        return teacherName;
    }
    public void setTeacherCheckInStatistciId(Long teacherCheckInStatistciId) 
    {
        this.teacherCheckInStatistciId = teacherCheckInStatistciId;
    }

    public Long getTeacherCheckInStatistciId() 
    {
        return teacherCheckInStatistciId;
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
    public void setLeaveDays(Integer leaveDays) 
    {
        this.leaveDays = leaveDays;
    }

    public Integer getLeaveDays() 
    {
        return leaveDays;
    }
    public void setOnDutyTimes(Integer onDutyTimes) 
    {
        this.onDutyTimes = onDutyTimes;
    }

    public Integer getOnDutyTimes() 
    {
        return onDutyTimes;
    }
    public void setOnDutyPension(BigDecimal onDutyPension) 
    {
        this.onDutyPension = onDutyPension;
    }

    public BigDecimal getOnDutyPension() 
    {
        return onDutyPension;
    }
    public void setFullCheckIn(Integer fullCheckIn) 
    {
        this.fullCheckIn = fullCheckIn;
    }

    public Integer getFullCheckIn() 
    {
        return fullCheckIn;
    }
    public void setFullCheckInPension(BigDecimal fullCheckInPension) 
    {
        this.fullCheckInPension = fullCheckInPension;
    }

    public BigDecimal getFullCheckInPension() 
    {
        return fullCheckInPension;
    }
    public void setOtherPension(BigDecimal otherPension) 
    {
        this.otherPension = otherPension;
    }

    public BigDecimal getOtherPension() 
    {
        return otherPension;
    }
    public void setDeduckMoney(BigDecimal deduckMoney) 
    {
        this.deduckMoney = deduckMoney;
    }

    public BigDecimal getDeduckMoney() 
    {
        return deduckMoney;
    }
    public void setAcutalSalary(BigDecimal acutalSalary) 
    {
        this.acutalSalary = acutalSalary;
    }

    public BigDecimal getAcutalSalary() 
    {
        return acutalSalary;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("teacherId", getTeacherId())
            .append("teacherName", getTeacherName())
            .append("teacherCheckInStatistciId", getTeacherCheckInStatistciId())
            .append("checkInBeginDate", getCheckInBeginDate())
            .append("checkInEndDate", getCheckInEndDate())
            .append("checkInSumDays", getCheckInSumDays())
            .append("checkInTimes", getCheckInTimes())
            .append("leaveDays", getLeaveDays())
            .append("onDutyTimes", getOnDutyTimes())
            .append("onDutyPension", getOnDutyPension())
            .append("fullCheckIn", getFullCheckIn())
            .append("fullCheckInPension", getFullCheckInPension())
            .append("otherPension", getOtherPension())
            .append("deduckMoney", getDeduckMoney())
            .append("acutalSalary", getAcutalSalary())
            .toString();
    }
}
