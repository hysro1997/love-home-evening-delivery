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
import com.hysro.ajkeeping.domain.AjGrade;
import com.hysro.ajkeeping.service.IAjGradeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 年级Controller
 *
 * @author hysro
 * @date 2024-08-04
 */
@RestController
@RequestMapping("/schools/grade")
public class AjGradeController extends BaseController
{
    @Autowired
    private IAjGradeService ajGradeService;

    /**
     * 查询年级列表
     */
    @PreAuthorize("@ss.hasPermi('schools:grade:list')")
    @GetMapping("/list")
    public TableDataInfo list(AjGrade ajGrade)
    {
        startPage();
        List<AjGrade> list = ajGradeService.selectAjGradeList(ajGrade);
        return getDataTable(list);
    }

    /**
     * 导出年级列表
     */
    @PreAuthorize("@ss.hasPermi('schools:grade:export')")
    @Log(title = "年级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AjGrade ajGrade)
    {
        List<AjGrade> list = ajGradeService.selectAjGradeList(ajGrade);
        ExcelUtil<AjGrade> util = new ExcelUtil<AjGrade>(AjGrade.class);
        util.exportExcel(response, list, "年级数据");
    }

    /**
     * 获取年级详细信息
     */
    @PreAuthorize("@ss.hasPermi('schools:grade:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ajGradeService.selectAjGradeById(id));
    }

    /**
     * 新增年级
     */
    @PreAuthorize("@ss.hasPermi('schools:grade:add')")
    @Log(title = "年级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody AjGrade ajGrade)
    {
        return toAjax(ajGradeService.insertAjGrade(ajGrade));
    }

    /**
     * 修改年级
     */
    @PreAuthorize("@ss.hasPermi('schools:grade:edit')")
    @Log(title = "年级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody AjGrade ajGrade)
    {
        return toAjax(ajGradeService.updateAjGrade(ajGrade));
    }

    /**
     * 删除年级
     */
    @PreAuthorize("@ss.hasPermi('schools:grade:remove')")
    @Log(title = "年级", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ajGradeService.deleteAjGradeByIds(ids));
    }
}
