# txinc-bank-demo #

## 简介 ##
bank-a的业务是减少用户金钱，rpc bank-b则是增加用户金钱，当访问bank-a时，bank-a通过feign rpc调用bank-b先执行增加用户金钱，然后回到bank-a执行用户减钱，如果此时，bank-a出现问题，rpc bank-b和bank-a的事务都不会提交。

## 项目介绍 ##

| 工程 | 端口 | 描述 |
|:--|:--|:--|
| bank-a | 8090 | 先rpc调用bank-b服务，执行加money，然后回到bank-a执行减money |
| bank-b | 8099 | 执行加money操作 |
| bank-common |  | 工具类，导入tc，供bank-a、bank-b使用 |

### 关键知识点 ###
- 导入tc（bank-common）
```xml
<dependencies>
    <!-- 加入分布式事务基础依赖 -->
    <dependency>
        <groupId>com.codingapi.txlcn</groupId>
        <artifactId>txlcn-tc</artifactId>
        <version>5.0.1.RELEASE</version>
    </dependency>

    <!-- 加入分布式事务基础依赖 -->
    <dependency>
        <groupId>com.codingapi.txlcn</groupId>
        <artifactId>txlcn-txmsg-netty</artifactId>
        <version>5.0.1.RELEASE</version>
    </dependency>
</dependencies>
```
- 在bank-a、bank-b的启动类上添加注释（标记为分布式事务管理），如下bank-a启动类
```java
...
@EnableDistributedTransaction
public class BankAApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankAApplication.class, args);
    }
}
```
- 具体实现上添加@LcnTransaction注解，如下bank-a/.../BankService.java
```java
@LcnTransaction
@Transactional
public String start(int money) {

    String user = "xiaoming";
    //此处调用Feign
    String status = bankBClient.addMoney(money, user);
    if("success".equals(status)) {
        Account account = new Account();
        account.setMoney(money);
        account.setUser(user);
        //如果有错误，本地回滚，调用的feign无法回滚
        int res = accountDao.update(account);
        throw new RuntimeException("insert money error");
//            return res > 0 ? "success" : "error";
    }
    return "rpc error";
}
```
```当bank-a抛出异常时，rpc bank-b事务没有提交。```