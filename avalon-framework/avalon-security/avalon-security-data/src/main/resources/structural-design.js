// lo  -> LogicExpression     - 逻辑表达式       -> and | or
// los -> LogicExpressions    - 逻辑表达式(复数)
// ct  -> ComparisonType      - 比较条件       -> = | <> | > | >= | < | <= | like | between ... and ... | in | not in | is null | is not null
// pes -> PredicateExpression - 谓语表达式(复数)

export default {
    admin: {
        tableRules: [{
            table: "ROLE",
            onRules: [{
                column: "USER_ID",
                los: [{ // AND ( ROLE.USER_ID = 1 AND ROLE.USER_ID > 2 ) OR ROLE.USER_ID = USER.ID
                    andOr: "AND", // 该处强制为AND
                    los: [{
                        andOr: "AND", // ROLE.USER_ID = 1 AND ROLE.USER_ID > 2
                        pes: [{
                            ct: "EQ",
                            value: 1
                        }, {
                            ct: "GT",
                            value: 2
                        }]
                    }]
                }, {
                    andOr: "OR", // OR ROLE.USER_ID = USER.ID
                    pes: [{
                        ct: "EQ",
                        column: {
                            table: "USER",
                            column: "ID"
                        }
                    }]
                }]
            }],
            whereRules: [{
                column: "NAME",
                los: [{ // AND ROLE.NAME IS NULL AND ROLE.NAME BETWEEN 1 AND 2 AND ROLE.NAME IN ( 1, 2, 3 )
                    andOr: "AND",
                    pes: [{
                        ct: "IS NULL"
                    }, {
                        ct: "BETWEEN",
                        value: [1, 2]
                    }, {
                        ct: "in",
                        value: [1, 2, 3]
                    }]
                }]
            }]
        }]
    }
};