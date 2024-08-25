package com.hysro.ajkeeping.controller;

import com.hysro.ajkeeping.domain.AjAdvenceFee;
import com.hysro.ajkeeping.domain.AjBaseCheckIn;
import com.hysro.ajkeeping.domain.AjHomoInBaseCheckIn;
import com.hysro.ajkeeping.domain.AjStudentInfo;
import com.hysro.ajkeeping.service.IAjAdvenceFeeService;
import com.hysro.ajkeeping.service.IAjBaseCheckInService;
import com.hysro.ajkeeping.service.IAjHomoInBaseCheckInService;
import com.hysro.ajkeeping.service.IAjStudentInfoService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生管理Controller
 *
 * @author hysro
 * @date 2024-08-22
 */
@RestController
@RequestMapping("/info/manageStudent")
public class AjManageStudentController extends BaseController {
    @Autowired
    private IAjStudentInfoService ajStudentInfoService;
    @Autowired
    private IAjBaseCheckInService ajBaseCheckInService;
    @Autowired
    private IAjHomoInBaseCheckInService ajHomoInBaseCheckInService;
    @Autowired
    private IAjAdvenceFeeService ajAdvenceFeeService;

    /**
     *
     * 获取基础信息，学校列表
     */
    @PreAuthorize("@ss.hasPermi('info:manageStudent:list')")
    @GetMapping("/initSchool")
    public AjaxResult initSchoolInfo(){
        return success(ajStudentInfoService.selectAjSchools());
    }

    /**
     *
     * 获取基础信息，年级列表
     */
    @PreAuthorize("@ss.hasPermi('info:manageStudent:list')")
    @GetMapping("/initGrade")
    public AjaxResult initGradeInfo(){
        return success(ajStudentInfoService.selectAjGrades());
    }

    /**
     * 查询学生信息列表
     */
    @PreAuthorize("@ss.hasPermi('info:manageStudent:list')")
    @GetMapping("/listStudents")
    public TableDataInfo list(AjStudentInfo ajStudentInfo)
    {
        startPage();
        List<AjStudentInfo> list = ajStudentInfoService.selectAjStudentInfoList(ajStudentInfo);
        return getDataTable(list);
    }
    /**
     * 获取学生信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('info:manageStudent:query')")
    @GetMapping(value = "/studentDetail/{studentId}")
    public AjaxResult getInfo(@PathVariable("studentId") Long studentId)
    {
        return success(ajStudentInfoService.selectAjStudentInfoByStudentId(studentId));
    }

    /**
     * 新增学生信息
     */
    @PreAuthorize("@ss.hasPermi('info:manageStudent:add')")
    @Log(title = "学生信息", businessType = BusinessType.INSERT)
    @PostMapping("/addStudent")
    public AjaxResult add(@Validated @RequestBody AjStudentInfo ajStudentInfo)
    {
        return success(ajStudentInfoService.insertAjStudentInfo(ajStudentInfo));
    }

    /**
     * 修改学生信息
     */
    @PreAuthorize("@ss.hasPermi('info:manageStudent:edit')")
    @Log(title = "学生信息", businessType = BusinessType.UPDATE)
    @PutMapping("/alterStudent")
    public AjaxResult edit(@Validated @RequestBody AjStudentInfo ajStudentInfo)
    {
        return success(ajStudentInfoService.updateAjStudentInfo(ajStudentInfo));
    }

    /**
     * 查询考勤总表列表
     */
    @PreAuthorize("@ss.hasPermi('info:manageStudent:list')")
    @GetMapping("/listBaseCheckIn")
    public TableDataInfo list(AjBaseCheckIn ajBaseCheckIn)
    {
        startPage();
        List<AjBaseCheckIn> list = ajBaseCheckInService.selectAjBaseCheckInList(ajBaseCheckIn);
        return getDataTable(list);
    }

    /**
     * 查询在考勤中的老师与学生列表
     */
    @PreAuthorize("@ss.hasPermi('info:manageStudent:list')")
    @GetMapping("/listHomo")
    public TableDataInfo list(AjHomoInBaseCheckIn ajHomoInBaseCheckIn)
    {
        startPage();
        List<AjHomoInBaseCheckIn> list = ajHomoInBaseCheckInService.selectAjHomoInBaseCheckInList(ajHomoInBaseCheckIn);
        return getDataTable(list);
    }

    /**
     * 新增在考勤中的老师与学生
     */
    @PreAuthorize("@ss.hasPermi('info:manageStudent:add')")
    @Log(title = "在考勤中的老师与学生", businessType = BusinessType.INSERT)
    @PostMapping("/addHomo")
    public AjaxResult add(@RequestBody AjHomoInBaseCheckIn ajHomoInBaseCheckIn)
    {
        return toAjax(ajHomoInBaseCheckInService.insertAjHomoInBaseCheckIn(ajHomoInBaseCheckIn));
    }

    /**
     * 删除在考勤中的老师与学生
     */
    @PreAuthorize("@ss.hasPermi('info:manageStudent:remove')")
    @Log(title = "在考勤中的老师与学生", businessType = BusinessType.DELETE)
    @DeleteMapping("/delHomo/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ajHomoInBaseCheckInService.deleteAjHomoInBaseCheckInByIds(ids));
    }

    /**
     * 查询预收费列表
     */
    @PreAuthorize("@ss.hasPermi('info:manageStudent:list')")
    @GetMapping("/listAdvanceFee")
    public TableDataInfo list(AjAdvenceFee ajAdvenceFee)
    {
        startPage();
        List<AjAdvenceFee> list = ajAdvenceFeeService.selectAjAdvenceFeeList(ajAdvenceFee);
        return getDataTable(list);
    }

    /**
     * 新增预收费
     */
    @PreAuthorize("@ss.hasPermi('info:manageStudent:add')")
    @Log(title = "预收费", businessType = BusinessType.INSERT)
    @PostMapping("/addAdvanceFee")
    public AjaxResult add(@RequestBody AjAdvenceFee ajAdvenceFee)
    {
        return toAjax(ajAdvenceFeeService.insertAjAdvenceFee(ajAdvenceFee));
    }

    /**
     * 修改预收费
     */
    @PreAuthorize("@ss.hasPermi('info:manageStudent:edit')")
    @Log(title = "预收费", businessType = BusinessType.UPDATE)
    @PutMapping("/alterAdvanceFee")
    public AjaxResult edit(@RequestBody AjAdvenceFee ajAdvenceFee)
    {
        return toAjax(ajAdvenceFeeService.updateAjAdvenceFee(ajAdvenceFee));
    }

    /**
     * 获取在学的学生
     */
    @PreAuthorize("@ss.hasPermi('info:manageStudent:list')")
    @GetMapping("/schoolStudents")
    public AjaxResult getInSchoolStudents(){
        return success(ajBaseCheckInService.selectSchoolStudents());
    }

    /**
     * 获取考勤总表详细信息
     */
    @PreAuthorize("@ss.hasPermi('info:manageStudent:query')")
    @GetMapping(value = "/baseCheckIn/{baseCheckInId}")
    public AjaxResult getBaseCheckInInfo(@PathVariable("baseCheckInId") Long baseCheckInId)
    {
        return success(ajBaseCheckInService.selectAjBaseCheckInByBaseCheckInId(baseCheckInId));
    }

    /**
     * 修改考勤总表
     */
    @PreAuthorize("@ss.hasPermi('info:manageStudent:edit')")
    @Log(title = "考勤总表", businessType = BusinessType.UPDATE)
    @PutMapping("/alterBaseCheckIn")
    public AjaxResult editHomoCheckIn(@RequestBody AjBaseCheckIn ajBaseCheckIn)
    {
        return toAjax(ajBaseCheckInService.updateAjBaseCheckInOnlyHomo(ajBaseCheckIn));
    }
}
