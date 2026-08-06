import { computed } from 'vue'
import { useI18n } from 'vue-i18n'

export const useNavigation = () => {
  const { t } = useI18n()

  const items = computed(() => [
    {
      label: t('nav.authors') || 'Auteur',
      to: '/authors',
      icon: 'i-heroicons-user'
    },
    {
      label: t('nav.books') || 'Livres',
      to: '/books',
      icon: 'i-heroicons-book-open'
    }
  ])

  return {
    items
  }
}
