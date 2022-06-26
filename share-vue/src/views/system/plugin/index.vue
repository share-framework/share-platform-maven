<template>
  <div class="app-container">
    <el-row>
      <el-col :span="24">
        <div id="vs"></div>

        <video id="my-video" class="video-js" playsinline controls preload="auto" width="100%" height="800"
                data-setup="{}">
          <source src="" type="video/mp4">
          <source src="" type="video/ogg">
          <source src="" type="video/webm">
          <p class="vjs-no-js"> To view this video please enable JavaScript, and consider upgrading to a web browser that <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a> </p>
        </video>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <el-carousel trigger="click" height="300px">
          <el-carousel-item v-for="item in 4" :key="item">
            <img src="https://s.cn.bing.net/th?id=OHR.GiffordPinchot_ZH-CN2050686223_1920x1080.jpg&rf=LaDigue_1920x1080.jpg" class="image">
          </el-carousel-item>
        </el-carousel>
      </el-col>
    </el-row>
    <el-row v-for="i in 4">
      <el-col :span="6" v-for="i in 4">
        <div style="width: 100%; padding-right: 10px;">
          <el-card :body-style="{ padding: '0px' }">
            <div style="width: 100%; height: 160px; background-color: #1c84c6; overflow: hidden;">
              <img src="https://tse1-mm.cn.bing.net/th/id/R-C.b32c03ba8886696a6a937d561d77961e?rik=1sZKzCsuHBH2Jw&riu=http%3a%2f%2fup.keaitupian.com%2fpic%2f9e%2fcd%2f30%2f9ecd30e1e1ee78e3d23bbe7620af5489.jpg&ehk=IV6MqVdssct2y%2f0AnOyWTjgFZuZs8P3hLhSynPLoZx0%3d&risl=&pid=ImgRaw&r=0" width="100%" class="image">
            </div>
            <div style="width: 100%; height: 70px; ">
              <div style="width: 100%; height: 40px; font-size:18px; padding: 5px; display: flex; justify-content: space-between;">
                <div>
                  <span>支付插件</span>
                  <el-tag type="danger" size="mini" style="margin-top: -10px;">hot</el-tag>
                </div>
                <el-button plain size="mini">安装</el-button>
              </div>
              <div style="width: 100%; height: 20px; padding: 5px; display: flex; justify-content: space-between; font-size: 14px;">
                <div style="width: 150px;">
                  <el-rate
                    v-model="3.7"
                    disabled
                    show-score
                    text-color="#ff9900"
                    score-template="3.7">
                  </el-rate>
                </div>
                <div>
                  <span>张三</span>
                </div>
              </div>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Player from 'xgplayer';
import 'xgplayer-mp4';
import { getToken } from '@/utils/auth'
import { getVideoStream } from '@/api/system/plugin'

export default {
  data() {
    return {
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        mediaSource: undefined,
        desc: '',
        video: undefined
      }
    }
  },
  mounted() {
    const player = new Player({
      id: 'vs',
      url: 'http://localhost:9090/api/video/id'
    })
    /*let video = document.getElementById("my-video");
    window.URL = window.URL || window.webkitURL;
    let xhr = new XMLHttpRequest();
    let play_url = 'http://localhost:9090/api/video/id';
    xhr.open("GET", play_url, true);
    xhr.setRequestHeader("Range", "bytes=0-")
    xhr.responseType = "blob";
    xhr.onload = function() {
      if (this.status == 206) {
        let blob = this.response;
        console.log(blob);
        video.onload = function(e) {
          window.URL.revokeObjectURL(video.src);
        };
        video.src = window.URL.createObjectURL(blob);
      }
    }
    xhr.send();*/
   /* let url = 'http://localhost/api/video/id';  // url
    let mimeCodec = 'video/mp4; codecs="avc1.640028, mp4a.40.2"'; // 编码格式
    this.v_init('#my-video',url,mimeCodec); // 调用 #video 是选择器 id*/
  },
  methods: {
    onSubmit() {
      this.$message('submit!')
    },
    onCancel() {
      this.$message({
        message: 'cancel!',
        type: 'warning'
      })
    },
    // 初始化 selector / assetUrl / mimeCodec / autoPlay
    // selector：video的选择器 exp: '#video'
    // assetUrl: video的请求地址 exp : './v.mp4'
    // mimeCodec: 编码模式  exp:  'video/mp4; codecs="avc1.640028, mp4a.40.2"'
    v_init: function (selector, assetUrl, mimeCodec) {
      this.mediaSource = new MediaSource();
      this.video = document.querySelector(selector); // 获取vide dom
      this.assetUrl = assetUrl;
      this.mimeCodec = mimeCodec;
      this.v_start();// 开启
    },
    v_start: function () {
      console.log(this.mediaSource.readyState); // closed
      this.video.src = URL.createObjectURL(this.mediaSource);
      this.fetchAB(this.assetUrl)
    },
    // 基于 XHR 的简单封装
    // arguments - url
    // arguments - cb (回调函数)
    fetchAB: function (url) {
      var xhr = new XMLHttpRequest;
      xhr.open('get', url, true);
      xhr.setRequestHeader("Range", "bytes=0-4096")/*
      xhr.setRequestHeader("Host", "127.0.0.1:9090")
      xhr.setRequestHeader("Sec-Fetch-Mode", "cors")
      xhr.setRequestHeader("Origin", "http://localhost:9528")*/
      xhr.setRequestHeader("X-Token", getToken())
      xhr.responseType = 'blob';
      var _this = this;
      xhr.onload = function () {
        var sourceBuffer = this.mediaSource.addSourceBuffer(this.mimeCodec);
        sourceBuffer.addEventListener('updateend', function (_) {
          _this.mediaSource.endOfStream();// 结束
          _this.video.play(); // 播放视频
          console.log(_this.mediaSource.readyState); // ended
        });
        sourceBuffer.appendBuffer(buf);
      };
      xhr.send();
    }
  }
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
.el-rate__icon {
  margin-right: -3px!important;
}
</style>

