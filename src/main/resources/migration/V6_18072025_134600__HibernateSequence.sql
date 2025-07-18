-- Create hibernate_sequence table for auto-increment
CREATE TABLE hibernate_sequence (
    sequence_name VARCHAR(255) NOT NULL,
    next_val BIGINT NOT NULL,
    PRIMARY KEY (sequence_name)
);
INSERT INTO hibernate_sequence VALUES ('default', 1);