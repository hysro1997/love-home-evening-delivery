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
import com.hysro.ajkeeping.domain.AjSalaryBill;
import com.hysro.ajkeeping.service.IAjSalaryBillService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工资明细Controller
 * 
 * @author hysro
 * @date 2024-08-04
 */
@RestController
@RequestMapping("/payment/teacherSalary")
public class AjSalaryBillController extends BaseController
{
    @Autowired
    private IAjSalaryBillService ajSalaryBillService;

    /**
     * 查询工资明细列表
     */
    @PreAuthorize("@ss.hasPermi('payment:teacherSalary:list')")
    @GetMapping("/list")
    public TableDataInfo list(AjSalaryBill ajSalaryBill)
    {
        startPage();
        List<AjSalaryBill> list = ajSalaryBillService.selectAjSalaryBillList(ajSalaryBill);
        return getDataTable(list);
    }

    /**
     * 导出工资明细列表
     */
    @PreAuthorize("@ss.hasPermi('payment:teacherSalary:export')")
    @Log(title = "工资明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AjSalaryBill ajSalaryBill)
    {
        List<AjSalaryBill> list = ajSalaryBillService.selectAjSalaryBillList(ajSalaryBill);
        ExcelUtil<AjSalaryBill> util = new ExcelUtil<AjSalaryBill>(AjSalaryBill.class);
        util.exportExcel(response, list, "工资明细数据");
    }

    /**
     * 获取工资明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('payment:teacherSalary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ajSalaryBillService.selectAjSalaryBillById(id));
    }

    /**
     * 新增工资明细
     */
    @PreAuthorize("@ss.hasPermi('payment:teacherSalary:add')")
    @Log(title = "工资明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AjSalaryBill ajSalaryBill)
    {
        return toAjax(ajSalaryBillService.insertAjSalaryBill(ajSalaryBill));
    }

    /**
     * 修改工资明细
     */
    @PreAuthorize("@ss.hasPermi('payment:teacherSalary:edit')")
    @Log(title = "工资明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AjSalaryBill ajSalaryBill)
    {
        return toAjax(ajSalaryBillService.updateAjSalaryBill(ajSalaryBill));
    }

    /**
     * 删除工资明细
     */
    @PreAuthorize("@ss.hasPermi('payment:teacherSalary:remove')")
    @Log(title = "工资明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ajSalaryBillService.deleteAjSalaryBillByIds(ids));
    }
}
