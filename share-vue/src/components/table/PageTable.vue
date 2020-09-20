<template>
  <el-table
    :data="tableData"
    border
    style="width: 100%">

  </el-table>

</template>

<script>
export default {
  name: 'PageTable',
  props: {
    data: {
      type: Array,
      default: () => {
        return []
      }
    }
  }
}
</script>

<style scoped>

</style>
