package com.hysro.ajkeeping.controller;

import com.hysro.ajkeeping.domain.AjTeacherCheckIn;
import com.hysro.ajkeeping.service.IAjTeacherCheckInService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 签到学生Controller
 *
 * @author hysro
 * @date 2024-08-15
 */
@RestController
@RequestMapping("/checkin/checkinTeachers")
public class AjCheckinTeachersController extends BaseController {
    @Autowired
    private IAjTeacherCheckInService ajTeacherCheckInService;

    /**
     * 查询员工考勤列表
     */
    @PreAuthorize("@ss.hasPermi('checkin:checkinTeachers:list')")
    @GetMapping("/list")
    public TableDataInfo list(AjTeacherCheckIn ajTeacherCheckIn)
    {
        startPage();
        List<AjTeacherCheckIn> list = ajTeacherCheckInService.selectAjTeacherCheckInList(ajTeacherCheckIn);
        return getDataTable(list);
    }

    /**
     * 修改员工考勤
     */
    @PreAuthorize("@ss.hasPermi('checkin:checkinTeachers:list')")
    @Log(title = "员工考勤", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AjTeacherCheckIn ajTeacherCheckIn)
    {
        ajTeacherCheckIn.setCheckInDatetime(new Date());
        return toAjax(ajTeacherCheckInService.updateAjTeacherCheckIn(ajTeacherCheckIn));
    }
}
