<script setup lang="ts">
import { useNavigation } from './composables/useNavigation'

const { items } = useNavigation()
</script>

<template>
  <UApp>
    <UNavigationMenu :items="items" class="border-b border-gray-200 dark:border-gray-800" />
    <div class="p-4">
      <RouterView/>
    </div>
  </UApp>
</template>
