package pub.avalonframework.security.data;

import java.util.List;

/**
 * @author baichao
 */
public interface TableRuleOperations {

    ColumnRuleOperations addOnColumnRule();

    ColumnRuleOperations addWhereColumnRule();

    Type getType();

    String getTableName();

    String getTableAlias();

    List<OnColumnRule> getOnRules();

    List<WhereColumnRule> getWhereRules();

    enum Type {
        REAL, VIRTUAL
    }
}