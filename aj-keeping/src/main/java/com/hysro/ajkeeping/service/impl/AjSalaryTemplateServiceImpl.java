package com.hysro.ajkeeping.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hysro.ajkeeping.mapper.AjSalaryTemplateMapper;
import com.hysro.ajkeeping.domain.AjSalaryTemplate;
import com.hysro.ajkeeping.service.IAjSalaryTemplateService;

/**
 * 老师工资模板Service业务层处理
 * 
 * @author hysro
 * @date 2024-08-04
 */
@Service
public class AjSalaryTemplateServiceImpl implements IAjSalaryTemplateService 
{
    @Autowired
    private AjSalaryTemplateMapper ajSalaryTemplateMapper;

    /**
     * 查询老师工资模板
     * 
     * @param salaryId 老师工资模板主键
     * @return 老师工资模板
     */
    @Override
    public AjSalaryTemplate selectAjSalaryTemplateBySalaryId(Long salaryId)
    {
        return ajSalaryTemplateMapper.selectAjSalaryTemplateBySalaryId(salaryId);
    }

    /**
     * 查询老师工资模板列表
     * 
     * @param ajSalaryTemplate 老师工资模板
     * @return 老师工资模板
     */
    @Override
    public List<AjSalaryTemplate> selectAjSalaryTemplateList(AjSalaryTemplate ajSalaryTemplate)
    {
        return ajSalaryTemplateMapper.selectAjSalaryTemplateList(ajSalaryTemplate);
    }

    /**
     * 新增老师工资模板
     * 
     * @param ajSalaryTemplate 老师工资模板
     * @return 结果
     */
    @Override
    public int insertAjSalaryTemplate(AjSalaryTemplate ajSalaryTemplate)
    {
        return ajSalaryTemplateMapper.insertAjSalaryTemplate(ajSalaryTemplate);
    }

    /**
     * 修改老师工资模板
     * 
     * @param ajSalaryTemplate 老师工资模板
     * @return 结果
     */
    @Override
    public int updateAjSalaryTemplate(AjSalaryTemplate ajSalaryTemplate)
    {
        return ajSalaryTemplateMapper.updateAjSalaryTemplate(ajSalaryTemplate);
    }

    /**
     * 批量删除老师工资模板
     * 
     * @param salaryIds 需要删除的老师工资模板主键
     * @return 结果
     */
    @Override
    public int deleteAjSalaryTemplateBySalaryIds(Long[] salaryIds)
    {
        return ajSalaryTemplateMapper.deleteAjSalaryTemplateBySalaryIds(salaryIds);
    }

    /**
     * 删除老师工资模板信息
     * 
     * @param salaryId 老师工资模板主键
     * @return 结果
     */
    @Override
    public int deleteAjSalaryTemplateBySalaryId(Long salaryId)
    {
        return ajSalaryTemplateMapper.deleteAjSalaryTemplateBySalaryId(salaryId);
    }
}
