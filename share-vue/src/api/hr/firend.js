import request from '@/utils/request'

export function getFriendList(params) {
  return request({
    url: '/friend/list',
    method: 'GET',
    params
  })
}
