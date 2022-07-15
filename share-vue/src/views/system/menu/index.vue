<template>
  <div class="app-container">
    <el-row>
      <el-form :model="queryParams" ref="queryForm" :inline="true">
        <el-form-item label="菜单名称" prop="menuName">
          <el-input
            v-model="queryParams.menuName"
            placeholder="请输入菜单名称"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="queryParams.status" placeholder="菜单状态" clearable size="small">

          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <el-row class="btn-group-row">
      <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
        <!-- :class="showDelBtn?'hidden':''" -->
        <el-button
          class="right-10"
           type="primary"
           size="small"
           icon="el-icon-circle-plus-outline"
           @click="handleAdd">新增</el-button>
        <el-popover
          :class="showDelBtn?'hidden':'right-10'"
          placement="bottom"
          width="240"
          v-model="allDialog.moveDialogVisible">
          <el-select v-model="organ.organParentCode" filterable placeholder="请选择" style="width:100%; padding-bottom: 10px;">
            <el-option
              v-for="item in menuData"
              :key="item.menuCode"
              :label="item.menuName"
              :value="item.menuCode">
            </el-option>
          </el-select>
          <div style="text-align: right; margin: 0">
            <el-button type="primary" size="mini" plain @click="handleMove">确定</el-button>
          </div>
          <el-button
             slot="reference"
             title="更换上级企业"
             size="small"
             icon="el-icon-truck">移动</el-button>
        </el-popover>
        <el-button
           :class="showDelBtn?'hidden':'right-10'"
           title="删除菜单，可以删除多个"
           size="small"
           icon="el-icon-delete"
           @click="handleDelete">删除</el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
        <el-table
          :data="menuData"
          style="width: 100%"
          row-key="id"
          lazy
          @select="handleSelectionChange"
          :load="load"
          :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
          :default-sort="{prop: 'sort', order: 'ascending'}">
          <el-table-column
            fixed
            prop="id"
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
            prop="organName"
            :show-overflow-tooltip="true"
            min-width="150"
            label="公司名称">
            <template slot-scope="scope">
              <span> {{scope.row.organName}}</span>
            </template>
          </el-table-column>
          <el-table-column
            prop="organCode"
            :show-overflow-tooltip="true"
            label="公司编码">
          </el-table-column>
          <el-table-column
            prop="organUrl"
            :show-overflow-tooltip="true"
            label="官网">
          </el-table-column>
          <el-table-column
            align="center"
            label="状态">
            <template slot-scope="scope">
              <i v-if="scope.row.disabled==1" class="status-info status-danger" title="停用"></i>
              <i v-else  class="status-info status-success" title="启用"></i>
            </template>
          </el-table-column>
          <el-table-column label="操作"
                           align="center"
                           width="260"
                           class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini"
                         type="text"
                         icon="el-icon-edit"
                         @click="handleSee(scope.row)"
                         v-hasPermi="['system:menu:see']"
              >查看</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-plus"
                @click="handleAdd(scope.row)"
                v-hasPermi="['system:menu:add']"
              >新增</el-button>
              <el-button size="mini"
                         type="text"
                         icon="el-icon-edit"
                         @click="handleUpdate(scope.row)"
                         v-hasPermi="['system:menu:update']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['system:menu:remove']"
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      :close-on-click-modal="false"
      width="50%"
      class="log-form">
      <el-row>
        <div style="height: 300px; overflow-x: auto;">
          <el-form ref="menuForm" :show="false" :rules="rules" :model="menu" label-width="80px">
            <el-form-item label="父级菜单">
              <el-input v-model="menuParentName" disabled></el-input>
            </el-form-item>
            <el-form-item label="菜单类型">
              <el-radio v-model="menu.menuType" :label="1">目录</el-radio>
              <el-radio v-model="menu.menuType" :label="2">菜单</el-radio>
              <el-radio v-model="menu.menuType" :label="3">按钮</el-radio>
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
            <el-form-item label="菜单图标" @click="showIconDialog">
              <div style="height: 40px; width: 40px; line-height: 40px; text-align: center; float: left;">
                <span :class="menu.menuIcon"></span>
              </div>
              <el-select v-model="menu.menuIcon" filterable placeholder="请选择" :disabled="seeShow" style="width: calc(100% - 40px);">
                <el-option
                  v-for="item in iconsData"
                  :key="item.iconCode"
                  :label="item.iconCode"
                  :value="item.iconCode">
                  <template>
                    <span :class="item.iconCode"></span>
                    <span> {{item.iconCode}}</span>
                  </template>
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="所属系统">
              <el-select v-model="menu.appSystemId" placeholder="请选择系统" :disabled="seeShow" class="el-select">
                <el-option label="Share核心" :value="1"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="是否隐藏">
              <el-switch v-model="menu.hidden" :disabled="seeShow"></el-switch>
            </el-form-item>
          </el-form>
        </div>
      </el-row>
      <el-alert
        title="往下滚动还有内容哦！"
        type="info"
        center
        show-icon>
      </el-alert>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="enterHandler" v-if="btnReadonly">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getManageTreeList, addMenu, updateMenu, delMenu } from '@/api/menu'
import { getIcons } from '@/api/icons'

export default {
  data() {
    return {
      queryParams: {
        menuName: undefined,
        status: undefined
      },
      menuData: [],
      menu: {
        menuName: '',
        menuCode: '',
        redirect: '',
        component: '#',
        menuUrl: '',
        menuType: 1,
        menuParentCode: '0',
        menuIcon: 'el-icon-question',
        appSystemId: 1,
        orderCode: 0,
        hidden: false
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
      seeShow: false,
      showMoveBtn: true,
      showDelBtn: true,
      method: '',
      iconsData: [],
      allDialog: {
        moveDialogVisible: false
      }
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
    handleAdd(menu) {
      if (menu instanceof PointerEvent) {
        if (this.multipleSelection.length > 1) {
          this.$notify({
            title: '错误通知',
            message: '只能选择一个父级菜单',
            type: 'error'
          })
          return
        } else if (this.multipleSelection.length === 0) {
          this.multipleSelection[0] = {
            id: undefined,
            menuCode: 'root',
            menuName: '顶级菜单'
          }
          this.dialogVisible = true
        } else {
          this.dialogVisible = true
        }
      } else {
        console.log(menu)
        this.multipleSelection[0] = {
          id: undefined,
          menuCode: menu.menuCode,
          menuName: menu.menuName
        }
        this.dialogVisible = true
      }
      console.log(this.multipleSelection)
      this.seeShow = false
      this.btnReadonly = true
      this.menu = {
        id: undefined,
        menuName: undefined,
        menuCode: undefined,
        redirect: undefined,
        component: '#',
        menuUrl: undefined,
        menuType: 1,
        menuParentCode: this.multipleSelection[0].menuCode,
        menuIcon: undefined,
        appSystemId: 1,
        orderCode: this.menuData[this.menuData.length - 1].orderCode,
        hidden: false
      }
      this.menuParentName = this.multipleSelection[0].menuName
      this.method = 'add'
      this.multipleSelection = []
    },
    handleSee(row) {
      this.seeShow = true
      this.btnReadonly = false
      this.propCopy(row)
      this.dialogVisible = true
    },
    handleUpdate(row) {
      this.seeShow = false
      this.btnReadonly = true
      this.propCopy(row)
      this.dialogVisible = true
      this.method = 'edit'
    },
    enterHandler() {
      const that = this
      if (that.method === 'add') {
        this.menu.id = 0
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
          this.$notify({
            title: '失败通知',
            message: `[ ${that.menu.menuName} ] 菜单` + error,
            type: 'error'
          })
        })
      } else {
        updateMenu(this.menu.id, this.menu).then(response => {
          const { code, data } = response
          if (code === 200) {
            this.$notify({
              title: '成功通知',
              message: `[ ${that.menu.menuName} ] 菜单` + data,
              type: 'success'
            })
            that.getManageTreeList(1)
            that.dialogVisible = false
            this.allDialog.moveDialogVisible = false
          }
        }).catch(error => {
          this.$notify({
            title: '失败通知',
            message: `[ ${that.menu.menuName} ] 菜单` + error,
            type: 'error'
          })
        })
      }
    },
    handleDelete() {
      const that = this
      delMenu(this.menu.id).then(response => {
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
        this.$notify({
          title: '失败通知',
          message: `[ ${that.menu.menuName} ] 菜单` + error,
          type: 'error'
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
        this.menu.id = val[val.length - 1].id
        this.menu.menuCode = val[val.length - 1].menuCode
        this.menu.menuName = val[val.length - 1].menuName
        this.multipleSelection = val
        this.showDelBtn = false
      } else {
        this.showDelBtn = true
      }
    },
    getManageTreeList(appId) {
      getManageTreeList({
        appId: appId
      }).then(response => {
        const { data } = response
        this.menuData = data
      }).catch(error => {
        this.$notify({
          title: '失败通知',
          message: error,
          type: 'error'
        })
      })
    },
    propCopy(row) {
      this.menu.id = row.id
      this.menu.appSystemId = row.appSystemId
      this.menu.component = row.component
      this.menu.menuParentCode = row.menuParentCode
      this.menu.menuName = row.menuName
      this.menu.redirect = row.redirect
      this.menu.appSystemId = row.appSystemId
      this.menu.menuIcon = row.icon
      this.menu.hidden = row.hidden
      this.menu.menuCode = row.menuCode
      this.menu.menuType = row.type
      this.menu.menuUrl = row.url
      this.menu.orderCode = row.sort
    },
    showIconDialog() {
      const that = this
      getIcons().then(response => {
        const { data, code, msg } = response
        that.iconsData = data
      }).catch(error => {
        this.$notify({
          title: '失败通知',
          message: error,
          type: 'error'
        })
      })
    },
    handleMove() {
      this.menu = {
        id: this.menu.id,
        menuParentCode: this.menu.menuParentCode
      }
      this.method = 'edit'
      this.enterHandler()
    },
    handleQuery() {

    },
    resetQuery () {

    }
  },
  mounted() {
    this.getManageTreeList(1)
    this.showIconDialog()
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
.btn-group-row {
  height: 40px;
}
</style>

