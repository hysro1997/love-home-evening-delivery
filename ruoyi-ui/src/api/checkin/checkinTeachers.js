import request from '@/utils/request'

// 查询员工考勤列表
export function listTeacherCheckin(query) {
  return request({
    url: '/checkin/checkinTeachers/list',
    method: 'get',
    params: query
  })
}

// 修改员工考勤
export function updateTeacherCheckin(data) {
  return request({
    url: '/checkin/checkinTeachers',
    method: 'put',
    data: data
  })
}
