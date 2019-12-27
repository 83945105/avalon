import {getLoginCacheKey, getPrimaryRoleCacheKey} from "../../../../src/vuex-modules/frame-store";
import {getCache} from "../../../../src/utils/cache";
import {isArray, isEmpty, isObject, isString} from "../../../../src/utils/util";

const RoleDirective = {};

RoleDirective.install = Vue => {
    if (Vue.prototype.$isServer) {
        return;
    }

    const getBindingRoles = (binding) => {
        if (isString(binding.value)) {
            return binding.value ? [binding.value] : void 0;
        } else if (isArray(binding.value)) {
            return binding.value.length > 0 ? binding.value : void 0;
        } else if (isObject(binding.value)) {
            if (isString(binding.value.role)) {
                return binding.value.role ? [binding.value.role] : void 0;
            } else if (isArray(binding.value.role)) {
                return binding.value.role.length > 0 ? binding.value.role : void 0;
            }
        }
        return void 0;
    };

    const getRole = () => {
        let loginCacheKey = getLoginCacheKey();
        if (!loginCacheKey) {
            return void 0;
        }
        let loginCacheJson = getCache({key: loginCacheKey});
        if (!loginCacheJson) {
            return void 0;
        }
        let userId = JSON.parse(loginCacheJson).userId;
        if (!userId) {
            return void 0;
        }
        let primaryRoleCacheKey = getPrimaryRoleCacheKey({key: userId});
        if (!primaryRoleCacheKey) {
            return void 0;
        }
        let primaryRoleJsonCache = getCache({key: primaryRoleCacheKey});
        if (!primaryRoleJsonCache) {
            return void 0;
        }
        let primaryRole = JSON.parse(primaryRoleJsonCache);
        if (!primaryRole) {
            return void 0;
        }
        return primaryRole.value;
    };

    Vue.directive('role', {
        bind(el, binding, vnode) {
            el.dataset['v_role_display'] = el.style.display;
            el.style.display = 'none';
            let bindingRoles = getBindingRoles(binding);
            let role = getRole();
            if (!isEmpty(bindingRoles) && bindingRoles.indexOf(role) !== -1) {
                el.style.display = el.dataset['v_role_display'];
            }
            el.dataset['v_role'] = role;
        },
        update(el, binding, vnode, oldVnode) {
            let role = el.dataset.v_role;
            let bindingRoles = getBindingRoles(binding);
            if (!isEmpty(bindingRoles) && bindingRoles.indexOf(role) !== -1) {
                if (el.style.display === el.dataset['v_role_display']) return;
                el.style.display = el.dataset['v_role_display'];
            } else {
                if (el.style.display === 'none') return;
                el.dataset['v_role_display'] = el.style.display;
                el.style.display = 'none';
            }
        }
    });

};

export default RoleDirective;