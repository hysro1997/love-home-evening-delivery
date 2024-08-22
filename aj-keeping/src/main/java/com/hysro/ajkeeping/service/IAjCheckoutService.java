package com.hysro.ajkeeping.service;

import com.hysro.ajkeeping.domain.*;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * 结算Service接口
 *
 * @author hysro
 * @date 2024-08-19
 */
public interface IAjCheckoutService {

    /**
     * 考勤总表及结账情况
     * @param baseEntity 分页
     * @return 考勤总表及结账情况
     */
    public List<AjBaseCheckInPaymentStatus> listBaseCheckin(BaseEntity baseEntity);

    /**
     * 考勤表下学生结账情况
     * @param baseCheckInId 考勤表id
     * @param studentGrade 年级
     * @return 学生结账情况
     */
    public List<Map<String,Object>> listStudents(Long baseCheckInId, String studentGrade);

    public int checkOut(AjStudentBill ajStudentBill, AjCostTemplate ajCostTemplate);
}
