-- Create comments table
CREATE TABLE comments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    comment VARCHAR(255) NOT NULL,
    comment_related_to INT NOT NULL,
    comment_added DATETIME,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);