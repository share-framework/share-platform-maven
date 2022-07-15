<template>
  <div class="app-container">
    <el-row>
      <el-form :model="queryParams" ref="queryForm" :inline="true">
        <el-form-item label="菜单名称" prop="organName">
          <el-input
            v-model="queryParams.organName"
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
              :key="item.organCode"
              :label="item.organName"
              :value="item.organCode">
            </el-option>
          </el-select>
          <div style="text-align: right; margin: 0">
            <el-button type="primary" size="mini" plain @click="handleMove">确定</el-button>
          </div>
          <el-button
             slot="reference"
             title="更换父菜单"
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
            label=" 菜单名称">
            <template slot-scope="scope">
              <i :class="scope.row.icon"></i>
              <i> </i>
              <span> {{scope.row.organName}}</span>
            </template>
          </el-table-column>
          <el-table-column
            prop="organCode"
            :show-overflow-tooltip="true"
            label="菜单编码">
          </el-table-column>
          <el-table-column
            prop="url"
            :show-overflow-tooltip="true"
            label="菜单地址">
          </el-table-column>
<!--          <el-table-column-->
<!--            prop="component"-->
<!--            :show-overflow-tooltip="true"-->
<!--            label="组件地址">-->
<!--          </el-table-column>-->
          <el-table-column
            prop="redirect"
            :show-overflow-tooltip="true"
            label="跳转地址">
          </el-table-column>
          <el-table-column
            prop="sort"
            sortable
            align="center"
            label="顺序">
          </el-table-column>
          <el-table-column
            align="center"
            label="状态">
            <template slot-scope="scope">
              <i v-if="scope.row.hidden==1" class="status-info status-danger" title="停用"></i>
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
              <el-input v-model="organParentName" disabled></el-input>
            </el-form-item>
            <el-form-item label="菜单类型">
              <el-radio v-model="organ.organType" :label="1">集团</el-radio>
              <el-radio v-model="organ.organType" :label="2">公司</el-radio>
              <el-radio v-model="organ.organType" :label="3">单位</el-radio>
              <el-radio v-model="organ.organType" :label="4">组织</el-radio>
            </el-form-item>
            <el-form-item label="组织名称" prop="organName">
              <el-input v-model="organ.organName" :readonly="seeShow"></el-input>
            </el-form-item>
            <el-form-item label="组织编码" prop="organCode">
              <el-input v-model="organ.organCode" :readonly="seeShow"></el-input>
            </el-form-item>
            <el-form-item label="组织官网">
              <el-input v-model="organ.organUrl" :readonly="seeShow"></el-input>
            </el-form-item>
          </el-form>
        </div>
      </el-row>
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
        organName: undefined,
        status: undefined
      },
      menuData: [],
      organ: {
        organName: '',
        organCode: '',
        organUrl: '',
        organType: 1,
        organParentCode: '0'
      },
      rules: {
        organName: [
          { required: true, message: '请输入菜单名称', trigger: 'blur' },
          { min: 1, max: 8, message: '长度在 1 到 8 个字符', trigger: 'blur' }
        ],
        organCode: [
          { required: true, message: '请输入菜单编码', trigger: 'blur' },
          { min: 1, max: 32, message: '长度在 1 到 32 个字符', trigger: 'blur' }
        ]
      },
      dialogVisible: false,
      multipleSelection: [],
      organParentName: '',
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
            message: '只能选择一个上层企业',
            type: 'error'
          })
          return
        } else if (this.multipleSelection.length === 0) {
          this.multipleSelection[0] = {
            id: undefined,
            organCode: 'root',
            organName: '顶级菜单'
          }
          this.dialogVisible = true
        } else {
          this.dialogVisible = true
        }
      } else {
        console.log(menu)
        this.multipleSelection[0] = {
          id: undefined,
          organCode: organ.organCode,
          organName: organ.organName
        }
        this.dialogVisible = true
      }
      console.log(this.multipleSelection)
      this.seeShow = false
      this.btnReadonly = true
      this.organ = {
        id: undefined,
        organName: undefined,
        organCode: undefined,
        redirect: undefined,
        component: '#',
        organUrl: undefined,
        organType: 1,
        organParentCode: this.multipleSelection[0].organCode,
        menuIcon: undefined,
        appSystemId: 1,
        orderCode: this.menuData[this.menuData.length - 1].orderCode,
        hidden: false
      }
      this.organParentName = this.multipleSelection[0].organName
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
        this.organ.id = 0
        addMenu(this.menu).then(response => {
          const { code, data } = response
          if (code === 200) {
            this.$notify({
              title: '成功通知',
              message: `[ ${that.organ.organName} ] 菜单` + data,
              type: 'success'
            })
            that.getManageTreeList(1)
            that.dialogVisible = false
          }
        }).catch(error => {
          this.$notify({
            title: '失败通知',
            message: `[ ${that.organ.organName} ] 菜单` + error,
            type: 'error'
          })
        })
      } else {
        updateMenu(this.organ.id, this.menu).then(response => {
          const { code, data } = response
          if (code === 200) {
            this.$notify({
              title: '成功通知',
              message: `[ ${that.organ.organName} ] 菜单` + data,
              type: 'success'
            })
            that.getManageTreeList(1)
            that.dialogVisible = false
            this.allDialog.moveDialogVisible = false
          }
        }).catch(error => {
          this.$notify({
            title: '失败通知',
            message: `[ ${that.organ.organName} ] 菜单` + error,
            type: 'error'
          })
        })
      }
    },
    handleDelete() {
      const that = this
      delMenu(this.organ.id).then(response => {
        const { code, data } = response
        if (code === 200) {
          this.$notify({
            title: '成功通知',
            message: `[ ${that.organ.organName} ] 菜单` + data,
            type: 'success'
          })
          that.getManageTreeList(1)
          that.dialogVisible = false
        }
      }).catch(error => {
        this.$notify({
          title: '失败通知',
          message: `[ ${that.organ.organName} ] 菜单` + error,
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
        this.organ.id = val[val.length - 1].id
        this.organ.organCode = val[val.length - 1].organCode
        this.organ.organName = val[val.length - 1].organName
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
      this.organ.id = row.id
      this.organ.organParentCode = row.organParentCode
      this.organ.organName = row.organName
      this.organ.organCode = row.organCode
      this.organ.organType = row.type
      this.organ.organUrl = row.url
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
        id: this.organ.id,
        organParentCode: this.organ.organParentCode
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

