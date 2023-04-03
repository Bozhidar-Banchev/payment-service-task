INSERT INTO merchants (ID, name, description, email, status, total_transaction_sum)
VALUES ('1e6eeef9-7e17-4bf8-9d43-419541e3c3d6', 'Merchant1', 'This is a test merchant', 'test@test1.com', 'ACTIVE', 11);

INSERT INTO merchants (ID, name, description, email, status, total_transaction_sum)
VALUES ('1e6eeef9-7e17-4bf8-9d43-419541e3c3d7', 'Merchant2', 'This is a test merchant', 'test@test2.com', 'ACTIVE', 12);

INSERT INTO merchants (ID, name, description, email, status, total_transaction_sum)
VALUES ('1e6eeef9-7e17-4bf8-9d43-419541e3c3d8', 'Merchant3', 'This is a test merchant', 'test@test3.com', 'ACTIVE', 13);


INSERT INTO user_roles (merchant_id, role_name)
VALUES ('1e6eeef9-7e17-4bf8-9d43-419541e3c3d6', 'ADMIN');
INSERT INTO user_roles (merchant_id, role_name)
VALUES ('1e6eeef9-7e17-4bf8-9d43-419541e3c3d7', 'MERCHANT');