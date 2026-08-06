<script setup lang="ts">
import { ref, markRaw } from "vue";
import SimpleTable from "../components/basic/SimpleTable.vue";
import BookModal from "../components/modals/BookModal.vue";
import ConfirmPopover from "../components/modals/ConfirmPopover.vue";
import UButton from "@nuxt/ui/components/Button.vue";
import { useModalService } from "../services/modal";
import { useToastService } from "../services/toast";
import { useI18n } from "vue-i18n";
import type { ColDef } from "../types/table";
import type {BookListItem} from "@/models/Book.ts";
import {Genre} from "@/enums/genre.ts";

const { t } = useI18n();

const columns: ColDef[] = [
  {
    field: 'title',
    header: t('main.columns.title')
  },
  {
    field: 'genre',
    header: t('main.columns.genre'),
    renderer: (rowData: BookListItem) => t(`main.genres.${rowData.genre}`)
  },
  {
    field: 'releaseYear',
    header: t('main.columns.releaseYear')
  },
  {
    field: 'author',
    header: t('main.columns.author'),
    renderer: (rowData: BookListItem) => `${rowData.author?.firstName} ${rowData.author?.lastName}`
  },
  {
    field: 'actions',
    components: [
      {
        component: markRaw(UButton),
        props: {
          icon: 'i-heroicons-pencil-square',
          variant: 'ghost',
          color: 'neutral',
          tooltip: t('main.actions.edit')
        },
        on: {
          click: (ev: any, rowData: BookListItem) => {
            onEditClick(rowData);
          }
        }
      },
      {
        component: markRaw(UButton),
        props: {
          icon: 'i-heroicons-trash',
          variant: 'ghost',
          color: 'neutral',
        },
        on: {
          click: (e: any, rowData: BookListItem) => {
            onDeleteClick(e, rowData);
          }
        },
        tooltip: t('main.actions.delete_tooltip')
      }
    ]
  }
]

const data = ref<BookListItem[]>([
  {
    id: 1,
    title: "Harry Potter à l'école des sorciers",
    genre: Genre.FANTASY,
    releaseYear: 1997,
    author: {
      id: 1,
      firstName: "J.K.",
      lastName: "Rowling",
      birthDate: new Date('1960-05-23')
    }
  },
])

const modal = useModalService();
const toast = useToastService();

async function onAddClick() {
  const result = await modal.open(BookModal);
  if (result) {
    data.value.push(result);
    toast.success(t('main.messages.added_title'), t('main.messages.added_body'));
  }
}

async function onEditClick(rowData: BookListItem) {
  const result = await modal.open(BookModal, {
    initialData: { ...rowData }
  });
  if (result) {
    const index = data.value.findIndex(item => item.id === rowData.id);
    if (index !== -1) {
      data.value[index] = result;
      toast.success(t('main.messages.modified_title'), t('main.messages.modified_body'));
    }
  }
}

async function onDeleteClick(e: any, rowData: BookListItem) {
  const confirmed = await modal.open(ConfirmPopover, {
    anchor: e.currentTarget,
    title: t('main.actions.delete_confirm_title'),
    confirmLabel: t('main.actions.delete'),
    confirmColor: 'error'
  });

  if (confirmed) {
    const index = data.value.findIndex(item => item.id === rowData.id);
    if (index !== -1) {
      data.value.splice(index, 1);
      toast.success(t('main.messages.deleted_title'), t('main.messages.deleted_body'));
    }
  }
}
</script>

<template>
  <div class="flex justify-end">
    <UButton class="flex justify-end" :label="t('main.actions.add')" icon="i-heroicons-plus" @click="onAddClick" />
  </div>
  <div class="mt-4 justify-center">
    <SimpleTable stripped :data="data" :cols-def="columns" />
  </div>
</template>
