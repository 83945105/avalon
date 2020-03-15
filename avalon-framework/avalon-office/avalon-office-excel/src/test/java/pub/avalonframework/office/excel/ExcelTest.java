package pub.avalonframework.office.excel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author baichao
 */
public class ExcelTest {

    @Test
    void test01() throws IOException, InstantiationException, IllegalAccessException {
        final String filePath = "/Users/baichao/develop/idea_projects/avalon/avalon-framework/avalon-office/avalon-office-excel/src/test/resources/test01.xlsx";

        // 导出

        /*模拟从数据库获取数据开始*/
        Collection<Map<String, Object>> records = new ArrayList<>();
        Map<String, Object> record01 = new HashMap<>();
        Map<String, Object> record02 = new HashMap<>();
        Map<String, Object> record03 = new HashMap<>();
        record01.put("username", "张三1");
        record01.put("password", "666");
        record02.put("username", "张三2");
        record02.put("password", "666");
        record03.put("username", "张三3");
        record03.put("password", "666");
        records.add(record01);
        records.add(record02);
        records.add(record03);
        /*模拟从数据库获取数据结束*/

//        ExcelExportFactory.buildSXSSFExportExcelWorkBook()//构建查询对象
//                .createSheet()//创建一个Sheet,可以传参设置Sheet名
//                .setColumnFields("username","password")//设置列字段,对应Map集合的key
//                .importData(records)//导入数据,支持多次导入
//                .export(filePath);//导出成Excel

        // 导入

    }
}