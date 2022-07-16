import request from '@/utils/request'

export function getOrganList(params) {
  return request({
    url: '/organ/list',
    method: 'GET',
    params
  })
}

export function addOrgan(data) {
  return request({
    url: '/organ',
    method: 'POST',
    data
  })
}

export function updateOrgan(id, data) {
  return request({
    url: `/organ/${id}`,
    method: 'PUT',
    data
  })
}

export function delOrgan(id) {
  return request({
    url: `/organ/${id}`,
    method: 'DELETE'
  })
}
