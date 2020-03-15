package pub.avalonframework.office.excel;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

/**
 * @author baichao
 */
public class ExcelTest {

    @Test
    void test01() throws IOException, InstantiationException, IllegalAccessException {
        final String filePath = "/Users/baichao/develop/idea_projects/avalon/avalon-framework/avalon-office/avalon-office-excel/src/test/resources/test01.xlsx";

        // 导出

        /*模拟从数据库获取数据开始*/
        List<Map<String, Object>> records = new ArrayList<>();
        Map<String, Object> record01 = new LinkedHashMap<>();
        Map<String, Object> record02 = new LinkedHashMap<>();
        Map<String, Object> record03 = new LinkedHashMap<>();
        record01.put("username", "张三1");
        record01.put("password", 1);
        record02.put("username", "张三2");
        record02.put("password", 2);
        record03.put("username", "张三3");
        record03.put("password", 3);
        records.add(record01);
        records.add(record02);
        records.add(record03);
        /*模拟从数据库获取数据结束*/

        int len = records.size();

        ExcelExportFactory.buildSXSSFExportExcelWorkBook()//构建查询对象
                .createSheet()//创建一个Sheet,可以传参设置Sheet名
                .setColumnFields("username", "password")//设置列字段,对应Map集合的key
                .importData(records)//导入数据,支持多次导入
                .export(filePath);//导出成Excel

        // 导入
        Assertions.assertEquals(0, ExcelImportFactory.buildXSSFImportExcelWorkBook()
                .getSheetSize());

        List<SheetRowList> rows01 = ExcelImportFactory.buildXSSFImportExcelWorkBook()
                .parseFile(filePath)
                .getSheet(0)
                .readRows()
                .getReadData();
        Assertions.assertEquals(len, rows01.size());
        for (int i = 0; i < len; i++) {
            Assertions.assertEquals(SheetRowList.class, rows01.get(i).getClass());
            Assertions.assertEquals(records.get(i).get("username").toString(), rows01.get(i).get(0).toString());
            Assertions.assertEquals(records.get(i).get("password").toString(), rows01.get(i).get(1).toString());
        }

        List<SheetRowMap> rows02 = ExcelImportFactory.buildXSSFImportExcelWorkBook()
                .parseFile(filePath)
                .getSheet(0)
                // 该Excel没有表头, 应设置rowSpan为0, 否则会自动计算表头占用1行,导致第一行数据读不到
                .setColumnFields(0, "username", "password")
                .readRows()
                .getReadData();
        Assertions.assertEquals(len, rows02.size());
        for (int i = 0; i < len; i++) {
            Assertions.assertEquals(SheetRowMap.class, rows02.get(i).getClass());
            Assertions.assertEquals(records.get(i).get("username").toString(), rows02.get(i).get("username").toString());
            Assertions.assertEquals(records.get(i).get("password").toString(), rows02.get(i).get("password").toString());
        }

        List<SheetRowMap> rows03 = ExcelImportFactory.buildXSSFImportExcelWorkBook(SheetRowMap.class)
                .parseFile(filePath)
                .getSheet(0)
                .readRows()
                .getReadData();
        Assertions.assertEquals(len, rows03.size());
        for (int i = 0; i < len; i++) {
            int num = i + 1;
            Assertions.assertEquals(SheetRowMap.class, rows03.get(i).getClass());
            Assertions.assertEquals("张三" + num, rows03.get(i).get("A" + num).toString());
            Assertions.assertEquals(num, Integer.valueOf(rows03.get(i).get("B" + num).toString()));
        }

        List<LinkedHashMap<String, Object>> rows04 = ExcelImportFactory.buildXSSFImportExcelWorkBook(new TypeReference<LinkedHashMap<String, Object>>() {
        })
                .parseFile(filePath)
                .getSheet(0)
                .readRows()
                .getReadData();
        Assertions.assertEquals(len, rows04.size());
        for (int i = 0; i < len; i++) {
            int num = i + 1;
            Assertions.assertEquals(LinkedHashMap.class, rows04.get(i).getClass());
            Assertions.assertEquals("张三" + num, rows04.get(i).get("A" + num).toString());
            Assertions.assertEquals(num, Integer.valueOf(rows04.get(i).get("B" + num).toString()));
        }
    }

}