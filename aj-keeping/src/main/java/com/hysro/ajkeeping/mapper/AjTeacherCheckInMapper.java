package com.hysro.ajkeeping.mapper;

import java.util.Date;
import java.util.List;
import com.hysro.ajkeeping.domain.AjTeacherCheckIn;
import org.apache.ibatis.annotations.Param;

/**
 * 员工考勤Mapper接口
 *
 * @author hysro
 * @date 2024-08-04
 */
public interface AjTeacherCheckInMapper
{
    /**
     * 查询员工考勤
     *
     * @param teacherCheckInId 员工考勤主键
     * @return 员工考勤
     */
    public AjTeacherCheckIn selectAjTeacherCheckInByTeacherCheckInId(Long teacherCheckInId);

    /**
     * 查询员工考勤列表
     *
     * @param ajTeacherCheckIn 员工考勤
     * @return 员工考勤集合
     */
    public List<AjTeacherCheckIn> selectAjTeacherCheckInList(AjTeacherCheckIn ajTeacherCheckIn);

    /**
     * 新增员工考勤
     *
     * @param ajTeacherCheckIn 员工考勤
     * @return 结果
     */
    public int insertAjTeacherCheckIn(AjTeacherCheckIn ajTeacherCheckIn);

    /**
     * 修改员工考勤
     *
     * @param ajTeacherCheckIn 员工考勤
     * @return 结果
     */
    public int updateAjTeacherCheckIn(AjTeacherCheckIn ajTeacherCheckIn);

    /**
     * 删除员工考勤
     *
     * @param teacherCheckInId 员工考勤主键
     * @return 结果
     */
    public int deleteAjTeacherCheckInByTeacherCheckInId(Long teacherCheckInId);

    /**
     * 批量删除员工考勤
     *
     * @param teacherCheckInIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAjTeacherCheckInByTeacherCheckInIds(Long[] teacherCheckInIds);

    /**
     * 计算教师考勤次数
     *
     * @param checkInBeginDate 开始日期
     * @param checkInEndDate 结束日期
     * @param teacherId 教师id
     * @param checkInStatus 考勤状态
     * @return 次数
     */
    public int countTeacherCheckinOrLeaveTimes(@Param("checkInBeginDate") Date checkInBeginDate,@Param("checkInEndDate") Date checkInEndDate,@Param("teacherId") Long teacherId,@Param("checkInStatus") Integer checkInStatus);

    /**
     * 计算教师值班次数
     *
     * @param checkInBeginDate 开始日期
     * @param checkInEndDate 结束日期
     * @param teacherId 教师id
     * @return 次数
     */
    public int countTeacherOnDutyTimes(@Param("checkInBeginDate")Date checkInBeginDate,@Param("checkInEndDate") Date checkInEndDate,@Param("teacherId") Long teacherId);
}
