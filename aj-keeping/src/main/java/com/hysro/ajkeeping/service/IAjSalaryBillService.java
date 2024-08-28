package com.hysro.ajkeeping.service;

import java.util.List;
import com.hysro.ajkeeping.domain.AjSalaryBill;

/**
 * 工资明细Service接口
 *
 * @author hysro
 * @date 2024-08-04
 */
public interface IAjSalaryBillService
{
    /**
     * 查询工资明细
     *
     * @param id 工资明细主键
     * @return 工资明细
     */
    public AjSalaryBill selectAjSalaryBillById(Long id);

    /**
     * 生成考勤数据
     *
     * @param ajSalaryBill 教师id、考勤起止日期
     * @return 考勤情况
     */
    public AjSalaryBill selectTeacherCheckinStatistic(AjSalaryBill ajSalaryBill);

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
     * 批量删除工资明细
     *
     * @param ids 需要删除的工资明细主键集合
     * @return 结果
     */
    public int deleteAjSalaryBillByIds(Long[] ids);

    /**
     * 删除工资明细信息
     *
     * @param id 工资明细主键
     * @return 结果
     */
    public int deleteAjSalaryBillById(Long id);
}
