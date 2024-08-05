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
import com.hysro.ajkeeping.domain.AjStudentBill;
import com.hysro.ajkeeping.service.IAjStudentBillService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生账单明细Controller
 *
 * @author hysro
 * @date 2024-08-05
 */
@RestController
@RequestMapping("/payment/studentBill")
public class AjStudentBillController extends BaseController
{
    @Autowired
    private IAjStudentBillService ajStudentBillService;

    /**
     * 查询学生账单明细列表
     */
    @PreAuthorize("@ss.hasPermi('payment:studentBill:list')")
    @GetMapping("/list")
    public TableDataInfo list(AjStudentBill ajStudentBill)
    {
        startPage();
        List<AjStudentBill> list = ajStudentBillService.selectAjStudentBillList(ajStudentBill);
        return getDataTable(list);
    }

    /**
     * 导出学生账单明细列表
     */
    @PreAuthorize("@ss.hasPermi('payment:studentBill:export')")
    @Log(title = "学生账单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AjStudentBill ajStudentBill)
    {
        List<AjStudentBill> list = ajStudentBillService.selectAjStudentBillList(ajStudentBill);
        ExcelUtil<AjStudentBill> util = new ExcelUtil<AjStudentBill>(AjStudentBill.class);
        util.exportExcel(response, list, "学生账单明细数据");
    }

    /**
     * 获取学生账单明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('payment:studentBill:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ajStudentBillService.selectAjStudentBillById(id));
    }

    /**
     * 新增学生账单明细
     */
    @PreAuthorize("@ss.hasPermi('payment:studentBill:add')")
    @Log(title = "学生账单明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AjStudentBill ajStudentBill)
    {
        return toAjax(ajStudentBillService.insertAjStudentBill(ajStudentBill));
    }

    /**
     * 修改学生账单明细
     */
    @PreAuthorize("@ss.hasPermi('payment:studentBill:edit')")
    @Log(title = "学生账单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AjStudentBill ajStudentBill)
    {
        return toAjax(ajStudentBillService.updateAjStudentBill(ajStudentBill));
    }

    /**
     * 删除学生账单明细
     */
    @PreAuthorize("@ss.hasPermi('payment:studentBill:remove')")
    @Log(title = "学生账单明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ajStudentBillService.deleteAjStudentBillByIds(ids));
    }
}
