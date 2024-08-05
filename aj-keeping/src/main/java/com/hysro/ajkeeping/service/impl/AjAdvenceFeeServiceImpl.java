package com.hysro.ajkeeping.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hysro.ajkeeping.mapper.AjAdvenceFeeMapper;
import com.hysro.ajkeeping.domain.AjAdvenceFee;
import com.hysro.ajkeeping.service.IAjAdvenceFeeService;

/**
 * 预收费Service业务层处理
 * 
 * @author hysro
 * @date 2024-08-05
 */
@Service
public class AjAdvenceFeeServiceImpl implements IAjAdvenceFeeService 
{
    @Autowired
    private AjAdvenceFeeMapper ajAdvenceFeeMapper;

    /**
     * 查询预收费
     * 
     * @param id 预收费主键
     * @return 预收费
     */
    @Override
    public AjAdvenceFee selectAjAdvenceFeeById(Long id)
    {
        return ajAdvenceFeeMapper.selectAjAdvenceFeeById(id);
    }

    /**
     * 查询预收费列表
     * 
     * @param ajAdvenceFee 预收费
     * @return 预收费
     */
    @Override
    public List<AjAdvenceFee> selectAjAdvenceFeeList(AjAdvenceFee ajAdvenceFee)
    {
        return ajAdvenceFeeMapper.selectAjAdvenceFeeList(ajAdvenceFee);
    }

    /**
     * 新增预收费
     * 
     * @param ajAdvenceFee 预收费
     * @return 结果
     */
    @Override
    public int insertAjAdvenceFee(AjAdvenceFee ajAdvenceFee)
    {
        return ajAdvenceFeeMapper.insertAjAdvenceFee(ajAdvenceFee);
    }

    /**
     * 修改预收费
     * 
     * @param ajAdvenceFee 预收费
     * @return 结果
     */
    @Override
    public int updateAjAdvenceFee(AjAdvenceFee ajAdvenceFee)
    {
        return ajAdvenceFeeMapper.updateAjAdvenceFee(ajAdvenceFee);
    }

    /**
     * 批量删除预收费
     * 
     * @param ids 需要删除的预收费主键
     * @return 结果
     */
    @Override
    public int deleteAjAdvenceFeeByIds(Long[] ids)
    {
        return ajAdvenceFeeMapper.deleteAjAdvenceFeeByIds(ids);
    }

    /**
     * 删除预收费信息
     * 
     * @param id 预收费主键
     * @return 结果
     */
    @Override
    public int deleteAjAdvenceFeeById(Long id)
    {
        return ajAdvenceFeeMapper.deleteAjAdvenceFeeById(id);
    }
}
