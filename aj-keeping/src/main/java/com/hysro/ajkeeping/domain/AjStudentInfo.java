package com.hysro.ajkeeping.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生信息对象 aj_student_info
 *
 * @author aj
 * @date 2024-08-04
 */
public class AjStudentInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long studentId;

    /** 学生姓名（可重复，建议添加后缀） */
    @Excel(name = "学生姓名")
    private String studentName;

    /** 学生性别（0默认，1男，2女） */
    @Excel(name = "学生性别", readConverterExp = "0=默认,1=男,2=女")
    private Integer studentGender;

    /** 学生学校 */
    @Excel(name = "学生学校")
    private String studentSchool;

    /** 学生年级 */
    @Excel(name = "学生年级")
    private String studentGrade;

    /** 学生班级 */
    @Excel(name = "学生班级")
    private String studentClass;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String studentPhone;

    /** 人脸识别base64 */
    private String studentFace;

    /** 学生来源（上门，招生，推荐） */
    @Excel(name = "学生来源")
    private String studentSource;

    /** 备注信息 */
    @Excel(name = "备注信息")
    private String studentRemark;

    /** 学生状态（0在学，1不学） */
    private Integer studentStatus;

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
    public void setStudentGender(Integer studentGender)
    {
        this.studentGender = studentGender;
    }

    public Integer getStudentGender()
    {
        return studentGender;
    }
    public void setStudentSchool(String studentSchool)
    {
        this.studentSchool = studentSchool;
    }

    public String getStudentSchool()
    {
        return studentSchool;
    }
    public void setStudentGrade(String studentGrade)
    {
        this.studentGrade = studentGrade;
    }

    public String getStudentGrade()
    {
        return studentGrade;
    }
    public void setStudentClass(String studentClass)
    {
        this.studentClass = studentClass;
    }

    public String getStudentClass()
    {
        return studentClass;
    }
    public void setStudentPhone(String studentPhone)
    {
        this.studentPhone = studentPhone;
    }

    public String getStudentPhone()
    {
        return studentPhone;
    }
    public void setStudentFace(String studentFace)
    {
        this.studentFace = studentFace;
    }

    public String getStudentFace()
    {
        return studentFace;
    }
    public void setStudentSource(String studentSource)
    {
        this.studentSource = studentSource;
    }

    public String getStudentSource()
    {
        return studentSource;
    }
    public void setStudentRemark(String studentRemark)
    {
        this.studentRemark = studentRemark;
    }

    public String getStudentRemark()
    {
        return studentRemark;
    }
    public void setStudentStatus(Integer studentStatus)
    {
        this.studentStatus = studentStatus;
    }

    public Integer getStudentStatus()
    {
        return studentStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("studentId", getStudentId())
            .append("studentName", getStudentName())
            .append("studentGender", getStudentGender())
            .append("studentSchool", getStudentSchool())
            .append("studentGrade", getStudentGrade())
            .append("studentClass", getStudentClass())
            .append("studentPhone", getStudentPhone())
            .append("studentFace", getStudentFace())
            .append("studentSource", getStudentSource())
            .append("studentRemark", getStudentRemark())
            .append("studentStatus", getStudentStatus())
            .toString();
    }
}
