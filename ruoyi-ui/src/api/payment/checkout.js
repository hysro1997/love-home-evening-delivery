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

// 依据考勤总表id统计考勤数据
export function statistic(data) {
  return request({
    url: '/payment/checkout/statistic',
    method: 'post',
    data: data
  })
}
