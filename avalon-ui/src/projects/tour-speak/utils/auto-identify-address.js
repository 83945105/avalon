import {isFunction} from "../../../utils/util.js";

export function copyTaobaoAddressArea(addressStr, callback) {
  let strs; //定义一数组
  let addresses = []; //定义一数组
  strs = addressStr.split("，");
  if (strs.length === 1) {
    strs = addressStr.split(",");
  }
  if (strs.length >= 4) {
    let name = strs[0];
    let mobile = strs[1];
    let address = "";
    if (strs.length === 5) {
      address = strs[3];
    } else {
      address = strs[2];
    }
    address = address.replace(/^(\s|\xA0)+|(\s|\xA0)+$/g, '');
    if (address.split(" ").length >= 4) {
      let indexs = 0;
      if (address.split(" ").length >= 5) {
        indexs = 3;
      } else {
        indexs = 2;
      }
      let index = find(address, ' ', indexs);
      let mainAddress = address.substring(0, index);
      let detail = address.substring(index);
      addresses = mainAddress.split(" ");
      let prov = addresses[0];

      let city = addresses[1];
      let district = addresses[2];
      let town = "";
      if (indexs === 3) {
        town = addresses[3];
      }
      const result = {
        receiverName: name,
        receiverMobile: mobile,
        receiverProvince: prov,
        addressDetail: detail,
        receiverState: prov,
        receiverAddress: detail,
        receiverPhone: "",
        receiverZip: strs[strs.length - 1],
        receiverCity: city,
        receiverDistrict: district,
        receiverTown: town
      };
      isFunction(callback) && callback(result);
      return result;
    }
  }
  return {};
}

export function copyTaobaoAddressAreaForComma(addressStr, callback) {
  let strs; //定义一数组
  strs = addressStr.split("，");
  if (strs.length === 1) {
    strs = addressStr.split(",");
  }
  if (strs.length === 7) {
    let name = strs[0];
    let mobile = strs[1];
    let prov = strs[2];
    let city = strs[3];
    let district = strs[4];
    let detail = strs[5];
    let town = "";

    const result = {
      receiverName: name,
      receiverMobile: mobile,
      receiverProvince: prov,
      addressDetail: detail,

      receiverPhone: "",
      receiverState: prov,
      receiverAddress: detail,
      receiverZip: strs[6],
      receiverCity: city,
      receiverDistrict: district,
      receiverTown: town
    };
    isFunction(callback) && callback(result);
    return result;
  }
  return {};
}

function find(str, cha, num) {
  let x = str.indexOf(cha);
  for (let i = 0; i < num; i++) {
    x = str.indexOf(cha, x + 1);
  }
  return x;
}

