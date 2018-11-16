truncate table resource_test;
truncate table role_test;
truncate table user_test;
truncate table role_resource_test;
truncate table user_role_test;

-- 初始化权限
insert into resource_test(id,code,name,parent_id)values('4028b2ea66f0f1240166f0f149b00000','resource:all','资源管理','root');
insert into resource_test(id,code,name,parent_id)values('4028b2ea66f0f1240166f0f14a170001','resource:query','资源查询','4028b2ea66f0f1240166f0f149b00000');

-- 初始化角色
insert into role_test(id,code,name)values('4028b2ea66f0f25d0166f0f27be90000','admin','管理员');

-- 初始化用户
insert into user_test(id,username,password,salt,status)values('4028b2ea66f102b60166f102d5230000','admin','c9f8612285d70301b9b65c1cddd29851','ad96206da26549e0a51f45c66503c27d','1');

-- 初始化角色和权限
insert into role_resource_test(role_id,resource_id)values('4028b2ea66f0f25d0166f0f27be90000','4028b2ea66f0f1240166f0f14a170001');

-- 初始化用户和角色
insert into user_role_test(user_id,role_id)values('4028b2ea66f102b60166f102d5230000','4028b2ea66f0f25d0166f0f27be90000');
