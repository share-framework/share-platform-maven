import request from '@/utils/request'

export function getAuthMenu() {
  return request({
    url: '/menu/tree',
    method: 'GET'
  })
}

export function addMenu(data) {
  return request({
    url: '/menu',
    method: 'POST',
    data
  })
}
