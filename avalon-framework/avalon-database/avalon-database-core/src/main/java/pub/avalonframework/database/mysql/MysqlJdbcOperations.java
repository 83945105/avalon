package pub.avalonframework.database.mysql;

import pub.avalonframework.database.AbstractJdbcOperations;
import pub.avalonframework.database.mysql.table.column.MysqlColumn;
import pub.avalonframework.database.mysql.table.column.MysqlColumnType;
import pub.avalonframework.database.table.column.Column;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public class MysqlJdbcOperations extends AbstractJdbcOperations {

    public MysqlJdbcOperations(DataSource dataSource) {
        super(dataSource);
    }

    private final static String SELECT_DATABASE_NAME_SQL = "SELECT DATABASE();";

    @Override
    public String selectDatabaseName() {
        try {
            getConnection();
            query(SELECT_DATABASE_NAME_SQL);
            if (resultSet.next()) {
                String databaseName = resultSet.getString(1);
                if (resultSet.next()) {
                    throw new SQLException("The current database name can only be one.");
                }
                return databaseName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    private final static String SELECT_TABLE_NAMES_SQL = "SELECT TABLE_NAME FROM information_schema.TABLES WHERE TABLE_SCHEMA = ?;";

    @Override
    public List<String> selectTableNames() {
        String databaseName = selectDatabaseName();
        try {
            getConnection();
            query(SELECT_TABLE_NAMES_SQL, databaseName);
            List<String> tableNames = new ArrayList<>();
            while (resultSet.next()) {
                tableNames.add(resultSet.getString(1));
            }
            return tableNames;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    private final static String SELECT_TABLE_COLUMNS_SQL = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ? ORDER BY ORDINAL_POSITION;";

    @Override
    public List<Column> selectTableColumns(String databaseName, String tableName) {
        try {
            getConnection();
            query(SELECT_TABLE_COLUMNS_SQL, databaseName, tableName);
            List<Column> columns = new ArrayList<>();
            while (resultSet.next()) {
                MysqlColumn column = new MysqlColumn();
                column.setName(resultSet.getString("COLUMN_NAME"));
                column.setPrimaryKey("PRI".equals(resultSet.getString("COLUMN_KEY")));
                column.setAutoIncrement("auto_increment".equals(resultSet.getString("EXTRA")));
                String columnType = resultSet.getString("COLUMN_TYPE");
                column.setUnsigned(columnType != null && columnType.contains("unsigned"));
                column.setZerofill(columnType != null && columnType.contains("zerofill"));
                column.setColumnType(MysqlColumnType.parseMysqlColumnType(resultSet.getString("DATA_TYPE")));
                String varcharMaxLength = resultSet.getString("CHARACTER_MAXIMUM_LENGTH");
                String numericMaxLength = resultSet.getString("NUMERIC_PRECISION");
                column.setLength(varcharMaxLength == null ? Integer.parseInt(numericMaxLength) : Integer.parseInt(varcharMaxLength));
                String numericScale = resultSet.getString("NUMERIC_SCALE");
                column.setScale(numericScale == null ? null : Integer.parseInt(numericScale));
                column.setNullable("YES".equals(resultSet.getString("IS_NULLABLE")));
                column.setDefault(resultSet.getString("COLUMN_DEFAULT"));
                column.setComment(resultSet.getString("COLUMN_COMMENT"));
                column.setIndex(null);//TODO 暂不获取索引信息
                columns.add(column);
            }
            return columns;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }
}