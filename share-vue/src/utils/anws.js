let websocket;

export function connection (url, params, func) {
  let urlParam = '?';
  for (const param in params) {
    urlParam += `${param.name}=${param.value}&`
  }
  urlParam = urlParam.substr(0, urlParam.length - 1);
  console.log(urlParam)
  websocket = new WebSocket(url + urlParam);
  this.websocket.onmessage = func.accept;
  this.websocket.onopen = func.open;
  this.websocket.onerror = func.error;
  this.websocket.onclose = func.close;
  return websocket;
}
/*
(function () {
  console.log("===123===")
} (function () {
  function connection (url, params, func) {
    let urlParam = '?';
    for (const param in params) {
      urlParam += `${param.name}=${param.value}&`
    }
    urlParam = urlParam.substr(0, urlParam.length - 1);
    console.log(urlParam)
    websocket = new WebSocket(url + urlParam);
    this.websocket.onmessage = func.accept;
    this.websocket.onopen = func.open;
    this.websocket.onerror = func.error;
    this.websocket.onclose = func.close;
    return websocket;
  }
  module.exports = connection

}))

          this.websocket = connection(process.env.VUE_APP_WEB_SOCKET_API,
            [{
              name: 'lineId',
              value: this.$store.state.user.xNumber
            },
            {
              name: 'token',
              value: 'token'
            }],
            {
              close: this.webSocketOnClose,
              open: this.webSocketOnOpen,
              error: this.webSocketOnError,
              accept: this.webSocketOnMessage
            }
          );
*/
