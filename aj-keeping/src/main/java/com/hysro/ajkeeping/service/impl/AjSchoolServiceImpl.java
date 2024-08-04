package com.hysro.ajkeeping.service.impl;

import java.util.List;

import com.ruoyi.common.core.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hysro.ajkeeping.mapper.AjSchoolMapper;
import com.hysro.ajkeeping.domain.AjSchool;
import com.hysro.ajkeeping.service.IAjSchoolService;

/**
 * 学校Service业务层处理
 *
 * @author hysro
 * @date 2024-08-04
 */
@Service
public class AjSchoolServiceImpl implements IAjSchoolService
{
    @Autowired
    private AjSchoolMapper ajSchoolMapper;
    @Autowired
    private RedisCache redisCache;
    private static final String schools = "schools";

    /**
     * 查询学校
     *
     * @param id 学校主键
     * @return 学校
     */
    @Override
    public AjSchool selectAjSchoolById(Long id)
    {
        return ajSchoolMapper.selectAjSchoolById(id);
    }

    /**
     * 查询学校列表
     *
     * @param ajSchool 学校
     * @return 学校
     */
    @Override
    public List<AjSchool> selectAjSchoolList(AjSchool ajSchool)
    {
        if (!redisCache.hasKey(schools)){
            redisCache.setCacheObject(schools, ajSchoolMapper.selectAjSchools());
        }
        return redisCache.getCacheObject(schools);
    }

    /**
     * 新增学校
     *
     * @param ajSchool 学校
     * @return 结果
     */
    @Override
    public int insertAjSchool(AjSchool ajSchool)
    {
        int result = ajSchoolMapper.insertAjSchool(ajSchool);
        this.updateSchoolsRedis();
        return result;
    }

    /**
     * 修改学校
     *
     * @param ajSchool 学校
     * @return 结果
     */
    @Override
    public int updateAjSchool(AjSchool ajSchool)
    {
        int result = ajSchoolMapper.updateAjSchool(ajSchool);
        this.updateSchoolsRedis();
        return result;
    }

    /**
     * 批量删除学校
     *
     * @param ids 需要删除的学校主键
     * @return 结果
     */
    @Override
    public int deleteAjSchoolByIds(Long[] ids)
    {
        int result = ajSchoolMapper.deleteAjSchoolByIds(ids);
        this.updateSchoolsRedis();
        return result;
    }

    /**
     * 删除学校信息
     *
     * @param id 学校主键
     * @return 结果
     */
    @Override
    public int deleteAjSchoolById(Long id)
    {
        int result = ajSchoolMapper.deleteAjSchoolById(id);
        this.updateSchoolsRedis();
        return result;
    }

    public void updateSchoolsRedis(){
        redisCache.deleteObject(schools);
        redisCache.setCacheObject(schools,ajSchoolMapper.selectAjSchools());
    }
}
