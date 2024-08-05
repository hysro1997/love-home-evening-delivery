package com.hysro.ajkeeping.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考勤总表对象 aj_base_check_in
 *
 * @author hysro
 * @date 2024-08-04
 */
public class AjBaseCheckIn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long baseCheckInId;

    /** 考勤名称 */
    @Excel(name = "考勤名称")
    private String baseCheckInName;

    /** 考勤学生的id数组 */
    @Excel(name = "考勤学生的id数组")
    private String baseCheckInStudents;

    /** 考勤教师的id数组 */
    @Excel(name = "考勤教师的id数组")
    private String baseCheckInTeachers;

    /** 考勤开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考勤开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date baseCheckInBeginDate;

    /** 考勤结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考勤结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date baseCheckInEndDate;

    /** 每周考勤天数 */
    @Excel(name = "每周考勤天数")
    private Integer baseCheckInDaysOneWeek;

    /** 考勤总天数 */
    @Excel(name = "考勤总天数")
    private Integer baseCheckInSumDays;

    /** 考勤周数 */
    @Excel(name = "考勤周数")
    private Integer baseCheckInWeeks;

    /** 考勤状态（0默认，1考勤中，2考勤结束） */
    @Excel(name = "考勤状态", readConverterExp = "0=默认,1=考勤中,2=考勤结束")
    private Integer baseCheckInStatus;

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
    public void setBaseCheckInStudents(String baseCheckInStudents)
    {
        this.baseCheckInStudents = baseCheckInStudents;
    }

    public String getBaseCheckInStudents()
    {
        return baseCheckInStudents;
    }
    public void setBaseCheckInTeachers(String baseCheckInTeachers)
    {
        this.baseCheckInTeachers = baseCheckInTeachers;
    }

    public String getBaseCheckInTeachers()
    {
        return baseCheckInTeachers;
    }
    public void setBaseCheckInBeginDate(Date baseCheckInBeginDate)
    {
        this.baseCheckInBeginDate = baseCheckInBeginDate;
    }

    public Date getBaseCheckInBeginDate()
    {
        return baseCheckInBeginDate;
    }
    public void setBaseCheckInEndDate(Date baseCheckInEndDate)
    {
        this.baseCheckInEndDate = baseCheckInEndDate;
    }

    public Date getBaseCheckInEndDate()
    {
        return baseCheckInEndDate;
    }
    public void setBaseCheckInDaysOneWeek(Integer baseCheckInDaysOneWeek)
    {
        this.baseCheckInDaysOneWeek = baseCheckInDaysOneWeek;
    }

    public Integer getBaseCheckInDaysOneWeek()
    {
        return baseCheckInDaysOneWeek;
    }
    public void setBaseCheckInSumDays(Integer baseCheckInSumDays)
    {
        this.baseCheckInSumDays = baseCheckInSumDays;
    }

    public Integer getBaseCheckInSumDays()
    {
        return baseCheckInSumDays;
    }
    public void setBaseCheckInWeeks(Integer baseCheckInWeeks)
    {
        this.baseCheckInWeeks = baseCheckInWeeks;
    }

    public Integer getBaseCheckInWeeks()
    {
        return baseCheckInWeeks;
    }
    public void setBaseCheckInStatus(Integer baseCheckInStatus)
    {
        this.baseCheckInStatus = baseCheckInStatus;
    }

    public Integer getBaseCheckInStatus()
    {
        return baseCheckInStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("baseCheckInId", getBaseCheckInId())
            .append("baseCheckInName", getBaseCheckInName())
            .append("baseCheckInStudents", getBaseCheckInStudents())
            .append("baseCheckInTeachers", getBaseCheckInTeachers())
            .append("baseCheckInBeginDate", getBaseCheckInBeginDate())
            .append("baseCheckInEndDate", getBaseCheckInEndDate())
            .append("baseCheckInDaysOneWeek", getBaseCheckInDaysOneWeek())
            .append("baseCheckInSumDays", getBaseCheckInSumDays())
            .append("baseCheckInWeeks", getBaseCheckInWeeks())
            .append("baseCheckInStatus", getBaseCheckInStatus())
            .toString();
    }
}
