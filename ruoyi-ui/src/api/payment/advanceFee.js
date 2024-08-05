import request from '@/utils/request'

// 查询预收费列表
export function listAdvanceFee(query) {
  return request({
    url: '/payment/advanceFee/list',
    method: 'get',
    params: query
  })
}

// 查询预收费详细
export function getAdvanceFee(id) {
  return request({
    url: '/payment/advanceFee/' + id,
    method: 'get'
  })
}

// 新增预收费
export function addAdvanceFee(data) {
  return request({
    url: '/payment/advanceFee',
    method: 'post',
    data: data
  })
}

// 修改预收费
export function updateAdvanceFee(data) {
  return request({
    url: '/payment/advanceFee',
    method: 'put',
    data: data
  })
}

// 删除预收费
export function delAdvanceFee(id) {
  return request({
    url: '/payment/advanceFee/' + id,
    method: 'delete'
  })
}
