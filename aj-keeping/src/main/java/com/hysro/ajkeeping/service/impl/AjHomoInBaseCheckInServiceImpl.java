package com.hysro.ajkeeping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hysro.ajkeeping.mapper.AjHomoInBaseCheckInMapper;
import com.hysro.ajkeeping.domain.AjHomoInBaseCheckIn;
import com.hysro.ajkeeping.service.IAjHomoInBaseCheckInService;

/**
 * 在考勤中的老师与学生Service业务层处理
 *
 * @author hysro
 * @date 2024-08-08
 */
@Service
public class AjHomoInBaseCheckInServiceImpl implements IAjHomoInBaseCheckInService
{
    @Autowired
    private AjHomoInBaseCheckInMapper ajHomoInBaseCheckInMapper;

    /**
     * 查询在考勤中的老师与学生
     *
     * @param id 在考勤中的老师与学生主键
     * @return 在考勤中的老师与学生
     */
    @Override
    public AjHomoInBaseCheckIn selectAjHomoInBaseCheckInById(Long id)
    {
        return ajHomoInBaseCheckInMapper.selectAjHomoInBaseCheckInById(id);
    }

    /**
     * 查询在考勤中的老师与学生列表
     *
     * @param ajHomoInBaseCheckIn 在考勤中的老师与学生
     * @return 在考勤中的老师与学生
     */
    @Override
    public List<AjHomoInBaseCheckIn> selectAjHomoInBaseCheckInList(AjHomoInBaseCheckIn ajHomoInBaseCheckIn)
    {
        return ajHomoInBaseCheckInMapper.selectAjHomoInBaseCheckInList(ajHomoInBaseCheckIn);
    }

    /**
     * 新增在考勤中的老师与学生
     *
     * @param ajHomoInBaseCheckIn 在考勤中的老师与学生
     * @return 结果
     */
    @Override
    public int insertAjHomoInBaseCheckIn(AjHomoInBaseCheckIn ajHomoInBaseCheckIn)
    {
        return ajHomoInBaseCheckInMapper.insertAjHomoInBaseCheckIn(ajHomoInBaseCheckIn);
    }

    /**
     * 修改在考勤中的老师与学生
     *
     * @param ajHomoInBaseCheckIn 在考勤中的老师与学生
     * @return 结果
     */
    @Override
    public int updateAjHomoInBaseCheckIn(AjHomoInBaseCheckIn ajHomoInBaseCheckIn)
    {
        return ajHomoInBaseCheckInMapper.updateAjHomoInBaseCheckIn(ajHomoInBaseCheckIn);
    }

    /**
     * 批量删除在考勤中的老师与学生
     *
     * @param ids 需要删除的在考勤中的老师与学生主键
     * @return 结果
     */
    @Override
    public int deleteAjHomoInBaseCheckInByIds(Long[] ids)
    {
        return ajHomoInBaseCheckInMapper.deleteAjHomoInBaseCheckInByIds(ids);
    }

    /**
     * 删除在考勤中的老师与学生信息
     *
     * @param id 在考勤中的老师与学生主键
     * @return 结果
     */
    @Override
    public int deleteAjHomoInBaseCheckInById(Long id)
    {
        return ajHomoInBaseCheckInMapper.deleteAjHomoInBaseCheckInById(id);
    }
}
