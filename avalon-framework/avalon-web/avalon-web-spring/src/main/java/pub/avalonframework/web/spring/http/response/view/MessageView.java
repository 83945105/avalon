package pub.avalonframework.web.spring.http.response.view;

import pub.avalonframework.web.spring.http.response.ResultInfo;

/**
 * The message view.
 *
 * @author baichao
 */
public interface MessageView extends ResponseView {

    /**
     * Get result info.
     *
     * @return The result info.
     */
    ResultInfo getResultInfo();

    /**
     * Set result info.
     *
     * @param resultInfo The result info.
     */
    void setResultInfo(ResultInfo resultInfo);

    /**
     * Set result info.
     *
     * @param resultInfoJson The result info json.
     */
    void setResultInfo(String resultInfoJson);
}