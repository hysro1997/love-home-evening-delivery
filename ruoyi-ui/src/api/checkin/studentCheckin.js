import request from '@/utils/request'

// 查询学生考勤列表
export function listStudentCheckin(query) {
  return request({
    url: '/checkin/studentCheckin/list',
    method: 'get',
    params: query
  })
}

// 查询学生考勤详细
export function getStudentCheckin(studentCheckInId) {
  return request({
    url: '/checkin/studentCheckin/' + studentCheckInId,
    method: 'get'
  })
}

// 新增学生考勤
export function addStudentCheckin(data) {
  return request({
    url: '/checkin/studentCheckin',
    method: 'post',
    data: data
  })
}

// 修改学生考勤
export function updateStudentCheckin(data) {
  return request({
    url: '/checkin/studentCheckin',
    method: 'put',
    data: data
  })
}

// 删除学生考勤
export function delStudentCheckin(studentCheckInId) {
  return request({
    url: '/checkin/studentCheckin/' + studentCheckInId,
    method: 'delete'
  })
}
