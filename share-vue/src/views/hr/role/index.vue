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
          fit
          @selection-change="handleSelectionChange">
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
            <template slot-scope="scope">
              {{roleTypeMap[scope.row.roleType]}}
            </template>
          </el-table-column>
          <el-table-column
            prop="roleOrder"
            align="center"
            label="角色序号">
          </el-table-column>
          <el-table-column
            prop="disabled"
            align="center"
            label="禁用状态">
            <template slot-scope="scope">
              <i v-if="scope.row.disabled==1" class="status-info status-danger" title="停用"></i>
              <i v-else  class="status-info status-success" title="启用"></i>
            </template>
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            min-width="100">
            <template slot-scope="scope">
              <div v-if="scope.row.roleType != 1">
                <el-button size="mini"
                           type="text"
                           icon="el-icon-edit"
                           @click="handleSee(scope.row)"
                           v-hasPermi="['organ:role:see']"
                >查看</el-button>
                <el-button size="mini"
                           type="text"
                           icon="el-icon-edit"
                           @click="handleUpdate(scope.row)"
                           v-hasPermi="['organ:role:update']"
                >修改</el-button>
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  v-hasPermi="['organ:role:remove']"
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
      <div>
        <el-form ref="menuForm" :show="false" :rules="rules" :model="role" label-width="80px">
          <el-form-item label="角色名称" prop="menuName">
            <el-input v-model="role.roleName" :readonly="seeShow"></el-input>
          </el-form-item>
          <el-form-item label="角色编码" prop="menuCode">
            <el-input v-model="role.roleCode" :readonly="seeShow"></el-input>
          </el-form-item>
          <el-form-item label="角色类型">
            <el-select v-model="role.roleType" placeholder="请选择系统" :disabled="seeShow" class="el-select">
              <el-option v-for="roleType in roleTypes" :key="roleType.id"
                         :label="roleType.name" :value="roleType.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="角色序号">
            <el-input-number v-model="role.roleOrder"
                             controls-position="right" :min="1" :disabled="seeShow"></el-input-number>
          </el-form-item>
          <el-form-item label="禁用状态">
            <el-switch v-model="role.disabled" :disabled="seeShow"></el-switch>
          </el-form-item>
          <el-form-item label="菜单权限">
            <el-checkbox v-model="menuExpand" @change="handleCheckedTreeExpand($event, 'menu')">展开/折叠</el-checkbox>
            <el-checkbox v-model="menuNodeAll" @change="handleCheckedTreeNodeAll($event, 'menu')">全选/全不选</el-checkbox>
            <el-checkbox v-model="role.menuCheckStrictly" @change="handleCheckedTreeConnect($event, 'menu')">父子联动</el-checkbox>
            <el-tree
              class="tree-border"
              :data="menuTreeData"
              show-checkbox
              ref="menu"
              node-key="menuCode"
              :default-checked-keys="defaultCheckedKeys"
              :check-strictly="!role.menuCheckStrictly"
              empty-text="加载中，请稍后"
              :props="defaultProps"
            ></el-tree>
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="role.memo" :disabled="seeShow" type="textarea" placeholder="请输入内容"></el-input>
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
        roleOrder: 1,
        roleType: 0,
        disabled: false,
        memo: undefined,
        menuCheckStrictly: true,
        deptCheckStrictly: true
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
      // 菜单列表
      menuTreeData: [],
      defaultProps: {
        label: 'menuName'
      },
      // 默认选中菜单
      defaultCheckedKeys: [],
      roleTypes: [{
        id: 1,
        name: '超级管理员'
      }, {
        id: 2,
        name: '管理员'
      }, {
        id: 3,
        name: '普通用户'
      }],
      roleTypeMap: {
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
        roleOrder: 1,
        disabled: false,
        memo: undefined
      }
      this.method = 'add'
    },
    handleSee(row) {
      this.seeShow = true
      this.btnReadonly = false
      this.propCopy(row)
      console.log(row)
      this.dialogVisible = true
      this.role.roleId = row.roleId
      this.loadMenu();
    },
    handleUpdate(row) {
      this.seeShow = false
      this.btnReadonly = true
      this.propCopy(row)
      this.dialogVisible = true
      this.method = 'edit'
      this.role.roleId = row.roleId
      this.loadMenu();
    },
    handleDelete(row) {

    },
    enterHandler() {
      const that = this
      this.role.menuCodes = this.getMenuAllCheckedKeys()
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
      const that = this
      getRoleMenuList({
        appId: 1,
        roleCode: this.role.roleCode
      }).then(response => {
        const { data } = response
        that.menuTreeData = data.menuList
        that.defaultCheckedKeys = data.menuCodes
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
    },
    // 树权限（展开/折叠）
    handleCheckedTreeExpand(value, type) {
      if (type == 'menu') {
        let treeList = this.menuTreeData;
        for (let i = 0; i < treeList.length; i++) {
          this.$refs.menu.store.nodesMap[treeList[i].menuCode].expanded = value;
        }
      } else if (type == 'dept') {
        let treeList = this.deptOptions;
        for (let i = 0; i < treeList.length; i++) {
          this.$refs.dept.store.nodesMap[treeList[i].id].expanded = value;
        }
      }
    },
    // 树权限（全选/全不选）
    handleCheckedTreeNodeAll(value, type) {
      if (type == 'menu') {
        this.$refs.menu.setCheckedNodes(value ? this.menuTreeData: []);
      } else if (type == 'dept') {
        this.$refs.dept.setCheckedNodes(value ? this.deptOptions: []);
      }
    },
    // 树权限（父子联动）
    handleCheckedTreeConnect(value, type) {
      if (type == 'menu') {
        this.role.menuCheckStrictly = value ? true: false;
      } else if (type == 'dept') {
        this.role.deptCheckStrictly = value ? true: false;
      }
    },
    // 所有菜单节点数据
    getMenuAllCheckedKeys() {
      // 目前被选中的菜单节点
      let checkedKeys = this.$refs.menu.getCheckedKeys();
      // 半选中的菜单节点
      let halfCheckedKeys = this.$refs.menu.getHalfCheckedKeys();
      checkedKeys.unshift.apply(checkedKeys, halfCheckedKeys);
      return checkedKeys;
    },
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

