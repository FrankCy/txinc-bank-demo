# txinc-bank-demo #

## 简介 ##
访问bank-a执行新增，a会rpc调用b，并减少，如果rpc-b发生错误，a的事务无法控制，需要采用分布式事务解决方案达到数据一致性。

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