package com.hysro.ajkeeping.service.impl;

import java.util.List;

import com.ruoyi.common.core.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hysro.ajkeeping.mapper.AjGradeMapper;
import com.hysro.ajkeeping.domain.AjGrade;
import com.hysro.ajkeeping.service.IAjGradeService;

/**
 * 年级Service业务层处理
 *
 * @author hysro
 * @date 2024-08-04
 */
@Service
public class AjGradeServiceImpl implements IAjGradeService
{
    @Autowired
    private AjGradeMapper ajGradeMapper;
    @Autowired
    private RedisCache redisCache;
    private static final String grades = "grades";

    /**
     * 查询年级
     *
     * @param id 年级主键
     * @return 年级
     */
    @Override
    public AjGrade selectAjGradeById(Long id)
    {
        return ajGradeMapper.selectAjGradeById(id);
    }

    /**
     * 查询年级列表
     *
     * @param ajGrade 年级
     * @return 年级
     */
    @Override
    public List<AjGrade> selectAjGradeList(AjGrade ajGrade)
    {
        if (!redisCache.hasKey(grades)){
            redisCache.setCacheObject(grades,ajGradeMapper.selectAjGrades());
        }
        return redisCache.getCacheObject(grades);
    }

    /**
     * 新增年级
     *
     * @param ajGrade 年级
     * @return 结果
     */
    @Override
    public int insertAjGrade(AjGrade ajGrade)
    {
        int result = ajGradeMapper.insertAjGrade(ajGrade);
        this.updateGradesRedis();
        return result;
    }

    /**
     * 修改年级
     *
     * @param ajGrade 年级
     * @return 结果
     */
    @Override
    public int updateAjGrade(AjGrade ajGrade)
    {
        int result = ajGradeMapper.updateAjGrade(ajGrade);
        this.updateGradesRedis();
        return result;
    }

    /**
     * 批量删除年级
     *
     * @param ids 需要删除的年级主键
     * @return 结果
     */
    @Override
    public int deleteAjGradeByIds(Long[] ids)
    {
        int result = ajGradeMapper.deleteAjGradeByIds(ids);
        this.updateGradesRedis();
        return result;
    }

    /**
     * 删除年级信息
     *
     * @param id 年级主键
     * @return 结果
     */
    @Override
    public int deleteAjGradeById(Long id)
    {
        int result = ajGradeMapper.deleteAjGradeById(id);
        this.updateGradesRedis();
        return result;
    }

    public void updateGradesRedis(){
        redisCache.deleteObject(grades);
        redisCache.setCacheObject(grades,ajGradeMapper.selectAjGrades());
    }
}
