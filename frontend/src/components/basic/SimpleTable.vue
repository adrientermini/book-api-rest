<script setup lang="ts">
import type {ComputedRef} from "vue";
import {computed} from "vue";
import type {ColDef, CellComponent} from "../../types/table";

const props = defineProps<{
  data: any[];
  colsDef: ColDef[];
}>();

const getTooltip = (comp: CellComponent, rowData: any) => {
  const tooltip = comp.tooltip ?? comp.props?.tooltip;
  if (typeof tooltip === 'function') {
    return tooltip(rowData);
  }
  return tooltip;
};

const getComponentProps = (comp: CellComponent, rowData: any, field: string) => {
  const { tooltip, ...rest } = comp.props || {};
  return { ...rest, rowData, field };
};

const cols: ComputedRef<ColDef[]> = computed(() => {
  return props.colsDef.map((c) => ({
    ...c,
    sortable: c.sortable ?? false,
    renderer: c.renderer ?? ((rowData: any, field: string) => rowData[field]),
  }));
});

const columns = computed(() => {
  return cols.value.map((c) => ({
    accessorKey: c.field,
    header: c.header,
  }));
});

const interceptEvents = (events: Record<string, any> = {}, rowData: any, field: string): Record<string, any> => {
  const interceptedEvents: Record<string, any> = {};
  Object.keys(events).forEach((e) => {
    interceptedEvents[e] = (ev: any) => {
      return events[e](ev, rowData, field);
    };
  });
  return interceptedEvents;
};
</script>

<template>
  <UTable :data="props.data" :columns="columns">
    <template v-for="c in cols" :key="c.field" #[`${c.field}-cell`]="{ row }">
      <template v-if="c.components">
        <div class="flex items-center justify-end gap-1">
          <template v-for="(comp, idx) in c.components" :key="idx">
            <UTooltip :text="getTooltip(comp, row.original)" :disabled="!getTooltip(comp, row.original)">
              <Component
                  :is="comp.component"
                  v-bind="getComponentProps(comp, row.original, c.field)"
                  v-on="interceptEvents(comp.on, row.original, c.field)"
              />
            </UTooltip>
          </template>
        </div>
      </template>
      <template v-else-if="c.renderer">
        {{ c.renderer(row.original, c.field) }}
      </template>
    </template>
  </UTable>
</template>
