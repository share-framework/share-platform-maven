<template>
  <div class="dashboard-container">
    <el-row :gutter="20">
      <el-col :span="6">
        <div class="grid-content bg-purple">

        </div>
      </el-col>

      <el-col :span="6">
        <div class="grid-content bg-purple">

        </div>
      </el-col>

      <el-col :span="6">
        <div class="grid-content bg-purple">

        </div>
      </el-col>

      <el-col :span="6">
        <div class="grid-content bg-purple">

        </div>
      </el-col>
    </el-row>
    <div class="dashboard-text">name: {{ name }}</div>
    <div class="dashboard-text">roles: <span v-for="role in roles" :key="role">{{ role }}</span></div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name',
      'roles'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
