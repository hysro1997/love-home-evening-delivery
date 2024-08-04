import request from '@/utils/request'

// 查询学生费用模板列表
export function listStudentBillTemplate(query) {
  return request({
    url: '/bill/studentBillTemplate/list',
    method: 'get',
    params: query
  })
}

// 查询学生费用模板详细
export function getStudentBillTemplate(costTemplateId) {
  return request({
    url: '/bill/studentBillTemplate/' + costTemplateId,
    method: 'get'
  })
}

// 新增学生费用模板
export function addStudentBillTemplate(data) {
  return request({
    url: '/bill/studentBillTemplate',
    method: 'post',
    data: data
  })
}

// 修改学生费用模板
export function updateStudentBillTemplate(data) {
  return request({
    url: '/bill/studentBillTemplate',
    method: 'put',
    data: data
  })
}

// 删除学生费用模板
export function delStudentBillTemplate(costTemplateId) {
  return request({
    url: '/bill/studentBillTemplate/' + costTemplateId,
    method: 'delete'
  })
}
