import { createFetch } from '@vueuse/core'

export const useApiFetch = createFetch({
  baseUrl: 'http://localhost:8080/book/api', // Ajout du context-path /book
  options: {
    async beforeFetch({ options }) {
      // On peut ajouter des headers ici (ex: Auth)
      return { options }
    },
  },
  fetchOptions: {
    mode: 'cors',
  },
})
