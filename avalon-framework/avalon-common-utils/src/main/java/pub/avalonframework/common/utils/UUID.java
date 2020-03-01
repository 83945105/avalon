package pub.avalonframework.common.utils;

/**
 * @author baichao
 */
public final class UUID {

    private UUID() {
    }

    public static String get() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }
}