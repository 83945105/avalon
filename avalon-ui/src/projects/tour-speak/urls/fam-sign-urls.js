import {BASE_URL} from './index.js';
import ProjectConfig from "../../../../config/project.js";

const microUrl = ProjectConfig.microService ? '/route-tour-speak' : '';
const FamSignUrls = {
    get: {
        pageListMySignClass: `${BASE_URL}${microUrl}/famSign/get/pageList/mySignClass`,
        pageListSignUserByClassId: `${BASE_URL}${microUrl}/famSign/get/pageList/signUserByClassId/{classId}`,
        pageListSignUserAttachmentByClassId: `${BASE_URL}${microUrl}/famSign/get/pageList/signUserAttachmentByClassId/{classId}`,
        listSignAndSignUserByClassId: `${BASE_URL}${microUrl}/famSign/get/list/signAndSignUserByClassId/{classId}`,
        downloadExcelSignUserByClassId: `${BASE_URL}${microUrl}/famSign/get/downloadExcelSignUserByClassId/{classId}`,
        downloadExcel: `${BASE_URL}${microUrl}/famSign/get/downloadExcel/{excelName}/{filePath}`
    },
    post: {
        famSignByClassId: `${BASE_URL}${microUrl}/famSign/post/famSignByClassId/{classId}`
    },
    put: {
        unFamSignByClassId: `${BASE_URL}${microUrl}/famSign/put/unFamSignByClassId/{classId}`,
        unFamSignByClassIdAndUserId: `${BASE_URL}${microUrl}/famSign/put/unFamSignByClassIdAndUserId/{classId}/{userId}`
    }
};

export default FamSignUrls;
