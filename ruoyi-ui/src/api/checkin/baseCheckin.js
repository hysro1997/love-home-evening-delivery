import request from '@/utils/request'

// 查询考勤总表列表
export function listBaseCheckin(query) {
  return request({
    url: '/checkin/baseCheckin/list',
    method: 'get',
    params: query
  })
}

// 查询考勤总表详细
export function getBaseCheckin(baseCheckInId) {
  return request({
    url: '/checkin/baseCheckin/' + baseCheckInId,
    method: 'get'
  })
}

// 新增考勤总表
export function addBaseCheckin(data) {
  return request({
    url: '/checkin/baseCheckin',
    method: 'post',
    data: data
  })
}

// 修改考勤总表
export function updateBaseCheckin(data) {
  return request({
    url: '/checkin/baseCheckin',
    method: 'put',
    data: data
  })
}

// 删除考勤总表
export function delBaseCheckin(baseCheckInId) {
  return request({
    url: '/checkin/baseCheckin/' + baseCheckInId,
    method: 'delete'
  })
}
