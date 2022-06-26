import request from '@/utils/request'

export function getVideoStream() {
  return request({
    url: '/video/ffmpeg',
    method: 'GET'
  })
}
