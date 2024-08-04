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
import com.hysro.ajkeeping.domain.AjStudentCheckInStatistic;
import com.hysro.ajkeeping.service.IAjStudentCheckInStatisticService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考勤统计Controller
 * 
 * @author hysro
 * @date 2024-08-04
 */
@RestController
@RequestMapping("/checkin/studentCheckinStatistic")
public class AjStudentCheckInStatisticController extends BaseController
{
    @Autowired
    private IAjStudentCheckInStatisticService ajStudentCheckInStatisticService;

    /**
     * 查询考勤统计列表
     */
    @PreAuthorize("@ss.hasPermi('checkin:studentCheckinStatistic:list')")
    @GetMapping("/list")
    public TableDataInfo list(AjStudentCheckInStatistic ajStudentCheckInStatistic)
    {
        startPage();
        List<AjStudentCheckInStatistic> list = ajStudentCheckInStatisticService.selectAjStudentCheckInStatisticList(ajStudentCheckInStatistic);
        return getDataTable(list);
    }

    /**
     * 导出考勤统计列表
     */
    @PreAuthorize("@ss.hasPermi('checkin:studentCheckinStatistic:export')")
    @Log(title = "考勤统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AjStudentCheckInStatistic ajStudentCheckInStatistic)
    {
        List<AjStudentCheckInStatistic> list = ajStudentCheckInStatisticService.selectAjStudentCheckInStatisticList(ajStudentCheckInStatistic);
        ExcelUtil<AjStudentCheckInStatistic> util = new ExcelUtil<AjStudentCheckInStatistic>(AjStudentCheckInStatistic.class);
        util.exportExcel(response, list, "考勤统计数据");
    }

    /**
     * 获取考勤统计详细信息
     */
    @PreAuthorize("@ss.hasPermi('checkin:studentCheckinStatistic:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ajStudentCheckInStatisticService.selectAjStudentCheckInStatisticById(id));
    }

    /**
     * 新增考勤统计
     */
    @PreAuthorize("@ss.hasPermi('checkin:studentCheckinStatistic:add')")
    @Log(title = "考勤统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AjStudentCheckInStatistic ajStudentCheckInStatistic)
    {
        return toAjax(ajStudentCheckInStatisticService.insertAjStudentCheckInStatistic(ajStudentCheckInStatistic));
    }

    /**
     * 修改考勤统计
     */
    @PreAuthorize("@ss.hasPermi('checkin:studentCheckinStatistic:edit')")
    @Log(title = "考勤统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AjStudentCheckInStatistic ajStudentCheckInStatistic)
    {
        return toAjax(ajStudentCheckInStatisticService.updateAjStudentCheckInStatistic(ajStudentCheckInStatistic));
    }

    /**
     * 删除考勤统计
     */
    @PreAuthorize("@ss.hasPermi('checkin:studentCheckinStatistic:remove')")
    @Log(title = "考勤统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ajStudentCheckInStatisticService.deleteAjStudentCheckInStatisticByIds(ids));
    }
}
