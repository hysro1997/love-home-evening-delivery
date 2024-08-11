import request from '@/utils/request'

// 查询在考勤中的老师与学生列表
export function listHomoInBaseCheckin(query) {
  return request({
    url: '/checkin/homoInBaseCheckin/list',
    method: 'get',
    params: query
  })
}

// 查询在考勤中的老师与学生详细
export function getHomoInBaseCheckin(id) {
  return request({
    url: '/checkin/homoInBaseCheckin/' + id,
    method: 'get'
  })
}

// 新增在考勤中的老师与学生
export function addHomoInBaseCheckin(data) {
  return request({
    url: '/checkin/homoInBaseCheckin',
    method: 'post',
    data: data
  })
}

// 修改在考勤中的老师与学生
export function updateHomoInBaseCheckin(data) {
  return request({
    url: '/checkin/homoInBaseCheckin',
    method: 'put',
    data: data
  })
}

// 删除在考勤中的老师与学生
export function delHomoInBaseCheckin(id) {
  return request({
    url: '/checkin/homoInBaseCheckin/' + id,
    method: 'delete'
  })
}
