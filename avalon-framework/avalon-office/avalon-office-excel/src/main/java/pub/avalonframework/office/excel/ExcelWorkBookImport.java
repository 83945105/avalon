package pub.avalonframework.office.excel;

import java.io.*;

/**
 * Excel导入工作簿
 *
 * @author baichao
 */
public interface ExcelWorkBookImport<R> extends ExcelWorkBook {

    /**
     * 解析文件
     *
     * @param inputStream 文件流
     * @return ExcelWorkBookImport
     */
    ExcelWorkBookImport<R> parseFile(InputStream inputStream);

    /**
     * 获取工作表
     *
     * @param index 下标
     * @return ExcelSheetImport
     */
    @Override
    ExcelSheetImport<R> getSheet(int index);

    /**
     * 解析文件
     *
     * @param file 文件
     * @return ExcelWorkBookImport
     */
    default ExcelWorkBookImport<R> parseFile(File file) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return this;
        }
        return this.parseFile(fileInputStream);
    }

    /**
     * 解析文件
     *
     * @param path 文件路径
     * @return ExcelWorkBookImport
     */
    default ExcelWorkBookImport<R> parseFile(String path) {
        return this.parseFile(new File(path));
    }

    @FunctionalInterface
    interface HandlerSheetA {

        /**
         * 接收当前读取的Sheet
         *
         * @param sheet 读取的Sheet
         * @param index 当前Sheet下标
         */
        void accept(ExcelSheetImport<?> sheet, int index);
    }

    @FunctionalInterface
    interface HandlerSheetB {

        /**
         * 接收当前读取的Sheet
         *
         * @param sheet 读取的Sheet
         * @param index 当前Sheet下标
         * @return true - 继续读取下一个Sheet | false - 终止读取
         */
        boolean apply(ExcelSheetImport<?> sheet, int index);
    }

    /**
     * 批量读取Sheet
     *
     * @param handlerSheet 操作读取的Sheet
     * @return ExcelWorkBookImport
     */
    default ExcelWorkBookImport readSheets(HandlerSheetA handlerSheet) {
        int totalSheetSize = this.getSheetSize();
        for (int i = 0; i < totalSheetSize; i++) {
            ExcelSheetImport<?> sheet = this.getSheet(i);
            handlerSheet.accept(sheet, i);
        }
        return this;
    }

    /**
     * 批量读取Sheet
     *
     * @param handlerSheet 操作读取的Sheet,返回false不继续读取
     * @return ExcelWorkBookImport
     */
    default ExcelWorkBookImport readSheets(HandlerSheetB handlerSheet) {
        int totalSheetSize = this.getSheetSize();
        for (int i = 0; i < totalSheetSize; i++) {
            ExcelSheetImport<?> sheet = this.getSheet(i);
            boolean goon = handlerSheet.apply(sheet, i);
            if (!goon) {
                break;
            }
        }
        return this;
    }
}