
运行项目前, 先启动mysql:
```shell
docker run -itd -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root mysql:8.0
```
创建数据库:
```mysql
create database spring_persistence;
```