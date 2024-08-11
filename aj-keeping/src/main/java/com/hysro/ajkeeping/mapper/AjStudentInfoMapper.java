package com.hysro.ajkeeping.mapper;

import java.util.List;
import com.hysro.ajkeeping.domain.AjStudentInfo;

/**
 * 学生信息Mapper接口
 *
 * @author aj
 * @date 2024-08-04
 */
public interface AjStudentInfoMapper
{
    /**
     * 查询学生信息
     *
     * @param studentId 学生信息主键
     * @return 学生信息
     */
    public AjStudentInfo selectAjStudentInfoByStudentId(Long studentId);

    /**
     * 查询学生信息
     *
     * @param studentName 学生姓名
     * @return 学生信息
     */
    public AjStudentInfo selectAjStudentInfoByStudentName(String studentName);


    /**
     * 查询学生信息列表
     *
     * @return 学生信息集合
     */
    public List<AjStudentInfo> selectAjStudentSimpleInfoList();

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
     * 删除学生信息
     *
     * @param studentId 学生信息主键
     * @return 结果
     */
    public int deleteAjStudentInfoByStudentId(Long studentId);

    /**
     * 批量删除学生信息
     *
     * @param studentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAjStudentInfoByStudentIds(Long[] studentIds);
}
