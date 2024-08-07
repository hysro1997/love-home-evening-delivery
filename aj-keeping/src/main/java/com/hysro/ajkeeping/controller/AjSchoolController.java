package com.hysro.ajkeeping.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.hysro.ajkeeping.domain.AjSchool;
import com.hysro.ajkeeping.service.IAjSchoolService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学校Controller
 *
 * @author hysro
 * @date 2024-08-04
 */
@RestController
@RequestMapping("/schools/school")
public class AjSchoolController extends BaseController
{
    @Autowired
    private IAjSchoolService ajSchoolService;

    /**
     * 查询学校列表
     */
    @PreAuthorize("@ss.hasPermi('schools:school:list')")
    @GetMapping("/list")
    public TableDataInfo list(AjSchool ajSchool)
    {
        startPage();
        List<AjSchool> list = ajSchoolService.selectAjSchoolList(ajSchool);
        return getDataTable(list);
    }

    /**
     * 导出学校列表
     */
    @PreAuthorize("@ss.hasPermi('schools:school:export')")
    @Log(title = "学校", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AjSchool ajSchool)
    {
        List<AjSchool> list = ajSchoolService.selectAjSchoolList(ajSchool);
        ExcelUtil<AjSchool> util = new ExcelUtil<AjSchool>(AjSchool.class);
        util.exportExcel(response, list, "学校数据");
    }

    /**
     * 获取学校详细信息
     */
    @PreAuthorize("@ss.hasPermi('schools:school:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ajSchoolService.selectAjSchoolById(id));
    }

    /**
     * 新增学校
     */
    @PreAuthorize("@ss.hasPermi('schools:school:add')")
    @Log(title = "学校", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody AjSchool ajSchool)
    {
        return toAjax(ajSchoolService.insertAjSchool(ajSchool));
    }

    /**
     * 修改学校
     */
    @PreAuthorize("@ss.hasPermi('schools:school:edit')")
    @Log(title = "学校", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody AjSchool ajSchool)
    {
        return toAjax(ajSchoolService.updateAjSchool(ajSchool));
    }

    /**
     * 删除学校
     */
    @PreAuthorize("@ss.hasPermi('schools:school:remove')")
    @Log(title = "学校", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ajSchoolService.deleteAjSchoolByIds(ids));
    }
}
