package com.hysro.ajkeeping.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.hysro.ajkeeping.domain.*;
import com.hysro.ajkeeping.mapper.*;
import com.ruoyi.common.core.redis.RedisCache;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    @Autowired
    private AjBaseCheckInPaymentStatusMapper ajBaseCheckInPaymentStatusMapper;

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
        this.setWeekendayStringToArray(ajBaseCheckIn);
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

    private void setWeekendayStringToArray(@NotNull AjBaseCheckIn ajBaseCheckIn){
        if (null != ajBaseCheckIn.getBaseCheckWeekenDayString() && 0 < ajBaseCheckIn.getBaseCheckWeekenDayString().length()){
            String stringTeamp = ajBaseCheckIn.getBaseCheckWeekenDayString().replace("[","");
            stringTeamp = stringTeamp.replace("]","");
            String[] temp = stringTeamp.split(",");
            int[] intTempArray = new int[temp.length];
            for (int i = 0; i < temp.length; i++){
                intTempArray[i] = Integer.parseInt(temp[i].trim());
            }
            ajBaseCheckIn.setBaseCheckWeekenDay(intTempArray);
        }
    }

    public void selectBaseHomoStudentsAndTeachers(Long baseCheckInId, @NotNull AjBaseCheckIn ajBaseCheckIn){
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
        this.calculateCheckInStatus(ajBaseCheckIn);
        ajBaseCheckIn.setBaseCheckWeekenDayString(Arrays.toString(ajBaseCheckIn.getBaseCheckWeekenDay()));
        ajBaseCheckInMapper.insertAjBaseCheckIn(ajBaseCheckIn);
        this.insertBaseHomoTeachersAndStudents(ajBaseCheckIn);
        AjBaseCheckInPaymentStatus ajBaseCheckInPaymentStatus = new AjBaseCheckInPaymentStatus();
        ajBaseCheckInPaymentStatus.setBaseCheckInId(ajBaseCheckIn.getBaseCheckInId());
        ajBaseCheckInPaymentStatus.setBaseCheckInName(ajBaseCheckIn.getBaseCheckInName());
        ajBaseCheckInPaymentStatus.setBillStatus(0);
        ajBaseCheckInPaymentStatus.setPaymentStatus(0);
        ajBaseCheckInPaymentStatusMapper.insertAjBaseCheckInPaymentStatus(ajBaseCheckInPaymentStatus);
        return 1;
    }

    /**
     * 修改考勤总表
     *
     * @param ajBaseCheckIn 考勤总表
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAjBaseCheckIn(AjBaseCheckIn ajBaseCheckIn)
    {
        this.calculateCheckInStatus(ajBaseCheckIn);
        ajBaseCheckIn.setBaseCheckWeekenDayString(Arrays.toString(ajBaseCheckIn.getBaseCheckWeekenDay()));
        ajBaseCheckInMapper.updateAjBaseCheckIn(ajBaseCheckIn);
        ajHomoInBaseCheckInMapper.deleteAjHomoInBaseCheckInByBaseCheckInId(ajBaseCheckIn.getBaseCheckInId());
        this.insertBaseHomoTeachersAndStudents(ajBaseCheckIn);
        return 1;
    }

    /**
     * 批量删除考勤总表
     *
     * @param baseCheckInIds 需要删除的考勤总表主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAjBaseCheckInByBaseCheckInIds(Long[] baseCheckInIds)
    {
        ajBaseCheckInPaymentStatusMapper.deleteAjBaseCheckInPaymentStatusByByBaseCheckInIds(baseCheckInIds);
        return ajBaseCheckInMapper.deleteAjBaseCheckInByBaseCheckInIds(baseCheckInIds);
    }

    /**
     * 删除考勤总表信息
     *
     * @param baseCheckInId 考勤总表主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAjBaseCheckInByBaseCheckInId(Long baseCheckInId)
    {
        ajBaseCheckInPaymentStatusMapper.deleteAjBaseCheckInPaymentStatusByBaseCheckInId(baseCheckInId);
        return ajBaseCheckInMapper.deleteAjBaseCheckInByBaseCheckInId(baseCheckInId);
    }

    /**
     * 计算考勤状态
     *
     * @param ajBaseCheckIn 考勤总表
     */
    private void calculateCheckInStatus(AjBaseCheckIn ajBaseCheckIn){
        Date nowDate = new Date();
        if (nowDate.before(ajBaseCheckIn.getBaseCheckInBeginDate())){
            ajBaseCheckIn.setBaseCheckInStatus(0);
        } else if (nowDate.after(ajBaseCheckIn.getBaseCheckInEndDate())){
            ajBaseCheckIn.setBaseCheckInStatus(2);
        } else {
            ajBaseCheckIn.setBaseCheckInStatus(1);
        }
    }

    /**
     * 把考勤的教师和学生存入homo考勤表
     *
     * @param ajBaseCheckIn 考勤总表
     */
    private void insertBaseHomoTeachersAndStudents(AjBaseCheckIn ajBaseCheckIn){
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
    }
}
