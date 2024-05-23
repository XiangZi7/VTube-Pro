import { createApp } from 'vue'
import './style/index.css'
import ElementPlus from 'element-plus'
import App from './App.vue'
import store from '@/stores'
import routers from '@/routers'
import 'element-plus/dist/index.css'

const app = createApp(App)
app.use(routers)
app.use(store)
app.use(ElementPlus)
app.mount('#app')
