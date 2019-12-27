import {BASE_URL} from './index.js';
import ProjectConfig from "../../../../config/project.js";

const microUrl = ProjectConfig.microService ? '/route-tour-speak' : '';
const PubOrgUrls = {
    get: {
        pageListSchool: `${BASE_URL}${microUrl}/pubOrg/get/pageList/school`,
        pageListGrade: `${BASE_URL}${microUrl}/pubOrg/get/pageList/grade`,
        listSchoolType: `${BASE_URL}${microUrl}/pubOrg/get/list/schoolType`
    }
};

export default PubOrgUrls;
