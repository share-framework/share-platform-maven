<template>
  <div class="app-container">
    <el-row>
      <el-form :model="queryParams" ref="queryForm" :inline="true">
        <el-form-item label="部门名称" prop="deptName">
          <el-input
            v-model="queryParams.deptName"
            placeholder="请输入部门名称"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="queryParams.status" placeholder="部门状态" clearable size="small">

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
          <el-select v-model="dept.deptParentCode" filterable placeholder="请选择" style="width:100%; padding-bottom: 10px;">
            <el-option
              v-for="item in deptData"
              :key="item.deptCode"
              :label="item.deptName"
              :value="item.deptCode">
            </el-option>
          </el-select>
          <div style="text-align: right; margin: 0">
            <el-button type="primary" size="mini" plain @click="handleMove">确定</el-button>
          </div>
          <el-button
            slot="reference"
            title="更换父部门"
            size="small"
            icon="el-icon-truck">移动</el-button>
        </el-popover>
        <el-button
          :class="showDelBtn?'hidden':'right-10'"
          title="删除部门，可以删除多个"
          size="small"
          icon="el-icon-delete"
          @click="handleDelete">删除</el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
        <el-table
          :data="deptData"
          style="width: 100%"
          row-key="deptCode"
          lazy
          @select="handleSelectionChange"
          :load="load"
          :tree-props="{children: 'children', hasChildren: 'hasChildren1'}"
          :default-sort="{prop: 'orderCode', order: 'ascending'}">
          <el-table-column
            fixed
            prop="deptId"
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
            prop="deptName"
            :show-overflow-tooltip="true"
            min-width="150"
            label="名称">
            <template slot-scope="scope">
              <!--              <i :class="scope.row.icon"></i>
                            <i> </i>-->
              <span> {{scope.row.deptName}}</span>
            </template>
          </el-table-column>
          <el-table-column
            prop="deptCode"
            :show-overflow-tooltip="true"
            label="编码">
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
                         v-hasPermi="['system:dept:see']"
              >查看</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-plus"
                @click="handleAdd(scope.row)"
                v-hasPermi="['system:dept:add']"
              >新增</el-button>
              <el-button size="mini"
                         type="text"
                         icon="el-icon-edit"
                         @click="handleUpdate(scope.row)"
                         v-hasPermi="['system:dept:update']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['system:dept:remove']"
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
        <div style="height: 350px; overflow-x: auto;">
          <el-form ref="deptForm" :show="false" :rules="rules" :model="dept" label-width="80px">
            <el-form-item label="父级部门" v-if="deptParentName !== 'ROOT'">
              <el-input v-model="deptParentName" disabled></el-input>
            </el-form-item>
            <el-form-item label="部门类型">
              <el-radio v-model="dept.deptType" :label="1">普通</el-radio>
              <el-radio v-model="dept.deptType" :label="2">虚拟</el-radio>
            </el-form-item>
            <el-form-item label="组织名称" prop="deptName">
              <el-input v-model="dept.deptName" :readonly="seeShow"></el-input>
            </el-form-item>
            <el-form-item label="组织编码" prop="deptCode">
              <el-input v-model="dept.deptCode" :readonly="seeShow"></el-input>
            </el-form-item>
            <el-form-item label="组织序号">
              <el-input v-model="dept.sort" :readonly="seeShow"></el-input>
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
import { getDeptList, addDept, updateDept, delDept } from '@/api/hr/dept'

export default {
  data() {
    return {
      queryParams: {
        deptName: undefined,
        deptParentCode: 'ROOT'
      },
      deptData: [],
      dept: {
        deptName: '',
        deptCode: '',
        deptType: 1,
        sort: 1,
        deptParentCode: ''
      },
      rules: {
        deptName: [
          { required: true, message: '请输入部门名称', trigger: 'blur' },
          { min: 1, max: 8, message: '长度在 1 到 8 个字符', trigger: 'blur' }
        ],
        deptCode: [
          { required: true, message: '请输入部门编码', trigger: 'blur' },
          { min: 1, max: 32, message: '长度在 1 到 32 个字符', trigger: 'blur' }
        ]
      },
      dialogVisible: false,
      multipleSelection: [],
      deptParentName: 'ROOT',
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
  mounted() {
    this.loadList(this.queryParams)
    this.showIconDialog()
  },
  methods: {
    load(tree, treeNode, resolve) {
      /*this.loadList({
        deptName: undefined,
        deptParentCode: tree.deptCode
      })*/
    },
    loadList(param) {
      getDeptList(param).then(response => {
        const { data } = response
        console.log(data)
        this.deptData = data
      }).catch(error => {
        console.log(error)
      })
    },
    handleAdd(dept) {
      if (dept instanceof PointerEvent) {
        dept = {
          deptCode: "ROOT",
          deptName: "ROOT"
        }
      }
      this.seeShow = false
      this.btnReadonly = true
      this.dept = {
        id: undefined,
        deptName: undefined,
        deptCode: undefined,
        deptType: 1,
        deptParentCode: dept.deptCode,
        sort: 1
      }
      this.deptParentName = dept.deptName
      this.method = 'add'
      this.dialogVisible = true
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
      console.log(that.method);
      if (that.method === 'add') {
        this.dept.deptId = 0
        addDept(this.dept).then(response => {
          const { code, data } = response
          if (code === 200) {
            this.$notify({
              title: '成功通知',
              message: `[ ${that.dept.deptName} ] 部门` + data,
              type: 'success'
            })
            this.loadList(this.queryParams)
            that.dialogVisible = false
          }
        }).catch(error => {
          this.$notify({
            title: '失败通知',
            message: `[ ${that.dept.deptName} ] 部门` + error,
            type: 'error'
          })
        })
      } else {
        updateDept(this.dept.deptId, this.dept).then(response => {
          const { code, data } = response
          if (code === 200) {
            this.$notify({
              title: '成功通知',
              message: `[ ${that.dept.deptName} ] 部门` + data,
              type: 'success'
            })
            this.loadList(this.queryParams)
            that.dialogVisible = false
            this.allDialog.moveDialogVisible = false
          }
        }).catch(error => {
          this.$notify({
            title: '失败通知',
            message: `[ ${that.dept.deptName} ] 部门` + error,
            type: 'error'
          })
        })
      }
    },
    handleDelete(row) {
      console.log(row)
      let id = undefined;
      if (row instanceof PointerEvent) {
        id = this.dept.deptId;
      } else {
        id = row.deptId;
      }
      const that = this
      delDept(id).then(response => {
        const { code, data } = response
        if (code === 200) {
          that.$notify({
            title: '成功通知',
            message: `[ ${row.deptName} ] 部门` + data,
            type: 'success'
          })
          this.loadList(this.queryParams)
          that.dialogVisible = false
        }
      }).catch((error, response) => {
        console.log(error, response)
        this.$notify({
          title: '失败通知',
          message: `[ ${row.deptName} ] 部门删除失败，原因为：` + error.message,
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
        this.dept.id = val[val.length - 1].id
        this.dept.deptCode = val[val.length - 1].deptCode
        this.dept.deptName = val[val.length - 1].deptName
        this.multipleSelection = val
        this.showDelBtn = false
      } else {
        this.showDelBtn = true
      }
    },
    propCopy(row) {
      this.dept.deptId = row.deptId
      this.dept.deptParentCode = row.deptParentCode
      this.dept.deptName = row.deptName
      this.dept.deptCode = row.deptCode
      this.dept.deptType = row.deptType
      this.dept.sort = row.sort
    },
    showIconDialog() {
      /*const that = this
      getIcons().then(response => {
        const { data, code, msg } = response
        that.iconsData = data
      }).catch(error => {
        this.$notify({
          title: '失败通知',
          message: error,
          type: 'error'
        })
      })*/
    },
    handleMove() {
      this.dept = {
        id: this.dept.id,
        deptParentCode: this.dept.deptParentCode
      }
      this.method = 'edit'
      this.enterHandler()
    },
    handleQuery() {

    },
    resetQuery () {

    }
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

