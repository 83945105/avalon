package pub.avalonframework.sqlhelper.core.expression.impl;

import pub.avalonframework.sqlhelper.core.data.block.AbstractComparisonDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.ComparisonType;
import pub.avalonframework.sqlhelper.core.expression.ComparisonRule;
import pub.avalonframework.sqlhelper.core.expression.ComparisonValueNullException;
import pub.avalonframework.sqlhelper.core.expression.UnsupportedComparisonRuleException;
import pub.avalonframework.sqlhelper.core.expression.ValueComparisonExpression;
import pub.avalonframework.sqlhelper.core.helper.Helper;

import java.util.Collection;

/**
 * @author baichao
 */
public interface ValueComparisonExpressionImpl<T, S extends AbstractComparisonDataBlock<S>> extends ValueComparisonExpression<T> {

    /**
     * Get helper.
     *
     * @return extends {@link Helper} object
     */
    T getHelper();

    /**
     * Get comparison data block.
     *
     * @return extends {@link AbstractComparisonDataBlock}
     */
    AbstractComparisonDataBlock<S> getComparisonDataBlock();

    /**
     * Add comparison data block.
     *
     * @param comparisonDataBlock Implements {@link AbstractComparisonDataBlock} object.
     */
    void addComparisonDataBlock(AbstractComparisonDataBlock<S> comparisonDataBlock);

    @Override
    default T sqlPart(String targetSqlPart) {
        this.addComparisonDataBlock(this.getComparisonDataBlock()
                .setTargetSqlPart(targetSqlPart));
        return this.getHelper();
    }

    @Override
    default T isNull() {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetNoneValue(ComparisonType.IS_NULL));
        return this.getHelper();
    }

    @Override
    default T isNotNull() {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetNoneValue(ComparisonType.IS_NOT_NULL));
        return this.getHelper();
    }

    @Override
    default T eq(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonValueNullException();
                default:
                    throw new UnsupportedComparisonRuleException();
            }
        }
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSingleValue(ComparisonType.EQUAL, value));
        return this.getHelper();
    }

    @Override
    default T neq(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonValueNullException();
                default:
                    throw new UnsupportedComparisonRuleException();
            }
        }
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSingleValue(ComparisonType.NOT_EQUAL, value));
        return this.getHelper();
    }

    @Override
    default T gt(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonValueNullException();
                default:
                    throw new UnsupportedComparisonRuleException();
            }
        }
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSingleValue(ComparisonType.GREATER, value));
        return this.getHelper();
    }

    @Override
    default T gte(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonValueNullException();
                default:
                    throw new UnsupportedComparisonRuleException();
            }
        }
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSingleValue(ComparisonType.GREATER_EQUAL, value));
        return this.getHelper();
    }

    @Override
    default T lt(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonValueNullException();
                default:
                    throw new UnsupportedComparisonRuleException();
            }
        }
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSingleValue(ComparisonType.LESS, value));
        return this.getHelper();
    }

    @Override
    default T lte(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonValueNullException();
                default:
                    throw new UnsupportedComparisonRuleException();
            }
        }
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSingleValue(ComparisonType.LESS_EQUAL, value));
        return this.getHelper();
    }

    @Override
    default T bt(Object value, Object secondValue, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonValueNullException();
                default:
                    throw new UnsupportedComparisonRuleException();
            }
        }
        if (secondValue == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonValueNullException();
                default:
                    throw new UnsupportedComparisonRuleException();
            }
        }
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetPairValue(ComparisonType.BETWEEN, value, secondValue));
        return this.getHelper();
    }

    @Override
    default T lk(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonValueNullException();
                default:
                    throw new UnsupportedComparisonRuleException();
            }
        }
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSingleValue(ComparisonType.LIKE, value));
        return this.getHelper();
    }

    @Override
    default T in(Object[] values, ComparisonRule comparisonRule) {
        if (values == null || values.length == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonValueNullException();
                default:
                    throw new UnsupportedComparisonRuleException();
            }
        }
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetMultiValue(ComparisonType.IN, values));
        return this.getHelper();
    }

    @Override
    default T in(Collection<Object> values, ComparisonRule comparisonRule) {
        if (values == null || values.size() == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonValueNullException();
                default:
                    throw new UnsupportedComparisonRuleException();
            }
        }
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetMultiValue(ComparisonType.IN, values));
        return this.getHelper();
    }

    @Override
    default T nin(Object[] values, ComparisonRule comparisonRule) {
        if (values == null || values.length == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonValueNullException();
                default:
                    throw new UnsupportedComparisonRuleException();
            }
        }
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetMultiValue(ComparisonType.NOT_IN, values));
        return this.getHelper();
    }

    @Override
    default T nin(Collection<Object> values, ComparisonRule comparisonRule) {
        if (values == null || values.size() == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonValueNullException();
                default:
                    throw new UnsupportedComparisonRuleException();
            }
        }
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetMultiValue(ComparisonType.NOT_IN, values));
        return this.getHelper();
    }
}