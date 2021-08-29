export function msgSuccess (message) {
  this.$notify({
    title: '成功通知',
    message: message,
    type: 'success'
  })
}

export function msgError (message) {
  this.$notify({
    title: '错误通知',
    message: message,
    type: 'error'
  })
}

export function msgInfo (message) {
  this.$notify({
    title: '提醒',
    message: message,
    type: 'waring'
  })
}
