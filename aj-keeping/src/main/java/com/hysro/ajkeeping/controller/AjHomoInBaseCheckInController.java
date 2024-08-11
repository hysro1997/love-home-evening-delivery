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
import com.hysro.ajkeeping.domain.AjHomoInBaseCheckIn;
import com.hysro.ajkeeping.service.IAjHomoInBaseCheckInService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 在考勤中的老师与学生Controller
 *
 * @author hysro
 * @date 2024-08-08
 */
@RestController
@RequestMapping("/checkin/homoInBaseCheckin")
public class AjHomoInBaseCheckInController extends BaseController
{
    @Autowired
    private IAjHomoInBaseCheckInService ajHomoInBaseCheckInService;

    /**
     * 查询在考勤中的老师与学生列表
     */
    @PreAuthorize("@ss.hasPermi('checkin:homoInBaseCheckin:list')")
    @GetMapping("/list")
    public TableDataInfo list(AjHomoInBaseCheckIn ajHomoInBaseCheckIn)
    {
        startPage();
        List<AjHomoInBaseCheckIn> list = ajHomoInBaseCheckInService.selectAjHomoInBaseCheckInList(ajHomoInBaseCheckIn);
        return getDataTable(list);
    }

    /**
     * 导出在考勤中的老师与学生列表
     */
    @PreAuthorize("@ss.hasPermi('checkin:homoInBaseCheckin:export')")
    @Log(title = "在考勤中的老师与学生", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AjHomoInBaseCheckIn ajHomoInBaseCheckIn)
    {
        List<AjHomoInBaseCheckIn> list = ajHomoInBaseCheckInService.selectAjHomoInBaseCheckInList(ajHomoInBaseCheckIn);
        ExcelUtil<AjHomoInBaseCheckIn> util = new ExcelUtil<AjHomoInBaseCheckIn>(AjHomoInBaseCheckIn.class);
        util.exportExcel(response, list, "在考勤中的老师与学生数据");
    }

    /**
     * 获取在考勤中的老师与学生详细信息
     */
    @PreAuthorize("@ss.hasPermi('checkin:homoInBaseCheckin:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ajHomoInBaseCheckInService.selectAjHomoInBaseCheckInById(id));
    }

    /**
     * 新增在考勤中的老师与学生
     */
    @PreAuthorize("@ss.hasPermi('checkin:homoInBaseCheckin:add')")
    @Log(title = "在考勤中的老师与学生", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AjHomoInBaseCheckIn ajHomoInBaseCheckIn)
    {
        return toAjax(ajHomoInBaseCheckInService.insertAjHomoInBaseCheckIn(ajHomoInBaseCheckIn));
    }

    /**
     * 修改在考勤中的老师与学生
     */
    @PreAuthorize("@ss.hasPermi('checkin:homoInBaseCheckin:edit')")
    @Log(title = "在考勤中的老师与学生", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AjHomoInBaseCheckIn ajHomoInBaseCheckIn)
    {
        return toAjax(ajHomoInBaseCheckInService.updateAjHomoInBaseCheckIn(ajHomoInBaseCheckIn));
    }

    /**
     * 删除在考勤中的老师与学生
     */
    @PreAuthorize("@ss.hasPermi('checkin:homoInBaseCheckin:remove')")
    @Log(title = "在考勤中的老师与学生", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ajHomoInBaseCheckInService.deleteAjHomoInBaseCheckInByIds(ids));
    }
}
