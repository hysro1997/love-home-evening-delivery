package com.hysro.ajkeeping.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 员工考勤对象 aj_teacher_check_in
 * 
 * @author hysro
 * @date 2024-08-04
 */
public class AjTeacherCheckIn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long teacherCheckInId;

    /** 教师id */
    @Excel(name = "教师id")
    private Long teacherId;

    /** 教师姓名 */
    @Excel(name = "教师姓名")
    private String teacherName;

    /** 签到日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签到日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkInDate;

    /** 考勤状态（0未到，1签到，2迟到，3请假） */
    @Excel(name = "考勤状态", readConverterExp = "0=未到，1签到，2迟到，3请假")
    private Integer checkInStatus;

    /** 签到时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签到时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkInDatetime;

    /** 当日值班次数 */
    @Excel(name = "当日值班次数")
    private Integer checkInDayOnDutyTimes;

    public void setTeacherCheckInId(Long teacherCheckInId) 
    {
        this.teacherCheckInId = teacherCheckInId;
    }

    public Long getTeacherCheckInId() 
    {
        return teacherCheckInId;
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
    public void setCheckInDate(Date checkInDate) 
    {
        this.checkInDate = checkInDate;
    }

    public Date getCheckInDate() 
    {
        return checkInDate;
    }
    public void setCheckInStatus(Integer checkInStatus) 
    {
        this.checkInStatus = checkInStatus;
    }

    public Integer getCheckInStatus() 
    {
        return checkInStatus;
    }
    public void setCheckInDatetime(Date checkInDatetime) 
    {
        this.checkInDatetime = checkInDatetime;
    }

    public Date getCheckInDatetime() 
    {
        return checkInDatetime;
    }
    public void setCheckInDayOnDutyTimes(Integer checkInDayOnDutyTimes) 
    {
        this.checkInDayOnDutyTimes = checkInDayOnDutyTimes;
    }

    public Integer getCheckInDayOnDutyTimes() 
    {
        return checkInDayOnDutyTimes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teacherCheckInId", getTeacherCheckInId())
            .append("teacherId", getTeacherId())
            .append("teacherName", getTeacherName())
            .append("checkInDate", getCheckInDate())
            .append("checkInStatus", getCheckInStatus())
            .append("checkInDatetime", getCheckInDatetime())
            .append("checkInDayOnDutyTimes", getCheckInDayOnDutyTimes())
            .toString();
    }
}
