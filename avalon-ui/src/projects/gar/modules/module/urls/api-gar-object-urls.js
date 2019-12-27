import {BASE_URL} from '../../../urls/index.js';

const ApiGarObjectUrls = {
    get: {
        tableOptions: `${BASE_URL}/api-gar-object/get/tableOptions`,
        objectByObjectId: `${BASE_URL}/api-gar-object/get/objectByObjectId/{objectId}`,
        pageListObject: `${BASE_URL}/api-gar-object/get/pageList/object`
    },
    put: {
        objectEnabledByObjectId: `${BASE_URL}/api-gar-object/put/objectEnabledByObjectId/{objectId}`,
        objectDisabledByObjectId: `${BASE_URL}/api-gar-object/put/objectDisabledByObjectId/{objectId}`
    }
};

export default ApiGarObjectUrls;
