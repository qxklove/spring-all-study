# Spring Boot Data JPA

## 背景
* JPA：Java Persistent API，为对象关系映射（O/R Mapping）提供了一种基于POJO的持久化模型，为Java社区屏蔽不同持久化API的差异。
  也就是说JPA是一套ORM规范，而Hibernate实现了JPA规范。
* Spring Data JPA：Spring提供的一套简化JPA开发的框架，按照约定好的【方法命名规则】写dao层接口，就可以在不写接口实现的情况下，实现对数据库的访问和操作。同时提供了很多除了CRUD之外的功能，如分页、排序、复杂查询等等。
可以理解为JPA规范的再次封装抽象，底层还是使用了Hibernate的JPA技术实现。
* Spring Data：在保留底层存储特性的同时，提供相对一致的、基于Spring的编程模型，主要模块有Spring Data Commons，Spring Data JDBC，Spring Data JPA，Spring Data Redis...

## 常用JPA注解
### 实体
* @Entity、@MappedSuperclass-多个实体的父类
* @Table(name)
### 主键
* @Id
  * @GeneratedValue(strategy, generator)：主键生成策略
  * @SequenceGenerate(name, sequenceName)：指明序列
### 映射
* @Column(name, nullable, length, insertable, updatable)
* @JoinTable(name)、@JoinColumn(name)
### 关系
* @OneToOne、@OneToMany、@ManyToOne、@ManyToMany
* @OrderBY

## 通过Spring Data JPA操作数据库
SpringBoot启动类加注解@EnableJpaRepositories，就会去发现有实现Repository<T, ID>相关接口的扩展

### Repository<T, ID>相关接口
* CrudRepository<T, ID>
* PagingAndSortingRepository<T, Long>
* JpaRepository<T, ID>

### 定义查询
* find...By.../read...By.../query...By.../get...By...
* count...By...
* ...OrderBy...[ASC/DESC]
* And/Or/IgnoreCase
* Top/First/Distinct

### 分页查询
* PagingAndSortingRepository<T, Long>
* Pageable/Sort
* Slice<T>/Page<T>


儿子额日子，我是你爸爸吧，科比绝杀，秀啊，谁此刻孤独，便永远孤独