CREATE DATABASE lost_found_db;
USE lost_found_db;
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    role ENUM('USER', 'ADMIN') DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE items (
    item_id INT AUTO_INCREMENT PRIMARY KEY,

    user_id INT NOT NULL,

    title VARCHAR(150) NOT NULL,
    description TEXT,
    category VARCHAR(50),
    location VARCHAR(150),
    date DATE,

    type ENUM('LOST', 'FOUND') NOT NULL,

    status ENUM(
        'ACTIVE',
        'CLAIMED',
        'RETURNED'
    ) DEFAULT 'ACTIVE',

    image_path VARCHAR(255),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id)
        REFERENCES users(user_id)
        ON DELETE CASCADE
);

CREATE TABLE claims (
    claim_id INT AUTO_INCREMENT PRIMARY KEY,

    item_id INT NOT NULL,
    claimant_id INT NOT NULL,

    description TEXT,

    status ENUM(
        'PENDING',
        'APPROVED',
        'REJECTED'
    ) DEFAULT 'PENDING',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (item_id)
        REFERENCES items(item_id)
        ON DELETE CASCADE,

    FOREIGN KEY (claimant_id)
        REFERENCES users(user_id)
        ON DELETE CASCADE
);
USE lost_found_db;
DESCRIBE items;
DESCRIBE claims;
USE lost_found_db;
SHOW TABLES;
SELECT * FROM users;
SELECT * FROM items;
SELECT * FROM claims;
 
