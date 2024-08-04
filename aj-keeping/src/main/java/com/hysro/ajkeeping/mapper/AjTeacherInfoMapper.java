package com.hysro.ajkeeping.mapper;

import java.util.List;
import com.hysro.ajkeeping.domain.AjTeacherInfo;

/**
 * 教师信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-04
 */
public interface AjTeacherInfoMapper 
{
    /**
     * 查询教师信息
     * 
     * @param teacherId 教师信息主键
     * @return 教师信息
     */
    public AjTeacherInfo selectAjTeacherInfoByTeacherId(Long teacherId);

    /**
     * 查询教师信息列表
     * 
     * @param ajTeacherInfo 教师信息
     * @return 教师信息集合
     */
    public List<AjTeacherInfo> selectAjTeacherInfoList(AjTeacherInfo ajTeacherInfo);

    /**
     * 新增教师信息
     * 
     * @param ajTeacherInfo 教师信息
     * @return 结果
     */
    public int insertAjTeacherInfo(AjTeacherInfo ajTeacherInfo);

    /**
     * 修改教师信息
     * 
     * @param ajTeacherInfo 教师信息
     * @return 结果
     */
    public int updateAjTeacherInfo(AjTeacherInfo ajTeacherInfo);

    /**
     * 删除教师信息
     * 
     * @param teacherId 教师信息主键
     * @return 结果
     */
    public int deleteAjTeacherInfoByTeacherId(Long teacherId);

    /**
     * 批量删除教师信息
     * 
     * @param teacherIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAjTeacherInfoByTeacherIds(Long[] teacherIds);
}
