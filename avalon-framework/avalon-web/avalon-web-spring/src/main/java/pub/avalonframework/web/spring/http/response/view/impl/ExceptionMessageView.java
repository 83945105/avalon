package pub.avalonframework.web.spring.http.response.view.impl;

import pub.avalonframework.web.spring.http.response.ResultInfo;
import pub.avalonframework.web.spring.http.response.utils.ResponseUtils;
import pub.avalonframework.web.spring.http.response.view.ExceptionView;
import pub.avalonframework.web.spring.http.response.view.MessageView;

/**
 * The exception message view.
 *
 * @author baichao
 */
public class ExceptionMessageView implements ExceptionView, MessageView {

    private ResultInfo resultInfo;

    @Override
    public ResultInfo getResultInfo() {
        return resultInfo;
    }

    @Override
    public void setResultInfo(ResultInfo resultInfo) {
        this.resultInfo = resultInfo;
    }

    @Override
    public void setResultInfo(String resultInfoJson) {
        this.resultInfo = ResponseUtils.jsonToResultInfo(resultInfoJson);
    }
}