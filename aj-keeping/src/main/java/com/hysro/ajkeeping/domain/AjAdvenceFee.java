package com.hysro.ajkeeping.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 预收费对象 aj_advence_fee
 *
 * @author hysro
 * @date 2024-08-05
 */
public class AjAdvenceFee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 学生id */
    @Excel(name = "学生id")
    private Long studentId;

    /** 考勤基表id */
    @Excel(name = "考勤基表id")
    private Long baseCheckInId;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String studentName;

    /** 预交费 */
    @Excel(name = "预交费")
    private BigDecimal advanceFee;

    @Excel(name = "备注")
    private String memo;

    /** 核实状态(0预交费, 1已核销, 2已退款作废) */
    @Excel(name = "核实状态(0=预交费,1=已核销,2=已退款作废)")
    private Integer verifyAdvanceFee;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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
    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    public String getStudentName()
    {
        return studentName;
    }
    public void setAdvanceFee(BigDecimal advanceFee)
    {
        this.advanceFee = advanceFee;
    }

    public BigDecimal getAdvanceFee()
    {
        return advanceFee;
    }
    public void setVerifyAdvanceFee(Integer verifyAdvanceFee)
    {
        this.verifyAdvanceFee = verifyAdvanceFee;
    }

    public Integer getVerifyAdvanceFee()
    {
        return verifyAdvanceFee;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("studentId", getStudentId())
            .append("baseCheckInId", getBaseCheckInId())
            .append("studentName", getStudentName())
            .append("advanceFee", getAdvanceFee())
            .append("verifyAdvanceFee", getVerifyAdvanceFee())
            .toString();
    }
}
