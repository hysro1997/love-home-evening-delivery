package com.hysro.ajkeeping.service.impl;

import java.util.Date;
import java.util.List;
import com.hysro.ajkeeping.domain.AjTeacherInfo;
import com.hysro.ajkeeping.mapper.AjTeacherCheckInMapper;
import com.hysro.ajkeeping.mapper.AjTeacherInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hysro.ajkeeping.mapper.AjTeacherCheckInStatisticMapper;
import com.hysro.ajkeeping.domain.AjTeacherCheckInStatistic;
import com.hysro.ajkeeping.service.IAjTeacherCheckInStatisticService;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private AjTeacherCheckInMapper ajTeacherCheckInMapper;
    @Autowired
    private AjTeacherInfoMapper ajTeacherInfoMapper;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void calculateTeacherCheckInStatistic(Date checkInBeginDate, Date checkInEndDate) {
        List<AjTeacherInfo> teacherInfoList = ajTeacherInfoMapper.selectAjTeacherSimpleInfoList();
        for (AjTeacherInfo teacherInfo: teacherInfoList){
            this.calculateTeacherCheckInStatistic(checkInBeginDate,checkInEndDate,teacherInfo.getTeacherId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void calculateTeacherCheckInStatistic(Date checkInBeginDate, Date checkInEndDate, Long teacherId) {
        AjTeacherCheckInStatistic statistic = new AjTeacherCheckInStatistic();
        statistic.setCheckInTimes(ajTeacherCheckInMapper.countTeacherCheckinOrLeaveTimes(checkInBeginDate,checkInEndDate,teacherId,1));
        statistic.setLeaveDays(ajTeacherCheckInMapper.countTeacherCheckinOrLeaveTimes(checkInBeginDate,checkInEndDate,teacherId,2));
        statistic.setOnDutyTimes(ajTeacherCheckInMapper.countTeacherOnDutyTimes(checkInBeginDate,checkInEndDate,teacherId));
        AjTeacherCheckInStatistic originStatistic = ajTeacherCheckInStatisticMapper.selectAjTeacherCheckInStatisticByTeacherIdAndCheckinBeginEndDate(checkInBeginDate,checkInEndDate,teacherId);
        if (originStatistic == null){
            statistic.setTeacherId(teacherId);
            statistic.setTeacherName(ajTeacherInfoMapper.selectAjTeacherInfoByTeacherId(teacherId).getTeacherName());
            statistic.setCheckInBeginDate(checkInBeginDate);
            statistic.setCheckInEndDate(checkInEndDate);
            statistic.setBillStatus(0);
            statistic.setPaymentStatus(0);
            ajTeacherCheckInStatisticMapper.insertAjTeacherCheckInStatistic(statistic);
        } else {
            if (originStatistic.getPaymentStatus() == 0){
                statistic.setId(originStatistic.getId());
                ajTeacherCheckInStatisticMapper.updateAjTeacherCheckInStatistic(statistic);
            }
        }

    }
}
