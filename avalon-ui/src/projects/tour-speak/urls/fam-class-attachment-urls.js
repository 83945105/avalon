import {BASE_URL} from './index.js';
import ProjectConfig from "../../../../config/project.js";

const microUrl = ProjectConfig.microService ? '/route-tour-speak' : '';
const FamClassAttachmentUrls = {
    get: {
        pageListMyFamClassAttachmentByClassId: `${BASE_URL}${microUrl}/famClassAttachment/get/pageList/myFamClassAttachmentByClassId/{classId}`,
        listFamClassAttachmentByClassAndUserId: `${BASE_URL}${microUrl}/famClassAttachment/get/list/famClassAttachmentByClassAndUserId/{classId}/{userId}`
    },
    post: {
        famClassAttachmentByClassId: `${BASE_URL}${microUrl}/famClassAttachment/post/famClassAttachmentByClassId/`
    },
    delete: {
        famClassAttachmentByFamClassAttachmentId: `${BASE_URL}${microUrl}/famClassAttachment/delete/famClassAttachmentByFamClassAttachmentId/{famClassAttachmentId}`
    }
};

export default FamClassAttachmentUrls;
