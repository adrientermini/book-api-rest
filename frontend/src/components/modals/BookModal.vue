<script setup lang="ts">
import {reactive, computed, watch} from "vue";
import {useI18n} from "vue-i18n";
import UButton from "@nuxt/ui/components/Button.vue";
import UModal from "@nuxt/ui/components/Modal.vue";
import UForm from "@nuxt/ui/components/Form.vue";
import UFormField from "@nuxt/ui/components/FormField.vue";
import UInput from "@nuxt/ui/components/Input.vue";
import USelect from "@nuxt/ui/components/Select.vue";
import type {BookListItem} from "@/models/Book.ts";
import {Genre} from "@/enums/genre.ts";

const props = defineProps<{
  open: boolean;
  initialData?: BookListItem;
}>();

const { t } = useI18n();
const genres = computed(() => [
  { label: t('main.genres.FANTASY'), value: Genre.FANTASY },
  { label: t('main.genres.HORROR'), value: Genre.HORROR }
]);

const emit = defineEmits<{
  (e: 'update:open', value: boolean): void;
  (e: 'submit', data: any): void;
  (e: 'close', data?: any): void;
}>();

const state = reactive<BookListItem>({
  id: undefined,
  title: "",
  genre: undefined,
  releaseYear: undefined,
});

watch(() => props.initialData, (newVal) => {
  if (newVal) {
    Object.assign(state, newVal);
  }
}, { immediate: true });

function onSubmit(event: any) {
  const data = { ...event.data };
  emit('submit', data);
  emit('close', data);
  emit('update:open', false);
}
</script>

<template>
  <UModal
    :open="props.open"
    @update:open="val => emit('update:open', val)"
    :title="props.initialData ? t('main.modal.edit_title') : t('main.modal.add_title')"
    :description="props.initialData ? t('main.modal.edit_description') : t('main.modal.add_description')"
  >
    <template #body>
      <UForm :state="state" @submit="onSubmit" class="space-y-4">
        <UFormField :label="t('main.fields.title')" name="title">
          <UInput v-model="state.title" class="w-full" />
        </UFormField>

        <UFormField :label="t('main.fields.genre')" name="genre">
          <USelect v-model="state.genre" :items="genres" class="w-full" />
        </UFormField>

        <UFormField :label="t('main.fields.releaseYear')" name="releaseYear">
          <UInput v-model="state.releaseYear" class="w-full" type="number"/>
        </UFormField>

        <div class="flex justify-end gap-2 pt-4">
          <UButton :label="t('main.modal.cancel')" variant="ghost" color="neutral" @click="emit('update:open', false); emit('close')" />
          <UButton type="submit" :label="props.initialData ? t('main.modal.save') : t('main.actions.add')" />
        </div>
      </UForm>
    </template>
  </UModal>
</template>
