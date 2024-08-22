package com.hysro.ajkeeping.service;

import java.util.List;
import com.hysro.ajkeeping.domain.AjStudentCheckIn;

/**
 * 学生考勤Service接口
 *
 * @author hysro
 * @date 2024-08-04
 */
public interface IAjStudentCheckInService
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
    public List<AjStudentCheckIn> selectAjStudentCheckInListNoOrder(AjStudentCheckIn ajStudentCheckIn);

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
     * 批量删除学生考勤
     *
     * @param studentCheckInIds 需要删除的学生考勤主键集合
     * @return 结果
     */
    public int deleteAjStudentCheckInByStudentCheckInIds(Long[] studentCheckInIds);

    /**
     * 删除学生考勤信息
     *
     * @param studentCheckInId 学生考勤主键
     * @return 结果
     */
    public int deleteAjStudentCheckInByStudentCheckInId(Long studentCheckInId);
}
