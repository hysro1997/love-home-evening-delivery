package com.hysro.ajkeeping.mapper;

import java.util.List;
import com.hysro.ajkeeping.domain.AjStudentCheckInStatistic;
import org.apache.ibatis.annotations.Param;

/**
 * 考勤统计Mapper接口
 *
 * @author hysro
 * @date 2024-08-04
 */
public interface AjStudentCheckInStatisticMapper
{
    /**
     * 查询考勤统计
     *
     * @param id 考勤统计主键
     * @return 考勤统计
     */
    public AjStudentCheckInStatistic selectAjStudentCheckInStatisticById(Long id);

    /**
     * 依据考勤总表id和学生id查询考勤统计
     *
     * @param baseCheckInId 考勤总表id
     * @param studentId 学生id
     * @return 考勤统计
     */
    public AjStudentCheckInStatistic selectAjStudentCheckInStatisticByBaseChekinIdAndStudentId(@Param("baseCheckInId") Long baseCheckInId,@Param("studentId") Long studentId);

    /**
     * 查询考勤统计列表
     *
     * @param ajStudentCheckInStatistic 考勤统计
     * @return 考勤统计集合
     */
    public List<AjStudentCheckInStatistic> selectAjStudentCheckInStatisticList(AjStudentCheckInStatistic ajStudentCheckInStatistic);

    /**
     * 数出考勤统计
     *
     * @param baseCheckInId 考勤总表id
     * @return 结果
     */
    public int countAjStudentCheckInStatistic(Long baseCheckInId);

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
     * 删除考勤统计
     *
     * @param id 考勤统计主键
     * @return 结果
     */
    public int deleteAjStudentCheckInStatisticById(Long id);

    /**
     * 批量删除考勤统计
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAjStudentCheckInStatisticByIds(Long[] ids);
}
