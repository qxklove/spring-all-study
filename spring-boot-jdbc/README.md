# Spring Boot JDBC

### Jdbc Template
* query
* queryForObject
* queryForList
* update
* execute

### SQL批处理
* JdbcTemplate
  * batchUpdate
    * BatchPreparedStatementSetter
* NamedParameterJdbcTemplate
  * batchUpdate
    * SqlParameterSourceUtils.createBatch

### Spring的JDBC异常抽象
Spring会将数据操作的异常转换为DataAccessException，无论用何种数据访问方式，都能使用一样的异常。  
Spring把各个不同数据库的错误码都收集在一起，做了ErrorCode定义，放在sql-error-codes.xml中，
也可以自定义错误码的处理，自定义sql-error-codes.xml放在ClassPath下，会覆盖掉Spring自带的处理。  
Spring通过SQLErrorCodeSQLExceptionTranslator解析错误码。


