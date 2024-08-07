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
import com.hysro.ajkeeping.domain.AjTeacherInfo;
import com.hysro.ajkeeping.service.IAjTeacherInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 教师信息Controller
 *
 * @author ruoyi
 * @date 2024-08-04
 */
@RestController
@RequestMapping("/info/teacherInfo")
public class AjTeacherInfoController extends BaseController
{
    @Autowired
    private IAjTeacherInfoService ajTeacherInfoService;

    /**
     * 查询教师信息列表
     */
    @PreAuthorize("@ss.hasPermi('info:teacherInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(AjTeacherInfo ajTeacherInfo)
    {
        startPage();
        List<AjTeacherInfo> list = ajTeacherInfoService.selectAjTeacherInfoList(ajTeacherInfo);
        return getDataTable(list);
    }

    /**
     * 导出教师信息列表
     */
    @PreAuthorize("@ss.hasPermi('info:teacherInfo:export')")
    @Log(title = "教师信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AjTeacherInfo ajTeacherInfo)
    {
        List<AjTeacherInfo> list = ajTeacherInfoService.selectAjTeacherInfoList(ajTeacherInfo);
        ExcelUtil<AjTeacherInfo> util = new ExcelUtil<AjTeacherInfo>(AjTeacherInfo.class);
        util.exportExcel(response, list, "教师信息数据");
    }

    /**
     * 获取教师信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('info:teacherInfo:query')")
    @GetMapping(value = "/{teacherId}")
    public AjaxResult getInfo(@PathVariable("teacherId") Long teacherId)
    {
        return success(ajTeacherInfoService.selectAjTeacherInfoByTeacherId(teacherId));
    }

    /**
     * 新增教师信息
     */
    @PreAuthorize("@ss.hasPermi('info:teacherInfo:add')")
    @Log(title = "教师信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody AjTeacherInfo ajTeacherInfo)
    {
        return toAjax(ajTeacherInfoService.insertAjTeacherInfo(ajTeacherInfo));
    }

    /**
     * 修改教师信息
     */
    @PreAuthorize("@ss.hasPermi('info:teacherInfo:edit')")
    @Log(title = "教师信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody AjTeacherInfo ajTeacherInfo)
    {
        return toAjax(ajTeacherInfoService.updateAjTeacherInfo(ajTeacherInfo));
    }

    /**
     * 删除教师信息
     */
    @PreAuthorize("@ss.hasPermi('info:teacherInfo:remove')")
    @Log(title = "教师信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teacherIds}")
    public AjaxResult remove(@PathVariable Long[] teacherIds)
    {
        return toAjax(ajTeacherInfoService.deleteAjTeacherInfoByTeacherIds(teacherIds));
    }
}
