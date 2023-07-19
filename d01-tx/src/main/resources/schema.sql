drop table if exists account;
create table if not exists account
(
    `id`   int(10) primary key,
    `balance`   int(10)     not null default 0
) engine = InnoDB
    default character set = utf8mb4;