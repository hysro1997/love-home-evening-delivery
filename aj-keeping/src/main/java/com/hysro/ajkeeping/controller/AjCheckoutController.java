package com.hysro.ajkeeping.controller;

import com.hysro.ajkeeping.domain.AjBaseCheckInPaymentStatus;
import com.hysro.ajkeeping.domain.AjHomoInBaseCheckIn;
import com.hysro.ajkeeping.service.IAjCheckoutService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
