package com.hysro.ajkeeping.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;

/**
 * 年级对象 aj_grade
 *
 * @author hysro
 * @date 2024-08-04
 */
public class AjGrade extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 年级 */
    @Excel(name = "年级")
    @NotBlank
    private String grade;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public String getGrade()
    {
        return grade;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("grade", getGrade())
            .toString();
    }
}
