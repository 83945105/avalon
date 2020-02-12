package pub.avalonframework.sqlhelper.jdbc.core;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.sqlbuilder.SelectSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.TableSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SelectSqlBuilderResult;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.TableSqlBuilderResult;

import java.util.List;
import java.util.Map;

/**
 * @author baichao
 */
public class SpringJdbcTemplate implements JdbcTemplate {

    private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

    public SpringJdbcTemplate(org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public DatabaseType getDatabaseType() {
        return null;
    }

    @Override
    public void copyTable(String targetTableName, boolean copyData, TableSqlBuilder tableSqlBuilder) {
        TableSqlBuilderResult tableSqlBuilderResult = tableSqlBuilder.copyTable(targetTableName, copyData);
        this.jdbcTemplate.update(tableSqlBuilderResult.getPreparedStatementSql());
    }

    @Override
    public void deleteTable(TableSqlBuilder tableSqlBuilder) {
        TableSqlBuilderResult tableSqlBuilderResult = tableSqlBuilder.deleteTable();
        this.jdbcTemplate.update(tableSqlBuilderResult.getPreparedStatementSql());
    }

    @Override
    public void renameTable(String targetTableName, TableSqlBuilder tableSqlBuilder) {
        TableSqlBuilderResult tableSqlBuilderResult = tableSqlBuilder.renameTable(targetTableName);
        this.jdbcTemplate.update(tableSqlBuilderResult.getPreparedStatementSql());
    }

    @Override
    public boolean isTableExist(TableSqlBuilder tableSqlBuilder) {
        TableSqlBuilderResult tableSqlBuilderResult = tableSqlBuilder.isTableExist();
        List<Map<String, Object>> results = this.jdbcTemplate.query(tableSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(tableSqlBuilderResult.getPreparedStatementArgs()), new RowMapperResultSetExtractor<>(new ColumnMapRowMapper()));
        return results != null && results.size() == 1 && results.get(0).size() == 1;
    }

    @Override
    public Map<String, Object> queryByPrimaryKey(Object keyValue, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.queryByPrimaryKey(keyValue);
/*        List<Map<String, Object>> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new ListMapResultSetExtractor(1));
        return JdbcTools.nullableSingleResult(results);*/
        return null;
    }

    @Override
    public <T> T queryByPrimaryKey(Object keyValue, SelectSqlBuilder selectSqlBuilder, Class<T> returnType) {
        return null;
    }
}
