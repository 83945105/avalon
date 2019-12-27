<template>
  <span></span>
</template>

<script>
  export default {

    name: "count-down",

    props: {
      // 递减数量
      countNum: {
        type: Number,
        default: 1000
      }
    },

    data() {
      return {
        num: 0,
        day: 0,
        hr: 0,
        min: 0,
        sec: 0,
        timeout: undefined
      }
    },

    watch: {
      num(val) {
        this.$emit('change', {
          dayNum: this.dayNum,
          dayString: this.dayString,
          hourNum: this.hourNum,
          hourString: this.hourString,
          minuteNum: this.minuteNum,
          minuteString: this.minuteString,
          secondNum: this.secondNum,
          secondString: this.secondString
        }, this);
      }
    },

    computed: {
      dayNum() {
        return parseInt(this.num / 1000 / 60 / 60 / 24);
      },
      dayString() {
        return `${this.dayNum}`;
      },
      hourNum() {
        return parseInt(this.num / 1000 / 60 / 60 % 24);
      },
      hourString() {
        return this.hourNum > 9 ? `${this.hourNum}` : `0${this.hourNum}`;
      },
      minuteNum() {
        return parseInt(this.num / 1000 / 60 % 60);
      },
      minuteString() {
        return this.minuteNum > 9 ? `${this.minuteNum}` : `0${this.minuteNum}`;
      },
      secondNum() {
        return parseInt(this.num / 1000 % 60);
      },
      secondString() {
        return this.secondNum > 9 ? `${this.secondNum}` : `0${this.secondNum}`;
      }
    },

    methods: {
      restart(millisecond) {
        this.clear();
        if (millisecond > 0) {
          this.num = millisecond;
          this.start();
        }
      },
      start() {
        this.timeout = setInterval(() => {
          this.num -= this.countNum;
          if (this.num < 0) {
            this.num = 0;
            window.clearInterval(this.timeout);
          }
        }, this.countNum);
      },
      stop() {
        window.clearInterval(this.timeout);
      },
      clear() {
        this.stop();
        this.num = 0;
      }
    }
  }
</script>
