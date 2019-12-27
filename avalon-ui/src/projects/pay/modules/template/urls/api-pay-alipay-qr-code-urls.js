import {BASE_URL} from '../../../urls/index.js';

const ApiPayAlipayQrCodeUrls = {
  get: {
    tradePreCreate: `${BASE_URL}/api-pay-alipay-qr-code/get/tradePreCreate`,
    tradeQuerySuccess: `${BASE_URL}/api-pay-alipay-qr-code/get/tradeQuerySuccess`
  },
  post: {},
  put: {},
  delete: {}
};

export default ApiPayAlipayQrCodeUrls;
