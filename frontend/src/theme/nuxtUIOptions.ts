import type {NuxtUIOptions} from '@nuxt/ui/vite'
import uButtonTheme from "./components/ubutton";
import uTableTheme from "./components/utable";
import uNavigationMenuTheme from "./components/unavigationmenu";

export const nuxtUIOptions: NuxtUIOptions = {
    colorMode: false,
    ui: {
        colors: {
            primary: 'primary',
            secondary: 'secondary',
            error: 'severe',
        },
        button: uButtonTheme,
        table: uTableTheme,
        navigationMenu: uNavigationMenuTheme
    }
}
