package com.hysro.ajkeeping.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hysro.ajkeeping.mapper.AjSalaryBillMapper;
import com.hysro.ajkeeping.domain.AjSalaryBill;
import com.hysro.ajkeeping.service.IAjSalaryBillService;

/**
 * 工资明细Service业务层处理
 * 
 * @author hysro
 * @date 2024-08-04
 */
@Service
public class AjSalaryBillServiceImpl implements IAjSalaryBillService 
{
    @Autowired
    private AjSalaryBillMapper ajSalaryBillMapper;

    /**
     * 查询工资明细
     * 
     * @param id 工资明细主键
     * @return 工资明细
     */
    @Override
    public AjSalaryBill selectAjSalaryBillById(Long id)
    {
        return ajSalaryBillMapper.selectAjSalaryBillById(id);
    }

    /**
     * 查询工资明细列表
     * 
     * @param ajSalaryBill 工资明细
     * @return 工资明细
     */
    @Override
    public List<AjSalaryBill> selectAjSalaryBillList(AjSalaryBill ajSalaryBill)
    {
        return ajSalaryBillMapper.selectAjSalaryBillList(ajSalaryBill);
    }

    /**
     * 新增工资明细
     * 
     * @param ajSalaryBill 工资明细
     * @return 结果
     */
    @Override
    public int insertAjSalaryBill(AjSalaryBill ajSalaryBill)
    {
        return ajSalaryBillMapper.insertAjSalaryBill(ajSalaryBill);
    }

    /**
     * 修改工资明细
     * 
     * @param ajSalaryBill 工资明细
     * @return 结果
     */
    @Override
    public int updateAjSalaryBill(AjSalaryBill ajSalaryBill)
    {
        return ajSalaryBillMapper.updateAjSalaryBill(ajSalaryBill);
    }

    /**
     * 批量删除工资明细
     * 
     * @param ids 需要删除的工资明细主键
     * @return 结果
     */
    @Override
    public int deleteAjSalaryBillByIds(Long[] ids)
    {
        return ajSalaryBillMapper.deleteAjSalaryBillByIds(ids);
    }

    /**
     * 删除工资明细信息
     * 
     * @param id 工资明细主键
     * @return 结果
     */
    @Override
    public int deleteAjSalaryBillById(Long id)
    {
        return ajSalaryBillMapper.deleteAjSalaryBillById(id);
    }
}
