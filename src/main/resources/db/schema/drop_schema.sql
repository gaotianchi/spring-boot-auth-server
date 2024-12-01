-- 删除表中涉及的外键约束
alter table blog_auth.role_permission drop foreign key role_permission_permission_code_fk;
alter table blog_auth.role_permission drop foreign key role_permission_role_code_fk;
alter table blog_auth.user_role drop foreign key user_role_role_code_fk;
alter table blog_auth.user_role drop foreign key user_role_user_id_fk;
alter table blog_auth.action_log drop foreign key action_log_user_id_fk;

-- 删除索引
drop index action_log_action_time_index on blog_auth.action_log;
drop index action_log_object_type_object_id_index on blog_auth.action_log;
drop index action_log_user_id_index on blog_auth.action_log;

-- 删除表
drop table if exists blog_auth.user_role;
drop table if exists blog_auth.action_log;
drop table if exists blog_auth.user;
drop table if exists blog_auth.role_permission;
drop table if exists blog_auth.role;
drop table if exists blog_auth.permission;
drop table if exists blog_auth.client;
