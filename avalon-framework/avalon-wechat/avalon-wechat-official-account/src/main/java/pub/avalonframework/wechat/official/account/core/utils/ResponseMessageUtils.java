package pub.avalonframework.wechat.official.account.core.utils;

import org.springframework.beans.BeanUtils;
import pub.avalonframework.wechat.official.account.core.JsonKey;
import pub.avalonframework.wechat.official.account.core.response.Article;
import pub.avalonframework.wechat.official.account.core.response.BaseMessage;
import pub.avalonframework.wechat.official.account.core.response.NewsMessage;
import pub.avalonframework.wechat.official.account.core.response.TextMessage;

/**
 * 用于构建返回消息
 *
 * @author baichao
 */
public final class ResponseMessageUtils {

    private ResponseMessageUtils() {
    }

    /**
     * 构建一个TextMessage对象
     */
    public static TextMessage buildTextMessage(BaseMessage baseMessage, String content) {
        TextMessage textMessage = new TextMessage();
        BeanUtils.copyProperties(baseMessage, textMessage);
        textMessage.setContent(content);
        textMessage.setMsgType(JsonKey.MessageTypeKey.RESP_TEXT.getValue());
        return textMessage;
    }

    /**
     * 构建一个Article
     *
     * @param title       名称
     * @param description 描述
     * @param picUrl      图片地址
     * @param url         点击图片跳转地址
     */
    public static Article buildArticle(String title, String description, String picUrl, String url) {
        title = title == null ? "" : title;
        description = description == null ? "" : description;
        picUrl = picUrl == null ? "" : picUrl;
        url = url == null ? "" : url;
        Article article = new Article();
        article.setTitle(title);
        article.setDescription(description);
        article.setPicUrl(picUrl);
        article.setUrl(url);
        return article;
    }

    /**
     * 构建一个NewsMessage对象
     */
    public static NewsMessage buildNewsMessage(BaseMessage baseMessage, Article... articles) {
        NewsMessage newsMessage = new NewsMessage();
        BeanUtils.copyProperties(baseMessage, newsMessage);
        newsMessage.setArticleCount(articles.length);
        for (Article article : articles) {
            newsMessage.addArticle(article);
        }
        newsMessage.setMsgType(JsonKey.MessageTypeKey.RESP_NEWS.getValue());
        return newsMessage;
    }
}