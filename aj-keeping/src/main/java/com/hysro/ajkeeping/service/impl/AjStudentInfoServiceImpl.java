package com.hysro.ajkeeping.service.impl;

import java.util.List;

import com.hysro.ajkeeping.domain.AjGrade;
import com.hysro.ajkeeping.domain.AjSchool;
import com.hysro.ajkeeping.mapper.AjGradeMapper;
import com.hysro.ajkeeping.mapper.AjSchoolMapper;
import com.ruoyi.common.core.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hysro.ajkeeping.mapper.AjStudentInfoMapper;
import com.hysro.ajkeeping.domain.AjStudentInfo;
import com.hysro.ajkeeping.service.IAjStudentInfoService;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private AjSchoolMapper ajSchoolMapper;
    @Autowired
    private AjGradeMapper ajGradeMapper;
    @Autowired
    private RedisCache redisCache;
    private static final String grades = "grades";
    private static final String schools = "schools";

    @Override
    public List<AjGrade> selectAjGrades() {
        if (!redisCache.hasKey(grades)){
            redisCache.setCacheObject(grades,ajGradeMapper.selectAjGrades());
        }
        return redisCache.getCacheObject(grades);
    }

    @Override
    public List<AjSchool> selectAjSchools() {
        if (!redisCache.hasKey(schools)){
            redisCache.setCacheObject(schools, ajSchoolMapper.selectAjSchools());
        }
        return redisCache.getCacheObject(schools);
    }

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
        if(null == ajStudentInfoMapper.selectAjStudentInfoByStudentName(ajStudentInfo.getStudentName().trim())){
            ajStudentInfo.setStudentName(ajStudentInfo.getStudentName().trim());
            return ajStudentInfoMapper.insertAjStudentInfo(ajStudentInfo);
        }
        return 0;
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
        if(null == ajStudentInfoMapper.selectAjStudentInfoByStudentName(ajStudentInfo.getStudentName().trim())){
            ajStudentInfo.setStudentName(ajStudentInfo.getStudentName().trim());
            return ajStudentInfoMapper.updateAjStudentInfo(ajStudentInfo);
        }
        return 2;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStudentStatus(Long[] studentIds) {
        for (long id: studentIds){
            AjStudentInfo ajStudentInfo = ajStudentInfoMapper.selectAjStudentInfoByStudentId(id);
            ajStudentInfo.setStudentStatus(ajStudentInfo.getStudentStatus()==0? 1:0);
            ajStudentInfoMapper.updateAjStudentInfo(ajStudentInfo);
        }
        return 1;
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
