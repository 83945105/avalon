package pub.avalonframework.office.excel.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import pub.avalonframework.office.excel.impl.SXSSFTitleCell;

import java.util.List;

/**
 * SXSSFWorkbook 解析器
 *
 * @author baichao
 */
public abstract class AbstractSXSSFExcelParser extends AbstractXSSFExcelParser {

    /**
     * 解析单元格json数据
     *
     * @param titlesJson 表头json
     * @return 表头信息二维数组
     */
    @Override
    public SXSSFTitleCell[][] parseCellsJson(String titlesJson) {
        JSONArray jsonArray = JSON.parseArray(titlesJson);
        SXSSFTitleCell[][] rs = new SXSSFTitleCell[jsonArray.size()][];
        for (int i = 0; i < jsonArray.size(); i++) {
            List<SXSSFTitleCell> list = JSON.parseArray(jsonArray.get(i).toString(), SXSSFTitleCell.class);
            rs[i] = list.toArray(new SXSSFTitleCell[0]);
        }
        return rs;
    }
}