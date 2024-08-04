package com.hysro.ajkeeping.service;

import java.util.List;
import com.hysro.ajkeeping.domain.AjStudentCheckInStatistic;

/**
 * 考勤统计Service接口
 * 
 * @author hysro
 * @date 2024-08-04
 */
public interface IAjStudentCheckInStatisticService 
{
    /**
     * 查询考勤统计
     * 
     * @param id 考勤统计主键
     * @return 考勤统计
     */
    public AjStudentCheckInStatistic selectAjStudentCheckInStatisticById(Long id);

    /**
     * 查询考勤统计列表
     * 
     * @param ajStudentCheckInStatistic 考勤统计
     * @return 考勤统计集合
     */
    public List<AjStudentCheckInStatistic> selectAjStudentCheckInStatisticList(AjStudentCheckInStatistic ajStudentCheckInStatistic);

    /**
     * 新增考勤统计
     * 
     * @param ajStudentCheckInStatistic 考勤统计
     * @return 结果
     */
    public int insertAjStudentCheckInStatistic(AjStudentCheckInStatistic ajStudentCheckInStatistic);

    /**
     * 修改考勤统计
     * 
     * @param ajStudentCheckInStatistic 考勤统计
     * @return 结果
     */
    public int updateAjStudentCheckInStatistic(AjStudentCheckInStatistic ajStudentCheckInStatistic);

    /**
     * 批量删除考勤统计
     * 
     * @param ids 需要删除的考勤统计主键集合
     * @return 结果
     */
    public int deleteAjStudentCheckInStatisticByIds(Long[] ids);

    /**
     * 删除考勤统计信息
     * 
     * @param id 考勤统计主键
     * @return 结果
     */
    public int deleteAjStudentCheckInStatisticById(Long id);
}
