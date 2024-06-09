INSERT INTO USERS (id, username, email, password, full_name, status, is_admin, expire_at, deleted_by, deleted_at, created_by, created_at, updated_by, updated_at) VALUES (1, 'admin', 'admin@gmky.dev', '$2a$10$VO2yeW6E72U.9njSCj4guOGfkzTQcohKu0WcTU.3wbre6D2CO6Vxa', 'Codebase Admin', 'ACTIVE', true, null, null, null, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
INSERT INTO USERS (id, username, email, password, full_name, status, is_admin, expire_at, deleted_by, deleted_at, created_by, created_at, updated_by, updated_at) VALUES (2, 'user', 'user@gmky.dev', '$2a$10$VO2yeW6E72U.9njSCj4guOGfkzTQcohKu0WcTU.3wbre6D2CO6Vxa', 'Codebase Admin', 'BANNED', true, null, null, null, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
INSERT INTO USERS (id, username, email, password, full_name, status, is_admin, expire_at, deleted_by, deleted_at, created_by, created_at, updated_by, updated_at) VALUES (3, 'user2', 'user2@gmky.dev', '$2a$10$VO2yeW6E72U.9njSCj4guOGfkzTQcohKu0WcTU.3wbre6D2CO6Vxa', 'Codebase Admin', 'DELETED', true, null, null, null, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
INSERT INTO USERS (id, username, email, password, full_name, status, is_admin, expire_at, deleted_by, deleted_at, created_by, created_at, updated_by, updated_at) VALUES (4, 'user3', 'use3r@gmky.dev', '$2a$10$VO2yeW6E72U.9njSCj4guOGfkzTQcohKu0WcTU.3wbre6D2CO6Vxa', 'Codebase Admin', 'PENDING', true, null, null, null, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

INSERT INTO ROLE_USER (role_id, user_id) VALUES (1, 1);
INSERT INTO ROLE_USER (role_id, user_id) VALUES (2, 2);
INSERT INTO ROLE_USER (role_id, user_id) VALUES (2, 3);
INSERT INTO ROLE_USER (role_id, user_id) VALUES (2, 4);
