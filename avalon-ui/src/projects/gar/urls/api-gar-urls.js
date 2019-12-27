import {BASE_URL} from './index.js';

const ApiGarUrls = {
  get: {
    isOnline: `${BASE_URL}/api-gar/get/isOnline`,
    online: `${BASE_URL}/api-gar/get/online`,
    logout: `${BASE_URL}/api-gar/get/logout`
  },
  post: {
    login: `${BASE_URL}/api-gar/post/login`
  }
};

export default ApiGarUrls;
