package pub.avalonframework.office.excel;

/**
 * @author baichao
 */
public interface Cell extends CellOperations {

    /**
     * 获取单元格内容
     *
     * @return
     */
    Object getCellValue();
}