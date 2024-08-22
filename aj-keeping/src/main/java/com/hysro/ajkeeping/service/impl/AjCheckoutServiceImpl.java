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
        ajStudentBill.setCheckInBaseId(statistic.getBaseCheckInId());
        billMapper.insertAjStudentBill(ajStudentBill);
        statistic.setBillStatus(1);

        int statisticNums = statisticMapper.countAjStudentCheckInStatistic(statistic.getBaseCheckInId());
        int billNums = billMapper.countAjStudentBill(statistic.getBaseCheckInId());
        statisticMapper.updateAjStudentCheckInStatistic(statistic);
        AjBaseCheckInPaymentStatus paymentStatus = paymentStatusMapper.selectAjBaseCheckInPaymentStatusByBaseCheckInId(statistic.getBaseCheckInId());
        if (billNums == statisticNums){
            paymentStatus.setBillStatus(2);
        } else {
            paymentStatus.setBillStatus(1);
        }
        paymentStatusMapper.updateAjBaseCheckInPaymentStatus(paymentStatus);
        return ajStudentBill.getId().intValue();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int pay(AjStudentBill ajStudentBill) {
        billMapper.updateAjStudentBill(ajStudentBill);
        ajStudentBill = billMapper.selectAjStudentBillById(ajStudentBill.getId());
        AjStudentCheckInStatistic statistic  = new AjStudentCheckInStatistic();
        statistic.setBaseCheckInId(ajStudentBill.getCheckInBaseId());
        statistic.setStudentId(ajStudentBill.getStudentId());
        statistic = statisticMapper.selectAjStudentCheckInStatisticList(statistic).get(0);
        statistic.setPaymentStatus(1);
        statisticMapper.updateAjStudentCheckInStatistic(statistic);
        int statisticNums = statisticMapper.countAjStudentCheckInStatistic(ajStudentBill.getCheckInBaseId());
        int billNums = billMapper.countAjStudentBillWithConditionPay(ajStudentBill.getCheckInBaseId());
        AjBaseCheckInPaymentStatus paymentStatus = paymentStatusMapper.selectAjBaseCheckInPaymentStatusByBaseCheckInId(ajStudentBill.getCheckInBaseId());
        if (billNums == statisticNums){
            paymentStatus.setPaymentStatus(2);
        } else {
            paymentStatus.setPaymentStatus(1);
        }
        paymentStatusMapper.updateAjBaseCheckInPaymentStatus(paymentStatus);
        return 1;
    }
}
