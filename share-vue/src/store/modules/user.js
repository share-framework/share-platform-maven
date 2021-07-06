import { login, logout, getInfoApi } from '@/api/user'
import { getToken, setToken, removeToken, setTokenPrefix } from '@/utils/auth'
import { resetRouter } from '@/router'
import Cookies from 'js-cookie'

const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
    avatar: '',
    xNumber: '',
    roles: []
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  X_NUMBER: (state, xNumber) => {
    state.xNumber = xNumber
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { number, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ number: number, password: password }).then(response => {
        const { data } = response
        commit('SET_TOKEN', data.prefix + ' ' + data.token)
        Cookies.set('xNumber', data.data.xnumber)
        setToken(data.token)
        setTokenPrefix(data.prefix)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfoApi(state.token).then(response => {
        const { data } = response

        if (!data) {
          reject('Verification failed, please Login again.')
        }
        data.roles = ['admin']
        // eslint-disable-next-line no-unused-vars
        const { roles, realName, avatar, xnumber } = data

        // roles must be a non-empty array
        if (!roles || roles.length <= 0) {
          reject('getInfo: roles must be a non-null array!')
        }

        commit('SET_ROLES', roles)
        commit('SET_NAME', realName)
        commit('SET_AVATAR', avatar)
        commit('X_NUMBER', xnumber)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        resetRouter()
        commit('RESET_STATE')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

