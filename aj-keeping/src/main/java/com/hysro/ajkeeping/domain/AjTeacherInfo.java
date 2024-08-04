package com.hysro.ajkeeping.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 教师信息对象 aj_teacher_info
 * 
 * @author ruoyi
 * @date 2024-08-04
 */
public class AjTeacherInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long teacherId;

    /** 教师姓名（可重复，建议添加后缀） */
    @Excel(name = "教师姓名", readConverterExp = "可=重复，建议添加后缀")
    private String teacherName;

    /** 教师性别 */
    @Excel(name = "教师性别")
    private Integer teacherGender;

    /** 教师电话 */
    @Excel(name = "教师电话")
    private String teacherPhone;

    /** 用工类别（0兼职，1全职，2暑假工） */
    @Excel(name = "用工类别", readConverterExp = "0=兼职，1全职，2暑假工")
    private Integer teacherEmployType;

    /** 教师人脸识别base64 */
    @Excel(name = "教师人脸识别base64")
    private String teacherFace;

    /** 教师状态（0在职，1离职） */
    @Excel(name = "教师状态", readConverterExp = "0=在职，1离职")
    private Integer teacherStatus;

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
    public void setTeacherGender(Integer teacherGender) 
    {
        this.teacherGender = teacherGender;
    }

    public Integer getTeacherGender() 
    {
        return teacherGender;
    }
    public void setTeacherPhone(String teacherPhone) 
    {
        this.teacherPhone = teacherPhone;
    }

    public String getTeacherPhone() 
    {
        return teacherPhone;
    }
    public void setTeacherEmployType(Integer teacherEmployType) 
    {
        this.teacherEmployType = teacherEmployType;
    }

    public Integer getTeacherEmployType() 
    {
        return teacherEmployType;
    }
    public void setTeacherFace(String teacherFace) 
    {
        this.teacherFace = teacherFace;
    }

    public String getTeacherFace() 
    {
        return teacherFace;
    }
    public void setTeacherStatus(Integer teacherStatus) 
    {
        this.teacherStatus = teacherStatus;
    }

    public Integer getTeacherStatus() 
    {
        return teacherStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teacherId", getTeacherId())
            .append("teacherName", getTeacherName())
            .append("teacherGender", getTeacherGender())
            .append("teacherPhone", getTeacherPhone())
            .append("teacherEmployType", getTeacherEmployType())
            .append("teacherFace", getTeacherFace())
            .append("teacherStatus", getTeacherStatus())
            .toString();
    }
}
