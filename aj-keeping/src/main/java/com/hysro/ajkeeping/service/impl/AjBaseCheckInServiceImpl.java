package com.hysro.ajkeeping.service.impl;

import java.util.List;

import com.hysro.ajkeeping.domain.AjHomoInBaseCheckIn;
import com.hysro.ajkeeping.domain.AjStudentInfo;
import com.hysro.ajkeeping.domain.AjTeacherInfo;
import com.hysro.ajkeeping.mapper.AjHomoInBaseCheckInMapper;
import com.hysro.ajkeeping.mapper.AjStudentInfoMapper;
import com.hysro.ajkeeping.mapper.AjTeacherInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hysro.ajkeeping.mapper.AjBaseCheckInMapper;
import com.hysro.ajkeeping.domain.AjBaseCheckIn;
import com.hysro.ajkeeping.service.IAjBaseCheckInService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 考勤总表Service业务层处理
 *
 * @author hysro
 * @date 2024-08-04
 */
@Service
public class AjBaseCheckInServiceImpl implements IAjBaseCheckInService
{
    @Autowired
    private AjBaseCheckInMapper ajBaseCheckInMapper;
    @Autowired
    private AjStudentInfoMapper ajStudentInfoMapper;
    @Autowired
    private AjTeacherInfoMapper ajTeacherInfoMapper;
    @Autowired
    private AjHomoInBaseCheckInMapper ajHomoInBaseCheckInMapper;

    @Override
    public List<AjStudentInfo> selectSchoolStudents() {
        return ajStudentInfoMapper.selectAjStudentSimpleInfoList();
    }

    @Override
    public List<AjTeacherInfo> selectSchoolTeachers() {
        return ajTeacherInfoMapper.selectAjTeacherSimpleInfoList();
    }
    /**
     * 查询考勤总表
     *
     * @param baseCheckInId 考勤总表主键
     * @return 考勤总表
     */
    @Override
    public AjBaseCheckIn selectAjBaseCheckInByBaseCheckInId(Long baseCheckInId)
    {
        AjBaseCheckIn ajBaseCheckIn = ajBaseCheckInMapper.selectAjBaseCheckInByBaseCheckInId(baseCheckInId);
        this.selectBaseHomoStudentsAndTeachers(baseCheckInId,ajBaseCheckIn);
        return ajBaseCheckIn;
    }

    /**
     * 查询考勤总表列表
     *
     * @param ajBaseCheckIn 考勤总表
     * @return 考勤总表
     */
    @Override
    public List<AjBaseCheckIn> selectAjBaseCheckInList(AjBaseCheckIn ajBaseCheckIn)
    {
        List<AjBaseCheckIn> ajBaseCheckIns = ajBaseCheckInMapper.selectAjBaseCheckInList(ajBaseCheckIn);
        for (AjBaseCheckIn checkIn : ajBaseCheckIns){
            this.selectBaseHomoStudentsAndTeachers(checkIn.getBaseCheckInId(),checkIn);
        }
        return ajBaseCheckIns;
    }

    public void selectBaseHomoStudentsAndTeachers(Long baseCheckInId, AjBaseCheckIn ajBaseCheckIn){
        AjHomoInBaseCheckIn ajHomoInBaseCheckIn = new AjHomoInBaseCheckIn();
        ajHomoInBaseCheckIn.setBaseCheckInId(baseCheckInId);
        ajBaseCheckIn.setAjHomoInBaseCheckIns(ajHomoInBaseCheckInMapper.selectAjHomoInBaseCheckInList(ajHomoInBaseCheckIn));
    }

    /**
     * 新增考勤总表
     *
     * @param ajBaseCheckIn 考勤总表
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertAjBaseCheckIn(AjBaseCheckIn ajBaseCheckIn)
    {
        ajBaseCheckInMapper.insertAjBaseCheckIn(ajBaseCheckIn);
        AjHomoInBaseCheckIn ajHomoInBaseCheckIn = new AjHomoInBaseCheckIn();
        ajHomoInBaseCheckIn.setBaseCheckInId(ajBaseCheckIn.getBaseCheckInId());
        if (0 < ajBaseCheckIn.getCheckinStudents().length){
            for(Long id : ajBaseCheckIn.getCheckinStudents()){
                ajHomoInBaseCheckIn.setStudentId(id);
                ajHomoInBaseCheckInMapper.insertAjHomoInBaseCheckInStudents(ajHomoInBaseCheckIn);
            }
        }
        if (0 < ajBaseCheckIn.getCheckinTeachers().length){
            for(Long id : ajBaseCheckIn.getCheckinTeachers()){
                ajHomoInBaseCheckIn.setTeacherId(id);
                ajHomoInBaseCheckInMapper.insertAjHomoInBaseCheckInTeachers(ajHomoInBaseCheckIn);
            }
        }
        return 1;
    }

    /**
     * 修改考勤总表
     *
     * @param ajBaseCheckIn 考勤总表
     * @return 结果
     */
    @Override
    public int updateAjBaseCheckIn(AjBaseCheckIn ajBaseCheckIn)
    {
        return ajBaseCheckInMapper.updateAjBaseCheckIn(ajBaseCheckIn);
    }

    /**
     * 批量删除考勤总表
     *
     * @param baseCheckInIds 需要删除的考勤总表主键
     * @return 结果
     */
    @Override
    public int deleteAjBaseCheckInByBaseCheckInIds(Long[] baseCheckInIds)
    {
        return ajBaseCheckInMapper.deleteAjBaseCheckInByBaseCheckInIds(baseCheckInIds);
    }

    /**
     * 删除考勤总表信息
     *
     * @param baseCheckInId 考勤总表主键
     * @return 结果
     */
    @Override
    public int deleteAjBaseCheckInByBaseCheckInId(Long baseCheckInId)
    {
        return ajBaseCheckInMapper.deleteAjBaseCheckInByBaseCheckInId(baseCheckInId);
    }
}
