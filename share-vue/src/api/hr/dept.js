import request from '@/utils/request'

export function getDeptList(params) {
  return request({
    url: '/dept/list',
    method: 'GET',
    params
  })
}

export function addDept(data) {
  return request({
    url: '/dept',
    method: 'POST',
    data
  })
}

export function updateDept(id, data) {
  return request({
    url: `/dept/${id}`,
    method: 'PUT',
    data
  })
}

export function delDept(id) {
  return request({
    url: `/dept/${id}`,
    method: 'DELETE'
  })
}
