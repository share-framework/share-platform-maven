import request from '@/utils/request'

export function getAuthMenu() {
  return request({
    url: '/menu/tree',
    method: 'GET'
  })
}

export function getManageTreeList(params) {
  return request({
    url: '/menu/manage/tree',
    method: 'GET',
    params
  })
}

export function addMenu(data) {
  return request({
    url: '/menu',
    method: 'POST',
    data
  })
}

export function updateMenu(id, data) {
  return request({
    url: '/menu',
    method: 'PUT',
    data
  })
}

export function delMenu(params) {
  return request({
    url: '/menu',
    method: 'DELETE',
    params
  })
}
