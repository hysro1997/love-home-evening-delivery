package com.hysro.ajkeeping.service;

import java.util.List;
import com.hysro.ajkeeping.domain.AjGrade;

/**
 * 年级Service接口
 * 
 * @author hysro
 * @date 2024-08-04
 */
public interface IAjGradeService 
{
    /**
     * 查询年级
     * 
     * @param id 年级主键
     * @return 年级
     */
    public AjGrade selectAjGradeById(Long id);

    /**
     * 查询年级列表
     * 
     * @param ajGrade 年级
     * @return 年级集合
     */
    public List<AjGrade> selectAjGradeList(AjGrade ajGrade);

    /**
     * 新增年级
     * 
     * @param ajGrade 年级
     * @return 结果
     */
    public int insertAjGrade(AjGrade ajGrade);

    /**
     * 修改年级
     * 
     * @param ajGrade 年级
     * @return 结果
     */
    public int updateAjGrade(AjGrade ajGrade);

    /**
     * 批量删除年级
     * 
     * @param ids 需要删除的年级主键集合
     * @return 结果
     */
    public int deleteAjGradeByIds(Long[] ids);

    /**
     * 删除年级信息
     * 
     * @param id 年级主键
     * @return 结果
     */
    public int deleteAjGradeById(Long id);
}
