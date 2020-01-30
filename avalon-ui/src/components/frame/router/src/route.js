const routerPathCache = {};

const setRoutePathCache = function (name, path) {
    routerPathCache[name] = path;
};

const getRoutePathCache = function (name) {
    return routerPathCache[name];
};

export {setRoutePathCache, getRoutePathCache};
