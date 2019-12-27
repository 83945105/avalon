import {BASE_URL} from '../../../urls/index.js';

const ApiGarObjectRoleUrls = {
  get: {
    listObjectRoleByObjectId: `${BASE_URL}/api-gar-object-role/get/list/objectRoleByObjectId/{objectId}`,
    listObjectRoleByRoleId: `${BASE_URL}/api-gar-object-role/get/list/objectRoleByRoleId/{roleId}`,
    listObjectIdByRoleId: `${BASE_URL}/api-gar-object-role/get/list/objectIdByRoleId/{roleId}`,
    listObjectIdByRoleValue: `${BASE_URL}/api-gar-object-role/get/list/objectIdByRoleValue/{roleValue}`,
    listRoleValueByObjectId: `${BASE_URL}/api-gar-object-role/get/list/roleValueByObjectId/{objectId}`,
    listRoleByObjectId: `${BASE_URL}/api-gar-object-role/get/list/roleByObjectId/{objectId}`
  },
  post: {
    objectRoleByObjectIdAndRoleId: `${BASE_URL}/api-gar-object-role/post/objectRoleByObjectIdAndRoleId/{objectId}/{roleId}`,
    listObjectRoleByObjectIdsAndRoleIds: `${BASE_URL}/api-gar-object-role/post/list/objectRoleByObjectIdsAndRoleIds/{objectIds}/{roleIds}`,
    objectRoleByObjectIdAndRoleValue: `${BASE_URL}/api-gar-object-role/post/objectRoleByObjectIdAndRoleValue/{objectId}/{roleValue}`
  },
  delete: {
    objectRoleByObjectRoleId: `${BASE_URL}/api-gar-object-role/delete/objectRoleByObjectRoleId/{objectRoleId}`,
    listObjectRoleByObjectRoleIds: `${BASE_URL}/api-gar-object-role/delete/list/objectRoleByObjectRoleIds/{objectRoleIds}`,
    objectRoleByObjectIdAndRoleId: `${BASE_URL}/api-gar-object-role/delete/objectRoleByObjectIdAndRoleId/{objectId}/{roleId}`,
    listObjectRoleByObjectIdsAndRoleIds: `${BASE_URL}/api-gar-object-role/delete/list/objectRoleByObjectIdsAndRoleIds/{objectIds}/{roleIds}`
  }
};

export default ApiGarObjectRoleUrls;
