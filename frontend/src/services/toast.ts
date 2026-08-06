import { useToast } from '@nuxt/ui/composables'

export const useToastService = () => {
  const toast = useToast()

  const success = (title: string, description?: string) => {
    return toast.add({
      title,
      description,
      color: 'success',
      icon: 'i-heroicons-check-circle'
    })
  }

  const error = (title: string, description?: string) => {
    return toast.add({
      title,
      description,
      color: 'error',
      icon: 'i-heroicons-x-circle'
    })
  }

  const info = (title: string, description?: string) => {
    return toast.add({
      title,
      description,
      color: 'info',
      icon: 'i-heroicons-information-circle'
    })
  }

  const warning = (title: string, description?: string) => {
    return toast.add({
      title,
      description,
      color: 'warning',
      icon: 'i-heroicons-exclamation-circle'
    })
  }

  return {
    success,
    error,
    info,
    warning,
    add: toast.add,
    update: toast.update,
    remove: toast.remove,
    clear: toast.clear
  }
}
