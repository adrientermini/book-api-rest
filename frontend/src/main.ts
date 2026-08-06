import './assets/css/main.css'

import {createApp} from 'vue'

import ui from '@nuxt/ui/vue-plugin'
import UApp from '@nuxt/ui/components/App.vue'
import UButton from '@nuxt/ui/components/Button.vue'
import UTable from '@nuxt/ui/components/Table.vue'
import UTooltip from '@nuxt/ui/components/Tooltip.vue'
import UNavigationMenu from '@nuxt/ui/components/NavigationMenu.vue'
import App from './App.vue'
import router from "@/router";
import i18n from './i18n'


const app = createApp(App)
    .use(router)
    .use(i18n)
    .use(ui)
    .component('UApp', UApp)
    .component('UButton', UButton)
    .component('UTable', UTable as any)
    .component('UTooltip', UTooltip)
    .component('UNavigationMenu', UNavigationMenu)

app.mount('#app')
