const ProjectConfig = require('./project.js');
const PackageJson = require('../package.json');
const CDNJson = require('./cdn.json');

const Mode = 'production';

module.exports.cdnModules = function () {
  let modules = [];
  if (ProjectConfig.cdn === true) {
    for (let name in PackageJson.dependencies) {
      const library = CDNJson[name];
      if (!library) continue;
      const version = PackageJson.dependencies[name].replace(/[\\^~]/g, "");
      let value = undefined;
      let devUrl = undefined;
      let prodUrl = undefined;
      if (typeof library === 'string') {
        value = library;
        devUrl = `https://www.unpkg.com/${name}@${version}`;
        prodUrl = `https://www.unpkg.com/${name}@${version}`;
      } else {
        value = library.value;
        devUrl = library.devUrl || `https://www.unpkg.com/${name}@${version}`;
        prodUrl = library.prodUrl || `https://www.unpkg.com/${name}@${version}`;
      }
      if (!value) continue;
      modules.push({
        name: name,
        version: version,
        library: library,
        value: value,
        devUrl: devUrl,
        prodUrl: prodUrl,
        url: Mode === 'production' ? prodUrl : devUrl
      });
    }
  } else if (Array.isArray(ProjectConfig.cdn)) {
    for (let name in PackageJson.dependencies) {
      if (ProjectConfig.cdn.indexOf(name) === -1) continue;
      const library = CDNJson[name];
      if (!library) continue;
      const version = PackageJson.dependencies[name].replace(/[\\^~]/g, "");
      let value = undefined;
      let devUrl = undefined;
      let prodUrl = undefined;
      if (typeof library === 'string') {
        value = library;
        devUrl = `https://www.unpkg.com/${name}@${version}`;
        prodUrl = `https://www.unpkg.com/${name}@${version}`;
      } else {
        value = library.value;
        devUrl = library.devUrl || `https://www.unpkg.com/${name}@${version}`;
        prodUrl = library.prodUrl || `https://www.unpkg.com/${name}@${version}`;
      }
      if (!value) continue;
      modules.push({
        name: name,
        version: version,
        library: library,
        value: value,
        devUrl: devUrl,
        prodUrl: prodUrl,
        url: Mode === 'production' ? prodUrl : devUrl
      });
    }
  }
  return modules;
};
