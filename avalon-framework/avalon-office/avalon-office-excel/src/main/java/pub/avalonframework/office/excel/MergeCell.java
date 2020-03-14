package pub.avalonframework.office.excel;

/**
 * 合并单元格
 *
 * @author baichao
 */
public interface MergeCell {

    /**
     * 获取开始行号
     *
     * @return
     */
    int getStartRowNum();

    /**
     * 获取结束行号
     *
     * @return
     */
    int getEndRowNum();

    /**
     * 获取开始列号
     *
     * @return
     */
    int getStartColNum();

    /**
     * 获取结束列号
     *
     * @return
     */
    int getEndColNum();

    /**
     * 设置开始行号
     *
     * @param startRowNum 开始行号
     */
    void setStartRowNum(int startRowNum);

    /**
     * 设置开始列号
     *
     * @param startColNum 开始列号
     */
    void setStartColNum(int startColNum);
}