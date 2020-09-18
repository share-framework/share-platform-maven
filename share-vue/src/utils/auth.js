import Cookies from 'js-cookie'

const TokenKey = 'share_cloud_token'
const TokenPrefix = 'share_cloud_prefix'

export function getToken() {
  if (Cookies.get(TokenPrefix)) {
    if (Cookies.get(TokenKey)) {
      return Cookies.get(TokenPrefix) + ' ' + Cookies.get(TokenKey)
    }
  }
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function setTokenPrefix(prefix) {
  return Cookies.set(TokenPrefix, prefix)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}
