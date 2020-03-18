package pub.avalonframework.wechat.official.account.core.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import pub.avalonframework.wechat.official.account.core.response.*;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 请求消息工具类
 *
 * @author baichao
 */
public final class RequestMessageUtils {

    private RequestMessageUtils() {
    }

    /**
     * 解析微信发来的请求（XML）
     *
     * @param request
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中  
        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流  
        InputStream inputStream = request.getInputStream();
        // 读取输入流  
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素  
        Element root = document.getRootElement();
        // 得到根元素的所有子节点  
        List<Element> elementList = root.elements();

        // 遍历所有子节点  
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        // 释放资源
        inputStream.close();
        return map;
    }

    /**
     * 根据消息类型转成对应xml
     */
    public static String baseMessageToXml(BaseMessage baseMessage) {
        if (baseMessage instanceof TextMessage) {
            return textMessageToXml((TextMessage) baseMessage);
        } else if (baseMessage instanceof MusicMessage) {
            return musicMessageToXml((MusicMessage) baseMessage);
        } else if (baseMessage instanceof NewsMessage) {
            return newsMessageToXml((NewsMessage) baseMessage);
        } else {
            return null;
        }
    }

    /**
     * 文本消息对象转换成xml
     *
     * @param textMessage 文本消息对象
     */
    public static String textMessageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 音乐消息对象转换成xml
     *
     * @param musicMessage 音乐消息对象
     */
    public static String musicMessageToXml(MusicMessage musicMessage) {
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }

    /**
     * 图文消息对象转换成xml
     *
     * @param newsMessage 图文消息对象
     */
    public static String newsMessageToXml(NewsMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", Article.class);
        return xstream.toXML(newsMessage);
    }

    /**
     * 扩展xstream，使其支持CDATA块
     *
     * @date 2013-05-19
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记  
                boolean cdata = true;

                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    /**
     * 计算采用utf-8编码方式时字符串所占字节数
     *
     * @param content
     */
    public static int getByteSize(String content) {
        int size = 0;
        if (null != content) {
            // 汉字采用utf-8编码时占3个字节
            size = content.getBytes(StandardCharsets.UTF_8).length;
        }
        return size;
    }

    /**
     * 判断是否是QQ表情
     *
     * @param content
     */
    public static boolean isQqFace(String content) {
        boolean result = false;
        // 判断QQ表情的正则表达式
        String qqfaceRegex = "(/::\\)|\\[囧\\]|/::~|/::B|/::\\||/:8-\\)|/::<|/::\\$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>){1,}";
        //String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::\\$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";  
        Pattern p = Pattern.compile(qqfaceRegex);
        Matcher m = p.matcher(content);
        if (m.matches()) {
            result = true;
        }
        return result;
    }

    /**
     * 将微信消息中的CreateTime转换成标准格式的时间（yyyy-MM-dd HH:mm:ss）
     *
     * @param createTime 消息创建时间
     */
    public static String formatTime(String createTime) {
        // 将微信传入的CreateTime转换成long类型，再乘以1000  
        long msgCreateTime = Long.parseLong(createTime) * 1000L;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(msgCreateTime));
    }

    /**
     * 换行符
     */
    public static final String NEWLINE = "\r\n";
    public static final String NEWLINEX2 = "\r\n\r\n";
    public static final String NEWLINEX3 = "\r\n\r\n\r\n";
    public static final String NEWLINEX4 = "\r\n\r\n\r\n\r\n";
    public static final String NEWLINEX5 = "\r\n\r\n\r\n\r\n\r\n";
    /**
     * 缩进符
     */
    public static final String TAB = "\t";
    public static final String TABX2 = "\t\t";
    public static final String TABX3 = "\t\t\t";
    public static final String TABX4 = "\t\t\t\t";
    public static final String TABX5 = "\t\t\t\t\t";
    public static final String TABX6 = "\t\t\t\t\t\t";
    public static final String TABX7 = "\t\t\t\t\t\t\t";
    public static final String TABX8 = "\t\t\t\t\t\t\t\t";
    public static final String TABX9 = "\t\t\t\t\t\t\t\t\t";
    public static final String TABX10 = "\t\t\t\t\t\t\t\t\t\t";

    public static final String NEWLINE_TAB = "\r\n\t";
    public static final String NEWLINE_TABX2 = "\r\n\t\t";
    public static final String NEWLINE_TABX3 = "\r\n\t\t\t";
    public static final String NEWLINE_TABX4 = "\r\n\t\t\t\t";
    public static final String NEWLINE_TABX5 = "\r\n\t\t\t\t\t";
    public static final String NEWLINE_TABX6 = "\r\n\t\t\t\t\t\t";
    public static final String NEWLINE_TABX7 = "\r\n\t\t\t\t\t\t\t";
    public static final String NEWLINE_TABX8 = "\r\n\t\t\t\t\t\t\t\t";
    public static final String NEWLINE_TABX9 = "\r\n\t\t\t\t\t\t\t\t\t";

    public static final String NEWLINEX2_TAB = "\r\n\r\n\t";
    public static final String NEWLINEX2_TABX2 = "\r\n\r\n\t\t";
    public static final String NEWLINEX2_TABX3 = "\r\n\r\n\t\t\t";
    public static final String NEWLINEX2_TABX4 = "\r\n\r\n\t\t\t\t";
    public static final String NEWLINEX2_TABX5 = "\r\n\r\n\t\t\t\t\t";
    public static final String NEWLINEX2_TABX6 = "\r\n\r\n\t\t\t\t\t\t";
    public static final String NEWLINEX2_TABX7 = "\r\n\r\n\t\t\t\t\t\t\t";
    public static final String NEWLINEX2_TABX8 = "\r\n\r\n\t\t\t\t\t\t\t\t";
    public static final String NEWLINEX2_TABX9 = "\r\n\r\n\t\t\t\t\t\t\t\t\t";
    /**
     * 注释头
     */
    public static final String NOTE_HEAD = "<!-- ";
    /**
     * 注释尾
     */
    public static final String NOTE_FOOT = " -->";
}