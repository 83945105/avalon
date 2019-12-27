import {BASE_URL} from '../../../urls/index.js';

const ApiGarTemplateUrls = {
    get: {
        validateValueCanUseBySubModuleId: `${BASE_URL}/api-gar-template/get/validateValueCanUseBySubModuleId/{value}/{subModuleId}`,
        templateByTemplateId: `${BASE_URL}/api-gar-template/get/templateByTemplateId/{templateId}`,
        listTemplate: `${BASE_URL}/api-gar-view/get/list/template`,
        pageListTemplate: `${BASE_URL}/api-gar-template/get/pageList/template`
    },
    post: {
        template: `${BASE_URL}/api-gar-template/post/template`
    },
    put: {
        templateByTemplateId: `${BASE_URL}/api-gar-template/put/templateByTemplateId/{templateId}`,
        switchTemplateIndexByTemplateId: `${BASE_URL}/api-gar-template/put/switchTemplateIndexByTemplateId/{sourceTemplateId}/{targetTemplateId}`,
        dragTemplateListRow: `${BASE_URL}/api-gar-template/put/dragTemplateListRow/{dragTemplateId}/{dropTemplateId}/{dropType}`
    },
    delete: {
        templateByTemplateId: `${BASE_URL}/api-gar-template/delete/templateByTemplateId/{templateId}`,
        listTemplateByTemplateIds: `${BASE_URL}/api-gar-template/delete/list/templateByTemplateIds/{templateIds}`
    }
};

export default ApiGarTemplateUrls;
