package pub.avalonframework.security.data;

/**
 * @author baichao
 */
public interface TableRuleOperations {

    ColumnRuleOperations addOnColumnRule();

    ColumnRuleOperations addWhereColumnRule();
}