import request from '@/utils/request'

// 查询考勤总表列表
export function listBaseCheckin(query) {
  return request({
    url: '/payment/checkout/listBaseCheckin',
    method: 'get',
    params: query
  })
}

// 查询考勤总表列表
export function listStudents(query) {
  return request({
    url: '/payment/checkout/listStudents/'+ query.baseCheckInId + '/' + query.studentGrade,
    method: 'get'
  })
}
