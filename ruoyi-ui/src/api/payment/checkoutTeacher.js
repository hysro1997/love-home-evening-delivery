import request from '@/utils/request'

// 查询教师信息列表
export function listTeachers(query) {
  return request({
    url: '/payment/checkoutTeacher/listTeachers',
    method: 'get',
    params: query
  })
}

// 查询教师工资列表
export function listTeacherSalary(query) {
  return request({
    url: '/payment/checkoutTeacher/listTeacherSalary',
    method: 'get',
    params: query
  })
}

// 查询教师考勤信息
export function getTeacherCheckinStatistic(query) {
  return request({
    url: '/payment/checkoutTeacher/getTeacherCheckinStatistic',
    method: 'get',
    params: query
  })
}

// 查询工资模板列表
export function listSalaryTemplate(query) {
  return request({
    url: '/payment/checkoutTeacher/listSalaryTemplate',
    method: 'get',
    params: query
  })
}
