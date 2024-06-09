-- User PRIVILEGES
INSERT INTO PRIVILEGES (id, resource_code, action, description, created_by, created_at, updated_by, updated_at) VALUES (1, 'USER', 'CREATE', 'Create new user', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
INSERT INTO PRIVILEGES (id, resource_code, action, description, created_by, created_at, updated_by, updated_at) VALUES (2, 'USER', 'VIEW', 'View user', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
INSERT INTO PRIVILEGES (id, resource_code, action, description, created_by, created_at, updated_by, updated_at) VALUES (3, 'USER', 'EDIT', 'Edit user', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
INSERT INTO PRIVILEGES (id, resource_code, action, description, created_by, created_at, updated_by, updated_at) VALUES (4, 'USER', 'DELETE', 'Delete user', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- Privilege Group
INSERT INTO PRIVILEGES (id, resource_code, action, description, created_by, created_at, updated_by, updated_at) VALUES (5, 'PRIVILEGE_GROUP', 'CREATE', 'Create new privilege group', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
INSERT INTO PRIVILEGES (id, resource_code, action, description, created_by, created_at, updated_by, updated_at) VALUES (6, 'PRIVILEGE_GROUP', 'VIEW', 'View privilege group', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
INSERT INTO PRIVILEGES (id, resource_code, action, description, created_by, created_at, updated_by, updated_at) VALUES (7, 'PRIVILEGE_GROUP', 'EDIT', 'Edit privilege group', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
INSERT INTO PRIVILEGES (id, resource_code, action, description, created_by, created_at, updated_by, updated_at) VALUES (8, 'PRIVILEGE_GROUP', 'DELETE', 'Delete privilege group', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- Role
INSERT INTO PRIVILEGES (id, resource_code, action, description, created_by, created_at, updated_by, updated_at) VALUES (9, 'ROLE', 'CREATE', 'Create new role', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
INSERT INTO PRIVILEGES (id, resource_code, action, description, created_by, created_at, updated_by, updated_at) VALUES (10, 'ROLE', 'VIEW', 'View role', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
INSERT INTO PRIVILEGES (id, resource_code, action, description, created_by, created_at, updated_by, updated_at) VALUES (11, 'ROLE', 'EDIT', 'Edit role', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
INSERT INTO PRIVILEGES (id, resource_code, action, description, created_by, created_at, updated_by, updated_at) VALUES (12, 'ROLE', 'DELETE', 'Delete role', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

