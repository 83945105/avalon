<template>
  <we-layer v-model="visible"
            title="请选择角色"
            width="350px"
            height="450px"
            :escCloseable="false"
            :maskClosable="false"
            :showClose="false"
            :showFooter="false">
    <div class="common-padding-all-large">
      <div v-for="(role, index) in roleList" class="common-padding-all-small">
        <we-button :type="(primaryRoleObj && role.id === primaryRoleObj.id) ? 'primary' : 'default'"
                   size="large"
                   class="common-w-all common-padding-all"
                   @click="handleSelectedRole({role, index})"
        >
          {{role.name}}
        </we-button>
      </div>
    </div>
  </we-layer>
</template>

<script>
  export default {
    name: "switch-primary-role-window",

    props: {
      value: Boolean,
      primaryRole: Object,
      roles: Array
    },

    data() {
      return {
        visible: this.value,
        roleList: this.roles,
        primaryRoleObj: this.primaryRole
      };
    },

    computed: {
      hasPrimaryRole() {
        return this.primaryRoleObj && this.roleList.map(val => val.id).indexOf(this.primaryRoleObj.id) !== -1;
      }
    },

    watch: {
      value(val) {
        this.visible = val;
      },
      visible(val) {
        this.$emit('input', val);
      },
      primaryRole(val) {
        this.primaryRoleObj = val;
      },
      roles(val) {
        this.roleList = val;
      }
    },

    methods: {
      handleSelectedRole({role, index}) {
        this.primaryRoleObj = role;
        this.$emit('selected-role', {primaryRole: role, index: index, roles: this.roleList}, this);
        this.visible = false;
      }
    }
  }
</script>
