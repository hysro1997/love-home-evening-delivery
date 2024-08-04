package com.hysro.ajkeeping.service;

import java.util.List;
import com.hysro.ajkeeping.domain.AjCostTemplate;

/**
 * 学生费用模板Service接口
 * 
 * @author hysro
 * @date 2024-08-04
 */
public interface IAjCostTemplateService 
{
    /**
     * 查询学生费用模板
     * 
     * @param costTemplateId 学生费用模板主键
     * @return 学生费用模板
     */
    public AjCostTemplate selectAjCostTemplateByCostTemplateId(Long costTemplateId);

    /**
     * 查询学生费用模板列表
     * 
     * @param ajCostTemplate 学生费用模板
     * @return 学生费用模板集合
     */
    public List<AjCostTemplate> selectAjCostTemplateList(AjCostTemplate ajCostTemplate);

    /**
     * 新增学生费用模板
     * 
     * @param ajCostTemplate 学生费用模板
     * @return 结果
     */
    public int insertAjCostTemplate(AjCostTemplate ajCostTemplate);

    /**
     * 修改学生费用模板
     * 
     * @param ajCostTemplate 学生费用模板
     * @return 结果
     */
    public int updateAjCostTemplate(AjCostTemplate ajCostTemplate);

    /**
     * 批量删除学生费用模板
     * 
     * @param costTemplateIds 需要删除的学生费用模板主键集合
     * @return 结果
     */
    public int deleteAjCostTemplateByCostTemplateIds(Long[] costTemplateIds);

    /**
     * 删除学生费用模板信息
     * 
     * @param costTemplateId 学生费用模板主键
     * @return 结果
     */
    public int deleteAjCostTemplateByCostTemplateId(Long costTemplateId);
}
