const merge = require('webpack-merge');

/**
 * 深合并
 * 该方法不会改变原有对象, 合并后返回一个新对象
 * 例：a={aa:{aaa:1}}、b={}、c={cc:2} 执行 d=deepMerge(a,b,c)
 * 以上方法不会改变a、b、c
 * d={aa:{aaa:1},cc:2}
 * d.aa !== a.aa
 * @returns {*}
 */
export default function deepMerge() {
  return merge(...arguments);
};
