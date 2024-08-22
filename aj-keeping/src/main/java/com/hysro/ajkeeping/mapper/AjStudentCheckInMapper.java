package com.hysro.ajkeeping.mapper;

import java.util.List;
import com.hysro.ajkeeping.domain.AjStudentCheckIn;
import org.apache.ibatis.annotations.Param;

/**
 * 学生考勤Mapper接口
 *
 * @author hysro
 * @date 2024-08-04
 */
public interface AjStudentCheckInMapper
{
    /**
     * 查询学生考勤
     *
     * @param studentCheckInId 学生考勤主键
     * @return 学生考勤
     */
    public AjStudentCheckIn selectAjStudentCheckInByStudentCheckInId(Long studentCheckInId);

    /**
     * 查询学生考勤列表
     *
     * @param ajStudentCheckIn 学生考勤
     * @return 学生考勤集合
     */
    public List<AjStudentCheckIn> selectAjStudentCheckInList(AjStudentCheckIn ajStudentCheckIn);

    /**
     * 查询学生考勤列表
     *
     * @param ajStudentCheckIn 学生考勤
     * @return 学生考勤集合
     */
    public List<AjStudentCheckIn> selectAjStudentCheckInList2(AjStudentCheckIn ajStudentCheckIn);

    /**
     * 新增学生考勤
     *
     * @param ajStudentCheckIn 学生考勤
     * @return 结果
     */
    public int insertAjStudentCheckIn(AjStudentCheckIn ajStudentCheckIn);

    /**
     * 修改学生考勤
     *
     * @param ajStudentCheckIn 学生考勤
     * @return 结果
     */
    public int updateAjStudentCheckIn(AjStudentCheckIn ajStudentCheckIn);

    /**
     * 删除学生考勤
     *
     * @param studentCheckInId 学生考勤主键
     * @return 结果
     */
    public int deleteAjStudentCheckInByStudentCheckInId(Long studentCheckInId);

    /**
     * 批量删除学生考勤
     *
     * @param studentCheckInIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAjStudentCheckInByStudentCheckInIds(Long[] studentCheckInIds);

    /**
     * 依据考勤总表id获取独一的学生列表
     *
     * @param baseCheckInId 考勤总表id
     * @return 学生考勤集合
     */
    public List<AjStudentCheckIn> selectDistincAjStudentCheckInListByBaseCkeckinId(@Param("baseCheckInId")Long baseCheckInId);

    /**
     * 依据考勤总表id获取独一的学生列表
     *
     * @param baseCheckInId 考勤总表id
     * @param studentId 学生id
     * @param checkInStatus 考勤状态
     * @return 学生考勤集合
     */
    public int countStudentCheckinOrLeaveTimes(@Param("baseCheckInId") Long baseCheckInId,@Param("studentId") Long studentId,@Param("checkInStatus") Integer checkInStatus);
}
