<template>
  <!-- 默认地址跳转登录页 -->
  <router-view></router-view>
  <!-- <Main /> -->
  <!-- <outputView/> -->
  <!-- <inputView/> -->
</template>
<script>
import Login from './views/Login.vue'
import Main from './views/Main.vue'
import outputView from "./views/outputView.vue"
import inputView from "./views/inputView.vue"
export default {
  components: {
    Login,
    Main,
    outputView,
    inputView
  }
}
</script>

<style scoped></style>
