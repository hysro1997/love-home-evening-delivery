package com.hysro.ajkeeping.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hysro.ajkeeping.mapper.AjStudentCheckInMapper;
import com.hysro.ajkeeping.domain.AjStudentCheckIn;
import com.hysro.ajkeeping.service.IAjStudentCheckInService;

/**
 * 学生考勤Service业务层处理
 *
 * @author hysro
 * @date 2024-08-04
 */
@Service
public class AjStudentCheckInServiceImpl implements IAjStudentCheckInService
{
    @Autowired
    private AjStudentCheckInMapper ajStudentCheckInMapper;

    /**
     * 查询学生考勤
     *
     * @param studentCheckInId 学生考勤主键
     * @return 学生考勤
     */
    @Override
    public AjStudentCheckIn selectAjStudentCheckInByStudentCheckInId(Long studentCheckInId)
    {
        return ajStudentCheckInMapper.selectAjStudentCheckInByStudentCheckInId(studentCheckInId);
    }

    /**
     * 查询学生考勤列表
     *
     * @param ajStudentCheckIn 学生考勤
     * @return 学生考勤
     */
    @Override
    public List<AjStudentCheckIn> selectAjStudentCheckInList(AjStudentCheckIn ajStudentCheckIn)
    {
        return ajStudentCheckInMapper.selectAjStudentCheckInList(ajStudentCheckIn);
    }

    /**
     * 查询学生考勤列表
     *
     * @param ajStudentCheckIn 学生考勤
     * @return 学生考勤
     */
    @Override
    public List<AjStudentCheckIn> selectAjStudentCheckInListNoOrder(AjStudentCheckIn ajStudentCheckIn)
    {
        return ajStudentCheckInMapper.selectAjStudentCheckInList2(ajStudentCheckIn);
    }

    /**
     * 新增学生考勤
     *
     * @param ajStudentCheckIn 学生考勤
     * @return 结果
     */
    @Override
    public int insertAjStudentCheckIn(AjStudentCheckIn ajStudentCheckIn)
    {
        return ajStudentCheckInMapper.insertAjStudentCheckIn(ajStudentCheckIn);
    }

    /**
     * 修改学生考勤
     *
     * @param ajStudentCheckIn 学生考勤
     * @return 结果
     */
    @Override
    public int updateAjStudentCheckIn(AjStudentCheckIn ajStudentCheckIn)
    {
        return ajStudentCheckInMapper.updateAjStudentCheckIn(ajStudentCheckIn);
    }

    /**
     * 批量删除学生考勤
     *
     * @param studentCheckInIds 需要删除的学生考勤主键
     * @return 结果
     */
    @Override
    public int deleteAjStudentCheckInByStudentCheckInIds(Long[] studentCheckInIds)
    {
        return ajStudentCheckInMapper.deleteAjStudentCheckInByStudentCheckInIds(studentCheckInIds);
    }

    /**
     * 删除学生考勤信息
     *
     * @param studentCheckInId 学生考勤主键
     * @return 结果
     */
    @Override
    public int deleteAjStudentCheckInByStudentCheckInId(Long studentCheckInId)
    {
        return ajStudentCheckInMapper.deleteAjStudentCheckInByStudentCheckInId(studentCheckInId);
    }
}
