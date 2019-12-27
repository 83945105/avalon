import {BASE_URL} from './index.js';
import ProjectConfig from "../../../../config/project.js";

const microUrl = ProjectConfig.microService ? '/route-tour-speak' : '';
const FamClassUrls = {
    get: {
        pageListMyFamClass: `${BASE_URL}${microUrl}/famClass/get/pageList/myFamClass`,
        myFamClassByFamClassId: `${BASE_URL}${microUrl}/famClass/get/myFamClassByFamClassId/{famClassId}`,
        famClassByFamClassId: `${BASE_URL}${microUrl}/famClass/get/famClassByFamClassId/{famClassId}`,
        pageListPublishFamClass: `${BASE_URL}${microUrl}/famClass/get/pageList/publishFamClass`
    },
    put: {
        famClassByFamClassId: `${BASE_URL}${microUrl}/famClass/put/famClassByFamClassId/{famClassId}`,
        publishFamClassByFamClassId: `${BASE_URL}${microUrl}/famClass/put/publishFamClassByFamClassId/{famClassId}`
    },
    post: {
        famClassImage: `${BASE_URL}${microUrl}/famClass/post/famClassImage`,
        famClass: `${BASE_URL}${microUrl}/famClass/post/famClass`,
        famClassForAdmin: `${BASE_URL}${microUrl}/famClass/post/famClassForAdmin`
    },
    delete: {
        famClassByFamClassId: `${BASE_URL}${microUrl}/famClass/delete/famClassByFamClassId/{famClassId}`
    }
};

export default FamClassUrls;
