-- Add 10 users (2 admins, 8 regular users)

-- Admin users
INSERT INTO users (user_name, user_email, user_pwd, user_role, user_phone, user_suspension_status, user_profile, registered_date)
VALUES 
('Admin One', 'admin1@webstore.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'ROLE_ADMIN', '1234567890', true, NULL, NOW()),
('Admin Two', 'admin2@webstore.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'ROLE_ADMIN', '0987654321', true, NULL, NOW());

-- Regular users (customers)
INSERT INTO users (user_name, user_email, user_pwd, user_role, user_phone, user_suspension_status, user_profile, registered_date)
VALUES 
('Customer One', 'customer1@webstore.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'ROLE_CUSTOMER', '1122334455', true, NULL, NOW()),
('Customer Two', 'customer2@webstore.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'ROLE_CUSTOMER', '2233445566', true, NULL, NOW()),
('Customer Three', 'customer3@webstore.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'ROLE_CUSTOMER', '3344556677', true, NULL, NOW()),
('Customer Four', 'customer4@webstore.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'ROLE_CUSTOMER', '4455667788', true, NULL, NOW()),
('Customer Five', 'customer5@webstore.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'ROLE_CUSTOMER', '5566778899', true, NULL, NOW()),
('Customer Six', 'customer6@webstore.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'ROLE_CUSTOMER', '6677889900', true, NULL, NOW()),
('Customer Seven', 'customer7@webstore.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'ROLE_CUSTOMER', '7788990011', true, NULL, NOW()),
('Customer Eight', 'customer8@webstore.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'ROLE_CUSTOMER', '8899001122', true, NULL, NOW());

-- Update the users_seq next_val to account for the new users
UPDATE users_seq SET next_val = next_val + 10;