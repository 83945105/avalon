import {BASE_URL} from './index.js';
import ProjectConfig from "../../../../config/project.js";

const microUrl = ProjectConfig.microService ? '/route-tour-speak' : '';
const H5Urls = {
    get: {
        famClassByClassId: `${BASE_URL}${microUrl}/h5/get/famClassByClassId/{classId}`
    },
    post: {
        signClassByClassId: `${BASE_URL}${microUrl}/h5/post/signClassByClassId/{classId}`
    }
};

export default H5Urls;
