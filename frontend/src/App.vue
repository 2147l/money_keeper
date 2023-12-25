<template>
  <!-- 默认地址跳转登录页 -->
  <router-view></router-view>
  <!-- <Main /> -->
  <!-- <outputView/> -->
  <!-- <inputView/> -->
  <!-- <accountSettings /> -->
  <!-- <feedback /> -->
  <!-- <version /> -->
  <!-- <myInfo /> -->
  <!-- <secure /> -->
  <!-- <lineChartOutput /> -->
  <!-- <lineChartIntput /> -->
  <!-- <tabBar /> -->
  <!-- <bill /> -->
  <!-- <monthlyView></monthlyView> -->

</template>
<script>
import Login from './views/Login.vue'
import Main from './views/Main.vue'
import outputView from "./views/outputView.vue"
import inputView from "./views/inputView.vue"
import accountSettings from "./views/accountSettings.vue"
import feedback from "./views/feedback.vue"
import version from "./views/version.vue"
import myInfo from "./views/myInfo.vue"
import secure from "./views/secure.vue"
import lineChartOutput from "./views/lineChartOutput.vue"
import lineChartIntput from "./views/lineChartInput.vue"
import tabBar from './components/tabBar.vue'
import bill from './views/bill.vue'
import monthlyView from './views/monthlyView.vue'
export default {
  components: {
    Login,
    Main,
    outputView,
    inputView,
    accountSettings,
    feedback,
    version,
    myInfo,
    secure,
    lineChartOutput,
    lineChartIntput,
    tabBar,
    bill,
    monthlyView,
  }
}
</script>

<style scoped></style>
