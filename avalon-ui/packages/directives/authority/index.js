import RoleDirective from './role/role.js';

const Directives = [
    RoleDirective
];

const install = Vue => {
    Directives.forEach(directive => Vue.use(directive));
};

export {
    RoleDirective
};

export default install;