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
import com.hysro.ajkeeping.domain.AjBaseCheckIn;
import com.hysro.ajkeeping.service.IAjBaseCheckInService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考勤总表Controller
 * 
 * @author hysro
 * @date 2024-08-04
 */
@RestController
@RequestMapping("/checkin/baseCheckin")
public class AjBaseCheckInController extends BaseController
{
    @Autowired
    private IAjBaseCheckInService ajBaseCheckInService;

    /**
     * 查询考勤总表列表
     */
    @PreAuthorize("@ss.hasPermi('checkin:baseCheckin:list')")
    @GetMapping("/list")
    public TableDataInfo list(AjBaseCheckIn ajBaseCheckIn)
    {
        startPage();
        List<AjBaseCheckIn> list = ajBaseCheckInService.selectAjBaseCheckInList(ajBaseCheckIn);
        return getDataTable(list);
    }

    /**
     * 导出考勤总表列表
     */
    @PreAuthorize("@ss.hasPermi('checkin:baseCheckin:export')")
    @Log(title = "考勤总表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AjBaseCheckIn ajBaseCheckIn)
    {
        List<AjBaseCheckIn> list = ajBaseCheckInService.selectAjBaseCheckInList(ajBaseCheckIn);
        ExcelUtil<AjBaseCheckIn> util = new ExcelUtil<AjBaseCheckIn>(AjBaseCheckIn.class);
        util.exportExcel(response, list, "考勤总表数据");
    }

    /**
     * 获取考勤总表详细信息
     */
    @PreAuthorize("@ss.hasPermi('checkin:baseCheckin:query')")
    @GetMapping(value = "/{baseCheckInId}")
    public AjaxResult getInfo(@PathVariable("baseCheckInId") Long baseCheckInId)
    {
        return success(ajBaseCheckInService.selectAjBaseCheckInByBaseCheckInId(baseCheckInId));
    }

    /**
     * 新增考勤总表
     */
    @PreAuthorize("@ss.hasPermi('checkin:baseCheckin:add')")
    @Log(title = "考勤总表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AjBaseCheckIn ajBaseCheckIn)
    {
        return toAjax(ajBaseCheckInService.insertAjBaseCheckIn(ajBaseCheckIn));
    }

    /**
     * 修改考勤总表
     */
    @PreAuthorize("@ss.hasPermi('checkin:baseCheckin:edit')")
    @Log(title = "考勤总表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AjBaseCheckIn ajBaseCheckIn)
    {
        return toAjax(ajBaseCheckInService.updateAjBaseCheckIn(ajBaseCheckIn));
    }

    /**
     * 删除考勤总表
     */
    @PreAuthorize("@ss.hasPermi('checkin:baseCheckin:remove')")
    @Log(title = "考勤总表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{baseCheckInIds}")
    public AjaxResult remove(@PathVariable Long[] baseCheckInIds)
    {
        return toAjax(ajBaseCheckInService.deleteAjBaseCheckInByBaseCheckInIds(baseCheckInIds));
    }
}
