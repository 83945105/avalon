import merge from "../../../utils/merge.js";
import Util from "../../../utils/util.js";

export const Data = {
  dict: {
  }
};

export default {
  data() {
    return merge({}, Data)
  },

  mixins: [Util],

  methods: {
    formatterTime(timeStr, len = 19) {
      return timeStr.replace("T", " ").substring(0, len);
    }
  }

}
