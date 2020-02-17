package pub.avalonframework.sqlhelper.core.sqlbuilder;

import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.InsertSqlBuilderResult;

import java.util.Collection;

/**
 * @author baichao
 */
public interface InsertSqlBuilder extends SqlBuilder {

    /**
     * Insert javaBean
     *
     * @param javaBean javaBean
     * @return {@link InsertSqlBuilderResult}
     */
    InsertSqlBuilderResult insertJavaBean(Object javaBean);

    /**
     * Inset javaBean
     * <p>when value is {@code null},skip field
     *
     * @param javaBean javaBean
     * @return {@link InsertSqlBuilderResult}
     */
    InsertSqlBuilderResult insertJavaBeanSelective(Object javaBean);

    /**
     * Batch inset javaBeans
     *
     * @param javaBeans javaBeans
     * @return {@link InsertSqlBuilderResult}
     */
    InsertSqlBuilderResult batchInsertJavaBeans(Collection<?> javaBeans);
}