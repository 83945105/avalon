package pub.avalonframework.security.data;

import java.util.List;
import java.util.Map;

/**
 * @author baichao
 */
public interface TableRuleOperations {

    ColumnRuleOperations addOnColumnRule();

    ColumnRuleOperations addWhereColumnRule();

    void addSubRuleStore(String key, RuleStore subRoleStore);

    Type getType();

    String getTableName();

    String getTableAlias();

    List<OnColumnRule> getOnRules();

    List<WhereColumnRule> getWhereRules();

    Map<String, RuleStore> getSubRuleStoreMap();

    TableRuleOperations merge(TableRuleOperations target);

    enum Type {
        REAL, VIRTUAL
    }
}