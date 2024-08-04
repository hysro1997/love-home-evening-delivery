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
import com.hysro.ajkeeping.domain.AjTeacherCheckInStatistic;
import com.hysro.ajkeeping.service.IAjTeacherCheckInStatisticService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 教师考勤汇总Controller
 * 
 * @author hysro
 * @date 2024-08-04
 */
@RestController
@RequestMapping("/checkin/teacherCheckinStatistic")
public class AjTeacherCheckInStatisticController extends BaseController
{
    @Autowired
    private IAjTeacherCheckInStatisticService ajTeacherCheckInStatisticService;

    /**
     * 查询教师考勤汇总列表
     */
    @PreAuthorize("@ss.hasPermi('checkin:teacherCheckinStatistic:list')")
    @GetMapping("/list")
    public TableDataInfo list(AjTeacherCheckInStatistic ajTeacherCheckInStatistic)
    {
        startPage();
        List<AjTeacherCheckInStatistic> list = ajTeacherCheckInStatisticService.selectAjTeacherCheckInStatisticList(ajTeacherCheckInStatistic);
        return getDataTable(list);
    }

    /**
     * 导出教师考勤汇总列表
     */
    @PreAuthorize("@ss.hasPermi('checkin:teacherCheckinStatistic:export')")
    @Log(title = "教师考勤汇总", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AjTeacherCheckInStatistic ajTeacherCheckInStatistic)
    {
        List<AjTeacherCheckInStatistic> list = ajTeacherCheckInStatisticService.selectAjTeacherCheckInStatisticList(ajTeacherCheckInStatistic);
        ExcelUtil<AjTeacherCheckInStatistic> util = new ExcelUtil<AjTeacherCheckInStatistic>(AjTeacherCheckInStatistic.class);
        util.exportExcel(response, list, "教师考勤汇总数据");
    }

    /**
     * 获取教师考勤汇总详细信息
     */
    @PreAuthorize("@ss.hasPermi('checkin:teacherCheckinStatistic:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ajTeacherCheckInStatisticService.selectAjTeacherCheckInStatisticById(id));
    }

    /**
     * 新增教师考勤汇总
     */
    @PreAuthorize("@ss.hasPermi('checkin:teacherCheckinStatistic:add')")
    @Log(title = "教师考勤汇总", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AjTeacherCheckInStatistic ajTeacherCheckInStatistic)
    {
        return toAjax(ajTeacherCheckInStatisticService.insertAjTeacherCheckInStatistic(ajTeacherCheckInStatistic));
    }

    /**
     * 修改教师考勤汇总
     */
    @PreAuthorize("@ss.hasPermi('checkin:teacherCheckinStatistic:edit')")
    @Log(title = "教师考勤汇总", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AjTeacherCheckInStatistic ajTeacherCheckInStatistic)
    {
        return toAjax(ajTeacherCheckInStatisticService.updateAjTeacherCheckInStatistic(ajTeacherCheckInStatistic));
    }

    /**
     * 删除教师考勤汇总
     */
    @PreAuthorize("@ss.hasPermi('checkin:teacherCheckinStatistic:remove')")
    @Log(title = "教师考勤汇总", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ajTeacherCheckInStatisticService.deleteAjTeacherCheckInStatisticByIds(ids));
    }
}
