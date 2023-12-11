create database blog;

create table userinfo
(
    id         varchar(200)        not null,
    password   varchar(200)        not null,
    name       varchar(200) unique not null,
    createdate DATE,
    primary key (id)
)
