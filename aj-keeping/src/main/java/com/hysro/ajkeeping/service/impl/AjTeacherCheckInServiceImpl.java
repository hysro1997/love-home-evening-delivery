package com.hysro.ajkeeping.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hysro.ajkeeping.mapper.AjTeacherCheckInMapper;
import com.hysro.ajkeeping.domain.AjTeacherCheckIn;
import com.hysro.ajkeeping.service.IAjTeacherCheckInService;

/**
 * 员工考勤Service业务层处理
 * 
 * @author hysro
 * @date 2024-08-04
 */
@Service
public class AjTeacherCheckInServiceImpl implements IAjTeacherCheckInService 
{
    @Autowired
    private AjTeacherCheckInMapper ajTeacherCheckInMapper;

    /**
     * 查询员工考勤
     * 
     * @param teacherCheckInId 员工考勤主键
     * @return 员工考勤
     */
    @Override
    public AjTeacherCheckIn selectAjTeacherCheckInByTeacherCheckInId(Long teacherCheckInId)
    {
        return ajTeacherCheckInMapper.selectAjTeacherCheckInByTeacherCheckInId(teacherCheckInId);
    }

    /**
     * 查询员工考勤列表
     * 
     * @param ajTeacherCheckIn 员工考勤
     * @return 员工考勤
     */
    @Override
    public List<AjTeacherCheckIn> selectAjTeacherCheckInList(AjTeacherCheckIn ajTeacherCheckIn)
    {
        return ajTeacherCheckInMapper.selectAjTeacherCheckInList(ajTeacherCheckIn);
    }

    /**
     * 新增员工考勤
     * 
     * @param ajTeacherCheckIn 员工考勤
     * @return 结果
     */
    @Override
    public int insertAjTeacherCheckIn(AjTeacherCheckIn ajTeacherCheckIn)
    {
        return ajTeacherCheckInMapper.insertAjTeacherCheckIn(ajTeacherCheckIn);
    }

    /**
     * 修改员工考勤
     * 
     * @param ajTeacherCheckIn 员工考勤
     * @return 结果
     */
    @Override
    public int updateAjTeacherCheckIn(AjTeacherCheckIn ajTeacherCheckIn)
    {
        return ajTeacherCheckInMapper.updateAjTeacherCheckIn(ajTeacherCheckIn);
    }

    /**
     * 批量删除员工考勤
     * 
     * @param teacherCheckInIds 需要删除的员工考勤主键
     * @return 结果
     */
    @Override
    public int deleteAjTeacherCheckInByTeacherCheckInIds(Long[] teacherCheckInIds)
    {
        return ajTeacherCheckInMapper.deleteAjTeacherCheckInByTeacherCheckInIds(teacherCheckInIds);
    }

    /**
     * 删除员工考勤信息
     * 
     * @param teacherCheckInId 员工考勤主键
     * @return 结果
     */
    @Override
    public int deleteAjTeacherCheckInByTeacherCheckInId(Long teacherCheckInId)
    {
        return ajTeacherCheckInMapper.deleteAjTeacherCheckInByTeacherCheckInId(teacherCheckInId);
    }
}
