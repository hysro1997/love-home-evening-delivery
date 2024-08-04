package com.hysro.ajkeeping.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hysro.ajkeeping.mapper.AjStudentCheckInStatisticMapper;
import com.hysro.ajkeeping.domain.AjStudentCheckInStatistic;
import com.hysro.ajkeeping.service.IAjStudentCheckInStatisticService;

/**
 * 考勤统计Service业务层处理
 * 
 * @author hysro
 * @date 2024-08-04
 */
@Service
public class AjStudentCheckInStatisticServiceImpl implements IAjStudentCheckInStatisticService 
{
    @Autowired
    private AjStudentCheckInStatisticMapper ajStudentCheckInStatisticMapper;

    /**
     * 查询考勤统计
     * 
     * @param id 考勤统计主键
     * @return 考勤统计
     */
    @Override
    public AjStudentCheckInStatistic selectAjStudentCheckInStatisticById(Long id)
    {
        return ajStudentCheckInStatisticMapper.selectAjStudentCheckInStatisticById(id);
    }

    /**
     * 查询考勤统计列表
     * 
     * @param ajStudentCheckInStatistic 考勤统计
     * @return 考勤统计
     */
    @Override
    public List<AjStudentCheckInStatistic> selectAjStudentCheckInStatisticList(AjStudentCheckInStatistic ajStudentCheckInStatistic)
    {
        return ajStudentCheckInStatisticMapper.selectAjStudentCheckInStatisticList(ajStudentCheckInStatistic);
    }

    /**
     * 新增考勤统计
     * 
     * @param ajStudentCheckInStatistic 考勤统计
     * @return 结果
     */
    @Override
    public int insertAjStudentCheckInStatistic(AjStudentCheckInStatistic ajStudentCheckInStatistic)
    {
        return ajStudentCheckInStatisticMapper.insertAjStudentCheckInStatistic(ajStudentCheckInStatistic);
    }

    /**
     * 修改考勤统计
     * 
     * @param ajStudentCheckInStatistic 考勤统计
     * @return 结果
     */
    @Override
    public int updateAjStudentCheckInStatistic(AjStudentCheckInStatistic ajStudentCheckInStatistic)
    {
        return ajStudentCheckInStatisticMapper.updateAjStudentCheckInStatistic(ajStudentCheckInStatistic);
    }

    /**
     * 批量删除考勤统计
     * 
     * @param ids 需要删除的考勤统计主键
     * @return 结果
     */
    @Override
    public int deleteAjStudentCheckInStatisticByIds(Long[] ids)
    {
        return ajStudentCheckInStatisticMapper.deleteAjStudentCheckInStatisticByIds(ids);
    }

    /**
     * 删除考勤统计信息
     * 
     * @param id 考勤统计主键
     * @return 结果
     */
    @Override
    public int deleteAjStudentCheckInStatisticById(Long id)
    {
        return ajStudentCheckInStatisticMapper.deleteAjStudentCheckInStatisticById(id);
    }
}
