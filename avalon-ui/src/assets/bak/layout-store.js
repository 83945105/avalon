/*!
 * Title: 布局
 * Description:
 * author: 白超
 * date: 2017/12/20
 * version: v1.0
 */

/**
 * https://github.com/kamranahmedse/driver.js
 */
import * as Driver from 'driver.js'; // import driver.js
import 'driver.js/dist/driver.min.css'; // import driver.js css

const MODULE_LAYOUT = {
  state: {
    driver: new Driver({
      animate: true,//是否开启动画
      opacity: 0.75,//遮罩层透明度
      padding: 10,//遮盖目标元素的方框内边距
      allowClose: false,//是否允许点击遮罩层关闭
      overlayClickNext: true,//点击遮罩进入下一条,关闭后点击遮罩关闭提示
      doneBtnText: '完成',//最后一个按钮文本
      closeBtnText: '关闭',//关闭按钮文本
      stageBackground: 'rgba(255, 255, 255, 0.4)',//目标元素遮盖框背景颜色
      nextBtnText: '下一个',//下一个按钮文本
      prevBtnText: '上一个',//上一个按钮文本
      showButtons: true,//是否显示控制按钮
      keyboardControl: true,//是否开启键盘控制步骤, 方向键
      scrollIntoViewOptions: {},        // We use `scrollIntoView()` when possible, pass here the options for it if you want any
      onHighlightStarted: (Element) => {
      }, // Called when element is about to be highlighted
      onHighlighted: (Element) => {
      },      // Called when element is fully highlighted
      onDeselected: (Element) => {
      },       // Called when element has been deselected
      onReset: (Element) => {
      },            // Called when overlay is about to be cleared
      onNext: (Element) => {
      },                    // Called when moving to next step on any step
      onPrevious: (Element) => {
      },                // Called when moving to next step on any step
    }),
    isDriverRead: true,//是否看过系统导航
    windowWidth: window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth,//窗口宽度
    windowHeight: window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight//窗口高度
  },

  actions: {
    startDriver(module, steps) {
      if (!steps) {
        alert('未设置Driver步骤');
      }
      module.state.driver.defineSteps(steps);
      module.state.driver.start();
    },
    initWindowWidthAsync(module, e) {
      module.state.windowWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
    },
    initWindowHeightAsync(module) {
      module.state.windowHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
    }
  }
};

export {MODULE_LAYOUT};
