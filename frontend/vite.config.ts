import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import ui from '@nuxt/ui/vite'
import {nuxtUIOptions} from './src/theme/nuxtUIOptions'

// https://vite.dev/config/
export default defineConfig({
    plugins: [
        vue(),
        ui(nuxtUIOptions),
    ],
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url)),
        },
    },
})
