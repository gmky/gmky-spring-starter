INSERT INTO ROLES (id, parent_id, name, status, description, role_type, deactivated_by, deactivated_at, created_by, created_at, updated_by, updated_at) VALUES (1, null, 'Admin', 'ACTIVE', 'Administrator', 'TEMPLATE', null, null, 'system', '2024-02-20 20:03:55.000000', 'system', '2024-02-20 20:03:57.000000');
INSERT INTO ROLES (id, parent_id, name, status, description, role_type, deactivated_by, deactivated_at, created_by, created_at, updated_by, updated_at) VALUES (2, null, 'User', 'ACTIVE', 'Normal user', 'TEMPLATE', null, null, 'system', '2024-02-20 20:03:55.000000', 'system', '2024-02-20 20:03:57.000000');

INSERT INTO PG_ROLE (pg_id, role_id) VALUES (1, 1);
INSERT INTO PG_ROLE (pg_id, role_id) VALUES (2, 1);
INSERT INTO PG_ROLE (pg_id, role_id) VALUES (3, 1);