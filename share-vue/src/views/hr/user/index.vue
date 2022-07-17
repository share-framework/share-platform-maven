<template>
  <div class="app-container">
    <el-row>
      <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
        <el-button
           type="primary"
           size="small"
           icon="el-icon-circle-plus-outline"
           @click="add">新增</el-button>
        <el-button
           type="primary"
           size="small"
           icon="el-icon-circle-plus-outline"
           @click="product">账号生产</el-button>
        <el-button
          :class="showDelBtn?'hidden':''"
          title="为角色分配菜单权限"
          size="small"
          icon="el-icon-delete"
          @click="loadMenu">菜单权限</el-button>
        <el-button
          :class="showDelBtn?'hidden':''"
          title="删除菜单，可以删除多个"
           size="small"
           icon="el-icon-delete"
           @click="delMenuHandle">删除</el-button>

      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <el-table
          :data="userData"
          fit
          @selection-change="handleSelectionChange">
          <el-table-column
            fixed
            prop="userId"
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
            prop="xnumber"
            label="账号">
          </el-table-column>
          <el-table-column
            prop="realName"
            label="用户名称">
          </el-table-column>
<!--          <el-table-column
            prop="userType"
            label="角色类型">
            <template slot-scope="scope">
              {{userTypeMap[scope.row.userType]}}
            </template>
          </el-table-column>-->
          <el-table-column
            prop="introduction"
            align="center"
            label="说明">
          </el-table-column>
<!--          <el-table-column
            prop="disabled"
            align="center"
            label="禁用状态">
            <template slot-scope="scope">
              <i v-if="scope.row.disabled==1" class="status-info status-danger" title="停用"></i>
              <i v-else  class="status-info status-success" title="启用"></i>
            </template>
          </el-table-column>-->
          <el-table-column
            fixed="right"
            label="操作"
            min-width="100">
            <template slot-scope="scope">
              <div v-if="scope.row.userType != 1">
                <el-button size="mini"
                           type="text"
                           icon="el-icon-edit"
                           @click="handleSee(scope.row)"
                           v-hasPermi="['organ:user:see']"
                >查看</el-button>
                <el-button size="mini"
                           type="text"
                           icon="el-icon-edit"
                           @click="handleUpdate(scope.row)"
                           v-hasPermi="['organ:user:update']"
                >修改</el-button>
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  v-hasPermi="['organ:user:remove']"
                >删除</el-button>
              </div>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="1"
          :page-sizes="[10, 20, 50, 100]"
          :page-size="10"
          layout="total, sizes, prev, pager, next, jumper"
          :total="userBodyParam.total">
        </el-pagination>
      </el-col>
    </el-row>
    <!--  弹窗位置  -->
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="50%"
      class="log-form">
      <div>
        <el-form ref="menuForm" :show="false" :rules="rules" :model="user" label-width="80px">
          <el-form-item label="账号" prop="menuName">
            <el-input v-model="user.xnumber" :readonly="seeShow"></el-input>
          </el-form-item>
          <el-form-item label="用户名称" prop="menuCode">
            <el-input v-model="user.realName" :readonly="seeShow"></el-input>
          </el-form-item>
<!--          <el-form-item label="角色类型">
            <el-select v-model="user.userType" placeholder="请选择系统" :disabled="seeShow" class="el-select">
              <el-option v-for="userType in userTypes" :key="userType.id"
                         :label="userType.name" :value="userType.id"></el-option>
            </el-select>
          </el-form-item>-->
          <el-form-item label="备注">
            <el-input v-model="user.introduction" :disabled="seeShow" type="textarea" placeholder="请输入内容"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer" :style="'display:' + (btnReadonly?'block':'none')">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="enterHandler">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getUserList, addUser, updateUser, delUser, productXNumber } from '@/api/user'

export default {
  data() {
    return {
      userBodyParam: {
        page: 1,
        param: {
          userCode: '',
          userId: 0,
          userName: '',
          userType: 0
        },
        rows: 10,
        total: 0
      },
      userData: [],
      user: {
        userId: 0,
        userName: '',
        userCode: '',
        userOrder: 1,
        userType: 0,
        disabled: false,
        memo: undefined,
        menuCheckStrictly: true,
        deptCheckStrictly: true
      },
      rules: {
        userName: [
          { required: true, message: '请输入角色名称', trigger: 'blur' },
          { min: 1, max: 8, message: '长度在 1 到 8 个字符', trigger: 'blur' }
        ],
        userCode: [
          { required: true, message: '请输入角色编码', trigger: 'blur' },
          { min: 1, max: 32, message: '长度在 1 到 32 个字符', trigger: 'blur' }
        ]
      },
      dialogVisible: false,
      menuPermissionDialogVisible: false,
      btnReadonly: true,
      seeShow: false,
      method: '',
      iconsData: [],
      showMoveBtn: true,
      showDelBtn: true,
      allDialog: {
        moveDialogVisible: false
      },
      // 菜单列表
      menuTreeData: [],
      defaultProps: {
        label: 'menuName'
      },
      // 默认选中菜单
      defaultCheckedKeys: [],
      userTypes: [{
        id: 1,
        name: '超级管理员'
      }, {
        id: 2,
        name: '管理员'
      }, {
        id: 3,
        name: '普通用户'
      }],
      userTypeMap: {
        1: '超级管理员',
        2: '管理员',
        3: '普通用户',
      },
      // 部门列表
      deptOptions: [],
      menuExpand: false,
      menuNodeAll: false
    }
  },
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
      this.userBodyParam.rows = val
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
      this.userBodyParam.page = val
      this.load(this.userBodyParam)
    },
    load(data) {
      getUserList(data).then(response => {
        const { data, total } = response
        this.userData = data
        this.userBodyParam.total = total
      }).catch(error => {
        this.$notify({
          title: '失败通知',
          message: error,
          type: 'error'
        })
      })
    },
    add() {
      this.seeShow = false
      this.btnReadonly = true
      this.dialogVisible = true
      this.user = {
        userId: 0,
        userName: '',
        userCode: '',
        userType: 3,
        userOrder: 1,
        disabled: false,
        memo: undefined
      }
      this.method = 'add'
    },
    product() {
      productXNumber().then(response => {
        const { code, data, message } = response
        if (code === 200) {
          this.$notify({
            title: '成功通知',
            message: data,
            type: 'success'
          })
        }
      }).catch(error => {
        this.$notify({
          title: '失败通知',
          message: error,
          type: 'error'
        })
      })
    },
    handleSee(row) {
      this.seeShow = true
      this.btnReadonly = false
      this.propCopy(row)
      console.log(row)
      this.dialogVisible = true
      this.user.userId = row.userId
      this.loadMenu();
    },
    handleUpdate(row) {
      this.seeShow = false
      this.btnReadonly = true
      this.propCopy(row)
      this.dialogVisible = true
      this.method = 'edit'
      this.user.userId = row.userId
      this.loadMenu();
    },
    handleDelete(row) {

    },
    enterHandler() {
      const that = this
      if (that.method === 'add') {
        this.user.userId = 0
        addUser(this.user).then(response => {
          const { code, message } = response
          if (code === 200) {
            this.$notify({
              title: '成功通知',
              message: `[ ${that.user.userName} ] ` + message,
              type: 'success'
            })
            that.load(that.userBodyParam)
            that.dialogVisible = false
          }
        }).catch(error => {
          this.$notify({
            title: '失败通知',
            message: `[ ${that.user.userName} ] ` + error,
            type: 'error'
          })
        })
      } else {
        updateUser(this.user.userId, this.user).then(response => {
          const { code, message } = response
          if (code === 200) {
            this.$notify({
              title: '成功通知',
              message: `[ ${that.user.userName} ]` + message,
              type: 'success'
            })
            that.load(that.userBodyParam)
            that.dialogVisible = false
            this.allDialog.moveDialogVisible = false
          }
        }).catch(error => {
          this.$notify({
            title: '失败通知',
            message: `[ ${that.user.userName} ]` + error,
            type: 'error'
          })
        })
      }
    },
    delMenuHandle() {
      const that = this
      this.$confirm(`您确认要删除角色[${that.user.userName}]吗？`, '是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delUser(this.user.userId).then(response => {
          const { code, message } = response
          if (code === 200) {
            this.$notify({
              title: '成功通知',
              message: `[ ${that.user.userName} ]` + message,
              type: 'success'
            })
            this.userBodyParam.page = 1
            this.load(this.userBodyParam)
            that.dialogVisible = false
          }
        }).catch(error => {
          this.$notify({
            title: '失败通知',
            message: `[ ${that.user.userName} ] 角色` + error,
            type: 'error'
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    handleSelectionChange(val) {
      if (val.length === 1) {
        this.showMoveBtn = false
      } else {
        this.showMoveBtn = true
      }
      console.log(val)
      if (val.length !== 0) {
        this.user.userId = val[val.length - 1].userId
        this.user.userCode = val[val.length - 1].userCode
        this.user.userName = val[val.length - 1].userName
        this.showDelBtn = false
      } else {
        this.showDelBtn = true
      }
    },
    propCopy(row) {
      this.user = row
    }
  },
  mounted() {
    this.load(this.userBodyParam)
  }
}
</script>

<style scoped>
.line {
  text-align: center;
}
.hidden {
  display: none;
}
.el-select {
  width: 100%;
}
</style>

