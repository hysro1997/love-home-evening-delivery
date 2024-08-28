package com.hysro.ajkeeping.controller;

import com.hysro.ajkeeping.domain.*;
import com.hysro.ajkeeping.service.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    private IAjStudentCheckInService ajStudentCheckInService;
    @Autowired
    private IAjCostTemplateService ajCostTemplateService;
    @Autowired
    private IAjStudentBillService ajStudentBillService;
    @Autowired
    private IAjAdvenceFeeService ajAdvenceFeeService;

    /**
     * 查询预收费列表
     */
    @PreAuthorize("@ss.hasPermi('payment:checkout:list')")
    @GetMapping("/advanceFee")
    public TableDataInfo list(AjAdvenceFee ajAdvenceFee)
    {
        startPage();
        List<AjAdvenceFee> list = ajAdvenceFeeService.selectAjAdvenceFeeList(ajAdvenceFee);
        return getDataTable(list);
    }

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
     * 查询学生出勤日期详情
     */
    @PreAuthorize("@ss.hasPermi('payment:checkout:list')")
    @GetMapping("/studentCheckInDays")
    public AjaxResult studentCheckInDays(AjStudentCheckIn ajStudentCheckIn)
    {
        return success(ajStudentCheckInService.selectAjStudentCheckInListNoOrder(ajStudentCheckIn));
    }

    /**
     * 查询收费模板列表
     */
    @PreAuthorize("@ss.hasPermi('payment:checkout:list')")
    @GetMapping("/getCostTemplate")
    public AjaxResult getCostTemplate()
    {
        return success(ajCostTemplateService.selectSimpleAjCostTemplateList());
    }

    /**
     * 获取学生费用模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('payment:checkout:list')")
    @GetMapping(value = "/getCostTemplate/{costTemplateId}")
    public AjaxResult getCostTemplateInfo(@PathVariable("costTemplateId") Long costTemplateId)
    {
        return success(ajCostTemplateService.selectAjCostTemplateByCostTemplateId(costTemplateId));
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
     * 查询考勤学生列表
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

    /**
     * 新增学生账单明细
     */
    @PreAuthorize("@ss.hasPermi('payment:checkout:add')")
    @Log(title = "学生账单明细", businessType = BusinessType.INSERT)
    @PostMapping("/studentBill")
    public AjaxResult add(@RequestBody AjStudentBill ajStudentBill,@RequestBody AjCostTemplate ajCostTemplate) throws Exception
    {
        return success(ajCheckoutService.checkOut(ajStudentBill, ajCostTemplate));
    }

    /**
     * 修改学生账单明细
     */
    @PreAuthorize("@ss.hasPermi('payment:checkout:edit')")
    @Log(title = "学生账单明细", businessType = BusinessType.UPDATE)
    @PutMapping("/pay")
    public AjaxResult edit(@RequestBody AjStudentBill ajStudentBill)
    {
        return success(ajCheckoutService.pay(ajStudentBill));
    }

    /**
     * 查询学生账单明细列表
     */
    @PreAuthorize("@ss.hasPermi('payment:checkout:list')")
    @GetMapping("/studentPayDetail")
    public AjaxResult list(AjStudentBill ajStudentBill)
    {
        return success(ajStudentBillService.selectAjStudentBillList(ajStudentBill));
    }
}
