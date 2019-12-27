import {BASE_URL} from './index.js';
import ProjectConfig from "../../../../config/project.js";

const microUrl = ProjectConfig.microService ? '/route-tour-speak' : '';
const PubUserUrls = {
    get: {
        pageListPubUser: `${BASE_URL}${microUrl}/pubUser/get/pageList/pubUser`
    }
};

export default PubUserUrls;
