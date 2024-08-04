import request from '@/utils/request'

// 查询工资明细列表
export function listTeacherSalary(query) {
  return request({
    url: '/payment/teacherSalary/list',
    method: 'get',
    params: query
  })
}

// 查询工资明细详细
export function getTeacherSalary(id) {
  return request({
    url: '/payment/teacherSalary/' + id,
    method: 'get'
  })
}

// 新增工资明细
export function addTeacherSalary(data) {
  return request({
    url: '/payment/teacherSalary',
    method: 'post',
    data: data
  })
}

// 修改工资明细
export function updateTeacherSalary(data) {
  return request({
    url: '/payment/teacherSalary',
    method: 'put',
    data: data
  })
}

// 删除工资明细
export function delTeacherSalary(id) {
  return request({
    url: '/payment/teacherSalary/' + id,
    method: 'delete'
  })
}
