package com.hysro.ajkeeping.mapper;

import java.util.List;
import com.hysro.ajkeeping.domain.AjHomoInBaseCheckIn;
import com.hysro.ajkeeping.domain.AjStudentCheckIn;

/**
 * 在考勤中的老师与学生Mapper接口
 *
 * @author hysro
 * @date 2024-08-08
 */
public interface AjHomoInBaseCheckInMapper
{
    /**
     * 查询在考勤中的老师与学生
     *
     * @param id 在考勤中的老师与学生主键
     * @return 在考勤中的老师与学生
     */
    public AjHomoInBaseCheckIn selectAjHomoInBaseCheckInById(Long id);

    /**
     * 查询在考勤中的老师与学生列表
     *
     * @param ajHomoInBaseCheckIn 在考勤中的老师与学生
     * @return 在考勤中的老师与学生集合
     */
    public List<AjHomoInBaseCheckIn> selectAjHomoInBaseCheckInList(AjHomoInBaseCheckIn ajHomoInBaseCheckIn);

    /**
     * 查询在考勤总表中的学生列表
     *
     * @param baseCheckInId 考勤总表id
     * @return 在考勤中的学生集合
     */
    public List<AjStudentCheckIn> selectAjStudentCheckInListByBaseCheckInId(Long baseCheckInId);

    /**
     * 新增在考勤中的老师与学生
     *
     * @param ajHomoInBaseCheckIn 在考勤中的老师与学生
     * @return 结果
     */
    public int insertAjHomoInBaseCheckIn(AjHomoInBaseCheckIn ajHomoInBaseCheckIn);

    /**
     * 新增在考勤中的老师与学生
     *
     * @param ajHomoInBaseCheckIn 在考勤中的老师与学生
     * @return 结果
     */
    public int insertAjHomoInBaseCheckInStudents(AjHomoInBaseCheckIn ajHomoInBaseCheckIn);
    /**
     * 新增在考勤中的老师与学生
     *
     * @param ajHomoInBaseCheckIn 在考勤中的老师与学生
     * @return 结果
     */
    public int insertAjHomoInBaseCheckInTeachers(AjHomoInBaseCheckIn ajHomoInBaseCheckIn);
    /**
     * 修改在考勤中的老师与学生
     *
     * @param ajHomoInBaseCheckIn 在考勤中的老师与学生
     * @return 结果
     */
    public int updateAjHomoInBaseCheckIn(AjHomoInBaseCheckIn ajHomoInBaseCheckIn);

    /**
     * 删除在考勤中的老师与学生
     *
     * @param id 在考勤中的老师与学生主键
     * @return 结果
     */
    public int deleteAjHomoInBaseCheckInById(Long id);


    /**
     * 依据考勤基表id删除在考勤中的老师与学生
     *
     * @param baseCheckInId 在考勤中的老师与学生主键
     * @return 结果
     */
    public int deleteAjHomoInBaseCheckInByBaseCheckInId(Long baseCheckInId);

    /**
     * 批量删除在考勤中的老师与学生
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAjHomoInBaseCheckInByIds(Long[] ids);
}
