package com.hysro.ajkeeping.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.hysro.ajkeeping.domain.AjStudentInfo;
import com.hysro.ajkeeping.service.IAjStudentInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生信息Controller
 *
 * @author aj
 * @date 2024-08-04
 */
@RestController
@RequestMapping("/info/studentInfo")
public class AjStudentInfoController extends BaseController
{
    @Autowired
    private IAjStudentInfoService ajStudentInfoService;

    /**
     *
     * 获取基础信息，学校列表
     */
    @PreAuthorize("@ss.hasPermi('info:studentInfo:list')")
    @GetMapping("/initSchool")
    public AjaxResult initSchoolInfo(){
        return success(ajStudentInfoService.selectAjSchools());
    }

    /**
     *
     * 获取基础信息，年级列表
     */
    @PreAuthorize("@ss.hasPermi('info:studentInfo:list')")
    @GetMapping("/initGrade")
    public AjaxResult initGradeInfo(){
        return success(ajStudentInfoService.selectAjGrades());
    }

    /**
     * 查询学生信息列表
     */
    @PreAuthorize("@ss.hasPermi('info:studentInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(AjStudentInfo ajStudentInfo)
    {
        startPage();
        List<AjStudentInfo> list = ajStudentInfoService.selectAjStudentInfoList(ajStudentInfo);
        return getDataTable(list);
    }

    /**
     * 导出学生信息列表
     */
    @PreAuthorize("@ss.hasPermi('info:studentInfo:export')")
    @Log(title = "学生信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AjStudentInfo ajStudentInfo)
    {
        List<AjStudentInfo> list = ajStudentInfoService.selectAjStudentInfoList(ajStudentInfo);
        ExcelUtil<AjStudentInfo> util = new ExcelUtil<AjStudentInfo>(AjStudentInfo.class);
        util.exportExcel(response, list, "学生信息数据");
    }

    /**
     * 获取学生信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('info:studentInfo:query')")
    @GetMapping(value = "/{studentId}")
    public AjaxResult getInfo(@PathVariable("studentId") Long studentId)
    {
        return success(ajStudentInfoService.selectAjStudentInfoByStudentId(studentId));
    }

    /**
     * 新增学生信息
     */
    @PreAuthorize("@ss.hasPermi('info:studentInfo:add')")
    @Log(title = "学生信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody AjStudentInfo ajStudentInfo)
    {
        return success(ajStudentInfoService.insertAjStudentInfo(ajStudentInfo));
    }

    /**
     * 修改学生信息
     */
    @PreAuthorize("@ss.hasPermi('info:studentInfo:edit')")
    @Log(title = "学生信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody AjStudentInfo ajStudentInfo)
    {
        return success(ajStudentInfoService.updateAjStudentInfo(ajStudentInfo));
    }

    /**
     * 学生退学/复学
     */
    @PreAuthorize("@ss.hasPermi('info:studentInfo:edit')")
    @Log(title = "学生信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updateStudentStatus/{studentIds}")
    public AjaxResult updateStudentStatus(@PathVariable Long[] studentIds)
    {
        return toAjax(ajStudentInfoService.updateStudentStatus(studentIds));
    }

    /**
     * 删除学生信息
     */
    @PreAuthorize("@ss.hasPermi('info:studentInfo:remove')")
    @Log(title = "学生信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{studentIds}")
    public AjaxResult remove(@PathVariable Long[] studentIds)
    {
        return toAjax(ajStudentInfoService.deleteAjStudentInfoByStudentIds(studentIds));
    }
}
