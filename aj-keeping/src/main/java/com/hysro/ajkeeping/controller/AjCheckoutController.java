package com.hysro.ajkeeping.controller;

import com.hysro.ajkeeping.domain.AjBaseCheckIn;
import com.hysro.ajkeeping.domain.AjBaseCheckInPaymentStatus;
import com.hysro.ajkeeping.domain.AjStudentCheckInStatistic;
import com.hysro.ajkeeping.service.IAjCheckoutService;
import com.hysro.ajkeeping.service.IAjStudentCheckInStatisticService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 结算Controller
 *
 * @author hysro
 * @date 2024-08-19
 */
@RestController
@RequestMapping("/payment/checkout")
public class AjCheckoutController extends BaseController {
    @Autowired
    private IAjCheckoutService ajCheckoutService;
    @Autowired
    private IAjStudentCheckInStatisticService ajStudentCheckInStatisticService;

    /**
     * 查询学生考勤统计详情
     */
    @PreAuthorize("@ss.hasPermi('payment:checkout:list')")
    @GetMapping("/studentStatistic")
    public AjaxResult studentStatistic(AjStudentCheckInStatistic ajStudentCheckInStatistic)
    {
        return success(ajStudentCheckInStatisticService.selectAjStudentCheckInStatisticList(ajStudentCheckInStatistic));
    }

    /**
     * 查询考勤总表列表
     */
    @PreAuthorize("@ss.hasPermi('payment:checkout:list')")
    @GetMapping("/listBaseCheckin")
    public TableDataInfo list(BaseEntity baseEntity)
    {
        startPage();
        List<AjBaseCheckInPaymentStatus> list = ajCheckoutService.listBaseCheckin(baseEntity);
        return getDataTable(list);
    }

    /**
     * 查询考勤总表列表
     */
    @PreAuthorize("@ss.hasPermi('payment:checkout:list')")
    @GetMapping("/listStudents/{baseCheckInId}/{studentGrade}")
    public AjaxResult listStudents(@PathVariable("baseCheckInId")Long baseCheckInId, @PathVariable("studentGrade")String studentGrade)
    {
        return success(ajCheckoutService.listStudents(baseCheckInId, studentGrade));
    }

    /**
     * 依据考勤总表id统计考勤数据
     */
    @PreAuthorize("@ss.hasPermi('payment:checkout:edit')")
    @PostMapping("/statistic")
    public AjaxResult statistic(@NotNull @RequestBody AjBaseCheckIn baseCheckIn)
    {
        ajStudentCheckInStatisticService.calculateStudentCheckInStatistic(baseCheckIn.getBaseCheckInId());
        return success();
    }
}
