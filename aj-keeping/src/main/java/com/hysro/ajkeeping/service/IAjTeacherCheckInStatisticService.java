package com.hysro.ajkeeping.service;

import java.util.Date;
import java.util.List;
import com.hysro.ajkeeping.domain.AjTeacherCheckInStatistic;

/**
 * 教师考勤汇总Service接口
 *
 * @author hysro
 * @date 2024-08-04
 */
public interface IAjTeacherCheckInStatisticService
{
    /**
     * 查询教师考勤汇总
     *
     * @param id 教师考勤汇总主键
     * @return 教师考勤汇总
     */
    public AjTeacherCheckInStatistic selectAjTeacherCheckInStatisticById(Long id);

    /**
     * 查询教师考勤汇总列表
     *
     * @param ajTeacherCheckInStatistic 教师考勤汇总
     * @return 教师考勤汇总集合
     */
    public List<AjTeacherCheckInStatistic> selectAjTeacherCheckInStatisticList(AjTeacherCheckInStatistic ajTeacherCheckInStatistic);

    /**
     * 新增教师考勤汇总
     *
     * @param ajTeacherCheckInStatistic 教师考勤汇总
     * @return 结果
     */
    public int insertAjTeacherCheckInStatistic(AjTeacherCheckInStatistic ajTeacherCheckInStatistic);

    /**
     * 修改教师考勤汇总
     *
     * @param ajTeacherCheckInStatistic 教师考勤汇总
     * @return 结果
     */
    public int updateAjTeacherCheckInStatistic(AjTeacherCheckInStatistic ajTeacherCheckInStatistic);

    /**
     * 批量删除教师考勤汇总
     *
     * @param ids 需要删除的教师考勤汇总主键集合
     * @return 结果
     */
    public int deleteAjTeacherCheckInStatisticByIds(Long[] ids);

    /**
     * 删除教师考勤汇总信息
     *
     * @param id 教师考勤汇总主键
     * @return 结果
     */
    public int deleteAjTeacherCheckInStatisticById(Long id);

    /**
     * 统计考勤表教师考勤情况
     *
     * @param checkInBeginDate 开始日期
     * @param checkInEndDate 结束日期
     */
    public void calculateTeacherCheckInStatistic(Date checkInBeginDate, Date checkInEndDate);


    /**
     * 统计考勤表教师考勤情况
     *
     * @param checkInBeginDate 开始日期
     * @param checkInEndDate 结束日期
     * @param teacherId 教师id
     */
    public void calculateTeacherCheckInStatistic(Date checkInBeginDate, Date checkInEndDate, Long teacherId);
}
