-- Create products table
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    product_highlights VARCHAR(200) NOT NULL,
    product_description LONGTEXT NOT NULL,
    product_price INT NOT NULL,
    product_discount INT NOT NULL,
    product_stocks INT NOT NULL,
    product_seller_name VARCHAR(255),
    product_seller_id INT,
    price_after_discount INT,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Create product_images table
CREATE TABLE product_images (
    product_id INT NOT NULL,
    images VARCHAR(255) NOT NULL,
    PRIMARY KEY (product_id, images),
    FOREIGN KEY (product_id) REFERENCES products(id)
);