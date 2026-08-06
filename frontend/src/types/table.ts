export type CellComponent = {
    component: any
    props?: Record<string, any>
    on?: Record<string, (...args: any[]) => void>
    model?: any
    tooltip?: string | ((rowData: any) => string)
}

export type ColDef = {
    field: string
    header?: string
    renderer?: (rowData: any, field: string) => string
    sortable?: boolean
    components?: CellComponent[]
}
