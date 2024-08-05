package com.hysro.ajkeeping.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 老师工资模板对象 aj_salary_template
 *
 * @author hysro
 * @date 2024-08-04
 */
public class AjSalaryTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long salaryId;

    /** 工资模板名次 */
    @Excel(name = "工资模板名次")
    private String salaryTemplateName;

    /** 月工资 */
    @Excel(name = "月工资")
    private BigDecimal salaryPerMonth;

    /** 使用月工资（0不用，1用） */
    @Excel(name = "使用月工资", readConverterExp = "0=不用,1=用")
    private Integer salaryUsePerMonth;

    /** 日工资 */
    @Excel(name = "日工资")
    private BigDecimal salaryPerDay;

    /** 使用日工资（0不用，1用） */
    @Excel(name = "使用日工资", readConverterExp = "0=不用,1=用")
    private Integer salaryUserPerDay;

    /** 值班费 */
    @Excel(name = "值班费")
    private BigDecimal salaryOnDuty;

    /** 请假工资 */
    @Excel(name = "请假工资")
    private BigDecimal salaryOffDutyFee;

    /** 默认（0不默认，1默认） */
    @Excel(name = "默认", readConverterExp = "0=不默认,1=默认")
    private Integer salaryDefault;

    /** 全勤奖 */
    @Excel(name = "全勤奖")
    private BigDecimal salaryAllDuty;

    public void setSalaryId(Long salaryId)
    {
        this.salaryId = salaryId;
    }

    public Long getSalaryId()
    {
        return salaryId;
    }
    public void setSalaryTemplateName(String salaryTemplateName)
    {
        this.salaryTemplateName = salaryTemplateName;
    }

    public String getSalaryTemplateName()
    {
        return salaryTemplateName;
    }
    public void setSalaryPerMonth(BigDecimal salaryPerMonth)
    {
        this.salaryPerMonth = salaryPerMonth;
    }

    public BigDecimal getSalaryPerMonth()
    {
        return salaryPerMonth;
    }
    public void setSalaryUsePerMonth(Integer salaryUsePerMonth)
    {
        this.salaryUsePerMonth = salaryUsePerMonth;
    }

    public Integer getSalaryUsePerMonth()
    {
        return salaryUsePerMonth;
    }
    public void setSalaryPerDay(BigDecimal salaryPerDay)
    {
        this.salaryPerDay = salaryPerDay;
    }

    public BigDecimal getSalaryPerDay()
    {
        return salaryPerDay;
    }
    public void setSalaryUserPerDay(Integer salaryUserPerDay)
    {
        this.salaryUserPerDay = salaryUserPerDay;
    }

    public Integer getSalaryUserPerDay()
    {
        return salaryUserPerDay;
    }
    public void setSalaryOnDuty(BigDecimal salaryOnDuty)
    {
        this.salaryOnDuty = salaryOnDuty;
    }

    public BigDecimal getSalaryOnDuty()
    {
        return salaryOnDuty;
    }
    public void setSalaryOffDutyFee(BigDecimal salaryOffDutyFee)
    {
        this.salaryOffDutyFee = salaryOffDutyFee;
    }

    public BigDecimal getSalaryOffDutyFee()
    {
        return salaryOffDutyFee;
    }
    public void setSalaryDefault(Integer salaryDefault)
    {
        this.salaryDefault = salaryDefault;
    }

    public Integer getSalaryDefault()
    {
        return salaryDefault;
    }
    public void setSalaryAllDuty(BigDecimal salaryAllDuty)
    {
        this.salaryAllDuty = salaryAllDuty;
    }

    public BigDecimal getSalaryAllDuty()
    {
        return salaryAllDuty;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("salaryId", getSalaryId())
            .append("salaryTemplateName", getSalaryTemplateName())
            .append("salaryPerMonth", getSalaryPerMonth())
            .append("salaryUsePerMonth", getSalaryUsePerMonth())
            .append("salaryPerDay", getSalaryPerDay())
            .append("salaryUserPerDay", getSalaryUserPerDay())
            .append("salaryOnDuty", getSalaryOnDuty())
            .append("salaryOffDutyFee", getSalaryOffDutyFee())
            .append("salaryDefault", getSalaryDefault())
            .append("salaryAllDuty", getSalaryAllDuty())
            .toString();
    }
}
