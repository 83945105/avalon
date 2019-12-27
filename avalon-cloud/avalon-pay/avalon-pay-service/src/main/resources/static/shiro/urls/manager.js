const ManagerUrl = {
  get: {
    /**
     * 获取当前用户
     */
    currentPrincipal: `api-shiro/get/currentPrincipal`,
    /**
     * 退出登录
     */
    logout: 'api-shiro/get/logout'
  },
  post: {
    /**
     * 登录
     * @param username
     * @param password
     */
    login: `api-shiro/post/login`
  },
  put: {},
  delete: {}
};

export default ManagerUrl;
