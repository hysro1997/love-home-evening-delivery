import request from '@/utils/request'

// 查询学生信息列表
export function listStudentInfo(query) {
  return request({
    url: '/info/manageStudent/listStudents',
    method: 'get',
    params: query
  })
}

// 查询学校信息
export function getSchools() {
  return request({
    url: '/info/manageStudent/initSchool',
    method: 'get'
  })
}

// 查询年级信息
export function getGrades() {
  return request({
    url: '/info/manageStudent/initGrade',
    method: 'get'
  })
}

// 查询学生信息详细
export function getStudentInfo(studentId) {
  return request({
    url: '/info/manageStudent/studentDetail/' + studentId,
    method: 'get'
  })
}

// 新增学生信息
export function addStudentInfo(data) {
  return request({
    url: '/info/manageStudent/addStudent',
    method: 'post',
    data: data
  })
}

// 修改学生信息
export function updateStudentInfo(data) {
  return request({
    url: '/info/manageStudent/alterStudent',
    method: 'put',
    data: data
  })
}

// 查询考勤总表列表
export function listBaseCheckin(query) {
  return request({
    url: '/info/manageStudent/listBaseCheckIn',
    method: 'get',
    params: query
  })
}

// 新增在考勤中的老师与学生
export function addHomoInBaseCheckin(data) {
  return request({
    url: '/info/manageStudent/addHomo',
    method: 'post',
    data: data
  })
}

// 查询在考勤中的老师与学生列表
export function listHomoInBaseCheckin(query) {
  return request({
    url: '/info/manageStudent/listHomo',
    method: 'get',
    params: query
  })
}

// 删除在考勤中的老师与学生
export function delHomoInBaseCheckin(id) {
  return request({
    url: '/info/manageStudent/delHomo/' + id,
    method: 'delete'
  })
}

// 查询预收费列表
export function listAdvanceFee(query) {
  return request({
    url: '/info/manageStudent/listAdvanceFee',
    method: 'get',
    params: query
  })
}

// 新增预收费
export function addAdvanceFee(data) {
  return request({
    url: '/info/manageStudent/addAdvanceFee',
    method: 'post',
    data: data
  })
}

// 修改预收费
export function updateAdvanceFee(data) {
  return request({
    url: '/info/manageStudent/alterAdvanceFee',
    method: 'put',
    data: data
  })
}

// 查询在考勤中的老师与学生详细
export function getInSchoolStudents() {
  return request({
    url: '/info/manageStudent/schoolStudents',
    method: 'get'
  })
}

// 查询考勤总表详细
export function getBaseCheckin(baseCheckInId) {
  return request({
    url: '/info/manageStudent/baseCheckIn/' + baseCheckInId,
    method: 'get'
  })
}

// 修改考勤总表
export function updateHomoInCheckin(data) {
  return request({
    url: '/info/manageStudent/alterBaseCheckIn',
    method: 'put',
    data: data
  })
}
