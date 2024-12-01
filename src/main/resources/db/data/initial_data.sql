INSERT INTO blog_auth.permission (code, name, description, created_at, updated_at)
VALUES
    (1000, 'ALL', '管理员权限', now(), now()),
    (1001, 'TEST', null, now(), now());

INSERT INTO blog_auth.role (code, name, description, created_at, updated_at)
VALUES
    (10, 'ADMIN', '管理员角色', now(), now()),
    (11, 'TEST', null, now(), now());

INSERT INTO blog_auth.role_permission (role_code, permission_code)
VALUES
    (10, 1000),
    (11, 1001);

INSERT INTO blog_auth.user (username, password, email, created_at, updated_at, is_enabled)
VALUES
    ('admin', '{noop}admin', 'admin@example.com', now(), now(), 1),
    ('test', '{noop}test', 'test@example.com', now(), now(), 1);

INSERT INTO blog_auth.user_role (user_id, role_code)
VALUES
    (1, 10),
    (2, 11);

INSERT INTO blog_auth.client (client_id, client_id_issued_at, client_secret, client_name, redirect_uris)
VALUES ('default_client', now(), '{noop}default_client',  'Public Client', 'https://oauthdebugger.com/debug');
