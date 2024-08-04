package com.hysro.ajkeeping.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.hysro.ajkeeping.domain.AjCostTemplate;
import com.hysro.ajkeeping.service.IAjCostTemplateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生费用模板Controller
 * 
 * @author hysro
 * @date 2024-08-04
 */
@RestController
@RequestMapping("/bill/studentBillTemplate")
public class AjCostTemplateController extends BaseController
{
    @Autowired
    private IAjCostTemplateService ajCostTemplateService;

    /**
     * 查询学生费用模板列表
     */
    @PreAuthorize("@ss.hasPermi('bill:studentBillTemplate:list')")
    @GetMapping("/list")
    public TableDataInfo list(AjCostTemplate ajCostTemplate)
    {
        startPage();
        List<AjCostTemplate> list = ajCostTemplateService.selectAjCostTemplateList(ajCostTemplate);
        return getDataTable(list);
    }

    /**
     * 导出学生费用模板列表
     */
    @PreAuthorize("@ss.hasPermi('bill:studentBillTemplate:export')")
    @Log(title = "学生费用模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AjCostTemplate ajCostTemplate)
    {
        List<AjCostTemplate> list = ajCostTemplateService.selectAjCostTemplateList(ajCostTemplate);
        ExcelUtil<AjCostTemplate> util = new ExcelUtil<AjCostTemplate>(AjCostTemplate.class);
        util.exportExcel(response, list, "学生费用模板数据");
    }

    /**
     * 获取学生费用模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('bill:studentBillTemplate:query')")
    @GetMapping(value = "/{costTemplateId}")
    public AjaxResult getInfo(@PathVariable("costTemplateId") Long costTemplateId)
    {
        return success(ajCostTemplateService.selectAjCostTemplateByCostTemplateId(costTemplateId));
    }

    /**
     * 新增学生费用模板
     */
    @PreAuthorize("@ss.hasPermi('bill:studentBillTemplate:add')")
    @Log(title = "学生费用模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AjCostTemplate ajCostTemplate)
    {
        return toAjax(ajCostTemplateService.insertAjCostTemplate(ajCostTemplate));
    }

    /**
     * 修改学生费用模板
     */
    @PreAuthorize("@ss.hasPermi('bill:studentBillTemplate:edit')")
    @Log(title = "学生费用模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AjCostTemplate ajCostTemplate)
    {
        return toAjax(ajCostTemplateService.updateAjCostTemplate(ajCostTemplate));
    }

    /**
     * 删除学生费用模板
     */
    @PreAuthorize("@ss.hasPermi('bill:studentBillTemplate:remove')")
    @Log(title = "学生费用模板", businessType = BusinessType.DELETE)
	@DeleteMapping("/{costTemplateIds}")
    public AjaxResult remove(@PathVariable Long[] costTemplateIds)
    {
        return toAjax(ajCostTemplateService.deleteAjCostTemplateByCostTemplateIds(costTemplateIds));
    }
}
