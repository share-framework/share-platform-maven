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
          :load="load"
          :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
          <el-table-column
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
        </el-table>
      </el-col>
    </el-row>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="50%"
      class="log-form">
      <div style="height: 300px; overflow-x: auto;">
        <el-form ref="menuForm" :show="false" :model="menu" label-width="80px">
          <el-form-item label="菜单名称">
            <el-input v-model="menu.menuName"></el-input>
          </el-form-item>
          <el-form-item label="菜单编码">
            <el-input v-model="menu.menuCode"></el-input>
          </el-form-item>
          <el-form-item label="菜单地址">
            <el-input v-model="menu.menuUrl"></el-input>
          </el-form-item>
          <el-form-item label="组件地址">
            <el-input v-model="menu.component"></el-input>
          </el-form-item>
          <el-form-item label="跳转地址">
            <el-input v-model="menu.redirect"></el-input>
          </el-form-item>
          <el-form-item label="菜单序号">
            <el-input v-model="menu.orderCode"></el-input>
          </el-form-item>
          <el-form-item label="菜单图标">
            <el-input v-model="menu.menuIcon"></el-input>
          </el-form-item>
          <el-form-item label="所属系统">
            <el-select v-model="menu.appSystemId" placeholder="请选择系统">
              <el-option label="Share核心" value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="菜单类型">
            <el-select v-model="menu.menuType" placeholder="请选择类型">
              <el-option label="普通类型" value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="是否显示">
            <el-switch v-model="menu.disabled"></el-switch>
          </el-form-item>
        </el-form>
      </div>
      <el-alert
        title="往下滚动还有内容哦！"
        type="info"
        center
        show-icon>
      </el-alert>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
import { getAuthMenu, addMenu } from '@/api/menu'

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
        menuParentCode: '',
        menuIcon: '',
        appSystemId: 1,
        orderCode: 0,
        disabled: true
      },
      dialogVisible: false
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
      this.dialogVisible = true
    },
    save() {
      const that = this
      addMenu(this.menu).then(response => {
        const { code, data } = response
        console.log(code)
        if (code === 200) {
          this.$notify({
            title: '成功',
            message: `[ ${that.menu.menuName} ] 菜单` + data,
            type: 'success'
          })
          that.dialogVisible = false
        }
      }).catch(error => {
        console.log(error)
      })
    }
  },
  mounted() {
    getAuthMenu().then(response => {
      const { data } = response
      this.menuData = data
    }).catch(error => {
      console.log(error)
    })
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

