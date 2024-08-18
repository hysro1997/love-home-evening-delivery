package com.hysro.ajkeeping.service.impl;

import com.hysro.ajkeeping.domain.AjBaseCheckInPaymentStatus;
import com.hysro.ajkeeping.domain.AjHomoInBaseCheckIn;
import com.hysro.ajkeeping.mapper.AjBaseCheckInPaymentStatusMapper;
import com.hysro.ajkeeping.service.IAjCheckoutService;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 结账Service业务层处理
 *
 * @author hysro
 * @date 2024-08-19
 */
@Service
public class AjCheckoutServiceImpl implements IAjCheckoutService {
    @Autowired
    private AjBaseCheckInPaymentStatusMapper paymentStatusMapper;

    /**
     * 考勤总表及结账情况
     *
     * @return 考勤总表及结账情况
     */
    @Override
    public List<AjBaseCheckInPaymentStatus> listBaseCheckin(BaseEntity baseEntity) {
        return paymentStatusMapper.selectAjBaseCheckInPaymentStatusListNoParam();
    }

    /**
     * 考勤表下学生结账情况
     * @param baseCheckInId 考勤表id
     * @param studentGrade 年级
     * @return 学生结账情况
     */
    @Override
    public List<Map<String, Object>> listStudents(Long baseCheckInId, String studentGrade) {
        return paymentStatusMapper.selectStudentWithBillAndPayment(baseCheckInId, studentGrade);
    }
}
