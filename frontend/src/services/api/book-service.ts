import { useApiFetch } from './api'
import type { BookListItem } from '@/models/Book'

export const useBookService = () => {
  const getAll = () => {
    return useApiFetch<BookListItem[]>('books').get().json()
  }

  const getById = (id: number) => {
    return useApiFetch<BookListItem>(`books/${id}`).get().json()
  }

  const create = (book: Partial<BookListItem>) => {
    return useApiFetch<BookListItem>('books').post(book).json()
  }

  const update = (id: number, book: Partial<BookListItem>) => {
    return useApiFetch<BookListItem>(`books/${id}`).put(book).json()
  }

  const remove = (id: number) => {
    return useApiFetch(`books/${id}`).delete().json()
  }

  return {
    getAll,
    getById,
    create,
    update,
    remove,
  }
}
