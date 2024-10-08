package com.hysro.ajkeeping.service;

import java.util.List;
import com.hysro.ajkeeping.domain.AjBaseCheckIn;
import com.hysro.ajkeeping.domain.AjStudentInfo;
import com.hysro.ajkeeping.domain.AjTeacherInfo;

/**
 * 考勤总表Service接口
 *
 * @author hysro
 * @date 2024-08-04
 */
public interface IAjBaseCheckInService
{

    /**
     * 获取在学的学生集合
     *
     * @return 学生集合
     */
    public List<AjStudentInfo> selectSchoolStudents();

    /**
     * 获取在岗的教师集合
     *
     * @return 教师集合
     */
    public List<AjTeacherInfo> selectSchoolTeachers();

    /**
     * 查询考勤总表
     *
     * @param baseCheckInId 考勤总表主键
     * @return 考勤总表
     */
    public AjBaseCheckIn selectAjBaseCheckInByBaseCheckInId(Long baseCheckInId);

    /**
     * 查询考勤总表列表
     *
     * @param ajBaseCheckIn 考勤总表
     * @return 考勤总表集合
     */
    public List<AjBaseCheckIn> selectAjBaseCheckInList(AjBaseCheckIn ajBaseCheckIn);

    /**
     * 新增考勤总表
     *
     * @param ajBaseCheckIn 考勤总表
     * @return 结果
     */
    public int insertAjBaseCheckIn(AjBaseCheckIn ajBaseCheckIn);

    /**
     * 修改考勤总表
     *
     * @param ajBaseCheckIn 考勤总表
     * @return 结果
     */
    public int updateAjBaseCheckIn(AjBaseCheckIn ajBaseCheckIn);

    /**
     * 修改考勤总表
     *
     * @param ajBaseCheckIn 考勤总表
     * @return 结果
     */
    public int updateAjBaseCheckInOnlyHomo(AjBaseCheckIn ajBaseCheckIn);

    /**
     * 批量删除考勤总表
     *
     * @param baseCheckInIds 需要删除的考勤总表主键集合
     * @return 结果
     */
    public int deleteAjBaseCheckInByBaseCheckInIds(Long[] baseCheckInIds);

    /**
     * 删除考勤总表信息
     *
     * @param baseCheckInId 考勤总表主键
     * @return 结果
     */
    public int deleteAjBaseCheckInByBaseCheckInId(Long baseCheckInId);
}
