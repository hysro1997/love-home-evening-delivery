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
import com.hysro.ajkeeping.domain.AjAdvenceFee;
import com.hysro.ajkeeping.service.IAjAdvenceFeeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 预收费Controller
 * 
 * @author hysro
 * @date 2024-08-05
 */
@RestController
@RequestMapping("/payment/advanceFee")
public class AjAdvenceFeeController extends BaseController
{
    @Autowired
    private IAjAdvenceFeeService ajAdvenceFeeService;

    /**
     * 查询预收费列表
     */
    @PreAuthorize("@ss.hasPermi('payment:advanceFee:list')")
    @GetMapping("/list")
    public TableDataInfo list(AjAdvenceFee ajAdvenceFee)
    {
        startPage();
        List<AjAdvenceFee> list = ajAdvenceFeeService.selectAjAdvenceFeeList(ajAdvenceFee);
        return getDataTable(list);
    }

    /**
     * 导出预收费列表
     */
    @PreAuthorize("@ss.hasPermi('payment:advanceFee:export')")
    @Log(title = "预收费", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AjAdvenceFee ajAdvenceFee)
    {
        List<AjAdvenceFee> list = ajAdvenceFeeService.selectAjAdvenceFeeList(ajAdvenceFee);
        ExcelUtil<AjAdvenceFee> util = new ExcelUtil<AjAdvenceFee>(AjAdvenceFee.class);
        util.exportExcel(response, list, "预收费数据");
    }

    /**
     * 获取预收费详细信息
     */
    @PreAuthorize("@ss.hasPermi('payment:advanceFee:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ajAdvenceFeeService.selectAjAdvenceFeeById(id));
    }

    /**
     * 新增预收费
     */
    @PreAuthorize("@ss.hasPermi('payment:advanceFee:add')")
    @Log(title = "预收费", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AjAdvenceFee ajAdvenceFee)
    {
        return toAjax(ajAdvenceFeeService.insertAjAdvenceFee(ajAdvenceFee));
    }

    /**
     * 修改预收费
     */
    @PreAuthorize("@ss.hasPermi('payment:advanceFee:edit')")
    @Log(title = "预收费", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AjAdvenceFee ajAdvenceFee)
    {
        return toAjax(ajAdvenceFeeService.updateAjAdvenceFee(ajAdvenceFee));
    }

    /**
     * 删除预收费
     */
    @PreAuthorize("@ss.hasPermi('payment:advanceFee:remove')")
    @Log(title = "预收费", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ajAdvenceFeeService.deleteAjAdvenceFeeByIds(ids));
    }
}
