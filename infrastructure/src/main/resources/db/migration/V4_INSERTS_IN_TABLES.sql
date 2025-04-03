INSERT INTO Users (Name, Email, Password, Role, IsActive, CreatedAt, UpdatedAt) VALUES
    ('Willian Souza', 'admin@iteam.com',
     '$2a$10$E62tvSFK0/ehdvF4CdzGUuI9x8veq/AQl5sAI1B/6CCu0UtANcqim',
     'ROLE_ADMIN', TRUE, '2025-04-02 20:34:14.147028', '2025-04-02 20:34:14.147028'),
    ('Tiago Ferreira', 'user@iteam.com',
     '$2a$10$E62tvSFK0/ehdvF4CdzGUuI9x8veq/AQl5sAI1B/6CCu0UtANcqim',
     'ROLE_USER', TRUE, '2025-04-02 20:34:14.147028', '2025-04-02 20:34:14.147028');

INSERT INTO Categories (Name, CreatedAt, UpdatedAt) VALUES
    ('Electronics', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Clothing', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Food', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Products (CategoryId, Name, Description, Price, Stock, CreatedAt, UpdatedAt) VALUES
    ('c911976c-5488-4ffc-ae1e-e4e15a98d4d1', 'Laptop', 'Powerful laptop for work and gaming.', 1299.99, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
