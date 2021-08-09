# Spring Boot Redis

## Spring对Redis的支持
* Spring Data Redis
    * 支持的客户端 Jedis/Lettuce
    * RedisTemplate
    * Repository支持
    
### Jedis客户端的简单使用
* Jedis不是线程安全的
* 通过JedisPool获取Jedis实例
* 直接使用Jedis中的方法

### 与Redis建立连接
配置连接工厂
* LettuceConnectionFactory 与 JedisConnectionFactory
  * RedisStandaloneConfiguration，单节点
  * RedisSentinelConfiguration，哨兵
  * RedisClusterConfiguration，集群

### Redis的哨兵模式
Redis Sentinel 是 Redis 的一种高可用方案
* 监控、通知、自动故障转移、服务发现

JedisSentinelPool

哨兵是一个独立的进程，作为进程，它会独立运行。其原理是哨兵通过发送命令，等待Redis服务器响应，从而监控运行的多个Redis实例。  
当哨兵监测到master宕机，会自动将slave切换成master，然后通过发布订阅模式通知其他的从服务器，修改配置文件，让它们切换主机。

然而一个哨兵进程对Redis服务器进行监控，可能会出现问题，为此，我们可以使用多个哨兵进行监控。各个哨兵之间还会进行监控，这样就形成了多哨兵模式。

故障切换（failover）的过程：假设主服务器宕机，哨兵1先检测到这个结果，系统并不会马上进行failover过程，仅仅是哨兵1主观的认为主服务器不可用，这个现象成为主观下线。
当后面的哨兵也检测到主服务器不可用，并且数量达到一定值时，那么哨兵之间就会进行一次投票，投票的结果由一个哨兵发起，进行failover操作。
切换成功后，就会通过发布订阅模式，让各个哨兵把自己监控的从服务器实现切换主机，这个过程称为客观下线。这样对于客户端而言，一切都是透明的。

### Redis集群模式
Redis Cluster
* 数据自动分片（分成16384个Hash Slot）
* 在部分节点失效时有一定可用性
* 不支持key的批量操作，因为可能数据在不同的master上

JedisCluster
* Jedis只从Master读数据，如果要自动读写分离，可以定制实现

### lettuce内置支持读写分离
* 只读主、只读从
* 优先读主，优先读从

LettuceClientConfiguration  
LettucePoolingClientConfiguration
LettuceClientConfigurationBuilderCustomizer

### RedisTemplate
* RedisTemplate<k,v>
  * opsForXxx()，Xxx是redis数据类型，如opsForHash()
* StringRedisTemplate，k,v都是String

一定要设置过期时间

### Redis Repository
实体注解
* @RedisHash，类似于Entity的实体
* @Id
* @Indexed，二级索引

### 区分不同类型数据源的Repository
* 根据实体的注解
* 根据继承的接口类型
* 扫描不同的包

## Spring的缓存抽象
为不同的缓存提供一层抽象
* 为Java方法增加缓存、缓存执行结果：对相同参数的调用如果有缓存过直接返回缓存的结果
* 支持ConcurrentMap、EhCache（JVM内部的缓存）、Caffeine、JCache、redis
* 接口
  * org.springframework.cache.Cache
  * org.springframework.cache.CacheManager

如果数据长久不会变或者可以它的变化接受一定的延时，可以在JVM内部进行缓存，如果在集群内部进行缓存且要保证一致性，需要分布式的缓存如redis

### 基于注解的缓存
* @EnableCaching：开启缓存支持
  * @Cacheable：执行此方法的结果已在缓存中取缓存中的，否则把结果放到缓存里去
  * @CacheEvict：缓存的清理
  * @CachePut：不管结果直接做一个缓存设值
  * @Caching：对上面的操作做一些打包，放入多个操作
  * @CacheConfig：缓存设置，名字

