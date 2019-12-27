package pub.avalonframework.cloud.gar.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author 白超
 * @date 2018/10/15
 */
public class Md5Utils {

    /**
     * MD5加密
     *
     * @param source
     * @param salt
     * @return
     */
    public static String md5(String source, String salt) {
        return new SimpleHash("MD5", source, ByteSource.Util.bytes(salt), 1024).toHex();
    }

}
