import { createApp, App } from 'vue'
import VXETable from 'vxe-table'
import Apps from './App.vue'
import router from '@/routers'
import store from '@/stores'
import 'vxe-table/lib/style.css'
import './style/index.css'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

function useTable(app: App) {
  app.use(VXETable)
}
const app = createApp(Apps)
app.use(router)
app.use(ElementPlus)
app.use(useTable)
app.use(store)
app.mount('#app')
