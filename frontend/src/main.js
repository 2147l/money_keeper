import './assets/publicStyle.css'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index'
import axios from 'axios'
import * as echarts from 'echarts'

const app = createApp(App)
app.use(router)
app.config.globalProperties.$axios = axios
app.config.globalProperties.$echarts = echarts
app.mount('#app')