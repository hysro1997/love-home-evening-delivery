package com.hysro.ajkeeping.task;

import com.hysro.ajkeeping.domain.AjBaseCheckIn;
import com.hysro.ajkeeping.domain.AjStudentCheckIn;
import com.hysro.ajkeeping.domain.AjTeacherCheckIn;
import com.hysro.ajkeeping.domain.AjTeacherInfo;
import com.hysro.ajkeeping.mapper.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * 每天定时生成考勤名单
 *
 * @author hysro
 * @date 2024-08-014
 */
@Component("CheckinListTask")
public class CheckinListTask {

    @Autowired
    private AjBaseCheckInMapper ajBaseCheckInMapper;
    @Autowired
    private AjHomoInBaseCheckInMapper ajHomoInBaseCheckInMapper;
    @Autowired
    private AjStudentCheckInMapper ajStudentCheckInMapper;
    @Autowired
    private AjTeacherInfoMapper ajTeacherInfoMapper;
    @Autowired
    private AjTeacherCheckInMapper ajTeacherCheckInMapper;

    @Transactional(rollbackFor = Exception.class)
    public void selectTodayStudentsAndTeachersCheckin(){
        AjBaseCheckIn ajBaseCheckIn  = new AjBaseCheckIn();
        AjStudentCheckIn ajStudentCheckIn = new AjStudentCheckIn();
        ajBaseCheckIn.setBaseCheckInStatus(1);
        Date today = new Date();
        int weekday = today.getDay();
        LocalDate localDate = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        today = java.sql.Date.valueOf(localDate);
        ajStudentCheckIn.setCheckInDate(today);
        List<AjBaseCheckIn> ajBaseCheckInList = ajBaseCheckInMapper.selectAjBaseCheckInList(ajBaseCheckIn);
        for (AjBaseCheckIn baseCheckIn : ajBaseCheckInList){
            setWeekendayStringToArray(baseCheckIn);
            for (int j: baseCheckIn.getBaseCheckWeekenDay()){
                if (j == weekday){
                    List<AjStudentCheckIn> studentCheckInList = ajHomoInBaseCheckInMapper.selectAjStudentCheckInListByBaseCheckInId(baseCheckIn.getBaseCheckInId());
                    ajStudentCheckIn.setBaseCheckInId(baseCheckIn.getBaseCheckInId());
                    List<AjStudentCheckIn> originStudentCheckInList = ajStudentCheckInMapper.selectAjStudentCheckInList(ajStudentCheckIn);
                    if (originStudentCheckInList != null && !originStudentCheckInList.isEmpty()){
                        originStudentCheckInList.forEach((studentCheckIn -> {
                            for (int i = 0; i < studentCheckInList.size(); i ++){
                                if (studentCheckInList.get(i).getStudentId().equals(studentCheckIn.getStudentId())){
                                    studentCheckInList.remove(i);
                                    break;
                                }
                            }
                        }));
                    }
                    Date finalToday = today;
                    studentCheckInList.forEach(studentCheckIn1 -> {
                        studentCheckIn1.setCheckInDate(finalToday);
                        ajStudentCheckInMapper.insertAjStudentCheckIn(studentCheckIn1);
                    });
                }
            }
        }
        AjTeacherInfo ajTeacherInfo = new AjTeacherInfo();
        ajTeacherInfo.setTeacherStatus(0);
        List<AjTeacherInfo> ajTeacherInfoList = ajTeacherInfoMapper.selectAjTeacherInfoList(ajTeacherInfo);
        for (AjTeacherInfo teacherInfo : ajTeacherInfoList){
            AjTeacherCheckIn teacherCheckIn = new AjTeacherCheckIn();
            teacherCheckIn.setCheckInDate(today);
            teacherCheckIn.setTeacherId(teacherInfo.getTeacherId());
            teacherCheckIn.setTeacherName(teacherInfo.getTeacherName());
            ajTeacherCheckInMapper.insertAjTeacherCheckIn(teacherCheckIn);
        }

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
}
