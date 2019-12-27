function mergeRouterAuthorizations({target, routerAuthorizations}, {multiRoleMenuRules, multiRoleMenuConflict}) {
  for (let name in routerAuthorizations) {
    //如果存在,判断子路由
    if (target[name]) {
      //校验是否一致


    } else {
      target[name] = routerAuthorizations[name];
    }
  }
}

export default function ({primaryRole, roles, routerAuthorizations}, {multiRoleMenuRules, multiRoleMenuConflict}) {
  if (!primaryRole) return;
  let results = {};
  if (!multiRoleMenuRules || multiRoleMenuRules === 'default') {
    //只显示当前角色菜单
    mergeRouterAuthorizations({
      target: results,
      routerAuthorizations: routerAuthorizations[primaryRole.value]
    }, {multiRoleMenuRules, multiRoleMenuConflict});
    return results;
  }
  if (multiRoleMenuRules === 'merge') {
    //多角色菜单合并
    roles.forEach(role => {
      mergeRouterAuthorizations({
        target: results,
        routerAuthorizations: routerAuthorizations[role.value]
      }, {multiRoleMenuRules, multiRoleMenuConflict});
    });
    return results;
  }
  return results;
}
