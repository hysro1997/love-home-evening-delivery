package com.hysro.ajkeeping.service;

import java.util.List;

import com.hysro.ajkeeping.domain.AjGrade;
import com.hysro.ajkeeping.domain.AjSchool;
import com.hysro.ajkeeping.domain.AjStudentInfo;

/**
 * 学生信息Service接口
 *
 * @author aj
 * @date 2024-08-04
 */
public interface IAjStudentInfoService
{

    /**
     * 获取年级
     *
     * @return 年级信息
     */
    public List<AjGrade> selectAjGrades();

    /**
     * 获取学校
     *
     * @return 学校信息
     */
    public List<AjSchool> selectAjSchools();

    /**
     * 查询学生信息
     *
     * @param studentId 学生信息主键
     * @return 学生信息
     */
    public AjStudentInfo selectAjStudentInfoByStudentId(Long studentId);

    /**
     * 查询学生信息列表
     *
     * @param ajStudentInfo 学生信息
     * @return 学生信息集合
     */
    public List<AjStudentInfo> selectAjStudentInfoList(AjStudentInfo ajStudentInfo);

    /**
     * 新增学生信息
     *
     * @param ajStudentInfo 学生信息
     * @return 结果
     */
    public int insertAjStudentInfo(AjStudentInfo ajStudentInfo);

    /**
     * 修改学生信息
     *
     * @param ajStudentInfo 学生信息
     * @return 结果
     */
    public int updateAjStudentInfo(AjStudentInfo ajStudentInfo);

    /**
     * 批量删除学生信息
     *
     * @param studentIds 需要删除的学生信息主键集合
     * @return 结果
     */
    public int deleteAjStudentInfoByStudentIds(Long[] studentIds);

    /**
     * 删除学生信息信息
     *
     * @param studentId 学生信息主键
     * @return 结果
     */
    public int deleteAjStudentInfoByStudentId(Long studentId);
}
