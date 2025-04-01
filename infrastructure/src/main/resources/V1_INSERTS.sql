INSERT INTO Users (Name, Email, Password, Role, IsActive)
VALUES
    ('Willian Souza', 'admin@iteam.com', 'password', 'admin', TRUE),
    ('Tiago Ferreira', 'user@iteam.com', 'password', 'user', TRUE);

INSERT INTO Categories (Id, Name, CreatedAt, UpdatedAt) VALUES
    ('3f5f5e22-7b44-44a0-b062-1f61e2c58f2f', 'Electronics', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('4a8f6c33-9b21-4577-9b8e-1d3e2c7d8f4a', 'Clothing', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('5c7d2e44-6a12-45a1-8c9d-3e5f7d9b3c2e', 'Food', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Products (Id, CategoryId, Name, Description, Price, Stock, CreatedAt, UpdatedAt) VALUES
    ('b1f4e7d3-a4c6-4c44-8b59-d4e0a1d5e9f7', '3f5f5e22-7b44-44a0-b062-1f61e2c58f2f', 'Smartphone', 'Latest model smartphone with high-end features.', 799.99, 50, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('c2a6f8d4-b6e7-4f55-9c88-e5f1b2d6f8e8', '3f5f5e22-7b44-44a0-b062-1f61e2c58f2f', 'Laptop', 'Powerful laptop for work and gaming.', 1299.99, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('d4f7e8a5-c9b8-4567-89d0-a3e2f7c8b9d9', '3f5f5e22-7b44-44a0-b062-1f61e2c58f2f', 'Wireless Headphones', 'Noise-canceling over-ear headphones.', 199.99, 75, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);