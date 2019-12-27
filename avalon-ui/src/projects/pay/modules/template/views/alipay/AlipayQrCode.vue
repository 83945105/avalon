<template>
  <div>

    <div style="margin: 15px 0px 0px 15px;">

      <div>
        <canvas id="canvas" style="width: 200px;height: 200px;border: 1px red solid;"></canvas>

        <h1 v-if="time">剩余支付时间：{{time}}</h1>
        <count-down :millisecond="timeoutExpress" @change="handleChangeTime"></count-down>
      </div>

      <el-form ref="form" :model="form" label-width="100px" style="margin: 15px 15px 15px 15px">
        <el-form-item label="商户订单号">
          <el-input v-model="form.out_trade_no"></el-input>
        </el-form-item>
        <el-form-item label="订单总金额">
          <el-input v-model="form.total_amount"></el-input>
        </el-form-item>
        <el-form-item label="订单标题">
          <el-input v-model="form.subject"></el-input>
        </el-form-item>
        <el-form-item>
          <we-button type="primary" :loading="loading" @click="getQrCode">获取支付二维码</we-button>
        </el-form-item>
      </el-form>

    </div>

  </div>
</template>

<script>
  import ApiPayAlipayQrCodeUrls from "../../urls/api-pay-alipay-qr-code-urls.js";
  import QrCode from 'qrcode';
  import CountDown from "../../../../../../components/time/src/CountDown.vue";

  export default {

    name: "alipay-qr-code",
    components: {CountDown},
    data() {
      return {
        form: {
          out_trade_no: `${parseInt(Math.random() * 1000000000000000)}`,
          total_amount: '0.01',
          subject: '测试二维码'
        },

        loading: false,

        timeoutExpress: 0,
        time: ''
      };
    },

    methods: {
      getQrCode() {
        this.loading = true;
        this.$Ajax.get(ApiPayAlipayQrCodeUrls.get.tradePreCreate, this.form)
          .success(true, data => {
            const timeoutExpress = data.records.timeout_express;
            if (isNaN(timeoutExpress)) {
              this.timeoutExpress = parseInt(timeoutExpress.replace("m")) * 60 * 1000;
            } else {
              this.timeoutExpress = timeoutExpress;
            }
            QrCode.toCanvas(this.$el.querySelector("#canvas"), data.records.qr_code, err => {
              if (err) {
                return;
              }
              this.$message({content: '生成二维码成功'});
            });
            this.pollingTradeQuerySuccess({out_trade_no: this.form.out_trade_no});
          })
          .finally(() => this.loading = false);
      },
      pollingTradeQuerySuccess({out_trade_no}) {
        this.$Ajax.get(ApiPayAlipayQrCodeUrls.get.tradeQuerySuccess, {
          out_trade_no: out_trade_no
        })
          .success(true, data => {
            if (data.records.success) {
              this.$alert({
                content: '支付完成'
              });
            } else {
              setTimeout(() => this.pollingTradeQuerySuccess({out_trade_no}), 3000);
            }
          });
      },
      handleChangeTime({dayNum, dayString, hourNum, hourString, minuteNum, minuteString, secondNum, secondString}) {
        if (hourNum === 0 && secondNum === 0 && secondNum === 0) {
          this.$alert({
            content: '订单超时'
          });
          this.time = '';
          return;
        }
        if (hourNum > 0) {
          this.time = `${hourString} 小时 ${minuteString} 分钟 ${secondString} 秒`;
        } else {
          this.time = `${minuteString} 分钟 ${secondString} 秒`;
        }
      }
    },

    mounted() {
      this.getQrCode();
    }
  }
</script>

<style scoped>

</style>
