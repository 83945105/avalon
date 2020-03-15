package pub.avalonframework.office.excel;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author baichao
 */
public final class SheetRowMap extends LinkedHashMap<String, Object> {

    public SheetRowMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public SheetRowMap(int initialCapacity) {
        super(initialCapacity);
    }

    public SheetRowMap() {
    }

    public SheetRowMap(Map<? extends String, ?> m) {
        super(m);
    }

    public SheetRowMap(int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }
}