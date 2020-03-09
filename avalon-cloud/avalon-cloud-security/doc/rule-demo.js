// 数据规则
export default [{
    "tableName": "order",
    "rules": [{
        "role": "admin",
        "op": "and",
        "rules": [{
            "field": "id",
            "op": "in",
            "value": "admin,teacher"
        }]
    }]
}];