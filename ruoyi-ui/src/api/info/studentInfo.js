import request from '@/utils/request'

// 查询学生信息列表
export function listStudentInfo(query) {
  return request({
    url: '/info/studentInfo/list',
    method: 'get',
    params: query
  })
}

// 查询学校信息
export function getSchools() {
  return request({
    url: '/info/studentInfo/initSchool',
    method: 'get'
  })
}

// 查询年级信息
export function getGrades() {
  return request({
    url: '/info/studentInfo/initGrade',
    method: 'get'
  })
}

// 查询学生信息详细
export function getStudentInfo(studentId) {
  return request({
    url: '/info/studentInfo/' + studentId,
    method: 'get'
  })
}

// 新增学生信息
export function addStudentInfo(data) {
  return request({
    url: '/info/studentInfo',
    method: 'post',
    data: data
  })
}

// 修改学生信息
export function updateStudentInfo(data) {
  return request({
    url: '/info/studentInfo',
    method: 'put',
    data: data
  })
}

// 删除学生信息
export function delStudentInfo(studentId) {
  return request({
    url: '/info/studentInfo/' + studentId,
    method: 'delete'
  })
}
