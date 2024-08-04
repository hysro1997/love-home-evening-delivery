import request from '@/utils/request'

// 查询学生账单明细列表
export function listStudentBill(query) {
  return request({
    url: '/payment/studentBill/list',
    method: 'get',
    params: query
  })
}

// 查询学生账单明细详细
export function getStudentBill(id) {
  return request({
    url: '/payment/studentBill/' + id,
    method: 'get'
  })
}

// 新增学生账单明细
export function addStudentBill(data) {
  return request({
    url: '/payment/studentBill',
    method: 'post',
    data: data
  })
}

// 修改学生账单明细
export function updateStudentBill(data) {
  return request({
    url: '/payment/studentBill',
    method: 'put',
    data: data
  })
}

// 删除学生账单明细
export function delStudentBill(id) {
  return request({
    url: '/payment/studentBill/' + id,
    method: 'delete'
  })
}
