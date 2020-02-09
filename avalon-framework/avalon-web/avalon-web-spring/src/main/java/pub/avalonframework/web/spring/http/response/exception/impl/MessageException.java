package pub.avalonframework.web.spring.http.response.exception.impl;

import pub.avalonframework.web.spring.http.response.ResultInfo;
import pub.avalonframework.web.spring.http.response.exception.ResponseException;
import pub.avalonframework.web.spring.http.response.utils.ResponseUtils;
import pub.avalonframework.web.spring.http.response.view.ExceptionView;
import pub.avalonframework.web.spring.http.response.view.MessageView;
import pub.avalonframework.web.spring.http.response.view.impl.ExceptionMessageView;

/**
 * The message exception.
 *
 * @author baichao
 */
public class MessageException extends ResponseException implements MessageView {

    private ResultInfo resultInfo;

    public MessageException(ResultInfo resultInfo) {
        super(resultInfo.getMessage());
        this.resultInfo = resultInfo;
    }

    public MessageException(ResultInfo resultInfo, Throwable cause) {
        super(resultInfo.getMessage(), cause);
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

    @Override
    public ExceptionView transformToExceptionView() {
        return new ExceptionMessageView(this.resultInfo);
    }
}