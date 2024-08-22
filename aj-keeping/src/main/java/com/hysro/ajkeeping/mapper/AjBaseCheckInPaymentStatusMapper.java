package com.hysro.ajkeeping.mapper;

import java.util.List;
import java.util.Map;

import com.hysro.ajkeeping.domain.AjBaseCheckInPaymentStatus;
import com.hysro.ajkeeping.domain.AjHomoInBaseCheckIn;
import org.apache.ibatis.annotations.Param;

/**
 * 结账情况概述Mapper接口
 *
 * @author hysro
 * @date 2024-08-18
 */
public interface AjBaseCheckInPaymentStatusMapper
{
    /**
     * 查询结账情况概述
     *
     * @param id 结账情况概述主键
     * @return 结账情况概述
     */
    public AjBaseCheckInPaymentStatus selectAjBaseCheckInPaymentStatusById(Long id);

    /**
     * 查询结账情况概述
     *
     * @param baseCheckInId 结账情况概述主键
     * @return 结账情况概述
     */
    public AjBaseCheckInPaymentStatus selectAjBaseCheckInPaymentStatusByBaseCheckInId(Long baseCheckInId);

    /**
     * 查询结账情况概述列表
     *
     * @param ajBaseCheckInPaymentStatus 结账情况概述
     * @return 结账情况概述集合
     */
    public List<AjBaseCheckInPaymentStatus> selectAjBaseCheckInPaymentStatusList(AjBaseCheckInPaymentStatus ajBaseCheckInPaymentStatus);

    /**
     * 查询结账情况概述列表
     *
     * @return 结账情况概述集合
     */
    public List<AjBaseCheckInPaymentStatus> selectAjBaseCheckInPaymentStatusListNoParam();

    /**
     * 新增结账情况概述
     *
     * @param ajBaseCheckInPaymentStatus 结账情况概述
     * @return 结果
     */
    public int insertAjBaseCheckInPaymentStatus(AjBaseCheckInPaymentStatus ajBaseCheckInPaymentStatus);

    /**
     * 考勤表下学生结账情况
     * @param baseCheckInId 考勤表id
     * @param studentGrade 年级
     * @return 学生结账情况
     */
    public List<Map<String, Object>> selectStudentWithBillAndPayment(@Param("baseCheckInId") Long baseCheckInId,@Param("studentGrade") String studentGrade);

    /**
     * 修改结账情况概述
     *
     * @param ajBaseCheckInPaymentStatus 结账情况概述
     * @return 结果
     */
    public int updateAjBaseCheckInPaymentStatus(AjBaseCheckInPaymentStatus ajBaseCheckInPaymentStatus);

    /**
     * 删除结账情况概述
     *
     * @param id 结账情况概述主键
     * @return 结果
     */
    public int deleteAjBaseCheckInPaymentStatusById(Long id);

    /**
     * 批量删除结账情况概述
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAjBaseCheckInPaymentStatusByIds(Long[] ids);

    /**
     * 删除结账情况概述
     *
     * @param baseCheckInId 结账情况概述主键
     * @return 结果
     */
    public int deleteAjBaseCheckInPaymentStatusByBaseCheckInId(Long baseCheckInId);

    /**
     * 批量删除结账情况概述
     *
     * @param baseCheckInIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAjBaseCheckInPaymentStatusByByBaseCheckInIds(Long[] baseCheckInIds);
}
