import request from '@/utils/request'

export function getIcons() {
  return request({
    url: '/icons/list',
    method: 'GET'
  })
}
