1. 该项目为dubbo使用zookeeper作为注册中心+version应用的demo
2. 实现步骤基本与direct相同，不同之处为
   1. provider
      1. ```<dubbo:registry address="zookeeper://localhost:2181"/>```
      2. ```<dubbo:service interface="com.cyj.dubbo.service.UserService" ref="userServiceImpl" version="1.0.0"/>```
   2. consumer
      1. ```<dubbo:registry address="zookeeper://localhost:2181"/>```
      2. ```<dubbo:reference id="userService" interface="com.cyj.dubbo.service.UserService" version="1.0.0"/>```
   3. 启动provider、consumer的tomcat之前需通过zkServer start启动zookeeper
3. 疑问
   1. 为什么在UserController中定义了两个UserService，spring可以自动找到对应的实现(UserServiceImpl与UserServiceImpl2)？
      1. 因为两者是通过@Autowired注入的，@Autowired会通过id进行查找。而在dubbo-user-service-consumer.xml中，```<dubbo:reference/>```分别定义了id为userService、userService2的引用
   2. 为什么使用注册中心？注册中心实现了各服务之间的注册与发现，是各个分布式节点之间的纽带
      1. 动态加入。服务提供者可以通过注册中心动态的把自己暴露给其他消费者，而无需消费者逐个去更新配置文件
      2. 动态发现。消费者可以动态感知新的配置、路由规则和新的服务提供者，无需重启服务
      3. 动态调整。注册中心支持参数的动态调整，更新后的配置自动更新到所有服务节点
      4. 统一配置。避免了每个服务的配置不同
   3. 为什么使用zookeeper作为注册中心？
      1. 因为除了数据存储外，zookeeper还提供了watcher机制。即只要服务fashen