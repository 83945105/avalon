package pub.avalonframework.sqlhelper.core.sqlbuilder.template;

import pub.avalonframework.sqlhelper.core.data.consume.CrudConsumer;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.*;

import java.util.Collection;

/**
 * @author baichao
 */
public interface SqlBuilderTemplate {

    /**
     * set sql part builder template
     *
     * @param sqlPartBuilderTemplate {@link DataBlockBuilderTemplate}
     */
    void setSqlPartBuilderTemplate(DataBlockBuilderTemplate sqlPartBuilderTemplate);

    /**
     * build copy table result
     *
     * @param consumer {@link CrudConsumer}
     * @param targetTableName target table name
     * @param copyData        copy data or not
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult buildCopyTable(CrudConsumer consumer, String targetTableName, boolean copyData);

    /**
     * build delete table result
     *
     * @param consumer {@link CrudConsumer}
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult buildDeleteTable(CrudConsumer consumer);

    /**
     * build rename table result
     *
     * @param consumer {@link CrudConsumer}
     * @param newTableName    new table name
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult buildRenameTable(CrudConsumer consumer, String newTableName);

    /**
     * build is table exist result
     *
     * @param consumer {@link CrudConsumer}
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult buildIsTableExist(CrudConsumer consumer);

    /**
     * build insert args result
     *
     * @param consumer {@link CrudConsumer}
     * @param args            args
     * @return {@link InsertSqlBuilderResult}
     */
    InsertSqlBuilderResult buildInsertArgs(CrudConsumer consumer, Object... args);

    /**
     * build insert javaBean result
     *
     * @param consumer {@link CrudConsumer}
     * @param javaBean        javaBean
     * @return {@link InsertSqlBuilderResult}
     */
    InsertSqlBuilderResult buildInsertJavaBean(CrudConsumer consumer, Object javaBean);

    /**
     * build insert javaBean result
     * <p>when value is {@code null},skip field
     *
     * @param consumer {@link CrudConsumer}
     * @param javaBean        javaBean
     * @return {@link InsertSqlBuilderResult}
     */
    InsertSqlBuilderResult buildInsertJavaBeanSelective(CrudConsumer consumer, Object javaBean);

    /**
     * build batch insert javaBeans result
     *
     * @param consumer {@link CrudConsumer}
     * @param javaBeans       javaBeans
     * @return {@link InsertSqlBuilderResult}
     */
    InsertSqlBuilderResult buildBatchInsertJavaBeans(CrudConsumer consumer, Collection<?> javaBeans);

    /**
     * build delete result
     *
     * @param consumer {@link CrudConsumer}
     * @return {@link DeleteSqlBuilderResult}
     */
    DeleteSqlBuilderResult buildDelete(CrudConsumer consumer);

    /**
     * build delete by primary key result
     *
     * @param consumer {@link CrudConsumer}
     * @param primaryKeyValue primary key value
     * @return {@link DeleteSqlBuilderResult}
     */
    DeleteSqlBuilderResult buildDeleteByPrimaryKey(CrudConsumer consumer, Object primaryKeyValue);

    /**
     * build batch delete by primary keys result
     *
     * @param consumer  {@link CrudConsumer}
     * @param primaryKeyValues primary key values
     * @return {@link DeleteSqlBuilderResult}
     */
    DeleteSqlBuilderResult buildBatchDeleteByPrimaryKeys(CrudConsumer consumer, Object... primaryKeyValues);

    /**
     * build update javaBean result
     *
     * @param consumer {@link CrudConsumer}
     * @param javaBean        javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildUpdateJavaBean(CrudConsumer consumer, Object javaBean);

    /**
     * build update javaBean result
     * <p>when value is {@code null},skip field
     *
     * @param consumer {@link CrudConsumer}
     * @param javaBean        javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildUpdateJavaBeanSelective(CrudConsumer consumer, Object javaBean);

    /**
     * build update args by primary key result
     *
     * @param consumer {@link CrudConsumer}
     * @param primaryKeyValue primary key value
     * @param args            args
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildUpdateArgsByPrimaryKey(CrudConsumer consumer, Object primaryKeyValue, Object... args);

    /**
     * build update javaBean by primary key result
     *
     * @param consumer {@link CrudConsumer}
     * @param primaryKeyValue primary key value
     * @param javaBean        javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildUpdateJavaBeanByPrimaryKey(CrudConsumer consumer, Object primaryKeyValue, Object javaBean);

    /**
     * build update javaBean by primary key result
     * <p>when value is {@code null},skip field
     *
     * @param consumer {@link CrudConsumer}
     * @param primaryKeyValue primary key value
     * @param javaBean        javaBean
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildUpdateJavaBeanByPrimaryKeySelective(CrudConsumer consumer, Object primaryKeyValue, Object javaBean);

    /**
     * build batch update javaBeans by primary keys result
     *
     * @param consumer {@link CrudConsumer}
     * @param javaBeans       javaBeans
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildBatchUpdateJavaBeansByPrimaryKeys(CrudConsumer consumer, Collection<?> javaBeans);

    /**
     * build update or insert javaBeans result
     * <p>if exist update else insert
     *
     * @param consumer {@link CrudConsumer}
     * @param javaBeans       javaBeans
     * @return {@link UpdateSqlBuilderResult}
     */
    UpdateSqlBuilderResult buildUpdateOrInsertJavaBeans(CrudConsumer consumer, Collection<?> javaBeans);

    /**
     * build query result
     *
     * @param consumer {@link CrudConsumer}
     * @return {@link SelectSqlBuilderResult}
     */
    SelectSqlBuilderResult buildQuery(CrudConsumer consumer);

    /**
     * build query count result
     *
     * @param consumer {@link CrudConsumer}
     * @return {@link SelectSqlBuilderResult}
     */
    SelectSqlBuilderResult buildQueryCount(CrudConsumer consumer);

    /**
     * build query by primary key result
     *
     * @param consumer {@link CrudConsumer}
     * @param primaryKeyValue primary key value
     * @return {@link SelectSqlBuilderResult}
     */
    SelectSqlBuilderResult buildQueryByPrimaryKey(CrudConsumer consumer, Object primaryKeyValue);
}