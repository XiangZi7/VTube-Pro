import { createApp } from 'vue'
import './style/index.css'
import App from './App.vue'
import store from '@/stores'
import routers from '@/routers'

const app = createApp(App)
app.use(routers)
app.use(store)

app.mount('#app')
