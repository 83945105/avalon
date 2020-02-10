package pub.avalonframework.jdbc.core.engine;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author baichao
 */
public class JdbcEngineTest {

    @Test
    void Test() throws SQLException {

        DataSource dataSource = null;

        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("");
        preparedStatement.setString(1, "");

        ResultSet resultSet = preparedStatement.executeQuery();

    }
}