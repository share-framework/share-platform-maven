<template>
  <div class="app-container">
    <el-row>
      <el-form :model="queryParams" ref="queryForm" :inline="true">
        <el-form-item label="公司名称" prop="organName">
          <el-input
            v-model="queryParams.organName"
            placeholder="请输入公司名称"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="queryParams.status" placeholder="公司状态" clearable size="small">

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
              v-for="item in organData"
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
             title="更换父公司"
             size="small"
             icon="el-icon-truck">移动</el-button>
        </el-popover>
        <el-button
           :class="showDelBtn?'hidden':'right-10'"
           title="删除公司，可以删除多个"
           size="small"
           icon="el-icon-delete"
           @click="handleDelete">删除</el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
        <el-table
          :data="organData"
          style="width: 100%"
          row-key="organCode"
          lazy
          @select="handleSelectionChange"
          :load="load"
          :tree-props="{children: 'children', hasChildren: 'hasChildren1'}"
          :default-sort="{prop: 'orderCode', order: 'ascending'}">
          <el-table-column
            fixed
            prop="organId"
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
            prop="organName"
            :show-overflow-tooltip="true"
            min-width="150"
            label="名称">
            <template slot-scope="scope">
<!--              <i :class="scope.row.icon"></i>
              <i> </i>-->
              <span> {{scope.row.organName}}</span>
            </template>
          </el-table-column>
          <el-table-column
            prop="organCode"
            :show-overflow-tooltip="true"
            label="编码">
          </el-table-column>
          <el-table-column
            prop="organUrl"
            :show-overflow-tooltip="true"
            label="官网地址">
          </el-table-column>
          <el-table-column
            prop="orderCode"
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
                         v-hasPermi="['system:organ:see']"
              >查看</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-plus"
                @click="handleAdd(scope.row)"
                v-hasPermi="['system:organ:add']"
              >新增</el-button>
              <el-button size="mini"
                         type="text"
                         icon="el-icon-edit"
                         @click="handleUpdate(scope.row)"
                         v-hasPermi="['system:organ:update']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['system:organ:remove']"
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
          <el-form ref="organForm" :show="false" :rules="rules" :model="organ" label-width="80px">
            <el-form-item label="父级公司" v-if="organParentName !== 'ROOT'">
              <el-input v-model="organParentName" disabled></el-input>
            </el-form-item>
            <el-form-item label="公司类型">
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
            <el-form-item label="组织序号">
              <el-input v-model="organ.orderCode" :readonly="seeShow"></el-input>
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
import { getOrganList, addOrgan, updateOrgan, delOrgan } from '@/api/hr/organ'

export default {
  data() {
    return {
      queryParams: {
        organName: undefined,
        organParentCode: 'ROOT'
      },
      organData: [],
      organ: {
        organName: '',
        organCode: '',
        organUrl: '',
        organType: 1,
        orderCode: 0,
        organParentCode: ''
      },
      rules: {
        organName: [
          { required: true, message: '请输入公司名称', trigger: 'blur' },
          { min: 1, max: 8, message: '长度在 1 到 8 个字符', trigger: 'blur' }
        ],
        organCode: [
          { required: true, message: '请输入公司编码', trigger: 'blur' },
          { min: 1, max: 32, message: '长度在 1 到 32 个字符', trigger: 'blur' }
        ]
      },
      dialogVisible: false,
      multipleSelection: [],
      organParentName: 'ROOT',
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
        organName: undefined,
        organParentCode: tree.organCode
      })*/
    },
    loadList(param) {
      getOrganList(param).then(response => {
        const { data } = response
        console.log(data)
        this.organData = data
      }).catch(error => {
        console.log(error)
      })
    },
    handleAdd(organ) {
      if (organ instanceof PointerEvent) {
        organ = {
          organCode: "ROOT",
          organName: "ROOT"
        }
        this.organ.organParentCode = "ROOT";
      } else {
        this.seeShow = false
        this.btnReadonly = true
        this.organ = {
          id: undefined,
          organName: undefined,
          organCode: undefined,
          organUrl: undefined,
          organType: 1,
          organParentCode: organ.organCode,
          orderCode: 0
        }
      }
      this.organParentName = organ.organName
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
        this.organ.id = 0
        addOrgan(this.organ).then(response => {
          const { code, data } = response
          if (code === 200) {
            this.$notify({
              title: '成功通知',
              message: `[ ${that.organ.organName} ] 公司` + data,
              type: 'success'
            })
            this.loadList(this.queryParams)
            that.dialogVisible = false
          }
        }).catch(error => {
          this.$notify({
            title: '失败通知',
            message: `[ ${that.organ.organName} ] 公司` + error,
            type: 'error'
          })
        })
      } else {
        updateOrgan(this.organ.id, this.organ).then(response => {
          const { code, data } = response
          if (code === 200) {
            this.$notify({
              title: '成功通知',
              message: `[ ${that.organ.organName} ] 公司` + data,
              type: 'success'
            })
            this.loadList(this.queryParams)
            that.dialogVisible = false
            this.allDialog.moveDialogVisible = false
          }
        }).catch(error => {
          this.$notify({
            title: '失败通知',
            message: `[ ${that.organ.organName} ] 公司` + error,
            type: 'error'
          })
        })
      }
    },
    handleDelete(row) {
      console.log(row)
      let id = undefined;
      if (row instanceof PointerEvent) {
        id = this.organ.id;
      } else {
        id = row.organId;
      }
      const that = this
      delOrgan(id).then(response => {
        const { code, data } = response
        if (code === 200) {
          that.$notify({
            title: '成功通知',
            message: `[ ${row.organName} ] 公司` + data,
            type: 'success'
          })
          this.loadList(this.queryParams)
          that.dialogVisible = false
        }
      }).catch((error, response) => {
        console.log(error, response)
        this.$notify({
          title: '失败通知',
          message: `[ ${row.organName} ] 公司删除失败，原因为：` + error.message,
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
    propCopy(row) {
      this.organ.id = row.id
      this.organ.organParentCode = row.organParentCode
      this.organ.organName = row.organName
      this.organ.organCode = row.organCode
      this.organ.organType = row.organType
      this.organ.organUrl = row.organUrl
      this.organ.orderCode = row.orderCode
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
      this.organ = {
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

