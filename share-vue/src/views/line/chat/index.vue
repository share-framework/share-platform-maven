<template>
  <div class="chat">
    <div style="display: flex; width: 98%; height: calc(100vh - 80px); margin: 10px; border-radius: 10px; overflow: hidden;
border: 1px solid #DCDFE6; background-color: #f6f9fe; box-shadow: 0 0 14px 0 #DCDFE6;">
      <div style="width: 360px;  height: calc(100vh - 100px); display: flex; justify-content: space-around;">
        <!--菜单栏-->
        <div style="width: 60px; height: calc(100vh - 100px); justify-content: flex-start;">
          <div style="width: 60px; height: 30px; display: flex; justify-content: space-evenly; align-items: center;">
            <i class="el-icon-circle-close"></i>
            <i class="el-icon-remove-outline"></i>
            <i class="el-icon-circle-plus-outline"></i>
          </div>
          <div style="width: 60px; height: 60px; display: flex; justify-content: center; align-items: center;">
            <div style="width: 40px; height: 40px; border-radius: 5px; overflow: hidden;">
              <div class="demo-basic--circle">
                <div class="block">
                  <el-avatar shape="square" :size="50" src="https://img1.baidu.com/it/u=420549850,1059016251&fm=26&fmt=auto&gp=0.jpg"></el-avatar>
                </div>
              </div>
            </div>
          </div>
          <div class="btn-menu">
            <i class="el-icon-chat-square"></i>
          </div>
          <div class="btn-menu">
            <i class="el-icon-user"></i>
          </div>
          <div class="btn-menu">
            <i class="el-icon-box"></i>
          </div>
          <div class="btn-menu">
            <i class="el-icon-attract"></i>
          </div>
        </div>
        <!--好友栏-->
        <div style="width: 300px;  height: calc(100vh - 100px); justify-content: flex-end; border-right: 1px solid rgba(56,56,56,0.17);">
          <div style=" width: 100%; height: 50px; padding: 10px; display: flex; justify-content: space-between; text-align: center; line-height: 20px;">
            <el-input
              placeholder="请输入好友昵称或者备注"
              v-model="input4" size="mini" style="width: 80%; margin-top: 2px;">
              <i slot="prefix" class="el-input__icon el-icon-search"></i>
            </el-input>
            <div style="width: 15%; height: 30px; font-size: 20px; line-height: 30px; text-align: left;">
              <i class="el-icon-zoom-in"> </i>
            </div>
          </div>
          <el-scrollbar style="height: 98%;">
            <ul class="infinite-list" v-infinite-scroll="load">
              <li v-for="friend in friendList"
                  :class="friend.xnumber == lineId ? 'infinite-list-item-select': 'infinite-list-item'"
                  @click="inUser(friend.xnumber, friend.realName)">
                <div style="width: 100%; height: 60px; display: flex; justify-content: space-between; align-items: center; padding: 10px;">
                  <div style="width: 40px; height: 40px; border-radius: 5px; overflow: hidden;">
                    <div class="demo-basic--circle">
                      <div class="block">
                        <el-avatar shape="square" :size="50" src="https://img0.baidu.com/it/u=3311900507,1448170316&fm=26&fmt=auto&gp=0.jpg"></el-avatar>
                        </div>
                    </div>
                  </div>
                  <div style="width: calc(100% - 40px); height: 60px;">
                    <div style="padding: 14px;">
                      <span>{{friend.realName}}</span>
                      <div class="bottom clearfix">
                        <time class="time">2021-07-05 23:29:27</time>
                      </div>
                    </div>
                  </div>
                </div>
              </li>
            </ul>
          </el-scrollbar>
        </div>
      </div>
      <!--右侧-->
      <div style="width: calc(100% - 360px);  height: calc(100vh - 100px);">
        <div style=" width: 100%; height: 50px; padding: 10px; display: flex; justify-content: space-between; text-align: center; line-height: 20px;  border-bottom: 1px solid rgba(56,56,56,0.17);">
          {{username}}
        </div>

        <line-chat v-if="toLineId != ''" :to-line-id="toLineId"></line-chat>
      </div>
    </div>
    <!--<input v-model="lineId" >
    <el-button @click="login" type="success">登录</el-button>-->
  </div>
</template>

<script>
  import { getFriendList } from '@/api/hr/firend'
  import LineChat from '@/components/LineChat'
  export default {
    name: "chat",
    components: {
      "line-chat": LineChat
    },
    data () {
      return {
        count: 0,
        input4: '',
        username: '',
        friendList: [],
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
        re_msg: '',
        ws: undefined,
        toLineId: "",
        lineId: "10000",
        isLogin: false
      }
    },
    methods: {
      load () {
        this.count = 100
      },
      inUser (lineId, name) {
        this.toLineId = lineId+"";
        console.log("=========");
        console.log(this.toLineId);
        this.username = name;
      },
      login () {
        this.$message({
          message: '登录成功！',
          type: 'success'
        });
      }
    },
    created() {
      this.lineId = this.$store.state.user.xNumber;
      getFriendList({}).then(response => {
        const { data } = response
        this.friendList = data
      }).catch(error => {
        this.$notify({
          title: '失败通知',
          message: error,
          type: 'error'
        })
      })
    }
  }
</script>

<style scoped>
  .chat {
    width: 100%;
    height: 90vh;
  }

  .infinite-list .infinite-list-item {
    padding: 0px;
    margin: 0px;
    display: flex;
    align-items: center;
    justify-content: space-around;
    height: 60px;
    background-color: #f6f9fe;
    padding: 0px!important;
  }

  .infinite-list-item-select {
    display: flex;
    align-items: center;
    justify-content: space-around;
    height: 60px;
    background-color: #eeeef1;
    padding: 0px!important;
  }

  .el-select .el-input {
    width: 130px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
  .infinite-list-item:hover {
    background-color: #f4f4f5;
  }

  .btn-menu {
    width: 60px;
    height: 60px;
    font-size: 30px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .btn-menu:hover {
    color: coral;
  }
</style>
