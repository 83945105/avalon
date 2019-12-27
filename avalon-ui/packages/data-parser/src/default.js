import Parser from "./parser.js";

export default class Default extends Parser {

  newInstance(properties) {
    return new Default(properties);
  };

  parse({data, response}) {
    this.executeBefore({data, response});
    this.executeSuccess({data, response});
    this.executeFinally({data, response});
    return this;
  };

}
