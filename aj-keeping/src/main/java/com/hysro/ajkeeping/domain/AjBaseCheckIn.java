package com.hysro.ajkeeping.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;

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
    @NotBlank
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
    @NotBlank
    private Date baseCheckInBeginDate;

    /** 考勤结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考勤结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    @NotBlank
    private Date baseCheckInEndDate;

    /** 每周考勤天数 */
    @Excel(name = "每周考勤天数")
    @NotBlank
    private Integer baseCheckInDaysOneWeek;

    /** 考勤总天数 */
    @Excel(name = "考勤总天数")
    @NotBlank
    private Integer baseCheckInSumDays;

    /** 考勤周数 */
    @Excel(name = "考勤周数")
    private Integer baseCheckInWeeks;

    /** 考勤状态（0默认，1考勤中，2考勤结束） */
    @Excel(name = "考勤状态", readConverterExp = "0=默认,1=考勤中,2=考勤结束")
    private Integer baseCheckInStatus;

    private Long[] checkinStudents;

    private Long[] checkinTeachers;

    @NotBlank
    private int[] baseCheckWeekenDay;

    private String baseCheckWeekenDayString;

    /** 参与考勤的列表 */
    private List<AjHomoInBaseCheckIn> ajHomoInBaseCheckIns;

    public String getBaseCheckWeekenDayString() {
        return baseCheckWeekenDayString;
    }

    public void setBaseCheckWeekenDayString(String baseCheckWeekenDayString) {
        this.baseCheckWeekenDayString = baseCheckWeekenDayString;
    }

    public int[] getBaseCheckWeekenDay() {
        return baseCheckWeekenDay;
    }

    public void setBaseCheckWeekenDay(int[] baseCheckWeekenDay) {
        this.baseCheckWeekenDay = baseCheckWeekenDay;
    }

    public Long[] getCheckinStudents() {
        return checkinStudents;
    }

    public void setCheckinStudents(Long[] checkinStudents) {
        this.checkinStudents = checkinStudents;
    }

    public Long[] getCheckinTeachers() {
        return checkinTeachers;
    }

    public void setCheckinTeachers(Long[] checkinTeachers) {
        this.checkinTeachers = checkinTeachers;
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

    public List<AjHomoInBaseCheckIn> getAjHomoInBaseCheckIns() {
        return ajHomoInBaseCheckIns;
    }

    public void setAjHomoInBaseCheckIns(List<AjHomoInBaseCheckIn> ajHomoInBaseCheckIns) {
        this.ajHomoInBaseCheckIns = ajHomoInBaseCheckIns;
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
