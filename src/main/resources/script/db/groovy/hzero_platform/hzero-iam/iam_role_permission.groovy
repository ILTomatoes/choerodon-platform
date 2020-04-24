package script.db

databaseChangeLog(logicalFilePath: 'script/db/iam_role_permission.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-iam_role_permission") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'iam_role_permission_s', startValue:"1")
        }
        createTable(tableName: "iam_role_permission", remarks: "") {
            column(name: "id", type: "bigint(20)", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)} 
            column(name: "role_id", type: "bigint(20)",  remarks: "角色id")   
            column(name: "permission_id", type: "bigint(20)",  remarks: "权限集id")   
            column(name: "h_create_flag", type: "varchar(" + 1 * weight + ")",   defaultValue:"Y",   remarks: "创建标识")   
            column(name: "h_inherit_flag", type: "varchar(" + 1 * weight + ")",   defaultValue:"N",   remarks: "继承标识")   

        }

        addUniqueConstraint(columnNames:"role_id,permission_id",tableName:"iam_role_permission",constraintName: "iam_role_permission_u1")
    }

    changeSet(author: "bojiangzhou", id: "2019-06-10-iam_role_permission") {
        addColumn(tableName: 'iam_role_permission') {
            column(name: "type", type: "varchar(30)", defaultValue: "PS", afterColumn: 'permission_id', remarks: "权限类型：PS:权限集，HITF-SVR:服务，HITF-ITF:接口")  {constraints(nullable:"false")}
        }
        dropUniqueConstraint(constraintName: "iam_role_permission_u1", tableName: "iam_role_permission")
        addUniqueConstraint(columnNames: "role_id,permission_id,type", tableName: "iam_role_permission", constraintName: "iam_role_permission_u1")
    }
}