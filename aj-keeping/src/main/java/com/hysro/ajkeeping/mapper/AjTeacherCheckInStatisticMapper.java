package com.hysro.ajkeeping.mapper;

import java.util.Date;
import java.util.List;
import com.hysro.ajkeeping.domain.AjTeacherCheckInStatistic;
import org.apache.ibatis.annotations.Param;

/**
 * 教师考勤汇总Mapper接口
 *
 * @author hysro
 * @date 2024-08-04
 */
public interface AjTeacherCheckInStatisticMapper
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
     * 依据考勤总表id和教师id查询考勤统计
     *
     * @param checkInBeginDate 开始日期
     * @param checkInEndDate 结束日期
     * @param teacherId 教师id
     * @return 考勤统计
     */
    public AjTeacherCheckInStatistic selectAjTeacherCheckInStatisticByTeacherIdAndCheckinBeginEndDate(@Param("checkInBeginDate") Date checkInBeginDate,@Param("checkInEndDate") Date checkInEndDate,@Param("teacherId") Long teacherId);

    /**
     * 修改教师考勤汇总
     *
     * @param ajTeacherCheckInStatistic 教师考勤汇总
     * @return 结果
     */
    public int updateAjTeacherCheckInStatistic(AjTeacherCheckInStatistic ajTeacherCheckInStatistic);

    /**
     * 删除教师考勤汇总
     *
     * @param id 教师考勤汇总主键
     * @return 结果
     */
    public int deleteAjTeacherCheckInStatisticById(Long id);

    /**
     * 批量删除教师考勤汇总
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAjTeacherCheckInStatisticByIds(Long[] ids);
}
