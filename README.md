# txinc-bank-demo #

## 简介 ##
访问bank-a执行新增，a会rpc调用b，并减少，如果rpc-b发生错误，a的事务无法控制，需要采用分布式事务解决方案达到数据一致性。

## 项目介绍 ##
| 工程 | 端口 | 描述 |
|:--|:--|:--|
| bank-a | 8090 | 先rpc调用bank-b服务，执行加money，然后回到bank-a执行减money |
| bank-b | 8099 | 执行加money操作 |
| bank-common |  | 工具类，导入tc，供bank-a、bank-b使用 |

