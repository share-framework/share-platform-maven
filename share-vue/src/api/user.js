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
