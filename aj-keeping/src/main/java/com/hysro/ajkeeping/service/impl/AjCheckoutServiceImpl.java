package com.hysro.ajkeeping.service.impl;

import com.hysro.ajkeeping.domain.AjBaseCheckInPaymentStatus;
import com.hysro.ajkeeping.domain.AjCostTemplate;
import com.hysro.ajkeeping.domain.AjStudentBill;
import com.hysro.ajkeeping.domain.AjStudentCheckInStatistic;
import com.hysro.ajkeeping.mapper.AjBaseCheckInPaymentStatusMapper;
import com.hysro.ajkeeping.mapper.AjCostTemplateMapper;
import com.hysro.ajkeeping.mapper.AjStudentBillMapper;
import com.hysro.ajkeeping.mapper.AjStudentCheckInStatisticMapper;
import com.hysro.ajkeeping.service.IAjCheckoutService;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private AjStudentBillMapper billMapper;
    @Autowired
    private AjCostTemplateMapper costTemplateMapper;
    @Autowired
    private AjStudentCheckInStatisticMapper statisticMapper;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int checkOut(AjStudentBill ajStudentBill, AjCostTemplate ajCostTemplate) {
        ajCostTemplate = costTemplateMapper.selectAjCostTemplateByCostTemplateId(ajCostTemplate.getCostTemplateId());
        ajStudentBill.setPerMonthFee(ajCostTemplate.getCostFeePerMonth());
        ajStudentBill.setPerDayFee(ajCostTemplate.getCostFeePerDay());
        ajStudentBill.setFoodFee(ajCostTemplate.getCostFoodFee());
        ajStudentBill.setTextbookFee(ajCostTemplate.getCostTextbooksFee());
        AjStudentCheckInStatistic statistic  = statisticMapper.selectAjStudentCheckInStatisticById(ajStudentBill.getCheckInStatisticId());
        ajStudentBill.setCheckInBeginDate(statistic.getCheckInBeginDate());
        ajStudentBill.setCheckInEndDate(statistic.getCheckInEndDate());
        ajStudentBill.setCheckInTimes(statistic.getCheckInTimes());
        ajStudentBill.setLeaveTimes(statistic.getLeaveDays());
        billMapper.insertAjStudentBill(ajStudentBill);
        statistic.setBillStatus(1);
        statisticMapper.updateAjStudentCheckInStatistic(statistic);
        return 1;
    }
}
