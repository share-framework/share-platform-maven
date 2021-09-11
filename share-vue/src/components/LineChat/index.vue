<template>
  <div>
    <div style="height: 400px;">
      <el-scrollbar style="height: 98%;" up >
        <div style="width: 100%; height: 58vh;">
          <div v-for="msg in msgList">
            <div v-if="msg.position == 0" style="width: 100%; display: flex; justify-content: flex-start;">
              <el-alert
                :title="msg.content"
                type="error"
                style="width: 40%; margin-top: 20px;">
              </el-alert>
            </div>
            <div v-else style="width: 100%; display: flex; justify-content: flex-end;">
              <el-alert
                :title="msg.content"
                type="success"
                style="width: 40%; margin-top: 20px;">
              </el-alert>
            </div>
          </div>
      </div>
      </el-scrollbar>
      <div style="width: 100%; height: 5vh; line-height: 5vh;
      text-align: center; display: flex; justify-content: flex-start;
        border-top: 1px solid rgba(56,56,56,0.17);">
        <div class="msg-btn">
          <i class="el-icon-microphone"></i>
        </div>
        <div class="msg-btn">
          <i class="el-icon-phone-outline"></i>
        </div>
        <div class="msg-btn">
          <i class="el-icon-video-camera"></i>
        </div>
        <div class="msg-btn">
          <i class="el-icon-picture-outline-round"></i>
        </div>
        <div class="msg-btn">
          <i class="el-icon-paperclip"></i>
        </div>
        <div class="msg-btn">
          <i class="el-icon-location-outline"></i>
        </div>
        <div class="msg-btn">
          <i class="el-icon-wind-power"></i>
        </div>
        <div class="msg-btn">
          <i class="el-icon-chat-line-square"></i>
        </div>
        <div class="msg-btn">
          <i class="el-icon-warning-outline"></i>
        </div>
      </div>
      <div style="width: 100%; height: 26%; position: relative;">
        <el-input
          type="textarea"
          placeholder="请输入内容"
          :rows="6"
          resize="none"
          v-model="sendContent">
        </el-input>
        <el-button type="primary" @click="send" size="mini" round style="position: absolute;
    bottom: 2vh;
    right: 10px;">发  送</el-button>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
      name: "LineChat",
      props: {
        toLineId: {
          type: String,
          required: true
        },
      },
      data() {
        return {
          websocket: null,
          msg: {
            header: {
              lineId: "",
              toLineId: "",
              msgType: 1
            },
            body: {
              id: 1,
              title: "2323",
              content: "",
              format: "",
              extra: ""
            },
            footer: {
              version: "1.0.0",
              timestamp: 3231212121323,
              clientName: "line"
            }
          },
          sendContent: undefined,
          msgList: []
        }
      },
      created(){
        console.log(process.env.VUE_APP_WEB_SOCKET_API)
        console.log(this.websocket)
        console.log("toLineId:"+this.toLineId)
        this.msg.header.lineId = this.$store.state.user.xNumber;
        this.msg.header.toLineId = this.toLineId;
        // 如果发现websocket不为空，先关闭，再建立连接
        if (this.websocket != null) {
          this.webSocketOnClose();
        }
        //页面刚进入时开启长连接
        this.initWebSocket()
      },
      destroyed: function() {
        //页面销毁时关闭长连接
        this.webSocketOnClose();
      },
      methods: {
        initWebSocket(){
          console.log(this.$store.state.user)
          this.websocket = new WebSocket(process.env.VUE_APP_WEB_SOCKET_API + "?lineId=" + this.$store.state.user.xNumber);
          this.websocket.onopen = this.webSocketOnOpen;

          this.websocket.onerror = this.webSocketOnError;

          this.websocket.onmessage = this.webSocketOnMessage;
        },
        webSocketOnOpen() {
          console.log("WebSocket连接成功");
        },
        webSocketOnError(e) {
          console.log("WebSocket连接发生错误");
          console.log(e);
          this.initWebSocket();
        },
        webSocketOnMessage(e){
          console.log("webSocketOnMessage");
          console.log(e.data);
          const redata = JSON.parse(e.data);
          // 接收数据
          console.log(redata);
          console.log(this.toLineId);
          if (redata.header.lineId == this.toLineId) {
            this.msgList.push({
              position: 0,
              content: redata.header.lineId + ": " + redata.body.content
            });
          } else {
            this.msgList.push({
              position: 1,
              content: this.msg.header.lineId + ": " + this.msg.body.content
            });
          }
        },
        send(){
          console.log("send");
          //数据发送
          this.msg.body.content = this.sendContent
          console.log(this.msg)
          this.websocket.send(JSON.stringify(this.msg));
          this.sendContent = undefined
        },

        webSocketOnClose(e){ //关闭
          console.log(e)
          console.log("connection closed");
          this.initWebSocket();
        },
      },
    }
</script>

<style scoped>
  .msg-btn {
    width: 10vh;
    height: 5vh;
    font-size: 24px;
  }
  .msg-btn:hover {
    background-color: #e8f3fe;
    color: coral;
  }
</style>
