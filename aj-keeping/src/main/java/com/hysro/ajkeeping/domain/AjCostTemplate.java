package com.hysro.ajkeeping.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生费用模板对象 aj_cost_template
 *
 * @author hysro
 * @date 2024-08-04
 */
public class AjCostTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long costTemplateId;

    /** 模板名次 */
    @Excel(name = "模板名次")
    private String costTemplateName;

    /** 每天费用 */
    @Excel(name = "每天费用")
    private BigDecimal costFeePerDay;

    /** 使用日收费（0不用，1用） */
    @Excel(name = "使用日收费", readConverterExp = "0=不用,1=用")
    private Integer costUseFeePerDay;

    /** 课时费 */
    @Excel(name = "课时费")
    private BigDecimal costFeePerLesson;

    /** 使用课时费（0不用，1用） */
    @Excel(name = "使用课时费", readConverterExp = "0=不用,1=用")
    private Integer costUseFeePerLesson;

    /** 伙食费 */
    @Excel(name = "伙食费")
    private BigDecimal costFoodFee;

    /** 材料费 */
    @Excel(name = "材料费")
    private BigDecimal costTextbooksFee;

    /** 每月收费 */
    @Excel(name = "每月收费")
    private BigDecimal costFeePerMonth;

    /** 使用每月收费（0不用，1用） */
    @Excel(name = "使用每月收费", readConverterExp = "0=不用,1=用")
    private Integer costUseFeePerMonth;

    /** 适合哪个年级用 */
    @Excel(name = "适合哪个年级用")
    private String costUseGrade;

    /** 默认（0不默认，1默认） */
    @Excel(name = "默认", readConverterExp = "0=不默认,1=默认")
    private Integer costDefault;

    public void setCostTemplateId(Long costTemplateId)
    {
        this.costTemplateId = costTemplateId;
    }

    public Long getCostTemplateId()
    {
        return costTemplateId;
    }
    public void setCostTemplateName(String costTemplateName)
    {
        this.costTemplateName = costTemplateName;
    }

    public String getCostTemplateName()
    {
        return costTemplateName;
    }
    public void setCostFeePerDay(BigDecimal costFeePerDay)
    {
        this.costFeePerDay = costFeePerDay;
    }

    public BigDecimal getCostFeePerDay()
    {
        return costFeePerDay;
    }
    public void setCostUseFeePerDay(Integer costUseFeePerDay)
    {
        this.costUseFeePerDay = costUseFeePerDay;
    }

    public Integer getCostUseFeePerDay()
    {
        return costUseFeePerDay;
    }
    public void setCostFeePerLesson(BigDecimal costFeePerLesson)
    {
        this.costFeePerLesson = costFeePerLesson;
    }

    public BigDecimal getCostFeePerLesson()
    {
        return costFeePerLesson;
    }
    public void setCostUseFeePerLesson(Integer costUseFeePerLesson)
    {
        this.costUseFeePerLesson = costUseFeePerLesson;
    }

    public Integer getCostUseFeePerLesson()
    {
        return costUseFeePerLesson;
    }
    public void setCostFoodFee(BigDecimal costFoodFee)
    {
        this.costFoodFee = costFoodFee;
    }

    public BigDecimal getCostFoodFee()
    {
        return costFoodFee;
    }
    public void setCostTextbooksFee(BigDecimal costTextbooksFee)
    {
        this.costTextbooksFee = costTextbooksFee;
    }

    public BigDecimal getCostTextbooksFee()
    {
        return costTextbooksFee;
    }
    public void setCostFeePerMonth(BigDecimal costFeePerMonth)
    {
        this.costFeePerMonth = costFeePerMonth;
    }

    public BigDecimal getCostFeePerMonth()
    {
        return costFeePerMonth;
    }
    public void setCostUseFeePerMonth(Integer costUseFeePerMonth)
    {
        this.costUseFeePerMonth = costUseFeePerMonth;
    }

    public Integer getCostUseFeePerMonth()
    {
        return costUseFeePerMonth;
    }
    public void setCostUseGrade(String costUseGrade)
    {
        this.costUseGrade = costUseGrade;
    }

    public String getCostUseGrade()
    {
        return costUseGrade;
    }
    public void setCostDefault(Integer costDefault)
    {
        this.costDefault = costDefault;
    }

    public Integer getCostDefault()
    {
        return costDefault;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("costTemplateId", getCostTemplateId())
            .append("costTemplateName", getCostTemplateName())
            .append("costFeePerDay", getCostFeePerDay())
            .append("costUseFeePerDay", getCostUseFeePerDay())
            .append("costFeePerLesson", getCostFeePerLesson())
            .append("costUseFeePerLesson", getCostUseFeePerLesson())
            .append("costFoodFee", getCostFoodFee())
            .append("costTextbooksFee", getCostTextbooksFee())
            .append("costFeePerMonth", getCostFeePerMonth())
            .append("costUseFeePerMonth", getCostUseFeePerMonth())
            .append("costUseGrade", getCostUseGrade())
            .append("costDefault", getCostDefault())
            .toString();
    }
}
