package com.hysro.ajkeeping.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hysro.ajkeeping.mapper.AjTeacherCheckInStatisticMapper;
import com.hysro.ajkeeping.domain.AjTeacherCheckInStatistic;
import com.hysro.ajkeeping.service.IAjTeacherCheckInStatisticService;

/**
 * 教师考勤汇总Service业务层处理
 * 
 * @author hysro
 * @date 2024-08-04
 */
@Service
public class AjTeacherCheckInStatisticServiceImpl implements IAjTeacherCheckInStatisticService 
{
    @Autowired
    private AjTeacherCheckInStatisticMapper ajTeacherCheckInStatisticMapper;

    /**
     * 查询教师考勤汇总
     * 
     * @param id 教师考勤汇总主键
     * @return 教师考勤汇总
     */
    @Override
    public AjTeacherCheckInStatistic selectAjTeacherCheckInStatisticById(Long id)
    {
        return ajTeacherCheckInStatisticMapper.selectAjTeacherCheckInStatisticById(id);
    }

    /**
     * 查询教师考勤汇总列表
     * 
     * @param ajTeacherCheckInStatistic 教师考勤汇总
     * @return 教师考勤汇总
     */
    @Override
    public List<AjTeacherCheckInStatistic> selectAjTeacherCheckInStatisticList(AjTeacherCheckInStatistic ajTeacherCheckInStatistic)
    {
        return ajTeacherCheckInStatisticMapper.selectAjTeacherCheckInStatisticList(ajTeacherCheckInStatistic);
    }

    /**
     * 新增教师考勤汇总
     * 
     * @param ajTeacherCheckInStatistic 教师考勤汇总
     * @return 结果
     */
    @Override
    public int insertAjTeacherCheckInStatistic(AjTeacherCheckInStatistic ajTeacherCheckInStatistic)
    {
        return ajTeacherCheckInStatisticMapper.insertAjTeacherCheckInStatistic(ajTeacherCheckInStatistic);
    }

    /**
     * 修改教师考勤汇总
     * 
     * @param ajTeacherCheckInStatistic 教师考勤汇总
     * @return 结果
     */
    @Override
    public int updateAjTeacherCheckInStatistic(AjTeacherCheckInStatistic ajTeacherCheckInStatistic)
    {
        return ajTeacherCheckInStatisticMapper.updateAjTeacherCheckInStatistic(ajTeacherCheckInStatistic);
    }

    /**
     * 批量删除教师考勤汇总
     * 
     * @param ids 需要删除的教师考勤汇总主键
     * @return 结果
     */
    @Override
    public int deleteAjTeacherCheckInStatisticByIds(Long[] ids)
    {
        return ajTeacherCheckInStatisticMapper.deleteAjTeacherCheckInStatisticByIds(ids);
    }

    /**
     * 删除教师考勤汇总信息
     * 
     * @param id 教师考勤汇总主键
     * @return 结果
     */
    @Override
    public int deleteAjTeacherCheckInStatisticById(Long id)
    {
        return ajTeacherCheckInStatisticMapper.deleteAjTeacherCheckInStatisticById(id);
    }
}
