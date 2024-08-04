package com.hysro.ajkeeping.mapper;

import java.util.List;
import com.hysro.ajkeeping.domain.AjTeacherCheckIn;

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
}
