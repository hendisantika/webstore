-- Create sequence tables for entities with GenerationType.AUTO

-- Sequence table for Category entity
CREATE TABLE categories_seq (
    next_val BIGINT NOT NULL
);
INSERT INTO categories_seq VALUES (1);

-- Sequence table for Product entity
CREATE TABLE products_seq (
    next_val BIGINT NOT NULL
);
INSERT INTO products_seq VALUES (1);

-- Sequence table for Comment entity
CREATE TABLE comments_seq (
    next_val BIGINT NOT NULL
);
INSERT INTO comments_seq VALUES (1);

-- Sequence table for UnbanRequest entity
CREATE TABLE unban_requests_seq (
    next_val BIGINT NOT NULL
);
INSERT INTO unban_requests_seq VALUES (1);

-- Sequence table for User entity
CREATE TABLE users_seq (
    next_val BIGINT NOT NULL
);
INSERT INTO users_seq VALUES (1);