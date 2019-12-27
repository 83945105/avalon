import {RoleDirective} from './authority/index.js';

const Directives = [RoleDirective];

const install = Vue => {
    Directives.forEach(directive => Vue.use(directive));
};

export {
    RoleDirective
};

export default install;