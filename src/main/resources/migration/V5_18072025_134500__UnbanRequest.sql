-- Create unban_requests table
CREATE TABLE unban_requests (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_message VARCHAR(500) NOT NULL,
    from_user VARCHAR(255) NOT NULL
);