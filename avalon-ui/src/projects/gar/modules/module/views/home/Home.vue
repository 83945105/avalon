<template>
  <div>
    首页
    <we-button @click="loadingBarStart">start</we-button>
    <we-button @click="loadingBarFinish">finish</we-button>
    <!--加载进度条-->
    <div v-if="isStart" class="global-loading-bar" style="top: 20%"
         :style="{'opacity':loadingBarOpacity}">
      <div id="loadingBarInner" class="global-loading-bar-inner"
           :style="{'width':`${loadingBarWidth}%`}"></div>
    </div>
    <!--/加载进度条-->
  </div>
</template>

<script>

  export default {
    name: "Home",

    data() {
      return {
        isStart: false,
        isFinish: false,
        loadingBarTimeCycle: 200,
        loadingBarWidth: 0,
        loadingBarOpacity: 0,
        loadingBarInstance: undefined,
      }
    },

    watch: {
      isStart(val) {
        if (val) {
          this.loadingBarInstance = setInterval(() => {
            let num = this.loadingBarWidth + parseInt(this.loadingBarRandom(5, 10));
            if (num < 100) {
              this.loadingBarWidth = num;
            } else {
              this.loadingBarWidth = 99.99;
            }
          }, this.loadingBarTimeCycle);
        } else {
          window.clearTimeout(this.loadingBarInstance);
          this.loadingBarWidth = 0;
          this.loadingBarOpacity = 0;
        }
      }
    },

    methods: {
      loadingBarStart() {
        this.loadingBarWidth = 0;
        this.loadingBarOpacity = 100;
        this.isStart = true;
      },
      loadingBarFinish() {
        this.loadingBarWidth = 100;
        setTimeout(() => {
          this.loadingBarOpacity = 0;
          this.loadingBarWidth = 0;
        }, 500);
        this.isStart = true;
      },
      loadingBarRandom(min, max) {
        return Math.random() * (max - min + 1) + min;
      }
    }
  }
</script>