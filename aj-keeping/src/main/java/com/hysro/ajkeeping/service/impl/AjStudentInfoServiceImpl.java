package com.hysro.ajkeeping.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hysro.ajkeeping.mapper.AjStudentInfoMapper;
import com.hysro.ajkeeping.domain.AjStudentInfo;
import com.hysro.ajkeeping.service.IAjStudentInfoService;

/**
 * 学生信息Service业务层处理
 * 
 * @author aj
 * @date 2024-08-04
 */
@Service
public class AjStudentInfoServiceImpl implements IAjStudentInfoService 
{
    @Autowired
    private AjStudentInfoMapper ajStudentInfoMapper;

    /**
     * 查询学生信息
     * 
     * @param studentId 学生信息主键
     * @return 学生信息
     */
    @Override
    public AjStudentInfo selectAjStudentInfoByStudentId(Long studentId)
    {
        return ajStudentInfoMapper.selectAjStudentInfoByStudentId(studentId);
    }

    /**
     * 查询学生信息列表
     * 
     * @param ajStudentInfo 学生信息
     * @return 学生信息
     */
    @Override
    public List<AjStudentInfo> selectAjStudentInfoList(AjStudentInfo ajStudentInfo)
    {
        return ajStudentInfoMapper.selectAjStudentInfoList(ajStudentInfo);
    }

    /**
     * 新增学生信息
     * 
     * @param ajStudentInfo 学生信息
     * @return 结果
     */
    @Override
    public int insertAjStudentInfo(AjStudentInfo ajStudentInfo)
    {
        return ajStudentInfoMapper.insertAjStudentInfo(ajStudentInfo);
    }

    /**
     * 修改学生信息
     * 
     * @param ajStudentInfo 学生信息
     * @return 结果
     */
    @Override
    public int updateAjStudentInfo(AjStudentInfo ajStudentInfo)
    {
        return ajStudentInfoMapper.updateAjStudentInfo(ajStudentInfo);
    }

    /**
     * 批量删除学生信息
     * 
     * @param studentIds 需要删除的学生信息主键
     * @return 结果
     */
    @Override
    public int deleteAjStudentInfoByStudentIds(Long[] studentIds)
    {
        return ajStudentInfoMapper.deleteAjStudentInfoByStudentIds(studentIds);
    }

    /**
     * 删除学生信息信息
     * 
     * @param studentId 学生信息主键
     * @return 结果
     */
    @Override
    public int deleteAjStudentInfoByStudentId(Long studentId)
    {
        return ajStudentInfoMapper.deleteAjStudentInfoByStudentId(studentId);
    }
}
