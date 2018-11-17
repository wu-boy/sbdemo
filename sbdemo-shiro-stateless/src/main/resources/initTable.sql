drop table if exists resource_test;
create table resource_test(
id varchar(32) primary key,
code varchar(32) unique not null,
name varchar(32) not null,
parent_id varchar(32) not null,
available bool not null default true);
comment on table resource_test is '资源表';
comment on column resource_test.id is '主键';
comment on column resource_test.code is '代码';
comment on column resource_test.name is '名称';
comment on column resource_test.parent_id is '父ID';
comment on column resource_test.available is '是否可用';

drop table if exists role_test;
create table role_test(
id varchar(32) primary key,
code varchar(32) unique not null,
name varchar(32) not null,
available bool not null default true);
comment on table role_test is '资源表';
comment on column role_test.id is '主键';
comment on column role_test.code is '代码';
comment on column role_test.name is '名称';
comment on column role_test.available is '是否可用';

drop table if exists role_resource_test;
create table role_resource_test(
role_id varchar(32),
resource_id varchar(32),
primary key(role_id,resource_id));
comment on table role_resource_test is '角色资源关系表';
comment on column role_resource_test.role_id is '角色ID';
comment on column role_resource_test.resource_id is '资源ID';

drop table if exists user_test;
create table user_test(
id varchar(32) primary key,
username varchar(32) unique not null,
password varchar(32) not null,
salt varchar(32) not null,
status varchar(32) not null);
comment on table user_test is '用户表';
comment on column user_test.id is '主键';
comment on column user_test.username is '用户名';
comment on column user_test.password is '密码';
comment on column user_test.salt is '盐';
comment on column user_test.status is '状态';

drop table if exists user_role_test;
create table user_role_test(
user_id varchar(32),
role_id varchar(32),
primary key(user_id,role_id));
comment on table user_role_test is '用户角色关系表';
comment on column user_role_test.user_id is '用户ID';
comment on column user_role_test.role_id is '角色ID';