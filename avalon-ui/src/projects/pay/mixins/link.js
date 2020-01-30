/*!
 * Title: 跳转
 * Description:
 * author: baichao
 * date: 2018/1/26
 * version: v1.0
 */

export default {

  methods: {
    /*消息通告*/
    /**
     * 跳转到设备详情页面（app）
     */
    routerToMessage() {
      this.$router.push({name: 'Message'});
    }
  }
};
