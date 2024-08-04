package com.hysro.ajkeeping.mapper;

import java.util.List;
import com.hysro.ajkeeping.domain.AjSalaryTemplate;

/**
 * 老师工资模板Mapper接口
 * 
 * @author hysro
 * @date 2024-08-04
 */
public interface AjSalaryTemplateMapper 
{
    /**
     * 查询老师工资模板
     * 
     * @param salaryId 老师工资模板主键
     * @return 老师工资模板
     */
    public AjSalaryTemplate selectAjSalaryTemplateBySalaryId(Long salaryId);

    /**
     * 查询老师工资模板列表
     * 
     * @param ajSalaryTemplate 老师工资模板
     * @return 老师工资模板集合
     */
    public List<AjSalaryTemplate> selectAjSalaryTemplateList(AjSalaryTemplate ajSalaryTemplate);

    /**
     * 新增老师工资模板
     * 
     * @param ajSalaryTemplate 老师工资模板
     * @return 结果
     */
    public int insertAjSalaryTemplate(AjSalaryTemplate ajSalaryTemplate);

    /**
     * 修改老师工资模板
     * 
     * @param ajSalaryTemplate 老师工资模板
     * @return 结果
     */
    public int updateAjSalaryTemplate(AjSalaryTemplate ajSalaryTemplate);

    /**
     * 删除老师工资模板
     * 
     * @param salaryId 老师工资模板主键
     * @return 结果
     */
    public int deleteAjSalaryTemplateBySalaryId(Long salaryId);

    /**
     * 批量删除老师工资模板
     * 
     * @param salaryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAjSalaryTemplateBySalaryIds(Long[] salaryIds);
}
