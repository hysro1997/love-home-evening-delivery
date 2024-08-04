package com.hysro.ajkeeping.mapper;

import java.util.List;
import com.hysro.ajkeeping.domain.AjSalaryBill;

/**
 * 工资明细Mapper接口
 * 
 * @author hysro
 * @date 2024-08-04
 */
public interface AjSalaryBillMapper 
{
    /**
     * 查询工资明细
     * 
     * @param id 工资明细主键
     * @return 工资明细
     */
    public AjSalaryBill selectAjSalaryBillById(Long id);

    /**
     * 查询工资明细列表
     * 
     * @param ajSalaryBill 工资明细
     * @return 工资明细集合
     */
    public List<AjSalaryBill> selectAjSalaryBillList(AjSalaryBill ajSalaryBill);

    /**
     * 新增工资明细
     * 
     * @param ajSalaryBill 工资明细
     * @return 结果
     */
    public int insertAjSalaryBill(AjSalaryBill ajSalaryBill);

    /**
     * 修改工资明细
     * 
     * @param ajSalaryBill 工资明细
     * @return 结果
     */
    public int updateAjSalaryBill(AjSalaryBill ajSalaryBill);

    /**
     * 删除工资明细
     * 
     * @param id 工资明细主键
     * @return 结果
     */
    public int deleteAjSalaryBillById(Long id);

    /**
     * 批量删除工资明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAjSalaryBillByIds(Long[] ids);
}
