package pub.avalonframework.sqlhelper.jdbc.core;

import org.springframework.jdbc.core.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author baichao
 */
public class ArgumentListPreparedStatementSetter implements PreparedStatementSetter, ParameterDisposer {

    private final List<Object> args;

    public ArgumentListPreparedStatementSetter(List<Object> args) {
        this.args = args;
    }

    @Override
    public void setValues(PreparedStatement ps) throws SQLException {
        if (this.args != null) {
            int len = this.args.size();
            for (int i = 0; i < len; i++) {
                Object arg = this.args.get(i);
                doSetValue(ps, i + 1, arg);
            }
        }
    }

    /**
     * @see ArgumentPreparedStatementSetter
     */
    protected void doSetValue(PreparedStatement ps, int parameterPosition, Object argValue) throws SQLException {
        if (argValue instanceof SqlParameterValue) {
            SqlParameterValue paramValue = (SqlParameterValue) argValue;
            StatementCreatorUtils.setParameterValue(ps, parameterPosition, paramValue, paramValue.getValue());
        } else {
            StatementCreatorUtils.setParameterValue(ps, parameterPosition, SqlTypeValue.TYPE_UNKNOWN, argValue);
        }
    }


    @Override
    public void cleanupParameters() {
        StatementCreatorUtils.cleanupParameters(this.args);
    }
}