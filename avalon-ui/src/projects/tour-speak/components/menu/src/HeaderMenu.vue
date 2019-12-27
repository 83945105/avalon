<template>
    <we-menu :value="true"
             mode="horizontal"
             sub-menu-mode="open"
             :text-color="textColor"
             :background-color="backgroundColor"
             :active-text-color="activeTextColor"
             :active-background-color="activeBackgroundColor"
             :active-line-color="activeLineColor"
             :selected-text-color="selectedTextColor"
             :selected-background-color="selectedBackgroundColor"
             :hover-text-color="hoverTextColor"
             :hover-background-color="hoverBackgroundColor"
             :width="width"
    >
        <template v-for="(row, index) in data">

            <template v-if="row.children && row.children.length > 0">
                <we-menu-item :value="row.routeValues.length === 0 ? row.value === value : row.routeValues.includes(value)"
                              @selected="handleSelectedMenuItem({row, $index: index})"
                >{{row.name}}
                    <template slot="subMenu">
                        <we-tooltip :content="row.name" placement="left-start">
                            <header-menu :data="row.children" :width="width" :sub-menu="true"
                                         @selected-menu-item="handleSelectedMenuItem"
                            ></header-menu>
                        </we-tooltip>
                    </template>
                </we-menu-item>
            </template>

            <template v-else>
                <we-menu-item :value="row.routeValues.length === 0 ? row.value === value : row.routeValues.includes(value)"
                              @selected="handleSelectedMenuItem({row, $index: index})"
                >{{row.name}}
                </we-menu-item>
            </template>

        </template>
    </we-menu>
</template>

<script>

    export default {

        name: "header-menu",

        props: {
            value: String,
            // 菜单数据
            data: {
                type: Array,
                default() {
                    return [];
                }
            },
            // 菜单宽度
            width: [String, Number],
            // 是否折叠
            collapse: Boolean,
            // 是否是子菜单
            subMenu: Boolean,
            textColor: String,
            backgroundColor: String,
            activeTextColor: String,
            activeBackgroundColor: String,
            activeLineColor: String,
            selectedTextColor: String,
            selectedBackgroundColor: String,
            hoverTextColor: String,
            hoverBackgroundColor: String
        },

        methods: {
            handleSelectedMenuItem({row, $index}) {
                this.$emit('selected-menu-item', {row, $index});
            }
        }

    }
</script>
