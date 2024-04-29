import { createApp, App } from 'vue'
import VXETable from 'vxe-table'
import Apps from './App.vue'
import store from '@/stores'
import router from '@/routers'
import 'vxe-table/lib/style.css'
import './style/index.css'

function useTable(app: App) {
  app.use(VXETable)
}
const app = createApp(Apps)
app.use(router)
app.use(useTable)
app.use(store)
app.mount('#app')
