-- Create users table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    user_email VARCHAR(255) NOT NULL UNIQUE,
    user_pwd VARCHAR(255) NOT NULL,
    user_role VARCHAR(50),
    user_phone VARCHAR(20) NOT NULL,
    user_suspension_status BOOLEAN,
    user_profile VARCHAR(255),
    registered_date DATETIME
);