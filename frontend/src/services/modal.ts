import { useOverlay } from '@nuxt/ui/composables'
import type { Component } from 'vue'

export const useModalService = () => {
  const overlay = useOverlay()

  const open = <T extends Component>(component: T, props: any = {}) => {
    const instance = overlay.create(component, {
      props,
      destroyOnClose: true
    })
    return instance.open()
  }

  return {
    open
  }
}
