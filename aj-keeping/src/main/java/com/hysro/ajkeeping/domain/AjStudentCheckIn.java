package com.hysro.ajkeeping.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生考勤对象 aj_student_check_in
 * 
 * @author hysro
 * @date 2024-08-04
 */
public class AjStudentCheckIn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long studentCheckInId;

    /** 学生id */
    @Excel(name = "学生id")
    private Long studentId;

    /** 对应考勤总表id */
    @Excel(name = "对应考勤总表id")
    private Long baseCheckInId;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String checkInStudentName;

    /** 学生年级 */
    @Excel(name = "学生年级")
    private String checkInStudentGrade;

    /** 签到日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签到日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkInDate;

    /** 签到状态（0未到，1签到，2请假） */
    @Excel(name = "签到状态", readConverterExp = "0=未到，1签到，2请假")
    private Integer checkInStatus;

    /** 签到时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签到时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkInDatetime;

    public void setStudentCheckInId(Long studentCheckInId) 
    {
        this.studentCheckInId = studentCheckInId;
    }

    public Long getStudentCheckInId() 
    {
        return studentCheckInId;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setBaseCheckInId(Long baseCheckInId) 
    {
        this.baseCheckInId = baseCheckInId;
    }

    public Long getBaseCheckInId() 
    {
        return baseCheckInId;
    }
    public void setCheckInStudentName(String checkInStudentName) 
    {
        this.checkInStudentName = checkInStudentName;
    }

    public String getCheckInStudentName() 
    {
        return checkInStudentName;
    }
    public void setCheckInStudentGrade(String checkInStudentGrade) 
    {
        this.checkInStudentGrade = checkInStudentGrade;
    }

    public String getCheckInStudentGrade() 
    {
        return checkInStudentGrade;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("studentCheckInId", getStudentCheckInId())
            .append("studentId", getStudentId())
            .append("baseCheckInId", getBaseCheckInId())
            .append("checkInStudentName", getCheckInStudentName())
            .append("checkInStudentGrade", getCheckInStudentGrade())
            .append("checkInDate", getCheckInDate())
            .append("checkInStatus", getCheckInStatus())
            .append("checkInDatetime", getCheckInDatetime())
            .toString();
    }
}
