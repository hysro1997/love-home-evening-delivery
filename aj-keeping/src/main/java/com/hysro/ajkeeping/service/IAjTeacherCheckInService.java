package com.hysro.ajkeeping.service;

import java.util.List;
import com.hysro.ajkeeping.domain.AjTeacherCheckIn;

/**
 * 员工考勤Service接口
 * 
 * @author hysro
 * @date 2024-08-04
 */
public interface IAjTeacherCheckInService 
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
     * 批量删除员工考勤
     * 
     * @param teacherCheckInIds 需要删除的员工考勤主键集合
     * @return 结果
     */
    public int deleteAjTeacherCheckInByTeacherCheckInIds(Long[] teacherCheckInIds);

    /**
     * 删除员工考勤信息
     * 
     * @param teacherCheckInId 员工考勤主键
     * @return 结果
     */
    public int deleteAjTeacherCheckInByTeacherCheckInId(Long teacherCheckInId);
}
