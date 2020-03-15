package pub.avalonframework.office.excel;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author baichao
 */
public final class SheetRowList extends ArrayList<Object> {

    public SheetRowList(int initialCapacity) {
        super(initialCapacity);
    }

    public SheetRowList() {
    }

    public SheetRowList(Collection<?> c) {
        super(c);
    }
}