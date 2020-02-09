import ResponseParser from "./response-parser.js";

export default class Default extends ResponseParser {

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