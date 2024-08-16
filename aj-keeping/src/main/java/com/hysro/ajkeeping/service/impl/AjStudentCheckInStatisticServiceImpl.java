package com.hysro.ajkeeping.service.impl;

import java.util.List;

import com.hysro.ajkeeping.domain.AjBaseCheckIn;
import com.hysro.ajkeeping.domain.AjStudentCheckIn;
import com.hysro.ajkeeping.mapper.AjBaseCheckInMapper;
import com.hysro.ajkeeping.mapper.AjStudentCheckInMapper;
import com.hysro.ajkeeping.mapper.AjStudentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hysro.ajkeeping.mapper.AjStudentCheckInStatisticMapper;
import com.hysro.ajkeeping.domain.AjStudentCheckInStatistic;
import com.hysro.ajkeeping.service.IAjStudentCheckInStatisticService;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private AjBaseCheckInMapper ajBaseCheckInMapper;
    @Autowired
    private AjStudentCheckInMapper ajStudentCheckInMapper;
    @Autowired
    private AjStudentInfoMapper ajStudentInfoMapper;

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

    /**
     * 统计考勤表学生考勤情况
     *
     * @param baseCheckInId 考勤总表id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void calculateStudentCheckInStatistic(Long baseCheckInId) {
        List<AjStudentCheckIn> studentCheckInList = ajStudentCheckInMapper.selectDistincAjStudentCheckInListByBaseCkeckinId(baseCheckInId);
        for (AjStudentCheckIn studentCheckIn: studentCheckInList){
            this.calculateStudentCheckInStatistic(baseCheckInId,studentCheckIn.getStudentId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void calculateStudentCheckInStatistic(Long baseCheckInId, Long studentId) {
        AjStudentCheckInStatistic statistic = new AjStudentCheckInStatistic();
        statistic.setCheckInTimes(ajStudentCheckInMapper.countStudentCheckinOrLeaveTimes(baseCheckInId,studentId,1));
        statistic.setLeaveDays(ajStudentCheckInMapper.countStudentCheckinOrLeaveTimes(baseCheckInId,studentId,2));
        AjStudentCheckInStatistic originStatistic = ajStudentCheckInStatisticMapper.selectAjStudentCheckInStatisticByBaseChekinIdAndStudentId(baseCheckInId, studentId);
        if (originStatistic == null){
            AjBaseCheckIn ajBaseCheckIn = ajBaseCheckInMapper.selectAjBaseCheckInByBaseCheckInId(baseCheckInId);
            statistic.setBaseCheckInId(baseCheckInId);
            statistic.setCheckInBeginDate(ajBaseCheckIn.getBaseCheckInBeginDate());
            statistic.setCheckInEndDate(ajBaseCheckIn.getBaseCheckInEndDate());
            statistic.setCheckInSumDays(ajBaseCheckIn.getBaseCheckInSumDays());
            statistic.setStudentId(studentId);
            statistic.setStudentName(ajStudentInfoMapper.selectAjStudentInfoByStudentId(studentId).getStudentName());
            statistic.setBillStatus(0);
            statistic.setPaymentStatus(0);
            ajStudentCheckInStatisticMapper.insertAjStudentCheckInStatistic(statistic);
        } else {
            if (originStatistic.getPaymentStatus() == 0){
                statistic.setId(originStatistic.getId());
                ajStudentCheckInStatisticMapper.updateAjStudentCheckInStatistic(statistic);
            }
        }
    }

}
