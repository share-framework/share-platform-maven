import request from '@/utils/request'

export function login(data) {
  console.log(1)
  return request({
    url: '/login',
    method: 'post',
    data
  })
}

export function getInfoApi() {
  return request({
    url: '/user/info',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}

//

export function getUserList(data) {
  return request({
    url: '/user/table',
    method: 'POST',
    data
  })
}

export function addUser(data) {
  return request({
    url: '/user',
    method: 'POST',
    data
  })
}

export function updateUser(id, data) {
  return request({
    url: `/user/${id}`,
    method: 'PUT',
    data
  })
}

export function delUser(id) {
  return request({
    url: `/user/${id}`,
    method: 'DELETE'
  })
}

export function phoneSign(phone, password) {
  let data = {
    phone: phone,
    password: password
  }
  return request({
    url: '/phone/sign',
    method: 'post',
    data
  })
}

export function emailSign(email, password) {
  let data = {
    email: email,
    password: password
  }
  return request({
    url: '/email/sign',
    method: 'post',
    data
  })
}

export function productXNumber() {
  return request({
    url: '/user/product/xnumber',
    method: 'post'
  })
}
