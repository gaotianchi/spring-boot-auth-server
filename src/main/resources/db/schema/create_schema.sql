CREATE TABLE IF NOT EXISTS blog_auth.client
(
    id                            int auto_increment
        primary key,
    client_id                     varchar(255)                                                                not null,
    client_id_issued_at           timestamp     default CURRENT_TIMESTAMP                                     not null,
    client_secret                 varchar(255)                                                                null,
    client_secret_expires_at      timestamp                                                                   null,
    client_name                   varchar(255)                                                                not null,
    authorization_grant_types     varchar(1000) default 'refresh_token,client_credentials,authorization_code' not null,
    redirect_uris                 varchar(1000)                                                               not null,
    scopes                        varchar(1000) default 'openid'                                              not null,
    client_authentication_methods varchar(1000) default 'client_secret_post,client_secret_basic'              not null,
    post_logout_redirect_uris     varchar(1000)                                                               null,
    require_proof_key             tinyint(1)    default 1                                                     not null,
    require_authorization_consent tinyint(1)    default 0                                                     not null,
    access_token_time_live        int           default 1800                                                not null,
    refresh_token_time_to_live    int           default 302400                                                not null,
    reuse_refresh_tokens          tinyint(1)    default 0                                                     not null
);

CREATE TABLE IF NOT EXISTS blog_auth.permission
(
    id          int auto_increment
        primary key,
    code        int                      not null,
    name        varchar(255)             not null,
    description varchar(255)             null,
    created_at  datetime default (now()) not null,
    updated_at  datetime default (now()) not null,
    constraint permission_pk_2
        unique (code)
)
    comment '权限表';

CREATE TABLE IF NOT EXISTS blog_auth.role
(
    id          int auto_increment
        primary key,
    code        int                      not null,
    name        varchar(255)             not null,
    description varchar(255)             null,
    created_at  datetime default (now()) not null,
    updated_at  datetime default (now()) not null,
    constraint role_pk
        unique (code)
)
    comment '角色表';

CREATE TABLE IF NOT EXISTS blog_auth.role_permission
(
    id              int auto_increment
        primary key,
    role_code       int not null,
    permission_code int not null,
    constraint role_permission_permission_code_fk
        foreign key (permission_code) references blog_auth.permission (code),
    constraint role_permission_role_code_fk
        foreign key (role_code) references blog_auth.role (code)
)
    comment '角色权限表';

CREATE TABLE IF NOT EXISTS blog_auth.user
(
    id              int auto_increment
        primary key,
    username        varchar(255)                         not null,
    password        varchar(255)                         not null,
    email           varchar(255)                         null,
    created_at      datetime   default CURRENT_TIMESTAMP not null,
    updated_at      datetime   default CURRENT_TIMESTAMP not null,
    last_login_time datetime                             null,
    last_login_ip   varchar(45)                          null,
    failed_attempts int        default 0                 not null,
    is_locked       tinyint(1) default 0                 not null,
    lock_expiration datetime                             null,
    is_enabled      tinyint(1) default 1                 not null,
    constraint user_admin_pk_2
        unique (username),
    constraint user_pk
        unique (email)
)
    comment '用户表';

CREATE TABLE IF NOT EXISTS blog_auth.action_log
(
    id          int auto_increment
        primary key,
    user_id     int                      not null,
    action      varchar(100)             not null,
    action_time datetime default (now()) not null,
    object_type varchar(50)              null comment '被操作的对象类型',
    object_id   int                      null,
    ip_address  varchar(255)             null,
    device_info varchar(255)             null,
    details     varchar(255)             null,
    created_at  datetime                 not null,
    constraint action_log_user_id_fk
        foreign key (user_id) references blog_auth.user (id)
            on delete cascade
)
    comment '用户行为日志';

create index action_log_action_time_index
    on blog_auth.action_log (action_time desc);

create index action_log_object_type_object_id_index
    on blog_auth.action_log (object_type, object_id);

create index action_log_user_id_index
    on blog_auth.action_log (user_id)
    comment '快速查询某个用户的操作日志';

CREATE TABLE IF NOT EXISTS blog_auth.user_role
(
    id        int auto_increment
        primary key,
    user_id   int not null,
    role_code int not null,
    constraint user_role_role_code_fk
        foreign key (role_code) references blog_auth.role (code),
    constraint user_role_user_id_fk
        foreign key (user_id) references blog_auth.user (id)
);