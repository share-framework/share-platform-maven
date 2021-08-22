import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n

import '@/styles/index.scss' // global css
import '@/styles/share.scss' // share css

import App from './App'
import store from './store'
import router from './router'
import {msgSuccess, msgError, msgInfo} from '@/utils/notify'
import {parseTime, formatTime} from '@/utils'

import '@/icons' // icon
import '@/permission' // permission control
import directive from '@/directive' // directive

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online ! ! !
 */
if (process.env.NODE_ENV === 'production') {
  const { mockXHR } = require('../mock')
  mockXHR()
}

// set ElementUI lang to EN
Vue.use(ElementUI, { locale })
// 如果想要中文版 element-ui，按如下方式声明
// Vue.use(ElementUI)
// 自定义 指令
Vue.use(directive)

Vue.config.productionTip = false

Vue.prototype.msgSuccess = msgSuccess
Vue.prototype.msgError = msgError
Vue.prototype.msgInfo = msgInfo
Vue.prototype.parseTime = parseTime
Vue.prototype.formatTime = formatTime


new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
