import request from '@/utils/request'

// 查询考勤统计列表
export function listStudentCheckinStatistic(query) {
  return request({
    url: '/checkin/studentCheckinStatistic/list',
    method: 'get',
    params: query
  })
}

// 查询考勤统计详细
export function getStudentCheckinStatistic(id) {
  return request({
    url: '/checkin/studentCheckinStatistic/' + id,
    method: 'get'
  })
}

// 新增考勤统计
export function addStudentCheckinStatistic(data) {
  return request({
    url: '/checkin/studentCheckinStatistic',
    method: 'post',
    data: data
  })
}

// 修改考勤统计
export function updateStudentCheckinStatistic(data) {
  return request({
    url: '/checkin/studentCheckinStatistic',
    method: 'put',
    data: data
  })
}

// 删除考勤统计
export function delStudentCheckinStatistic(id) {
  return request({
    url: '/checkin/studentCheckinStatistic/' + id,
    method: 'delete'
  })
}
