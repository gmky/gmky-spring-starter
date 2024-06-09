INSERT INTO PRIVILEGE_GROUP (id, name, description, pg_type, created_by, created_at, updated_by, updated_at) VALUES (1, 'UserFullAccess', 'User full access', 'TEMPLATE', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
INSERT INTO PG_ITEM (pg_id, privilege_id) VALUES (1, 1);
INSERT INTO PG_ITEM (pg_id, privilege_id) VALUES (1, 2);
INSERT INTO PG_ITEM (pg_id, privilege_id) VALUES (1, 3);
INSERT INTO PG_ITEM (pg_id, privilege_id) VALUES (1, 4);

INSERT INTO PRIVILEGE_GROUP (id, name, description, pg_type, created_by, created_at, updated_by, updated_at) VALUES (2, 'PrivilegeGroupFullAccess', 'Privilege group full access', 'TEMPLATE', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
INSERT INTO PG_ITEM (pg_id, privilege_id) VALUES (2, 5);
INSERT INTO PG_ITEM (pg_id, privilege_id) VALUES (2, 6);
INSERT INTO PG_ITEM (pg_id, privilege_id) VALUES (2, 7);
INSERT INTO PG_ITEM (pg_id, privilege_id) VALUES (2, 8);

INSERT INTO PRIVILEGE_GROUP (id, name, description, pg_type, created_by, created_at, updated_by, updated_at) VALUES (3, 'RoleFullAccess', 'Role full access', 'TEMPLATE', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
INSERT INTO PG_ITEM (pg_id, privilege_id) VALUES (3, 9);
INSERT INTO PG_ITEM (pg_id, privilege_id) VALUES (3, 10);
INSERT INTO PG_ITEM (pg_id, privilege_id) VALUES (3, 11);
INSERT INTO PG_ITEM (pg_id, privilege_id) VALUES (3, 12);