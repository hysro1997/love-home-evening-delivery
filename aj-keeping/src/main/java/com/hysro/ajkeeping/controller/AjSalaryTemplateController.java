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
import com.hysro.ajkeeping.domain.AjSalaryTemplate;
import com.hysro.ajkeeping.service.IAjSalaryTemplateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 老师工资模板Controller
 * 
 * @author hysro
 * @date 2024-08-04
 */
@RestController
@RequestMapping("/bill/teacherBillTemplate")
public class AjSalaryTemplateController extends BaseController
{
    @Autowired
    private IAjSalaryTemplateService ajSalaryTemplateService;

    /**
     * 查询老师工资模板列表
     */
    @PreAuthorize("@ss.hasPermi('bill:teacherBillTemplate:list')")
    @GetMapping("/list")
    public TableDataInfo list(AjSalaryTemplate ajSalaryTemplate)
    {
        startPage();
        List<AjSalaryTemplate> list = ajSalaryTemplateService.selectAjSalaryTemplateList(ajSalaryTemplate);
        return getDataTable(list);
    }

    /**
     * 导出老师工资模板列表
     */
    @PreAuthorize("@ss.hasPermi('bill:teacherBillTemplate:export')")
    @Log(title = "老师工资模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AjSalaryTemplate ajSalaryTemplate)
    {
        List<AjSalaryTemplate> list = ajSalaryTemplateService.selectAjSalaryTemplateList(ajSalaryTemplate);
        ExcelUtil<AjSalaryTemplate> util = new ExcelUtil<AjSalaryTemplate>(AjSalaryTemplate.class);
        util.exportExcel(response, list, "老师工资模板数据");
    }

    /**
     * 获取老师工资模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('bill:teacherBillTemplate:query')")
    @GetMapping(value = "/{salaryId}")
    public AjaxResult getInfo(@PathVariable("salaryId") Long salaryId)
    {
        return success(ajSalaryTemplateService.selectAjSalaryTemplateBySalaryId(salaryId));
    }

    /**
     * 新增老师工资模板
     */
    @PreAuthorize("@ss.hasPermi('bill:teacherBillTemplate:add')")
    @Log(title = "老师工资模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AjSalaryTemplate ajSalaryTemplate)
    {
        return toAjax(ajSalaryTemplateService.insertAjSalaryTemplate(ajSalaryTemplate));
    }

    /**
     * 修改老师工资模板
     */
    @PreAuthorize("@ss.hasPermi('bill:teacherBillTemplate:edit')")
    @Log(title = "老师工资模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AjSalaryTemplate ajSalaryTemplate)
    {
        return toAjax(ajSalaryTemplateService.updateAjSalaryTemplate(ajSalaryTemplate));
    }

    /**
     * 删除老师工资模板
     */
    @PreAuthorize("@ss.hasPermi('bill:teacherBillTemplate:remove')")
    @Log(title = "老师工资模板", businessType = BusinessType.DELETE)
	@DeleteMapping("/{salaryIds}")
    public AjaxResult remove(@PathVariable Long[] salaryIds)
    {
        return toAjax(ajSalaryTemplateService.deleteAjSalaryTemplateBySalaryIds(salaryIds));
    }
}
