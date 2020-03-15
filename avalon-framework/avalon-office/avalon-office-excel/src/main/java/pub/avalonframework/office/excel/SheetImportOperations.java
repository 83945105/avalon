package pub.avalonframework.office.excel;

import java.util.List;

/**
 * Sheet导入操作
 *
 * @author baichao
 */
@SuppressWarnings("unused")
public interface SheetImportOperations<R, T extends SheetImportOperations<R, T>> extends Sheet<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    ExcelWorkBookImport getOwnerWorkBook();

    /**
     * 获取物理行数
     *
     * @return 物理行数
     */
    int getPhysicalNumberOfRows();

    /**
     * 读取数据(使用默认数据类型或者表头设置的数据类型)
     *
     * @return SheetImportOperations
     */
    SheetImportOperations<R, T> readRows();

    /**
     * 读取数据(使用默认数据类型或者表头设置的数据类型)
     *
     * @param handlerRow 操作当前行数据
     * @return SheetImportOperations
     */
    SheetImportOperations<R, T> readRows(HandlerRowA<R> handlerRow);

    /**
     * 读取数据(使用默认数据类型或者表头设置的数据类型)
     *
     * @param handlerRow 操作当前行数据,返回false不继续读取下一行
     * @return SheetImportOperations
     */
    SheetImportOperations<R, T> readRows(HandlerRowB<R> handlerRow);

    /**
     * 获取读到的数据
     *
     * @return ArrayList
     */
    List<R> getReadData();

    /**
     * 根据下标获取第N次读取的数据
     *
     * @param index 下标
     * @return ArrayList
     */
    List<R> getReadData(int index);

    /**
     * 获取所有读取的数据
     *
     * @return ArrayList
     */
    List<List<R>> getAllReadData();

    /**
     * 操作行
     */
    @FunctionalInterface
    interface HandlerRowA<T> {

        /**
         * 接收行
         *
         * @param record  当前行数据对象
         * @param records 数据容器
         * @param rowNum  行号
         * @param index   当前行数据在数据容器中的下标
         */
        void accept(T record, List<T> records, int rowNum, int index);
    }

    /**
     * 操作行
     */
    @FunctionalInterface
    interface HandlerRowB<T> {

        /**
         * 接收行
         *
         * @param record  当前行数据对象
         * @param records 数据容器
         * @param rowNum  行号
         * @param index   当前行数据在数据容器中的下标
         * @return 是否继续读取下一行
         */
        boolean apply(T record, List<T> records, int rowNum, int index);
    }
}