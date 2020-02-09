package pub.avalonframework.sqlhelper.generator.beans;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.generator.jdbc.DataBaseTypeDiscriminator;

/**
 * @author baichao
 */
public class DefaultDatabaseTypeDiscriminator implements DataBaseTypeDiscriminator {
    @Override
    public DatabaseType apply(String driverClassName, String jdbcUrl) {
        if (jdbcUrl.contains("jdbc:mysql")) {
            return DatabaseType.MYSQL;
        }
        throw new RuntimeException("can not discriminate this databaseType.");
    }
}
