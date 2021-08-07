# Spring Boot Mongo

MongoDB是一款开源的文档型数据库，
NoSQL数据库类型：键值数据库（redis）,文档数据库，列式数据库（HBase），图形数据库

## Spring对MongoDB的支持
* Spring Data MongoDB
  * MongoTemplate
  * Repository支持

### Spring Data MongoDB基本用法
* 注解
  * @Document
  * Id
* MongoTemplate
  * save / remove
  * Criteria / Query / Update

### Spring Data MongoDB的Repository
@EnableMongoRepositories

对应接口
* MongoRepositories<T,ID>
* PagingAndSortingRepositories<T,ID>
* CrudRepositories<T,ID>

mongoDB里创建数据库springbucks和创建用户springbucks  

    use springbucks;  
    db.createUser({
      user:"springbucks",
      pwd:"springbucks",
      roles:[{role:"readWrite",db:"springbucks"}]
    });
