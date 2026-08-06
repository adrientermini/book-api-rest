<script setup lang="ts">
import { ref, markRaw } from "vue";
import { useI18n } from "vue-i18n";
import type { ColDef } from "../types/table";
import type { Author } from "@/models/Author.ts";
import SimpleTable from "../components/basic/SimpleTable.vue";
import AuthorModal from "../components/modals/AuthorModal.vue";
import ConfirmPopover from "../components/modals/ConfirmPopover.vue";
import UButton from "@nuxt/ui/components/Button.vue";
import { useModalService } from "../services/modal";
import { useToastService } from "../services/toast";

const { t } = useI18n();
const modal = useModalService();
const toast = useToastService();

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

const data = ref<Author[]>([
  {
    id: 1,
    firstName: "J.K.",
    lastName: "Rowling",
    birthDate: new Date('1965-07-31')
  }
])

async function onAddClick() {
  const result = await modal.open(AuthorModal);
  if (result) {
    result.id = Math.max(0, ...data.value.map(a => a.id || 0)) + 1;
    data.value.push(result);
    toast.success(t('main.messages.author_added_title'), t('main.messages.author_added_body'));
  }
}

async function onEditClick(rowData: Author) {
  const result = await modal.open(AuthorModal, {
    initialData: { ...rowData }
  });
  if (result) {
    const index = data.value.findIndex(item => item.id === rowData.id);
    if (index !== -1) {
      data.value[index] = result;
      toast.success(t('main.messages.author_modified_title'), t('main.messages.author_modified_body'));
    }
  }
}

async function onDeleteClick(e: any, rowData: Author) {
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
      toast.success(t('main.messages.author_deleted_title'), t('main.messages.author_deleted_body'));
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
