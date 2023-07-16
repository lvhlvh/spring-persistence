drop table if exists balance;
create table if not exists balance
(
    `user_id`   int(10) primary key,
    `user_name` varchar(20) not null,
    `balance`   int(10)     not null default 0
    ) engine = InnoDB
    default character set = utf8mb4;