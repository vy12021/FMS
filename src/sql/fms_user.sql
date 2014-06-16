#在进行用户安全系统设计一般有用户、组（角色）、权限三个主体
#用户通过拥有角色来获取最终的权限
#一个用户可以有多个角色，一个角色可以分配给多个用户
#一个角色可以拥有多个权限，一个权限可以分配给多个角色
#所有我们通过设计两张中间表来保存他们之间的关联
#用户和角色关联关系我们设计：用户角色表
#角色和权限管理关系我们设计：角色权限表

#用户表（保存用户信息）
create table fms_user(
id INTEGER primary key auto_increment, #用户主键，自增
username char(40) not null unique,                     #用户名
password char(40) not null,                     #口令
activateStatus char(5),                 #激活状态
createDate char(50),                    #创建时间
created INTEGER                         #创建人(后台管理所用)
);

#用户组(角色)
create table fms_group(
id INTEGER primary key auto_increment, #用户组主键，自增
groupName char(30) not null unique,                    #组名称
description char(200),                 #用户组的一些描述信息
createDate char(50),                   #组创建时间
created INTEGER                        #创建人的用户ID
);

#用户和用户组中间表(完成多对多关系)
create table fms_user_group(
id INTEGER primary key auto_increment, #主键，自增
userId INTEGER not null,                #用户id
groupId INTEGER not null,               #组id
description char(100),                 #描述信息*?
createDate char(50),                   #分配日期
created INTEGER                        #创建人的用户id
);

#权限表(具体操作控制细节)
create table fms_permission(
id INTEGER primary key auto_increment, #主键，自增
resourceName char(50) not null unique,                 #资源名称(被操作对象)
permissionName char(50) not null unique,               #权限名称
description char(100),                 #描述信息
createDate char(50),                   #创建日期
created INTEGER                        #创建人用户id
);

#用户组和权限关联中间表(完成多对多关联)
create table fms_group_permission(
id INTEGER primary key auto_increment, #主键，自增
groupId INTEGER not null,              #组id
permissionId Integer not null,         #权限id
description char(100),                 #描述信息
createDate char(50),                   #分配日期
created INTEGER                        #创建人用户id
);

#用户注册详细信息(通过userId和user惟一关联)
create table fms_userInfo(
id INTEGER primary key auto_increment,
userId INTEGER not null unique,
realName char(30),
sex char(10),
age char(10),
email char(40) not null unique,
phone char(30) unique
);

create table fms_activate(
id INTEGER primary key auto_increment ,
userId INTEGER not null unique ,
activateId char(100)
);

#系统模块数据
create table fms_sys_module(
id INTEGER primary key auto_increment,
superId INTEGER,
moduleName char(50),
moduleUrl char(200),
resourceName char(100),
iconUri char(200),
isLeave INTEGER,
authId char(100)
);

INSERT INTO `fms_sys_module` VALUES (1, 0, '根节点', NULL, 'root', NULL, 0, '1');
INSERT INTO `fms_sys_module` VALUES (2, 1, '系统管理', NULL, 'systemMgmt', '/static/resources/style/images/sysmgmt.png', 0, '1');
INSERT INTO `fms_sys_module` VALUES (3, 2, '控制面板', NULL, 'dashBoard', '', 0, '1');
INSERT INTO `fms_sys_module` VALUES (4, 3, '用户管理', NULL, 'userMgmt', NULL, 1, '1');
INSERT INTO `fms_sys_module` VALUES (5, 3, '角色管理', NULL, 'roleMgmt', NULL, 1, '1');
INSERT INTO `fms_sys_module` VALUES (6, 3, '模块管理', NULL, 'moduleMgmt', NULL, 1, '1');
INSERT INTO `fms_sys_module` VALUES (7, 1, '资源管理', NULL, 'resourceMgmt', '/static/resources/style/images/resourcemgmt.png', 0, '1');
