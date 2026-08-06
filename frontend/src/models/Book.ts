import {Genre} from "@/enums/genre.ts";
import type {Author} from "@/models/Author.ts";

export type BookListItem = {
    id?: number
    title?: string
    genre?: Genre
    releaseYear?: number
    author?: Author
}
