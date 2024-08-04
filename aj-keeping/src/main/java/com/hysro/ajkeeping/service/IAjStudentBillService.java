package com.hysro.ajkeeping.service;

import java.util.List;
import com.hysro.ajkeeping.domain.AjStudentBill;

/**
 * 学生账单明细Service接口
 * 
 * @author hysro
 * @date 2024-08-04
 */
public interface IAjStudentBillService 
{
    /**
     * 查询学生账单明细
     * 
     * @param id 学生账单明细主键
     * @return 学生账单明细
     */
    public AjStudentBill selectAjStudentBillById(Long id);

    /**
     * 查询学生账单明细列表
     * 
     * @param ajStudentBill 学生账单明细
     * @return 学生账单明细集合
     */
    public List<AjStudentBill> selectAjStudentBillList(AjStudentBill ajStudentBill);

    /**
     * 新增学生账单明细
     * 
     * @param ajStudentBill 学生账单明细
     * @return 结果
     */
    public int insertAjStudentBill(AjStudentBill ajStudentBill);

    /**
     * 修改学生账单明细
     * 
     * @param ajStudentBill 学生账单明细
     * @return 结果
     */
    public int updateAjStudentBill(AjStudentBill ajStudentBill);

    /**
     * 批量删除学生账单明细
     * 
     * @param ids 需要删除的学生账单明细主键集合
     * @return 结果
     */
    public int deleteAjStudentBillByIds(Long[] ids);

    /**
     * 删除学生账单明细信息
     * 
     * @param id 学生账单明细主键
     * @return 结果
     */
    public int deleteAjStudentBillById(Long id);
}
