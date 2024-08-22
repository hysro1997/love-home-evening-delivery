package com.hysro.ajkeeping.mapper;

import java.util.List;
import com.hysro.ajkeeping.domain.AjStudentBill;

/**
 * 学生账单明细Mapper接口
 *
 * @author hysro
 * @date 2024-08-05
 */
public interface AjStudentBillMapper
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
     * 数学生账单明细
     *
     * @param checkInBaseId 学生账单考勤表id
     * @return 结果
     */
    public int countAjStudentBill(Long checkInBaseId);

    /**
     * 数学生账单明细
     *
     * @param checkInBaseId 学生账单考勤表id
     * @return 结果
     */
    public int countAjStudentBillWithConditionPay(Long checkInBaseId);

    /**
     * 修改学生账单明细
     *
     * @param ajStudentBill 学生账单明细
     * @return 结果
     */
    public int updateAjStudentBill(AjStudentBill ajStudentBill);

    /**
     * 删除学生账单明细
     *
     * @param id 学生账单明细主键
     * @return 结果
     */
    public int deleteAjStudentBillById(Long id);

    /**
     * 批量删除学生账单明细
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAjStudentBillByIds(Long[] ids);
}
