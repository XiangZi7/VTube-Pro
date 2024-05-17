import ElementPlus from 'element-plus'
import { createApp } from 'vue'
import Apps from './App.vue'
import router from '@/routers'
import store from '@/stores'
import './style/index.css'

const app = createApp(Apps)
app.use(router)
app.use(ElementPlus)
app.use(store)
app.mount('#app')
