import request from '@/utils/request'

// 查询员工考勤列表
export function listTeacherCheckin(query) {
  return request({
    url: '/checkin/teacherCheckin/list',
    method: 'get',
    params: query
  })
}

// 查询员工考勤详细
export function getTeacherCheckin(teacherCheckInId) {
  return request({
    url: '/checkin/teacherCheckin/' + teacherCheckInId,
    method: 'get'
  })
}

// 新增员工考勤
export function addTeacherCheckin(data) {
  return request({
    url: '/checkin/teacherCheckin',
    method: 'post',
    data: data
  })
}

// 修改员工考勤
export function updateTeacherCheckin(data) {
  return request({
    url: '/checkin/teacherCheckin',
    method: 'put',
    data: data
  })
}

// 删除员工考勤
export function delTeacherCheckin(teacherCheckInId) {
  return request({
    url: '/checkin/teacherCheckin/' + teacherCheckInId,
    method: 'delete'
  })
}
