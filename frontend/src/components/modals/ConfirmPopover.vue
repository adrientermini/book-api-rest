<script setup lang="ts">
import UButton from "@nuxt/ui/components/Button.vue";
import UPopover from "@nuxt/ui/components/Popover.vue";
import { useI18n } from "vue-i18n";

const props = defineProps<{
  anchor: any;
  title?: string;
  confirmLabel?: string;
  cancelLabel?: string;
  confirmColor?: any;
}>();

const emit = defineEmits<{
  (e: 'close', confirmed: boolean): void;
}>();

const { t } = useI18n();
</script>

<template>
  <UPopover
    :reference="props.anchor"
    :open="true"
    @update:open="(val) => !val && emit('close', false)"
  >
    <template #content>
      <div class="p-4 space-y-3">
        <p class="text-sm font-medium">{{ props.title || t('main.confirm.title') }}</p>
        <div class="flex justify-end gap-2">
          <UButton
            :label="props.cancelLabel || t('main.confirm.cancel')"
            variant="ghost"
            color="neutral"
            @click="emit('close', false)"
          />
          <UButton
            :label="props.confirmLabel || t('main.confirm.confirm')"
            @click="emit('close', true)"
            :color="props.confirmColor || 'neutral'"
          />
        </div>
      </div>
    </template>
  </UPopover>
</template>
