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
import com.hysro.ajkeeping.domain.AjStudentCheckIn;
import com.hysro.ajkeeping.service.IAjStudentCheckInService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生考勤Controller
 * 
 * @author hysro
 * @date 2024-08-04
 */
@RestController
@RequestMapping("/checkin/studentCheckin")
public class AjStudentCheckInController extends BaseController
{
    @Autowired
    private IAjStudentCheckInService ajStudentCheckInService;

    /**
     * 查询学生考勤列表
     */
    @PreAuthorize("@ss.hasPermi('checkin:studentCheckin:list')")
    @GetMapping("/list")
    public TableDataInfo list(AjStudentCheckIn ajStudentCheckIn)
    {
        startPage();
        List<AjStudentCheckIn> list = ajStudentCheckInService.selectAjStudentCheckInList(ajStudentCheckIn);
        return getDataTable(list);
    }

    /**
     * 导出学生考勤列表
     */
    @PreAuthorize("@ss.hasPermi('checkin:studentCheckin:export')")
    @Log(title = "学生考勤", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AjStudentCheckIn ajStudentCheckIn)
    {
        List<AjStudentCheckIn> list = ajStudentCheckInService.selectAjStudentCheckInList(ajStudentCheckIn);
        ExcelUtil<AjStudentCheckIn> util = new ExcelUtil<AjStudentCheckIn>(AjStudentCheckIn.class);
        util.exportExcel(response, list, "学生考勤数据");
    }

    /**
     * 获取学生考勤详细信息
     */
    @PreAuthorize("@ss.hasPermi('checkin:studentCheckin:query')")
    @GetMapping(value = "/{studentCheckInId}")
    public AjaxResult getInfo(@PathVariable("studentCheckInId") Long studentCheckInId)
    {
        return success(ajStudentCheckInService.selectAjStudentCheckInByStudentCheckInId(studentCheckInId));
    }

    /**
     * 新增学生考勤
     */
    @PreAuthorize("@ss.hasPermi('checkin:studentCheckin:add')")
    @Log(title = "学生考勤", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AjStudentCheckIn ajStudentCheckIn)
    {
        return toAjax(ajStudentCheckInService.insertAjStudentCheckIn(ajStudentCheckIn));
    }

    /**
     * 修改学生考勤
     */
    @PreAuthorize("@ss.hasPermi('checkin:studentCheckin:edit')")
    @Log(title = "学生考勤", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AjStudentCheckIn ajStudentCheckIn)
    {
        return toAjax(ajStudentCheckInService.updateAjStudentCheckIn(ajStudentCheckIn));
    }

    /**
     * 删除学生考勤
     */
    @PreAuthorize("@ss.hasPermi('checkin:studentCheckin:remove')")
    @Log(title = "学生考勤", businessType = BusinessType.DELETE)
	@DeleteMapping("/{studentCheckInIds}")
    public AjaxResult remove(@PathVariable Long[] studentCheckInIds)
    {
        return toAjax(ajStudentCheckInService.deleteAjStudentCheckInByStudentCheckInIds(studentCheckInIds));
    }
}
