<script setup lang="ts">
import {markRaw, onMounted, ref} from "vue";
import {useI18n} from "vue-i18n";
import type {ColDef} from "../types/table";
import type {Author} from "@/models/Author.ts";
import SimpleTable from "../components/basic/SimpleTable.vue";
import AuthorModal from "../components/modals/AuthorModal.vue";
import ConfirmPopover from "../components/modals/ConfirmPopover.vue";
import UButton from "@nuxt/ui/components/Button.vue";
import {useModalService} from "../services/modal";
import {useToastService} from "../services/toast";
import {useAuthorService} from "../services/api/author-service";

const { t } = useI18n();
const modal = useModalService();
const toast = useToastService();
const authorService = useAuthorService();

const data = ref<Author[]>([]);

onMounted(() => {
  getAll();
})

const columns: ColDef[] = [
  {
    field: 'firstName',
    header: t('main.columns.firstName') || 'Prénom'
  },
  {
    field: 'lastName',
    header: t('main.columns.lastName') || 'Nom'
  },
  {
    field: 'birthDate',
    header: t('main.columns.birthDate') || 'Date de naissance',
    renderer: (rowData: Author) => new Date(rowData.birthDate).toLocaleDateString()
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
          click: (ev: any, rowData: Author) => {
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
          click: (e: any, rowData: Author) => {
            onDeleteClick(e, rowData);
          }
        },
        tooltip: t('main.actions.delete_tooltip')
      }
    ]
  }
]

function getAll() {
  useAuthorService().getAll().then(authors => {
    data.value = authors;
  }).catch(error => console.error(error));
}

async function onAddClick() {
  const result = await modal.open(AuthorModal);
  if (result) {
    useAuthorService().create(result).then(author => {
      toast.success(t('main.messages.author_added_title'), t('main.messages.author_added_body'));
      getAll();
    }).catch(error => console.error(error))
}

async function onEditClick(rowData: Author) {
  const result = await modal.open(AuthorModal, {
    initialData: { ...rowData }
  });
  if (result && rowData.id) {
    useAuthorService().update(result).then(author => {
      toast.success(t('main.messages.author_modified_title'), t('main.messages.author_modified_body'));
      getAll();
    }).catch(error => console.error(error))
  }
}

async function onDeleteClick(e: any, rowData: Author) {
  const confirmed = await modal.open(ConfirmPopover, {
    anchor: e.currentTarget,
    title: t('main.actions.delete_confirm_title'),
    confirmLabel: t('main.actions.delete'),
    confirmColor: 'error'
  });

  if (confirmed && rowData.id) {
    useAuthorService().remove(rowData.id).then(author => {
      toast.success(t('main.messages.author_deleted_title'), t('main.messages.author_deleted_body'));
      getAll();
    }).catch(error => console.error(error))
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
