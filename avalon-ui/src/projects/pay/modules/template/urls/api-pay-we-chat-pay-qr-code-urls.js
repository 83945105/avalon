import {BASE_URL} from '../../../urls/index.js';

const ApiPayWeChatPayQrCodeUrls = {
  get: {
    tradePreCreate: `${BASE_URL}/api-pay-weChatPay-qr-code/get/tradePreCreate`,
    tradeQuerySuccess: `${BASE_URL}/api-pay-weChatPay-qr-code/get/tradeQuerySuccess`
  },
  post: {},
  put: {},
  delete: {}
};

export default ApiPayWeChatPayQrCodeUrls;
