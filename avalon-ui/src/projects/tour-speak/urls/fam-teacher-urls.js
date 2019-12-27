import {BASE_URL} from './index.js';
import ProjectConfig from "../../../../config/project.js";

const microUrl = ProjectConfig.microService ? '/route-tour-speak' : '';
const FamTeacherUrls = {
    get: {
        famTeacherByFamTeacherId: `${BASE_URL}${microUrl}/famTeacher/get/famTeacherByFamTeacherId/{famTeacherId}`,
        listFamTeacherIds: `${BASE_URL}${microUrl}/famTeacher/get/list/famTeacherIds`,
        currentFamTeacher: `${BASE_URL}${microUrl}/famTeacher/get/currentFamTeacher`,
        pageListFamTeacher: `${BASE_URL}${microUrl}/famTeacher/get/pageList/famTeacher`
    },
    put: {
        famTeacherByFamTeacherId: `${BASE_URL}${microUrl}/famTeacher/put/famTeacherByFamTeacherId/{famTeacherId}`,
        currentFamTeacher: `${BASE_URL}${microUrl}/famTeacher/put/currentFamTeacher`
    },
    post: {
        famTeacherByPubUserId: `${BASE_URL}${microUrl}/famTeacher/post/famTeacherByPubUserId/{pubUserId}`
    },
    delete: {
        famTeacherByFamTeacherId: `${BASE_URL}${microUrl}/famTeacher/delete/famTeacherByFamTeacherId/{famTeacherId}`
    }
};

export default FamTeacherUrls;
