package com.hysro.ajkeeping.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hysro.ajkeeping.mapper.AjTeacherInfoMapper;
import com.hysro.ajkeeping.domain.AjTeacherInfo;
import com.hysro.ajkeeping.service.IAjTeacherInfoService;

/**
 * 教师信息Service业务层处理
 *
 * @author ruoyi
 * @date 2024-08-04
 */
@Service
public class AjTeacherInfoServiceImpl implements IAjTeacherInfoService
{
    @Autowired
    private AjTeacherInfoMapper ajTeacherInfoMapper;

    /**
     * 查询教师信息
     *
     * @param teacherId 教师信息主键
     * @return 教师信息
     */
    @Override
    public AjTeacherInfo selectAjTeacherInfoByTeacherId(Long teacherId)
    {
        return ajTeacherInfoMapper.selectAjTeacherInfoByTeacherId(teacherId);
    }

    /**
     * 查询教师信息列表
     *
     * @param ajTeacherInfo 教师信息
     * @return 教师信息
     */
    @Override
    public List<AjTeacherInfo> selectAjTeacherInfoList(AjTeacherInfo ajTeacherInfo)
    {
        return ajTeacherInfoMapper.selectAjTeacherInfoList(ajTeacherInfo);
    }

    /**
     * 查询教师信息列表带排序，无参数
     *
     * @param ajTeacherInfo 教师信息
     * @return 教师信息集合
     */
    @Override
    public List<AjTeacherInfo> selectAjTeacherInfoListOrderByStatus(AjTeacherInfo ajTeacherInfo) {
        return ajTeacherInfoMapper.selectAjTeacherInfoListOrderByStatus(ajTeacherInfo);
    }

    /**
     * 新增教师信息
     *
     * @param ajTeacherInfo 教师信息
     * @return 结果
     */
    @Override
    public int insertAjTeacherInfo(AjTeacherInfo ajTeacherInfo)
    {
        return ajTeacherInfoMapper.insertAjTeacherInfo(ajTeacherInfo);
    }

    /**
     * 修改教师信息
     *
     * @param ajTeacherInfo 教师信息
     * @return 结果
     */
    @Override
    public int updateAjTeacherInfo(AjTeacherInfo ajTeacherInfo)
    {
        return ajTeacherInfoMapper.updateAjTeacherInfo(ajTeacherInfo);
    }

    /**
     * 批量删除教师信息
     *
     * @param teacherIds 需要删除的教师信息主键
     * @return 结果
     */
    @Override
    public int deleteAjTeacherInfoByTeacherIds(Long[] teacherIds)
    {
        return ajTeacherInfoMapper.deleteAjTeacherInfoByTeacherIds(teacherIds);
    }

    /**
     * 删除教师信息信息
     *
     * @param teacherId 教师信息主键
     * @return 结果
     */
    @Override
    public int deleteAjTeacherInfoByTeacherId(Long teacherId)
    {
        return ajTeacherInfoMapper.deleteAjTeacherInfoByTeacherId(teacherId);
    }
}
