<template>
  <div>
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
        <we-button :loading="loading" type="primary" @click="handleToPay">确认支付</we-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import ApiPayAlipayComputerWebsiteUrls from "../../urls/api-pay-alipay-computer-website-urls.js";

  export default {
    name: "alipay-computer-website",

    data() {
      return {
        loading: false,
        form: {
          out_trade_no: '20150320010101001',
          total_amount: '0.01',
          subject: 'Iphone6 16G'
        }
      };
    },

    methods: {
      handleToPay() {
        this.loading = true;
        this.$Ajax.get(ApiPayAlipayComputerWebsiteUrls.get.tradePagePayFormString, this.form)
          .success(true, data => {
            // 抽取出form表单
            const form = /<form[^>]*>([\s\S]*)<\/form>/.exec(data.records.form)[0];
            // 写入html
            this.$el.innerHTML = form;
            // 提交
            this.$el.getElementsByTagName("form")[0].submit();
          })
          .finally(() => this.loading = false);
      }
    }
  }
</script>

<style scoped>

</style>
