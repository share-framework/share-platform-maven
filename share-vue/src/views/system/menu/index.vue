<template>
  <div class="app-container">
    <el-row>
      <el-col :span="24">
        <el-button
           type="primary"
           size="small"
           icon="el-icon-circle-plus-outline"
           @click="add">新增</el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <el-table
          :data="menuData"
          style="width: 100%"
          row-key="id"
          border
          lazy
          @select="handleSelectionChange"
          :load="load"
          :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
          <el-table-column
            fixed
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
            prop="name"
            label="菜单名称"
            width="180">
          </el-table-column>
          <el-table-column
            prop="url"
            label="菜单地址"
            width="180">
          </el-table-column>
          <el-table-column
            prop="component"
            label="组件地址">
          </el-table-column>
          <el-table-column
            prop="redirect"
            label="跳转地址">
          </el-table-column>
          <el-table-column
            prop="sort"
            label="顺序">
          </el-table-column>
          <el-table-column
            label="图标">
            <template slot-scope="scope">
              <i :class="scope.row.icon"></i>
              <span style="margin-left: 10px">{{ scope.row.icon }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="是否显示">
            <template slot-scope="scope">
              <span style="margin-left: 10px">{{ scope.row.disabled==0?'显示':'隐藏' }}</span>
            </template>
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
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="50%"
      class="log-form">
      <div style="height: 300px; overflow-x: auto;">
        <el-form ref="menuForm" :show="false" :rules="rules" :model="menu" label-width="80px">
          <el-form-item label="父级菜单">
            <el-input v-model="menuParentName" disabled></el-input>
          </el-form-item>
          <el-form-item label="菜单名称" prop="menuName">
            <el-input v-model="menu.menuName" :readonly="seeShow"></el-input>
          </el-form-item>
          <el-form-item label="菜单编码" prop="menuCode">
            <el-input v-model="menu.menuCode" :readonly="seeShow"></el-input>
          </el-form-item>
          <el-form-item label="菜单地址">
            <el-input v-model="menu.menuUrl" :readonly="seeShow"></el-input>
          </el-form-item>
          <el-form-item label="组件地址">
            <el-input v-model="menu.component" :readonly="seeShow"></el-input>
          </el-form-item>
          <el-form-item label="跳转地址">
            <el-input v-model="menu.redirect" :readonly="seeShow"></el-input>
          </el-form-item>
          <el-form-item label="菜单序号">
            <el-input v-model="menu.orderCode" :readonly="seeShow"></el-input>
          </el-form-item>
          <el-form-item label="菜单图标">
            <el-input v-model="menu.menuIcon" :readonly="seeShow"></el-input>
          </el-form-item>
          <el-form-item label="所属系统">
            <el-select v-model="menu.appSystemId" placeholder="请选择系统" :disabled="seeShow">
              <el-option label="Share核心" value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="菜单类型">
            <el-select v-model="menu.menuType" placeholder="请选择类型" :disabled="seeShow">
              <el-option label="普通类型" value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="是否显示">
            <el-switch v-model="menu.disabled" :disabled="seeShow"></el-switch>
          </el-form-item>
        </el-form>
      </div>
      <el-alert
        title="往下滚动还有内容哦！"
        type="info"
        center
        show-icon>
      </el-alert>
      <span slot="footer" class="dialog-footer" :style="'display:' + (btnReadonly?'block':'none')">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
import { getManageTreeList, addMenu, updateMenu, delMenu } from '@/api/menu'

export default {
  data() {
    return {
      menuData: [],
      menu: {
        menuName: '',
        menuCode: '',
        redirect: '',
        component: '#',
        menuUrl: '',
        menuType: 1,
        menuParentCode: '0',
        menuIcon: '',
        appSystemId: 1,
        orderCode: 0,
        disabled: true
      },
      rules: {
        menuName: [
          { required: true, message: '请输入菜单名称', trigger: 'blur' },
          { min: 1, max: 8, message: '长度在 1 到 8 个字符', trigger: 'blur' }
        ],
        menuCode: [
          { required: true, message: '请输入菜单编码', trigger: 'blur' },
          { min: 1, max: 32, message: '长度在 1 到 32 个字符', trigger: 'blur' }
        ]
      },
      dialogVisible: false,
      multipleSelection: [],
      menuParentName: '顶级菜单',
      btnReadonly: true,
      seeShow: false
    }
  },
  methods: {
    load(tree, treeNode, resolve) {
      /* console.log(tree, treeNode, resolve)

      getAuthMenu().then(response => {
        const { data } = response
        console.log(data)
        this.menuData = data
      }).catch(error => {
        console.log(error)
      }) */
    },
    add() {
      if (this.multipleSelection.length === 1) {
        this.menu = {
          menuName: '',
          menuCode: '',
          redirect: '',
          component: '#',
          menuUrl: '',
          menuType: 1,
          menuParentCode: '0',
          menuIcon: '',
          appSystemId: 1,
          orderCode: 0,
          disabled: true
        }
        this.seeShow = false
        this.btnReadonly = true
        this.dialogVisible = true
      } else {
        this.$notify({
          title: '错误通知',
          message: this.multipleSelection.length === 0 ? '请选择父级菜单' : '只能选择一个父级菜单',
          type: 'error'
        })
      }
    },
    save() {
      const that = this
      addMenu(this.menu).then(response => {
        const { code, data } = response
        if (code === 200) {
          this.$notify({
            title: '成功通知',
            message: `[ ${that.menu.menuName} ] 菜单` + data,
            type: 'success'
          })
          that.getManageTreeList(1)
          that.dialogVisible = false
        }
      }).catch(error => {
        console.log(error)
      })
    },
    handleSelectionChange(val) {
      this.menu.menuParentCode = val[val.length - 1].id
      this.menuParentName = val[val.length - 1].name
      this.multipleSelection = val
    },
    getManageTreeList(appId) {
      getManageTreeList({
        appId: appId
      }).then(response => {
        const { data } = response
        this.menuData = data
      }).catch(error => {
        console.log(error)
      })
    },
    see(row) {
      this.seeShow = true
      this.btnReadonly = false
      this.menu.appSystemId = row.appSystemId
      this.menu.component = row.component
      this.menu.menuParentCode = row.menuParentCode
      this.menu.menuName = row.name
      this.menu.redirect = row.redirect
      this.menu.appSystemId = row.appSystemId
      this.menu.menuIcon = row.icon
      this.menu.disabled = row.disabled
      this.menu.menuCode = row.id
      this.menu.menuType = row.type
      this.menu.menuUrl = row.url
      this.menu.orderCode = row.sort
      this.dialogVisible = true
    },
    edit(row) {
      this.seeShow = false
      this.btnReadonly = true
      this.menu.appSystemId = row.appSystemId
      this.menu.component = row.component
      this.menu.menuParentCode = row.menuParentCode
      this.menu.menuName = row.name
      this.menu.redirect = row.redirect
      this.menu.appSystemId = row.appSystemId
      this.menu.menuIcon = row.icon
      this.menu.disabled = row.disabled
      this.menu.menuCode = row.code
      this.menu.menuType = row.type
      this.menu.menuUrl = row.url
      this.menu.orderCode = row.sort
      this.dialogVisible = true
    }
  },
  mounted() {
    this.getManageTreeList(1)
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
.el-row {
  margin-bottom: 20px;
}
</style>

