package com.hysro.ajkeeping.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 在考勤中的老师与学生对象 aj_homo_in_base_check_in
 * 
 * @author hysro
 * @date 2024-08-08
 */
public class AjHomoInBaseCheckIn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 考勤基表 */
    @Excel(name = "考勤基表")
    private Long baseCheckInId;

    /** 学生id */
    @Excel(name = "学生id")
    private Long studentId;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String studentName;

    /** 教师id */
    @Excel(name = "教师id")
    private Long teacherId;

    /** 教师姓名 */
    @Excel(name = "教师姓名")
    private String teacherName;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("baseCheckInId", getBaseCheckInId())
            .append("studentId", getStudentId())
            .append("studentName", getStudentName())
            .append("teacherId", getTeacherId())
            .append("teacherName", getTeacherName())
            .toString();
    }
}
