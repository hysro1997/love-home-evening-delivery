package com.hysro.ajkeeping.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hysro.ajkeeping.mapper.AjCostTemplateMapper;
import com.hysro.ajkeeping.domain.AjCostTemplate;
import com.hysro.ajkeeping.service.IAjCostTemplateService;

/**
 * 学生费用模板Service业务层处理
 * 
 * @author hysro
 * @date 2024-08-04
 */
@Service
public class AjCostTemplateServiceImpl implements IAjCostTemplateService 
{
    @Autowired
    private AjCostTemplateMapper ajCostTemplateMapper;

    /**
     * 查询学生费用模板
     * 
     * @param costTemplateId 学生费用模板主键
     * @return 学生费用模板
     */
    @Override
    public AjCostTemplate selectAjCostTemplateByCostTemplateId(Long costTemplateId)
    {
        return ajCostTemplateMapper.selectAjCostTemplateByCostTemplateId(costTemplateId);
    }

    /**
     * 查询学生费用模板列表
     * 
     * @param ajCostTemplate 学生费用模板
     * @return 学生费用模板
     */
    @Override
    public List<AjCostTemplate> selectAjCostTemplateList(AjCostTemplate ajCostTemplate)
    {
        return ajCostTemplateMapper.selectAjCostTemplateList(ajCostTemplate);
    }

    /**
     * 新增学生费用模板
     * 
     * @param ajCostTemplate 学生费用模板
     * @return 结果
     */
    @Override
    public int insertAjCostTemplate(AjCostTemplate ajCostTemplate)
    {
        return ajCostTemplateMapper.insertAjCostTemplate(ajCostTemplate);
    }

    /**
     * 修改学生费用模板
     * 
     * @param ajCostTemplate 学生费用模板
     * @return 结果
     */
    @Override
    public int updateAjCostTemplate(AjCostTemplate ajCostTemplate)
    {
        return ajCostTemplateMapper.updateAjCostTemplate(ajCostTemplate);
    }

    /**
     * 批量删除学生费用模板
     * 
     * @param costTemplateIds 需要删除的学生费用模板主键
     * @return 结果
     */
    @Override
    public int deleteAjCostTemplateByCostTemplateIds(Long[] costTemplateIds)
    {
        return ajCostTemplateMapper.deleteAjCostTemplateByCostTemplateIds(costTemplateIds);
    }

    /**
     * 删除学生费用模板信息
     * 
     * @param costTemplateId 学生费用模板主键
     * @return 结果
     */
    @Override
    public int deleteAjCostTemplateByCostTemplateId(Long costTemplateId)
    {
        return ajCostTemplateMapper.deleteAjCostTemplateByCostTemplateId(costTemplateId);
    }
}
