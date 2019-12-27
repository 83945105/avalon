import {BASE_URL} from './index.js';
import ProjectConfig from "../../../../config/project.js";

const microUrl = ProjectConfig.microService ? '/route-tour-speak' : '';
const FamQuestionReplyUrls = {
    get: {
        listFamQuestionReplyByClassId: `${BASE_URL}${microUrl}/famQuestionReply/get/list/famQuestionReplyByClassId/{classId}`,
        pageListFamQuestionReplyByClassId: `${BASE_URL}${microUrl}/famQuestionReply/get/pageList/famQuestionReplyByClassId/{classId}`
    },
    post: {
        famQuestionReplyByClassId: `${BASE_URL}${microUrl}/famQuestionReply/post/famQuestionReplyByClassId/{classId}`
    }
};

export default FamQuestionReplyUrls;
