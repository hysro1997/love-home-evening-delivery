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
import com.hysro.ajkeeping.domain.AjTeacherCheckIn;
import com.hysro.ajkeeping.service.IAjTeacherCheckInService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 员工考勤Controller
 * 
 * @author hysro
 * @date 2024-08-04
 */
@RestController
@RequestMapping("/checkin/teacherCheckin")
public class AjTeacherCheckInController extends BaseController
{
    @Autowired
    private IAjTeacherCheckInService ajTeacherCheckInService;

    /**
     * 查询员工考勤列表
     */
    @PreAuthorize("@ss.hasPermi('checkin:teacherCheckin:list')")
    @GetMapping("/list")
    public TableDataInfo list(AjTeacherCheckIn ajTeacherCheckIn)
    {
        startPage();
        List<AjTeacherCheckIn> list = ajTeacherCheckInService.selectAjTeacherCheckInList(ajTeacherCheckIn);
        return getDataTable(list);
    }

    /**
     * 导出员工考勤列表
     */
    @PreAuthorize("@ss.hasPermi('checkin:teacherCheckin:export')")
    @Log(title = "员工考勤", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AjTeacherCheckIn ajTeacherCheckIn)
    {
        List<AjTeacherCheckIn> list = ajTeacherCheckInService.selectAjTeacherCheckInList(ajTeacherCheckIn);
        ExcelUtil<AjTeacherCheckIn> util = new ExcelUtil<AjTeacherCheckIn>(AjTeacherCheckIn.class);
        util.exportExcel(response, list, "员工考勤数据");
    }

    /**
     * 获取员工考勤详细信息
     */
    @PreAuthorize("@ss.hasPermi('checkin:teacherCheckin:query')")
    @GetMapping(value = "/{teacherCheckInId}")
    public AjaxResult getInfo(@PathVariable("teacherCheckInId") Long teacherCheckInId)
    {
        return success(ajTeacherCheckInService.selectAjTeacherCheckInByTeacherCheckInId(teacherCheckInId));
    }

    /**
     * 新增员工考勤
     */
    @PreAuthorize("@ss.hasPermi('checkin:teacherCheckin:add')")
    @Log(title = "员工考勤", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AjTeacherCheckIn ajTeacherCheckIn)
    {
        return toAjax(ajTeacherCheckInService.insertAjTeacherCheckIn(ajTeacherCheckIn));
    }

    /**
     * 修改员工考勤
     */
    @PreAuthorize("@ss.hasPermi('checkin:teacherCheckin:edit')")
    @Log(title = "员工考勤", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AjTeacherCheckIn ajTeacherCheckIn)
    {
        return toAjax(ajTeacherCheckInService.updateAjTeacherCheckIn(ajTeacherCheckIn));
    }

    /**
     * 删除员工考勤
     */
    @PreAuthorize("@ss.hasPermi('checkin:teacherCheckin:remove')")
    @Log(title = "员工考勤", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teacherCheckInIds}")
    public AjaxResult remove(@PathVariable Long[] teacherCheckInIds)
    {
        return toAjax(ajTeacherCheckInService.deleteAjTeacherCheckInByTeacherCheckInIds(teacherCheckInIds));
    }
}
