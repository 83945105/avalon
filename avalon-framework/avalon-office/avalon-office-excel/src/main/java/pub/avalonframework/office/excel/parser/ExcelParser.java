package pub.avalonframework.office.excel.parser;

import pub.avalonframework.common.utils.SortUtils;
import pub.avalonframework.office.excel.BaseCell;
import pub.avalonframework.office.excel.BaseExcelTitleCell;
import pub.avalonframework.office.excel.ExcelException;
import pub.avalonframework.office.excel.impl.ExcelTitleCellError;
import pub.avalonframework.office.excel.impl.ExcelTitleException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Consumer;

/**
 * Excel解析器
 *
 * @author baichao
 */
public interface ExcelParser {

    /**
     * 解析单元格json数据
     *
     * @param titlesJson 表头json
     * @return 表头信息二维数组
     */
    BaseCell[][] parseCellsJson(String titlesJson);

    /**
     * 构建Excel 表头单元格
     *
     * @param excelTitle 表头
     * @param startRow   占用开始行号
     * @param endRow     占用结束行
     * @param startCol   占用开始列
     * @param endCol     占用结束列
     * @return 单元格合并对象
     */
    BaseExcelTitleCell buildExcelTitleCell(BaseExcelTitleCell excelTitle, int startRow, int endRow, int startCol, int endCol);

    /**
     * 搜寻影响数据的表头合并单元格数据
     * 因为表头可能有各种方式合并的情况,该方法用于找到最下面一排也就是和数据对应的合并单元格
     *
     * @param titles 表头合并单元格
     * @return 数据表头
     */
    default LinkedList<BaseExcelTitleCell> searchDataTitleCells(List<BaseExcelTitleCell> titles) {
        BaseExcelTitleCell target;
        LinkedList<BaseExcelTitleCell> targetMergeCell = new LinkedList<>();
        //先把所有源放入目标
        targetMergeCell.addAll(titles);
        //遍历源,用源的每一个元素去和目标进行比对位置
        for (BaseExcelTitleCell title : titles) {
            for (int j = 0; j < targetMergeCell.size(); ) {
                target = targetMergeCell.get(j);
                //比对位置
                //如果当前源在目标下面,且二者列有交集,则当前目标不符合条件,要删除
                boolean doRemove = title.getStartRowNum() > target.getEndRowNum() && !(title.getStartColNum() > target.getEndColNum() || title.getEndColNum() < target.getStartColNum());
                if (doRemove) {
                    //执行删除,删除后后一个元素前移,不用增加j
                    targetMergeCell.remove(j);
                } else {
                    //不删除的时候在进行下一个元素
                    j++;
                }

            }
        }
        //排序
        SortUtils.bubbleSort(targetMergeCell, (left, right) -> left.getStartColNum() > right.getStartColNum());
        return targetMergeCell;
    }

    /**
     * 处理表头
     *
     * @param titles         表头对象二维数组
     * @param defaultSeatRow 记录位置信息初始化默认行数
     * @param defaultSeatCol 记录位置信息初始化默认列数
     * @param handler        处理单元格合并对象回调函数
     */
    default void handlerExcelTitles(BaseExcelTitleCell[][] titles, int defaultSeatRow, int defaultSeatCol, Consumer<BaseExcelTitleCell> handler) {
        BaseExcelTitleCell[] excelTitles;
        //结束行
        int endRow;
        //结束列
        int endCol;
        //位置
        int[][] seat = new int[defaultSeatRow][defaultSeatCol];
        int[] cursor;//游标
        for (int i = 0; i < titles.length; i++) {
            excelTitles = titles[i];
            for (BaseExcelTitleCell baseExcelTitleCell : excelTitles) {
                cursor = searchStartCursor(seat, i);
                seat = validateExpandSeat(seat, cursor, baseExcelTitleCell);
                //将占用的位置设置为已占用
                endRow = cursor[0] + baseExcelTitleCell.getRowSpan() - 1;
                endCol = cursor[1] + baseExcelTitleCell.getColSpan() - 1;
                for (int k = cursor[0]; k <= endRow; k++) {
                    for (int l = cursor[1]; l <= endCol; l++) {
                        seat[k][l] = SeatStatus.YES.value;
                    }
                }
                handler.accept(this.buildExcelTitleCell(baseExcelTitleCell, cursor[0] + 1, endRow, cursor[1] + 1, endCol));
            }
        }
    }

    /**
     * 解析json数据
     *
     * @param inputStream json数据输入流
     * @return 表头信息二维数组
     */
    default BaseCell[][] parseCellsJson(InputStream inputStream) {
        InputStreamReader reader = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExcelException(e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return parseCellsJson(sb.toString());
    }

    /**
     * 解析单元格json数据
     *
     * @param file json数据文件
     * @return 表头信息二维数组
     */
    default BaseCell[][] parseCellsJson(File file) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ExcelException(e);
        }
        return this.parseCellsJson(fileInputStream);
    }

    /**
     * 处理表头
     * 初始化位置信息默认行数为titles长度2倍,列数默认为10
     *
     * @param titles 表头对象二维数组
     * @return 单元格合并集合
     */
    default ArrayList<BaseExcelTitleCell> handlerExcelTitles(BaseExcelTitleCell[][] titles) {
        return handlerExcelTitles(titles, titles.length * 2, 10);
    }

    /**
     * 处理表头
     *
     * @param titles         表头对象二维数组
     * @param defaultSeatRow 记录位置信息初始化默认行数
     * @param defaultSeatCol 记录位置信息初始化默认列数
     * @return 单元格合并集合
     */
    default ArrayList<BaseExcelTitleCell> handlerExcelTitles(BaseExcelTitleCell[][] titles, int defaultSeatRow, int defaultSeatCol) {
        ArrayList<BaseExcelTitleCell> rs = new ArrayList<>();
        handlerExcelTitles(titles, defaultSeatRow, defaultSeatCol, rs::add);
        return rs;
    }

    /**
     * 搜寻位置中指定行第一个没被占用的起点
     *
     * @param seat   位置二维数组
     * @param rowNum 行号
     * @return 起点游标
     */
    default int[] searchStartCursor(int[][] seat, int rowNum) {
        int len = seat[rowNum] == null ? 0 : seat[rowNum].length;
        int i = 0;
        for (; i < len; i++) {
            //没有占用
            if (seat[rowNum][i] != SeatStatus.YES.value) {
                return new int[]{rowNum, i};
            }
        }
        return new int[]{rowNum, i};
    }

    /**
     * 校验+扩充占用位置
     *
     * @param seat        位置二维数组
     * @param startCursor 起点游标
     * @param excelTitle  表头
     * @return 校验/扩充后的位置
     * @throws ExcelTitleException 当单元格存在数据时
     */
    default int[][] validateExpandSeat(int[][] seat, int[] startCursor, BaseExcelTitleCell excelTitle) {
        int startRow = startCursor[0];
        int endRow = startRow + excelTitle.getRowSpan() - 1;
        int startCol = startCursor[1];
        int endCol = startCol + excelTitle.getColSpan() - 1;
        //循环所占行
        for (int i = startRow; i <= endRow; i++) {
            if (seat.length <= i) {
                //备份
                int[][] copy = seat;
                //初始化seat,长度变为结束行号+1
                seat = new int[endRow + 1][];
                int j = 0;
                for (; j < copy.length; j++) {
                    //复制原始数据
                    seat[j] = copy[j];
                }
                for (; j <= endRow; j++) {
                    //扩充行,初始化列数据
                    seat[j] = new int[endCol + 1];
                }
                //扩充了行,不需要继续校验扩充的内容,肯定可用
                return seat;
            }
            //循环所占列
            for (int j = startCol; j <= endCol; j++) {
                if (seat[i] == null || seat[i].length <= j) {
                    //扩充列
                    seat[i] = seat[i] == null ? new int[endCol + 1] : Arrays.copyOf(seat[i], endCol + 1);
                    //扩充了列,不用继续校验当前行的列
                    break;
                }
                //当前单元格存在数据
                if (seat[i][j] == SeatStatus.YES.value) {
                    throw new ExcelTitleException(new ExcelTitleCellError(i, j, excelTitle));
                }
            }
        }
        return seat;
    }

    /**
     * 搜寻指定列号对应的表头单元格
     *
     * @param titleCells  表头单元格集合
     * @param columnIndex 列下标
     * @return 对应的合并单元格, 没找到返回null
     */
    default BaseExcelTitleCell searchTitleCell(Collection<BaseExcelTitleCell> titleCells, int columnIndex) {
        int columnNum = columnIndex + 1;
        for (BaseExcelTitleCell titleCell : titleCells) {
            if (columnNum >= titleCell.getStartColNum() && columnNum <= titleCell.getEndColNum()) {
                return titleCell;
            }
        }
        return null;
    }

    /**
     * 位置状态
     */
    @SuppressWarnings("unused")
    enum SeatStatus {

        /**
         * 存在数据
         */
        YES(1),
        /**
         * 不存在数据
         */
        NO(0);

        public final int value;

        SeatStatus(int value) {
            this.value = value;
        }
    }
}