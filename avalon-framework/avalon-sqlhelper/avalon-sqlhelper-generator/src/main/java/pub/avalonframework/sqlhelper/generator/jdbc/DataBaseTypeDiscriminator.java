package pub.avalonframework.sqlhelper.generator.jdbc;

import pub.avalonframework.database.DatabaseType;

/**
 * Database type discriminator.
 *
 * @author baichao
 */
@FunctionalInterface
public interface DataBaseTypeDiscriminator {

    /**
     * discriminator database type.
     *
     * @param driverClassName The driver class name.
     * @param jdbcUrl         The jdbc url.
     * @return The database type.
     */
    DatabaseType apply(String driverClassName, String jdbcUrl);
}