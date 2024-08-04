package com.hysro.ajkeeping.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hysro.ajkeeping.mapper.AjBaseCheckInMapper;
import com.hysro.ajkeeping.domain.AjBaseCheckIn;
import com.hysro.ajkeeping.service.IAjBaseCheckInService;

/**
 * 考勤总表Service业务层处理
 * 
 * @author hysro
 * @date 2024-08-04
 */
@Service
public class AjBaseCheckInServiceImpl implements IAjBaseCheckInService 
{
    @Autowired
    private AjBaseCheckInMapper ajBaseCheckInMapper;

    /**
     * 查询考勤总表
     * 
     * @param baseCheckInId 考勤总表主键
     * @return 考勤总表
     */
    @Override
    public AjBaseCheckIn selectAjBaseCheckInByBaseCheckInId(Long baseCheckInId)
    {
        return ajBaseCheckInMapper.selectAjBaseCheckInByBaseCheckInId(baseCheckInId);
    }

    /**
     * 查询考勤总表列表
     * 
     * @param ajBaseCheckIn 考勤总表
     * @return 考勤总表
     */
    @Override
    public List<AjBaseCheckIn> selectAjBaseCheckInList(AjBaseCheckIn ajBaseCheckIn)
    {
        return ajBaseCheckInMapper.selectAjBaseCheckInList(ajBaseCheckIn);
    }

    /**
     * 新增考勤总表
     * 
     * @param ajBaseCheckIn 考勤总表
     * @return 结果
     */
    @Override
    public int insertAjBaseCheckIn(AjBaseCheckIn ajBaseCheckIn)
    {
        return ajBaseCheckInMapper.insertAjBaseCheckIn(ajBaseCheckIn);
    }

    /**
     * 修改考勤总表
     * 
     * @param ajBaseCheckIn 考勤总表
     * @return 结果
     */
    @Override
    public int updateAjBaseCheckIn(AjBaseCheckIn ajBaseCheckIn)
    {
        return ajBaseCheckInMapper.updateAjBaseCheckIn(ajBaseCheckIn);
    }

    /**
     * 批量删除考勤总表
     * 
     * @param baseCheckInIds 需要删除的考勤总表主键
     * @return 结果
     */
    @Override
    public int deleteAjBaseCheckInByBaseCheckInIds(Long[] baseCheckInIds)
    {
        return ajBaseCheckInMapper.deleteAjBaseCheckInByBaseCheckInIds(baseCheckInIds);
    }

    /**
     * 删除考勤总表信息
     * 
     * @param baseCheckInId 考勤总表主键
     * @return 结果
     */
    @Override
    public int deleteAjBaseCheckInByBaseCheckInId(Long baseCheckInId)
    {
        return ajBaseCheckInMapper.deleteAjBaseCheckInByBaseCheckInId(baseCheckInId);
    }
}
