export const uButtonTheme = {
    slots: {
        base: 'cursor-pointer',
    },
    variants: {
        size: {
            xs: { base: 'px-3 py-1.5' },
            sm: { base: 'px-4 py-2' },
            md: { base: 'px-5 py-2.5' },
            lg: { base: 'px-6 py-3' },
            xl: { base: 'px-7 py-3.5' },
        }
    },
    compoundVariants: [
        { size: 'xs', square: true, class: 'p-2' },
        { size: 'sm', square: true, class: 'p-2.5' },
        { size: 'md', square: true, class: 'p-3' },
        { size: 'lg', square: true, class: 'p-3.5' },
        { size: 'xl', square: true, class: 'p-4' },
    ]
}

export default uButtonTheme;
