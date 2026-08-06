import { useApiFetch } from './api'
import type { Author } from '@/models/Author'

export const useAuthorService = () => {
  const getAll = () => {
    return useApiFetch<Author[]>('authors').get().json()
  }

  const getById = (id: number) => {
    return useApiFetch<Author>(`authors/${id}`).get().json()
  }

  const create = (author: Partial<Author>) => {
    return useApiFetch<Author>('authors').post(author).json()
  }

  const update = (id: number, author: Partial<Author>) => {
    return useApiFetch<Author>(`authors/${id}`).put(author).json()
  }

  const remove = (id: number) => {
    return useApiFetch(`authors/${id}`).delete().json()
  }

  return {
    getAll,
    getById,
    create,
    update,
    remove,
  }
}
