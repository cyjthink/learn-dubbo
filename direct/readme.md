1. 该demo为dubbo的直连模式
2. 之所以将项目分为：interface、provider、consumer三部分，是为了使consumer无法直接调用到provider中的实现类UserServiceImpl
3. 实现步骤
   1. interface
      1. 定义实体类（分布式中实体类必须支持序列化）
      2. 定义接口类
   2. provider
      1. 配置pom，引用interface
      2. 实现接口类
      3. 配置dubbo-user-service-provider.xml
         1. ```<dubbo:application name="userServiceProvider"/>```
         2. ```<dubbo:protocol name="dubbo" port="20880"/>```
         3. ```<dubbo:service interface="com.cyj.dubbo.service.UserService" ref="userServiceImpl" registry="N/A"/>```
         4. ```<bean id="userServiceImpl" class="com.cyj.dubbo.service.UserServiceImpl"/>```
      4. 配置web.xml
         1. ```<context-param>```
         2. ```<listener>```
   3. consumer
      1. 配置pom，引用interface
      2. 实现Controller类
      3. 编写userDetail.jsp（若EL表达式未生效可添加 isELIgnored="false"）
      4. 配置dubbo-user-service-consumer.xml
         1. ```<dubbo:application name="userServiceConsumer"/>```
         2. ```<dubbo:reference id="userService" interface="com.cyj.dubbo.service.UserService" url="dubbo://localhost:20880" registry="N/A"/>```
      5. 配置application.xml
         1. 组件扫描：```<context:component-scan base-package="com.cyj.dubbo.controller"/>```
         2. 注解驱动：```<mvc:annotation-driven/>```
         3. 视图解析器：```<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">```
      6. 配置web.xml
         1. ```<servlet>```
         2. ```<servlet-mapping>```
4. 疑问
   1. 为什么使用dubbo？
      1. 解决分布式系统下的服务治理
   2. dubbo的核心功能？
      1. Remoting:网络通信框架
      2. Cluster:服务框架
      3. Registry:服务注册
   3. dubbo的核心组件
      1. Provider
      2. Container
      3. Consumer
      4. Registry
      5. Monitor
   4. dubbo的注册与发现流程
      1. 容器负责启动、加载、运行服务提供者
      2. 服务提供者在启动时向注册中心注册自己提供的服务
      3. 服务消费者在启动时向注册中心订阅自己需要的服务
      4. 注册中心返回服务提供者地址列表给消费者。如果有变更则基于长链接推送变更数据
      5. 服务消费者基于负责均衡算法从提供者地址列表中选一台提供者进行调用。如果调用失败则选另一台
      6. 服务消费者和服务提供者在内存中的累计调用次数和调用时间数据定时发送给监控中心
   5. dubbo与spring cloud的区别？
      1. dubbo：关注服务的调用。流量分发、流量监控和熔断
      2. spring cloud：考虑的是微服务治理的方方面面，打造一个生态