import {BASE_URL} from './index.js';
import ProjectConfig from "../../../../config/project.js";

const microUrl = ProjectConfig.microService ? '/route-tour-speak' : '';
const FamClassHomeworkUrls = {
    get: {
        famClassHomeworkByFamClassId: `${BASE_URL}${microUrl}/famClassHomework/get/famClassHomeworkByFamClassId/{famClassId}`,
        famClassHomeworkByFamClassIdAndUserId: `${BASE_URL}${microUrl}/famClassHomework/get/famClassHomeworkByFamClassIdAndUserId/{famClassId}/{userId}`
    },
    put: {
        famClassHomeworkByFamClassHomeworkId: `${BASE_URL}${microUrl}/famClassHomework/put/famClassHomeworkByFamClassHomeworkId/{famClassHomeworkId}`,
        updateOrInsertFamClassHomeworkByFamClassHomeworkId: `${BASE_URL}${microUrl}/famClassHomework/put/updateOrInsertFamClassHomeworkByFamClassHomeworkId/{famClassHomeworkId}`
    },
    post: {
        famClassHomeworkByFamClassId: `${BASE_URL}${microUrl}/famClassHomework/post/famClassHomeworkByFamClassId/{famClassId}`
    }
};

export default FamClassHomeworkUrls;
