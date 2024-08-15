import request from '@/utils/request'

// 查询年级列表
export function listGrade() {
  return request({
    url: '/checkin/checkinStudents/list',
    method: 'get'
  })
}

// 查询学生考勤列表
export function listCheckinStudents(query) {
  return request({
    url: '/checkin/checkinStudents/listStudents',
    method: 'post',
    params: query
  })
}

// 修改学生考勤
export function updateCheckinStudent(data) {
  return request({
    url: '/checkin/checkinStudents',
    method: 'put',
    data: data
  })
}

// 修改学生考勤
export function updateCheckinStudentList(data) {
  return request({
    url: '/checkin/checkinStudents/editList',
    method: 'put',
    data: data
  })
}
