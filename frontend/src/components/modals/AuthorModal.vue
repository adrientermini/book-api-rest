<script setup lang="ts">
import {reactive, watch} from "vue";
import {useI18n} from "vue-i18n";
import UButton from "@nuxt/ui/components/Button.vue";
import UModal from "@nuxt/ui/components/Modal.vue";
import UForm from "@nuxt/ui/components/Form.vue";
import UFormField from "@nuxt/ui/components/FormField.vue";
import UInput from "@nuxt/ui/components/Input.vue";
import type {Author} from "@/models/Author.ts";

const props = defineProps<{
  open: boolean;
  initialData?: Author;
}>();

const { t } = useI18n();

const emit = defineEmits<{
  (e: 'update:open', value: boolean): void;
  (e: 'submit', data: any): void;
  (e: 'close', data?: any): void;
}>();

const state = reactive({
  id: undefined as number | undefined,
  firstName: "",
  lastName: "",
  birthDate: "" as string | undefined,
});

watch(() => props.initialData, (newVal) => {
  if (newVal) {
    state.id = newVal.id;
    state.firstName = newVal.firstName;
    state.lastName = newVal.lastName;
    state.birthDate = newVal.birthDate ? new Date(newVal.birthDate).toISOString().split('T')[0] : "";
  }
}, { immediate: true });

function onSubmit(event: any) {
  const data = { 
    ...event.data,
    birthDate: event.data.birthDate ? new Date(event.data.birthDate) : undefined
  };
  emit('submit', data);
  emit('close', data);
  emit('update:open', false);
}
</script>

<template>
  <UModal
    :open="props.open"
    @update:open="val => emit('update:open', val)"
    :title="props.initialData ? t('main.modal.author_edit_title') : t('main.modal.author_add_title')"
    :description="props.initialData ? t('main.modal.author_edit_description') : t('main.modal.author_add_description')"
  >
    <template #body>
      <UForm :state="state" @submit="onSubmit" class="space-y-4">
        <UFormField :label="t('main.fields.firstName')" name="firstName">
          <UInput v-model="state.firstName" class="w-full" />
        </UFormField>

        <UFormField :label="t('main.fields.lastName')" name="lastName">
          <UInput v-model="state.lastName" class="w-full" />
        </UFormField>

        <UFormField :label="t('main.fields.birthDate')" name="birthDate">
          <UInput v-model="state.birthDate" class="w-full" type="date"/>
        </UFormField>

        <div class="flex justify-end gap-2 pt-4">
          <UButton :label="t('main.modal.cancel')" variant="ghost" color="neutral" @click="emit('update:open', false); emit('close')" />
          <UButton type="submit" :label="props.initialData ? t('main.modal.save') : t('main.actions.add')" />
        </div>
      </UForm>
    </template>
  </UModal>
</template>
