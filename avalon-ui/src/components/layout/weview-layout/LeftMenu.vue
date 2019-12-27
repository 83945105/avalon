<template>
  <we-menu :value="!subMenu"
           :accordion-transition="false"
           :collapse-transition="false"
           :sub-menu-trigger="collapse ? 'hover' : 'click'"
           :sub-menu-mode="collapse ? 'open' : 'local'"
           :collapse="subMenu ? false : collapse"
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
        <we-menu-item :value="row.routeValues.includes(value)"
                      @selected="handleSelectedMenuItem({row, $index: index})">
          <template slot="icon">
            <we-icon :name="row.iconName" style="font-size: 20px;"></we-icon>
          </template>
          {{row.name}}
          <template slot="subMenu">
            <we-tooltip :manual="!collapse" :content="row.name" placement="top-start">
              <left-menu :data="row.children" :value="value" :width="width" :collapse="collapse" :sub-menu="true"
                         @selected-menu-item="handleSelectedMenuItem"></left-menu>
            </we-tooltip>
          </template>
        </we-menu-item>
      </template>

      <template v-else>
        <we-tooltip :manual="!collapse" :content="row.name" placement="right">
          <we-menu-item :value="row.routeValues.includes(value)"
                        @selected="handleSelectedMenuItem({row, $index: index})">
            <template slot="icon">
              <we-icon :name="row.iconName" style="font-size: 20px;"></we-icon>
            </template>
            {{row.name}}
          </we-menu-item>
        </we-tooltip>
      </template>

    </template>
  </we-menu>
</template>

<script>

  export default {

    name: "left-menu",

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
