import request from '@/utils/request'

// 查询老师工资模板列表
export function listTeacherBillTemplate(query) {
  return request({
    url: '/bill/teacherBillTemplate/list',
    method: 'get',
    params: query
  })
}

// 查询老师工资模板详细
export function getTeacherBillTemplate(salaryId) {
  return request({
    url: '/bill/teacherBillTemplate/' + salaryId,
    method: 'get'
  })
}

// 新增老师工资模板
export function addTeacherBillTemplate(data) {
  return request({
    url: '/bill/teacherBillTemplate',
    method: 'post',
    data: data
  })
}

// 修改老师工资模板
export function updateTeacherBillTemplate(data) {
  return request({
    url: '/bill/teacherBillTemplate',
    method: 'put',
    data: data
  })
}

// 删除老师工资模板
export function delTeacherBillTemplate(salaryId) {
  return request({
    url: '/bill/teacherBillTemplate/' + salaryId,
    method: 'delete'
  })
}
