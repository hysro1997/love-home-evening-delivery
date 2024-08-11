package com.hysro.ajkeeping.service;

import java.util.List;
import com.hysro.ajkeeping.domain.AjHomoInBaseCheckIn;

/**
 * 在考勤中的老师与学生Service接口
 *
 * @author hysro
 * @date 2024-08-08
 */
public interface IAjHomoInBaseCheckInService
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
     * 新增在考勤中的老师与学生
     *
     * @param ajHomoInBaseCheckIn 在考勤中的老师与学生
     * @return 结果
     */
    public int insertAjHomoInBaseCheckIn(AjHomoInBaseCheckIn ajHomoInBaseCheckIn);

    /**
     * 修改在考勤中的老师与学生
     *
     * @param ajHomoInBaseCheckIn 在考勤中的老师与学生
     * @return 结果
     */
    public int updateAjHomoInBaseCheckIn(AjHomoInBaseCheckIn ajHomoInBaseCheckIn);

    /**
     * 批量删除在考勤中的老师与学生
     *
     * @param ids 需要删除的在考勤中的老师与学生主键集合
     * @return 结果
     */
    public int deleteAjHomoInBaseCheckInByIds(Long[] ids);

    /**
     * 删除在考勤中的老师与学生信息
     *
     * @param id 在考勤中的老师与学生主键
     * @return 结果
     */
    public int deleteAjHomoInBaseCheckInById(Long id);
}
