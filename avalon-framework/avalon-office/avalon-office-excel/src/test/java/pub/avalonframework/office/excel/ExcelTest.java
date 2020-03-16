package pub.avalonframework.office.excel;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalonframework.office.excel.impl.SXSSFExcelTitle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author baichao
 */
public class ExcelTest {

    @Test
    void test01() throws IOException {
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
            Assertions.assertEquals(records.get(i).get("password"), Double.valueOf(rows01.get(i).get(1).toString()).intValue());
        }

        List<SheetRowMap> rows02 = ExcelImportFactory.buildXSSFImportExcelWorkBook()
                .parseFile(filePath)
                .getSheet(0)
                // 该Excel没有表头, 应设置 expectedRowSpan为0, 否则会自动计算表头占用1行,导致第一行数据读不到
                .setColumnFields(SheetRowMap.class, 0, "username", "password")
                .readRows()
                .getReadData();
        Assertions.assertEquals(len, rows02.size());
        for (int i = 0; i < len; i++) {
            Assertions.assertEquals(SheetRowMap.class, rows02.get(i).getClass());
            Assertions.assertEquals(records.get(i).get("username").toString(), rows02.get(i).get("username").toString());
            Assertions.assertEquals(records.get(i).get("password"), Double.valueOf(rows02.get(i).get("password").toString()).intValue());
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
            Assertions.assertEquals(num, Double.valueOf(rows03.get(i).get("B" + num).toString()).intValue());
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
            Assertions.assertEquals(num, Double.valueOf(rows04.get(i).get("B" + num).toString()).intValue());
        }
    }

    @Test
    void test02() throws IOException {
        final String filePath = "/Users/baichao/develop/idea_projects/avalon/avalon-framework/avalon-office/avalon-office-excel/src/test/resources/test02.xlsx";

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
                .setColumnFields(true, "username", "password")//设置列字段,对应Map集合的key
                .importData(records)//导入数据,支持多次导入
                .export(filePath);//导出成Excel

        // 导入
        Assertions.assertEquals(0, ExcelImportFactory.buildXSSFImportExcelWorkBook()
                .getSheetSize());

        List<SheetRowList> rows01 = ExcelImportFactory.buildXSSFImportExcelWorkBook()
                .parseFile(filePath)
                .getSheet(0)
                // 将游标移动至 0 , 也就是第一行 , 接下来读取数据会从第2行开始 , 跳过表头
                .setRowCursor(idx -> 0)
                .readRows()
                .getReadData();
        Assertions.assertEquals(len, rows01.size());
        for (int i = 0; i < len; i++) {
            Assertions.assertEquals(SheetRowList.class, rows01.get(i).getClass());
            Assertions.assertEquals(records.get(i).get("username").toString(), rows01.get(i).get(0).toString());
            Assertions.assertEquals(records.get(i).get("password"), Double.valueOf(rows01.get(i).get(1).toString()).intValue());
        }

        List<SheetRowMap> rows02 = ExcelImportFactory.buildXSSFImportExcelWorkBook()
                .parseFile(filePath)
                .getSheet(0)
                .setColumnFields(SheetRowMap.class, "username", "password")
                .readRows()
                .getReadData();
        Assertions.assertEquals(len, rows02.size());
        for (int i = 0; i < len; i++) {
            Assertions.assertEquals(SheetRowMap.class, rows02.get(i).getClass());
            Assertions.assertEquals(records.get(i).get("username").toString(), rows02.get(i).get("username").toString());
            Assertions.assertEquals(records.get(i).get("password"), Double.valueOf(rows02.get(i).get("password").toString()).intValue());
        }

        List<SheetRowMap> rows03 = ExcelImportFactory.buildXSSFImportExcelWorkBook(SheetRowMap.class)
                .parseFile(filePath)
                .getSheet(0)
                .setRowCursor(idx -> 0)
                .readRows()
                .getReadData();
        Assertions.assertEquals(len, rows03.size());
        for (int i = 0; i < len; i++) {
            int num = i + 1;
            String keyNum = String.valueOf(num + 1);
            Assertions.assertEquals(SheetRowMap.class, rows03.get(i).getClass());
            Assertions.assertEquals("张三" + num, rows03.get(i).get("A" + keyNum).toString());
            Assertions.assertEquals(num, Double.valueOf(rows03.get(i).get("B" + keyNum).toString()).intValue());
        }

        List<LinkedHashMap<String, Object>> rows04 = ExcelImportFactory.buildXSSFImportExcelWorkBook(new TypeReference<LinkedHashMap<String, Object>>() {
        })
                .parseFile(filePath)
                .getSheet(0)
                .setRowCursor(idx -> 0)
                .readRows()
                .getReadData();
        Assertions.assertEquals(len, rows04.size());
        for (int i = 0; i < len; i++) {
            int num = i + 1;
            String keyNum = String.valueOf(num + 1);
            Assertions.assertEquals(LinkedHashMap.class, rows04.get(i).getClass());
            Assertions.assertEquals("张三" + num, rows04.get(i).get("A" + keyNum).toString());
            Assertions.assertEquals(num, Double.valueOf(rows04.get(i).get("B" + keyNum).toString()).intValue());
        }
    }

    public final static class User {

        private String username;

        private String password;

        private Integer mobile;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Integer getMobile() {
            return mobile;
        }

        public void setMobile(Integer mobile) {
            this.mobile = mobile;
        }
    }

    @Test
    void test03() throws IOException {
        final String filePath = "/Users/baichao/develop/idea_projects/avalon/avalon-framework/avalon-office/avalon-office-excel/src/test/resources/test03.xlsx";

        // 导出

        /*模拟从数据库获取数据开始*/
        List<User> records = new ArrayList<>();
        User user01 = new User();
        User user02 = new User();
        User user03 = new User();
        user01.setUsername("张三1");
        user01.setPassword("666");
        user01.setMobile(110);
        user02.setUsername("张三2");
        user02.setPassword("666");
        user02.setMobile(120);
        user03.setUsername("张三3");
        user03.setPassword("666");
        user03.setMobile(119);
        records.add(user01);
        records.add(user02);
        records.add(user03);
        /*模拟从数据库获取数据结束*/

        int len = records.size();

        ExcelExportFactory.buildSXSSFExportExcelWorkBook()
                .createSheet()
                .setColumnFields("username", "password")
                .importData(records)
                .getOwnerWorkBook()
                .createSheet()
                .setColumnFields("mobile")
                .importData(records)
                .export(filePath);

        // 导入

        List<List<User>> rows01 = ExcelImportFactory.buildXSSFImportExcelWorkBook()
                .parseFile(filePath)
                .getSheet(0)
                // 由于未导出表头至Excel, 因此必须设置映射字段, 且设置期望占用行数为0
                .setColumnFields(0, "username", "password")
                .readRows(User.class)
                .getAllReadData();

        for (List<User> users : rows01) {
            Assertions.assertEquals(len, users.size());
            for (int i = 0; i < len; i++) {
                Assertions.assertNotEquals(records.get(i), users.get(i));
                Assertions.assertEquals(records.get(i).getUsername(), users.get(i).getUsername());
                Assertions.assertEquals(records.get(i).getPassword(), users.get(i).getPassword());
            }
        }

        List<User> rows02 = ExcelImportFactory.buildXSSFImportExcelWorkBook()
                .parseFile(filePath)
                .getSheet(0)
                // 由于未导出表头至Excel, 因此必须设置映射字段, 且设置期望占用行数为0
                .setColumnFields(0, "username", "password")
                .readRows(User.class)
                .getOwnerWorkBook()
                .getSheet(1)
                .setColumnFields(0, "mobile")
                .readRows(User.class)
                .getReadData();

        for (int i = 0; i < len; i++) {
            Assertions.assertNotEquals(records.get(i), rows02.get(i));
            Assertions.assertEquals(records.get(i).getMobile(), rows02.get(i).getMobile());
        }
    }

    @Test
    void test04() throws IOException {
        final String filePath = "/Users/baichao/develop/idea_projects/avalon/avalon-framework/avalon-office/avalon-office-excel/src/test/resources/test04.xlsx";

        //构建用于描述标题的二维数组,获取数据代码略
        ExcelTitleCell[][] titles = new SXSSFExcelTitle[1][];
        ExcelTitleCell[] title = new SXSSFExcelTitle[3];
        title[0] = new SXSSFExcelTitle();
        title[1] = new SXSSFExcelTitle();
        title[2] = new SXSSFExcelTitle();
        title[0].setField("username");//设置标题所在列对应数据的字段
        title[1].setField("password");
        title[2].setField("mobile");
        title[0].setTitle("姓名");//设置标题名称
        title[1].setTitle("密码");
        title[2].setTitle("手机号");
        title[0].setWidth(50);//设置列宽,默认10,由于计算原因,实际效果稍有误差
        title[1].setWidth(60);
        title[2].setWidth(70);
        title[0].getCellStyle()
                //设置水平对齐方式为左对齐,标题默认水平居中
                .setHorizontalAlignType(CellStyle.HorizontalAlignType.LEFT)
                //设置垂直对齐方式为底部对齐,标题默认垂直居中
                .setVerticalAlignType(CellStyle.VerticalAlignType.BOTTOM)
                //设置左边框样式,同样还可以设置其它方向边框
                .setBorderLeftStyle(CellStyle.BorderStyle.DASH_DOT);
        titles[0] = title;

        /*模拟从数据库获取数据开始*/
        List<User> records = new ArrayList<>();
        User user01 = new User();
        User user02 = new User();
        User user03 = new User();
        user01.setUsername("张三1");
        user01.setPassword("666");
        user01.setMobile(110);
        user02.setUsername("张三2");
        user02.setPassword("666");
        user02.setMobile(120);
        user03.setUsername("张三3");
        user03.setPassword("666");
        user03.setMobile(119);
        records.add(user01);
        records.add(user02);
        records.add(user03);
        /*模拟从数据库获取数据结束*/

        ExcelExportFactory.buildSXSSFExportExcelWorkBook()
                .createSheet()
                .setTitles(titles)//设置标题
                .importData(records)
                .export(filePath);
    }
}