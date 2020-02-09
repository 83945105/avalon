package pub.avalonframework.web.spring.http.response.view.impl;

import pub.avalonframework.web.spring.http.response.ResultInfo;
import pub.avalonframework.web.spring.http.response.utils.ResponseUtils;
import pub.avalonframework.web.spring.http.response.view.MessageView;

/**
 * The default message view.
 *
 * @author baichao
 */
public final class DefaultMessageView implements MessageView {

    private ResultInfo resultInfo;

    public DefaultMessageView(ResultInfo resultInfo) {
        this.resultInfo = resultInfo;
    }

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