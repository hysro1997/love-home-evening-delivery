import request from '@/utils/request'

// 查询教师考勤汇总列表
export function listTeacherCheckinStatistic(query) {
  return request({
    url: '/checkin/teacherCheckinStatistic/list',
    method: 'get',
    params: query
  })
}

// 查询教师考勤汇总详细
export function getTeacherCheckinStatistic(id) {
  return request({
    url: '/checkin/teacherCheckinStatistic/' + id,
    method: 'get'
  })
}

// 新增教师考勤汇总
export function addTeacherCheckinStatistic(data) {
  return request({
    url: '/checkin/teacherCheckinStatistic',
    method: 'post',
    data: data
  })
}

// 修改教师考勤汇总
export function updateTeacherCheckinStatistic(data) {
  return request({
    url: '/checkin/teacherCheckinStatistic',
    method: 'put',
    data: data
  })
}

// 删除教师考勤汇总
export function delTeacherCheckinStatistic(id) {
  return request({
    url: '/checkin/teacherCheckinStatistic/' + id,
    method: 'delete'
  })
}
