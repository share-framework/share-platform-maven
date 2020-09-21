import request from '@/utils/request'

export function getRoleList(data) {
  return request({
    url: '/role/table',
    method: 'POST',
    data
  })
}

/**
 * 获取有角色权限的菜单
 * @param params
 * @returns {AxiosPromise}
 */
export function getRoleMenuList(params) {
  return request({
    url: '/role/menu',
    method: 'GET',
    params
  })
}

export function getRole(id) {
  return request({
    url: `/role/${id}`,
    method: 'GET'
  })
}

export function addRole(data) {
  return request({
    url: '/role',
    method: 'POST',
    data
  })
}

export function updateRole(id, data) {
  return request({
    url: `/role/${id}`,
    method: 'PUT',
    data
  })
}

export function delRole(id) {
  return request({
    url: `/role/${id}`,
    method: 'DELETE'
  })
}

export function addRolePermission(params) {
  return request({
    url: `/role/menu`,
    method: 'POST',
    params
  })
}

export function delRolePermission(params) {
  return request({
    url: `/role/menu`,
    method: 'DELETE',
    params
  })
}
