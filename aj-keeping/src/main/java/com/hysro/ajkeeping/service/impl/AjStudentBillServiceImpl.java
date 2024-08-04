package com.hysro.ajkeeping.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hysro.ajkeeping.mapper.AjStudentBillMapper;
import com.hysro.ajkeeping.domain.AjStudentBill;
import com.hysro.ajkeeping.service.IAjStudentBillService;

/**
 * 学生账单明细Service业务层处理
 * 
 * @author hysro
 * @date 2024-08-04
 */
@Service
public class AjStudentBillServiceImpl implements IAjStudentBillService 
{
    @Autowired
    private AjStudentBillMapper ajStudentBillMapper;

    /**
     * 查询学生账单明细
     * 
     * @param id 学生账单明细主键
     * @return 学生账单明细
     */
    @Override
    public AjStudentBill selectAjStudentBillById(Long id)
    {
        return ajStudentBillMapper.selectAjStudentBillById(id);
    }

    /**
     * 查询学生账单明细列表
     * 
     * @param ajStudentBill 学生账单明细
     * @return 学生账单明细
     */
    @Override
    public List<AjStudentBill> selectAjStudentBillList(AjStudentBill ajStudentBill)
    {
        return ajStudentBillMapper.selectAjStudentBillList(ajStudentBill);
    }

    /**
     * 新增学生账单明细
     * 
     * @param ajStudentBill 学生账单明细
     * @return 结果
     */
    @Override
    public int insertAjStudentBill(AjStudentBill ajStudentBill)
    {
        return ajStudentBillMapper.insertAjStudentBill(ajStudentBill);
    }

    /**
     * 修改学生账单明细
     * 
     * @param ajStudentBill 学生账单明细
     * @return 结果
     */
    @Override
    public int updateAjStudentBill(AjStudentBill ajStudentBill)
    {
        return ajStudentBillMapper.updateAjStudentBill(ajStudentBill);
    }

    /**
     * 批量删除学生账单明细
     * 
     * @param ids 需要删除的学生账单明细主键
     * @return 结果
     */
    @Override
    public int deleteAjStudentBillByIds(Long[] ids)
    {
        return ajStudentBillMapper.deleteAjStudentBillByIds(ids);
    }

    /**
     * 删除学生账单明细信息
     * 
     * @param id 学生账单明细主键
     * @return 结果
     */
    @Override
    public int deleteAjStudentBillById(Long id)
    {
        return ajStudentBillMapper.deleteAjStudentBillById(id);
    }
}
