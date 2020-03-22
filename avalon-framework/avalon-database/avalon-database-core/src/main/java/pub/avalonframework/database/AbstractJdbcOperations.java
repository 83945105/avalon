package pub.avalonframework.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author baichao
 */
public abstract class AbstractJdbcOperations implements JdbcOperations {

    protected DataSource dataSource;

    protected Connection connection;

    protected PreparedStatement preparedStatement;

    protected ResultSet resultSet;

    public AbstractJdbcOperations(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected void getConnection() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void query(String sql, Object... args) {
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (args != null) {
                for (int i = 1; i <= args.length; i++) {
                    preparedStatement.setObject(i, args[i - 1]);
                }
            }
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void close() {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}