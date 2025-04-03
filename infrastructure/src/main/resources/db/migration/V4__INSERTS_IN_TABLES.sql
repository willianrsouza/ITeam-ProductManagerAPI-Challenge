INSERT INTO Users (Id, Name, Email, Password, Role, IsActive, CreatedAt, UpdatedAt)
VALUES
(UUID(), 'Willian Souza', 'admin@iteam.com',
 '$2a$10$E62tvSFK0/ehdvF4CdzGUuI9x8veq/AQl5sAI1B/6CCu0UtANcqim',
 'ROLE_ADMIN', TRUE, '2025-04-02 20:34:14.147028', '2025-04-02 20:34:14.147028'),
(UUID(), 'Tiago Ferreira', 'user@iteam.com',
 '$2a$10$E62tvSFK0/ehdvF4CdzGUuI9x8veq/AQl5sAI1B/6CCu0UtANcqim',
 'ROLE_USER', TRUE, '2025-04-02 20:34:14.147028', '2025-04-02 20:34:14.147028');


INSERT INTO Categories (Id, Name, CreatedAt, UpdatedAt) VALUES
(UUID(),'Electronics', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(),'Clothing', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(),'Food', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

