<template>
  <div class="app-container">
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
        desc: ''
      }
    }
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

