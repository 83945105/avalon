/*!
 * Title: 跳转
 * Description:
 * author: baichao
 * date: 2018/1/26
 * version: v1.0
 */

export default {

  methods: {
    /*登录模块开始*/
    routerToLoginIndex() {
      this.$router.push({name: 'Index'});
    },
    routerToLoginOptionsLoading() {
      this.$router.push({name: 'OptionsLoading'});
    },
    /*登录模块结束*/


    pageToModule(pageName) {
      switch (pageName) {
        case 'Login':
          window.location.href = `/gar/view/login`;
          break;
      }
    }
  }
};
