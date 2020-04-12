export default {
    "admin": {
        "tableRules": [{
            "table": "USER",
            "joinRules": [
                {
                    "joinType": "INNER",
                    "tableName": "ROLE",
                    "onRules": [
                        {
                            "column": "USER_ID",
                            "logicExpression": {
                                "logicOperator": "AND",
                                "predicateExpressions": [
                                    {
                                        "comparisonOperator": "EQ",
                                        "value": {

                                        }
                                    }
                                ]
                            }
                        }
                    ]
                }
            ],
            "onRules": [
                {
                    "column": "ID",
                    "logicExpression": {
                        "logicOperator": "AND",
                        "logicExpressions": [
                            {
                                "logicOperator": "AND",
                                "predicateExpressions": [
                                    {
                                        "comparisonOperator": "EQ",
                                        "value": 1
                                    }
                                ]
                            }
                        ]
                    }
                },
                {
                    "column": "AGE",
                    "logicExpression": {
                        "logicOperator": "AND",
                        "predicateExpressions": [
                            {
                                "comparisonOperator": "EQ",
                                "value": 1
                            }
                        ]
                    }
                }
            ]
        }]
    }
};