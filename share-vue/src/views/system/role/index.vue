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
          :data="roleData"
          stripe
          fit
          @selection-change="handleSelectionChange"
          style="width: 100%">
          <el-table-column
            fixed
            prop="roleId"
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
            prop="roleName"
            label="角色名称">
          </el-table-column>
          <el-table-column
            prop="roleCode"
            label="角色编码">
          </el-table-column>
          <el-table-column
            prop="roleType"
            label="角色类型">
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            width="100">
            <template slot-scope="scope">
              <el-button @click="see(scope.row)" type="text" size="small">查看</el-button>
              <el-button @click="edit(scope.row)" type="text" size="small">编辑</el-button>
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
          :total="roleBodyParam.total">
        </el-pagination>
      </el-col>
    </el-row>
    <!--  弹窗位置  -->
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="50%"
      class="log-form">
      <div style="height: 180px; overflow-x: auto;">
        <el-form ref="menuForm" :show="false" :rules="rules" :model="role" label-width="80px">
          <el-form-item label="角色名称" prop="menuName">
            <el-input v-model="role.roleName" :readonly="seeShow"></el-input>
          </el-form-item>
          <el-form-item label="角色编码" prop="menuCode">
            <el-input v-model="role.roleCode" :readonly="seeShow"></el-input>
          </el-form-item>
          <el-form-item label="角色类型">
            <el-select v-model="role.roleType" placeholder="请选择系统" :disabled="seeShow" class="el-select">
              <el-option label="超级管理员" :value="1"></el-option>
              <el-option label="管理员" :value="2"></el-option>
              <el-option label="普通用户" :value="3"></el-option>
            </el-select>
          </el-form-item>
          <!--<el-form-item label="是否显示">
            <el-switch v-model="role.disabled" :disabled="seeShow"></el-switch>
          </el-form-item>-->
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer" :style="'display:' + (btnReadonly?'block':'none')">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="enterHandler">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="菜单权限分配"
      :visible.sync="menuPermissionDialogVisible"
      @closed="reloadPage"
      width="50%">
      <el-tree
        :data="menuTreeData"
        show-checkbox
        node-key="menuId"
        :default-expanded-keys="[2, 3]"
        :default-checked-keys="defaultCheckedKeys"
        :props="defaultProps"
        @check="distributionPermission"
      >
      </el-tree>
      <span slot="footer" class="dialog-footer">
          <el-button @click="menuPermissionDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="menuPermissionDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getRoleList, getRoleMenuList, addRole, updateRole, delRole, addRolePermission, delRolePermission } from '@/api/role'

export default {
  data() {
    return {
      roleBodyParam: {
        page: 1,
        param: {
          roleCode: '',
          roleId: 0,
          roleName: '',
          roleType: 0
        },
        rows: 10,
        total: 0
      },
      roleData: [],
      role: {
        roleId: 0,
        roleName: '',
        roleCode: '',
        roleType: 0,
        disabled: true
      },
      rules: {
        roleName: [
          { required: true, message: '请输入角色名称', trigger: 'blur' },
          { min: 1, max: 8, message: '长度在 1 到 8 个字符', trigger: 'blur' }
        ],
        roleCode: [
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
      menuTreeData: [],
      defaultProps: {
        label: 'menuName'
      },
      defaultCheckedKeys: []
    }
  },
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
      this.roleBodyParam.rows = val
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
      this.roleBodyParam.page = val
      this.load(this.roleBodyParam)
    },
    load(data) {
      getRoleList(data).then(response => {
        const { data, total } = response
        this.roleData = data
        this.roleBodyParam.total = total
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
      this.role = {
        roleId: 0,
        roleName: '',
        roleCode: '',
        roleType: 3,
        disabled: true
      }
      this.method = 'add'
    },
    see(row) {
      this.seeShow = true
      this.btnReadonly = false
      this.propCopy(row)
      this.dialogVisible = true
    },
    edit(row) {
      this.seeShow = false
      this.btnReadonly = true
      this.propCopy(row)
      this.dialogVisible = true
      this.method = 'edit'
    },
    enterHandler() {
      const that = this
      if (that.method === 'add') {
        this.role.roleId = 0
        addRole(this.role).then(response => {
          const { code, message } = response
          if (code === 200) {
            this.$notify({
              title: '成功通知',
              message: `[ ${that.role.roleName} ] ` + message,
              type: 'success'
            })
            that.load(that.roleBodyParam)
            that.dialogVisible = false
          }
        }).catch(error => {
          this.$notify({
            title: '失败通知',
            message: `[ ${that.role.roleName} ] ` + error,
            type: 'error'
          })
        })
      } else {
        updateRole(this.role.roleId, this.role).then(response => {
          const { code, message } = response
          if (code === 200) {
            this.$notify({
              title: '成功通知',
              message: `[ ${that.role.roleName} ]` + message,
              type: 'success'
            })
            that.load(that.roleBodyParam)
            that.dialogVisible = false
            this.allDialog.moveDialogVisible = false
          }
        }).catch(error => {
          this.$notify({
            title: '失败通知',
            message: `[ ${that.role.roleName} ]` + error,
            type: 'error'
          })
        })
      }
    },
    delMenuHandle() {
      const that = this
      this.$confirm(`您确认要删除角色[${that.role.roleName}]吗？`, '是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delRole(this.role.roleId).then(response => {
          const { code, message } = response
          if (code === 200) {
            this.$notify({
              title: '成功通知',
              message: `[ ${that.role.roleName} ]` + message,
              type: 'success'
            })
            this.roleBodyParam.page = 1
            this.load(this.roleBodyParam)
            that.dialogVisible = false
          }
        }).catch(error => {
          this.$notify({
            title: '失败通知',
            message: `[ ${that.role.roleName} ] 角色` + error,
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
        this.role.roleId = val[val.length - 1].roleId
        this.role.roleCode = val[val.length - 1].roleCode
        this.role.roleName = val[val.length - 1].roleName
        this.showDelBtn = false
      } else {
        this.showDelBtn = true
      }
    },
    propCopy(row) {
      this.role = row
    },
    loadMenu() {
      this.menuPermissionDialogVisible = true
      const that = this
      getRoleMenuList({
        appId: 1,
        roleId: this.role.roleId
      }).then(response => {
        const { data } = response
        that.menuTreeData = data.menuList
        that.defaultCheckedKeys = data.menuIds
      }).catch(error => {
        this.$notify({
          title: '失败通知',
          message: error,
          type: 'error'
        })
      })
    },
    distributionPermission(data) {
      const that = this
      if (!this.defaultCheckedKeys.includes(data.menuId)) {
        addRolePermission({
          roleId: this.role.roleId,
          menuCode: data.menuCode
        }).then(response => {
          that.loadMenu()
        }).catch(error => {
          this.$notify({
            title: '失败通知',
            message: error,
            type: 'error'
          })
        })
      } else {
        delRolePermission({
          roleId: this.role.roleId,
          menuCode: data.menuCode
        }).then(response => {
          that.loadMenu()
        }).catch(error => {
          this.$notify({
            title: '失败通知',
            message: error,
            type: 'error'
          })
        })
      }
    },
    reloadPage() {
      this.$confirm('菜单已经修改是否需要重新刷新页面，让菜单重新加载?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        location.reload()
      }).catch(() => {
        this.$message({
          type: 'warning',
          message: '可能会影响左侧菜单使用体验！'
        })
      })
    }
  },
  mounted() {
    this.load(this.roleBodyParam)
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

