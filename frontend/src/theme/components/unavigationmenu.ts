export const uNavigationMenuTheme = {
    slots: {
        root: 'bg-primary-500 w-full px-4',
        link: 'text-white hover:bg-primary-600 transition-colors',
        linkLabel: 'text-white',
        linkLeadingIcon: '!text-white',
        linkTrailingIcon: '!text-white'
    },
    variants: {
        active: {
            true: {
                link: 'bg-secondary-500 hover:bg-secondary-600 text-primary-500 rounded-sm',
                linkLeadingIcon: '!text-white',
                linkTrailingIcon: '!text-white'
            }
        }
    }
}

export default uNavigationMenuTheme;
