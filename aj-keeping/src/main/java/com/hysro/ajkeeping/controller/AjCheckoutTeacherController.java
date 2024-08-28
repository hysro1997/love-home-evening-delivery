package com.hysro.ajkeeping.controller;

import com.hysro.ajkeeping.domain.AjSalaryBill;
import com.hysro.ajkeeping.domain.AjTeacherInfo;
import com.hysro.ajkeeping.service.IAjSalaryBillService;
import com.hysro.ajkeeping.service.IAjTeacherInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 结算老师工资Controller
 *
 * @author hysro
 * @date 2024-08-28
 */
@RestController
@RequestMapping("/payment/checkoutTeacher")
public class AjCheckoutTeacherController extends BaseController {
    @Autowired
    private IAjTeacherInfoService teacherInfoService;
    @Autowired
    private IAjSalaryBillService ajSalaryBillService;

    /**
     * 查询教师信息列表
     */
    @PreAuthorize("@ss.hasPermi('payment:checkoutTeacher:list')")
    @GetMapping("/listTeachers")
    public TableDataInfo listTeachers(AjTeacherInfo ajTeacherInfo)
    {
        startPage();
        List<AjTeacherInfo> list = teacherInfoService.selectAjTeacherInfoListOrderByStatus(ajTeacherInfo);
        return getDataTable(list);
    }

    /**
     * 查询工资明细列表
     */
    @PreAuthorize("@ss.hasPermi('payment:checkoutTeacher:list')")
    @GetMapping("/listTeacherSalary")
    public TableDataInfo listTeacherSalary(AjSalaryBill ajSalaryBill)
    {
        startPage();
        List<AjSalaryBill> list = ajSalaryBillService.selectAjSalaryBillList(ajSalaryBill);
        return getDataTable(list);
    }
}
