package com.hysro.ajkeeping.service;

import java.util.List;
import com.hysro.ajkeeping.domain.AjSchool;

/**
 * 学校Service接口
 * 
 * @author hysro
 * @date 2024-08-04
 */
public interface IAjSchoolService 
{
    /**
     * 查询学校
     * 
     * @param id 学校主键
     * @return 学校
     */
    public AjSchool selectAjSchoolById(Long id);

    /**
     * 查询学校列表
     * 
     * @param ajSchool 学校
     * @return 学校集合
     */
    public List<AjSchool> selectAjSchoolList(AjSchool ajSchool);

    /**
     * 新增学校
     * 
     * @param ajSchool 学校
     * @return 结果
     */
    public int insertAjSchool(AjSchool ajSchool);

    /**
     * 修改学校
     * 
     * @param ajSchool 学校
     * @return 结果
     */
    public int updateAjSchool(AjSchool ajSchool);

    /**
     * 批量删除学校
     * 
     * @param ids 需要删除的学校主键集合
     * @return 结果
     */
    public int deleteAjSchoolByIds(Long[] ids);

    /**
     * 删除学校信息
     * 
     * @param id 学校主键
     * @return 结果
     */
    public int deleteAjSchoolById(Long id);
}
