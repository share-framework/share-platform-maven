<template>
  <div class="an-login">
    <el-container>
      <el-main class="an-login-main">
        <el-row>
          <el-col :span="16">
            <div :style="'background:url('+lg+') no-repeat; background-size: 100% 100%; width: 100%; height:'+height+'px;'"></div>
          </el-col>
          <el-col :span="8">
            <div>
              <div style="width: 100%; display: flex; justify-content: center; margin-top:30%;">
                <img :src="logo" width="50%" height="80%">
              </div>
              <div style="width: 100%; display: flex; justify-content: center;">
                <el-tabs v-model="activeName" @tab-click="tabSwitchClick" style="width:60%;">
                  <el-tab-pane label="注册" name="account">
                    <el-form style="width:100%" ref="loginForm" :model="loginForm" :rules="loginRules">
                      <el-form-item>
                        <el-input
                          ref="number"
                          v-model="loginForm.email"
                          placeholder="请输入邮箱"
                          prefix-icon="el-icon-user"
                          name="number"
                          type="text"
                          tabindex="1"
                          auto-complete="on"
                        />
                      </el-form-item>
                      <el-form-item>
                        <el-input
                          :key="passwordType"
                          ref="password"
                          v-model="loginForm.password"
                          placeholder="请输入密码"
                          :type="passwordType"
                          prefix-icon="el-icon-lock"
                          name="password"
                          tabindex="2"
                          auto-complete="on"
                          @keyup.enter.native="handleSign"
                          show-password
                        />
                      </el-form-item>
                      <el-button style="width:100%" type="primary" :loading="loading" @click="handleSign" >注册</el-button>
                    </el-form>
                  </el-tab-pane>
                </el-tabs>

              </div>
            </div>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import { validUsername } from '@/utils/validate'
import lg from '@/assets/bg.jpg'
import logo from '@/assets/logo.png'

export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error('Please enter the correct user name'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('The password can not be less than 6 digits'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        number: 10000,
        password: '123456'
      },
      loginRules: {
        number: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined,
      lg: lg,
      logo: logo,
      height: 900,
      activeName: 'account'
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleSign() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', {
            number: this.loginForm.number,
            password: this.loginForm.password
          }).then(() => {
            this.$router.push({ path: this.redirect || '/' })
            this.loading = false
          }).catch(() => {
            this.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    tabSwitchClick() {
      console.log(this.activeName)
      if (this.activeName === 'account') {
        this.activeName = 'scan'
      } else {
        this.activeName = 'account'
      }
    }
  },
  mounted() {
    this.height = document.body.clientHeight
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg:#283443;
$light_gray:#fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}

.an-login-main {
  padding: 0;
  height: 100%;
}
</style>
