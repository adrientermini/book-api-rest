import { createRouter, createWebHistory } from "vue-router";
import BooksView from "../views/BooksView.vue";
import AuthorsView from "../views/AuthorsView.vue";

const router = createRouter({
  routes: [
    {
      path: "/",
      redirect: "/authors",
    },
    {
      path: "/authors",
      name: "authors",
      component: AuthorsView,
    },
    {
      path: "/books",
      name: "books",
      component: BooksView,
    },
  ],
  history: createWebHistory()
})

export default router;
