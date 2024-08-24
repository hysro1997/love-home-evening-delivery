package com.hysro.ajkeeping.controller;

import com.hysro.ajkeeping.domain.AjGrade;
import com.hysro.ajkeeping.domain.AjStudentCheckIn;
import com.hysro.ajkeeping.service.IAjGradeService;
import com.hysro.ajkeeping.service.IAjStudentCheckInService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
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
@RequestMapping("/checkin/checkinStudents")
public class AjCheckinStudentsController extends BaseController {

    @Autowired
    private IAjGradeService ajGradeService;
    @Autowired
    private IAjStudentCheckInService ajStudentCheckInService;

    /**
     * 查询年级列表
     */
    @PreAuthorize("@ss.hasPermi('checkin:checkinStudents:list')")
    @GetMapping("/list")
    public AjaxResult list(AjGrade ajGrade)
    {
        return success(ajGradeService.selectAjGradeList(ajGrade));
    }

    /**
     * 查询学生考勤列表
     */
    @PreAuthorize("@ss.hasPermi('checkin:checkinStudents:list')")
    @PostMapping("/listStudents")
    public AjaxResult listStudents(AjStudentCheckIn ajStudentCheckIn)
    {
        return success(ajStudentCheckInService.selectAjStudentCheckInList(ajStudentCheckIn));
    }

    /**
     * 修改学生考勤
     */
    @PreAuthorize("@ss.hasPermi('checkin:checkinStudents:list')")
    @Log(title = "学生签到", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AjStudentCheckIn ajStudentCheckIn)
    {
        ajStudentCheckIn.setCheckInDatetime(new Date());
        return toAjax(ajStudentCheckInService.updateAjStudentCheckIn(ajStudentCheckIn));
    }

    /**
     * 修改学生考勤
     */
    @PreAuthorize("@ss.hasPermi('checkin:checkinStudents:list')")
    @Log(title = "学生签到", businessType = BusinessType.UPDATE)
    @PutMapping("/editList")
    public AjaxResult editList(@RequestBody List<AjStudentCheckIn> ajStudentCheckIns)
    {
        for (AjStudentCheckIn ajStudentCheckIn: ajStudentCheckIns){
            ajStudentCheckIn.setCheckInDatetime(new Date());
            ajStudentCheckInService.updateAjStudentCheckIn(ajStudentCheckIn);
        }
        return success();
    }
}
