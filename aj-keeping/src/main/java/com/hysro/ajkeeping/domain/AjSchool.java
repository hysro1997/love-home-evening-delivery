package com.hysro.ajkeeping.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;

/**
 * 学校对象 aj_school
 *
 * @author hysro
 * @date 2024-08-04
 */
public class AjSchool extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 学校 */
    @Excel(name = "学校")
    @NotBlank
    private String schoolName;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setSchoolName(String schoolName)
    {
        this.schoolName = schoolName;
    }

    public String getSchoolName()
    {
        return schoolName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("schoolName", getSchoolName())
            .toString();
    }
}
