<template>
  <div>
    <div style="height: 400px;">
      <div style="width: 100%; height: 60vh; overflow-y: auto;">
        <div style="width: 100%; display: flex; justify-content: flex-start;" v-for="title in titles">
          <el-alert

            :title="title"
            type="error" style="width: 40%; margin-top: 20px;">
          </el-alert>
        </div>
        <div style="width: 100%; display: flex;
    justify-content: flex-end;" v-for="title in metitles">
          <el-alert

            :title="title"
            type="success" style="width: 40%; margin-top: 20px;">
          </el-alert>
        </div>
      </div>
      <div style="width: 100%; height: 18vh; position: relative;">
        <el-input
          type="textarea"
          placeholder="请输入内容"
          :rows="6"
          v-model="message">
        </el-input>
        <el-button type="primary" @click="send" style="position: absolute;
    bottom: -6px;
    right: 2px;">发送</el-button>
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
          titles: [],
          metitles: [],
          message: undefined
        }
      },
      created(){
        //页面刚进入时开启长连接
        this.initWebSocket()
      },
      destroyed: function() {
        //页面销毁时关闭长连接
        this.websocketclose();
      },
      mounted() {
        this.msg.header.toLineId = this.toLineId;
        this.msg.header.lineId = this.$store.state.user.xNumber;
      },
      methods: {
        initWebSocket(){
          console.log(this.$store.state.user)
          this.websocket = new WebSocket("ws://127.0.0.1:8001/ws/conn?lineId="+this.$store.state.user.xNumber);
          this.websocket.onopen = this.websocketonopen;

          this.websocket.onerror = this.websocketonerror;

          this.websocket.onmessage = this.websocketonmessage;
          this.websocket.onclose = this.websocketclose;
        },
        websocketonopen() {
          console.log("WebSocket连接成功");
        },
        websocketonerror(e) { //错误
          console.log("WebSocket连接发生错误");
        },
        websocketonmessage(e){
          console.log(e.data);
          const redata = JSON.parse(e.data);
          // 接收数据
          console.log(redata);
          if (redata.header.lineId == this.toLineId) {
            this.titles.push(redata.header.lineId + ": " + redata.body.content);
          } else {
            this.metitles.push(redata.header.lineId + ": " + redata.body.content);
          }
        },
        send(){
          //数据发送
          this.msg.body.content = this.message
          this.websocket.send(JSON.stringify(this.msg));
          this.message = undefined
        },

        websocketclose(e){ //关闭
          console.log("connection closed (" + e.code + ")");
        },
      },
    }
</script>

<style scoped>

</style>
