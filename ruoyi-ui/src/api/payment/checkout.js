import request from '@/utils/request'

// 查询考勤总表列表
export function listBaseCheckin(query) {
  return request({
    url: '/payment/checkout/listBaseCheckin',
    method: 'get',
    params: query
  })
}

// 查询学生考勤统计详情
export function studentStatistic(query) {
  return request({
    url: '/payment/checkout/studentStatistic',
    method: 'get',
    params: query
  })
}

// 查询考勤总表列表
export function listStudents(query) {
  return request({
    url: '/payment/checkout/listStudents/'+ query.baseCheckInId + '/' + query.studentGrade,
    method: 'get'
  })
}

// 依据考勤总表id统计考勤数据
export function statistic(data) {
  return request({
    url: '/payment/checkout/statistic',
    method: 'post',
    data: data
  })
}

// 查询学生考勤日期详情
export function studentCheckInDays(query) {
  return request({
    url: '/payment/checkout/studentCheckInDays',
    method: 'get',
    params: query
  })
}

// 查询收费模板列表
export function getCostTemplate() {
  return request({
    url: '/payment/checkout/getCostTemplate',
    method: 'get'
  })
}

// 查询收费模板详情
export function getCostTemplateDetail(costTemplateId) {
  return request({
    url: '/payment/checkout/getCostTemplate/' + costTemplateId,
    method: 'get'
  })
}

// 新增学生账单明细
export function addStudentBill(data) {
  return request({
    url: '/payment/checkout/studentBill',
    method: 'post',
    data: data
  })
}
