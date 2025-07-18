-- Create categories table
CREATE TABLE categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    category_title VARCHAR(255) NOT NULL UNIQUE
);