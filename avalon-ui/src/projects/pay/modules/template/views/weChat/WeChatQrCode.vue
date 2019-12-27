<template>
  <div>

    <div style="margin: 15px 0px 0px 15px;">

      <div class="weChatPay-qr-code">

        <h1 v-if="time">剩余支付时间：{{time}}</h1>
        <count-down ref="countDown" :millisecond="timeoutExpress" @change="handleChangeTime"></count-down>
      </div>

      <el-form ref="form" :model="form" label-width="120px" style="margin: 15px 15px 15px 15px">
        <el-form-item label="商户订单号">
          <el-input v-model="form.out_trade_no"></el-input>
        </el-form-item>
        <el-form-item label="订单总金额(分)">
          <el-input v-model="form.total_fee"></el-input>
        </el-form-item>
        <el-form-item label="商品描述">
          <el-input v-model="form.body"></el-input>
        </el-form-item>
        <el-form-item>
          <we-button type="primary" :loading="loading" @click="getQrCode">获取支付二维码</we-button>
        </el-form-item>
      </el-form>

    </div>

  </div>
</template>

<script>
  import QRCode from 'qrcode';
  import CountDown from "../../../../../../components/time/src/CountDown.vue";
  import ApiPayWeChatPayQrCodeUrls from "../../urls/api-pay-we-chat-pay-qr-code-urls.js";

  export default {

    name: "we-chat-qr-code",
    components: {CountDown},
    data() {
      return {
        form: {
          out_trade_no: `${parseInt(Math.random() * 1000000000000000)}`,
          total_fee: '1',
          body: '测试二维码'
        },

        loading: false,

        timeoutExpress: 0,
        time: '',
        timer: undefined
      };
    },

    methods: {
      getQrCode() {
        this.loading = true;
        this.$Ajax.get(ApiPayWeChatPayQrCodeUrls.get.tradePreCreate, this.form)
          .success(true, data => {
            const timeoutExpress = data.records.timeout_express;
            if (isNaN(timeoutExpress)) {
              this.$refs.countDown.restart(parseInt(timeoutExpress.replace("m")) * 60 * 1000);
            } else {
              this.$refs.countDown.restart(timeoutExpress);
            }
            QRCode.toCanvas(data.records.qr_code, {width: 200, errorCorrectionLevel: 'H'}, (err, canvas) => {
              if (err) {
                throw err;
              }
              this.$message({content: '生成二维码成功'});
              this.$el.querySelector(".weChatPay-qr-code").appendChild(canvas);
            });
            this.pollingTradeQuerySuccess({out_trade_no: this.form.out_trade_no});
          })
          .finally(() => this.loading = false);
      },
      pollingTradeQuerySuccess({out_trade_no}) {
        this.$Ajax.get(ApiPayWeChatPayQrCodeUrls.get.tradeQuerySuccess, {
          out_trade_no: out_trade_no
        })
          .success(true, data => {
            if (data.records.success) {
              this.$refs.countDown.clear();
              this.$alert({
                content: '支付完成'
              });
            } else {
              this.timer = setTimeout(() => this.pollingTradeQuerySuccess({out_trade_no}), 3000);
            }
          });
      },
      handleChangeTime({dayNum, dayString, hourNum, hourString, minuteNum, minuteString, secondNum, secondString}) {
        if (hourNum === 0 && minuteNum === 0 && secondNum === 0) {
          window.clearTimeout(this.timer);
          this.$alert({
            content: '支付超时,点击确定重新获取二维码',
            onClickConfirmButton: () => {
              this.$el.querySelector(".weChatPay-qr-code").querySelector('canvas').remove();
              this.$refs.countDown.clear();
              this.getQrCode();
            }
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
