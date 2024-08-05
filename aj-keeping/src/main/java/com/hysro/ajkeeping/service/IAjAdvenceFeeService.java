package com.hysro.ajkeeping.service;

import java.util.List;
import com.hysro.ajkeeping.domain.AjAdvenceFee;

/**
 * 预收费Service接口
 * 
 * @author hysro
 * @date 2024-08-05
 */
public interface IAjAdvenceFeeService 
{
    /**
     * 查询预收费
     * 
     * @param id 预收费主键
     * @return 预收费
     */
    public AjAdvenceFee selectAjAdvenceFeeById(Long id);

    /**
     * 查询预收费列表
     * 
     * @param ajAdvenceFee 预收费
     * @return 预收费集合
     */
    public List<AjAdvenceFee> selectAjAdvenceFeeList(AjAdvenceFee ajAdvenceFee);

    /**
     * 新增预收费
     * 
     * @param ajAdvenceFee 预收费
     * @return 结果
     */
    public int insertAjAdvenceFee(AjAdvenceFee ajAdvenceFee);

    /**
     * 修改预收费
     * 
     * @param ajAdvenceFee 预收费
     * @return 结果
     */
    public int updateAjAdvenceFee(AjAdvenceFee ajAdvenceFee);

    /**
     * 批量删除预收费
     * 
     * @param ids 需要删除的预收费主键集合
     * @return 结果
     */
    public int deleteAjAdvenceFeeByIds(Long[] ids);

    /**
     * 删除预收费信息
     * 
     * @param id 预收费主键
     * @return 结果
     */
    public int deleteAjAdvenceFeeById(Long id);
}
