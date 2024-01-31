-- Inserting product types
INSERT INTO product_type (name, description) VALUES
                                                     ('Vegetable', 'Vegetables are healthy and nutritious'),
                                                     ('Fruit', 'Fruits are a rich source of vitamins and minerals'),
                                                     ('Meat', 'High-quality protein from various meats'),
                                                     ('Fish', 'Seafood rich in omega-3 fatty acids'),
                                                     ('Dairy', 'Dairy products for obtaining calcium and vitamin D'),
                                                     ('Cereal', 'Whole grains and cereals for sustained energy'),
                                                     ('Beverages', 'Variety of refreshing and nutritious beverages'),
                                                     ('Snacks', 'Delicious and healthy snacks for munching');

-- Inserting products
INSERT INTO product (kcal, product_type, name, description) VALUES
                                                                    (25, 1, 'Broccoli', 'Fresh and healthy broccoli'),
                                                                    (45, 2, 'Apple', 'Juicy apple full of vitamins'),
                                                                    (120, 3, 'Chicken Breast', 'Lean and delicious chicken breast'),
                                                                    (180, 4, 'Salmon', 'Fresh salmon fillet rich in omega-3'),
                                                                    (90, 5, 'Milk', 'Fresh milk for obtaining calcium'),
                                                                    (110, 6, 'Oats', 'Whole oats for an energetic breakfast'),
                                                                    (0, 7, 'Mineral Water', 'Pure and refreshing water'),
                                                                    (50, 8, 'Walnuts', 'Crunchy walnuts full of nutrients');

-- Inserting menus
INSERT INTO menu (price, name, image_reference) VALUES
                                                        (15.99, 'Vegetarian Menu', '/image/menu/menu_vegetarian_azerbaijan_stock.jpg'),
                                                        (19.99, 'Healthy Menu', '/image/menu/menu_healthy_freepik.jpg'),
                                                        (24.99, 'Fitness Menu', '/image/menu/menu_fitness_freepik.jpg'),
                                                        (18.99, 'Kids Menu', '/image/menu/menu_kids_azerbaijan_stock.jpg'),
                                                        (22.99, 'Gourmet Menu', '/image/menu/menu_default_freepik.jpg');

-- Inserting menu products
INSERT INTO menu_products (menu_id, products_id) VALUES
                                                     -- Vegetarian Menu
                                                     (1, 1), -- Broccoli
                                                     (1, 2), -- Apple
                                                     (1, 5), -- Milk

                                                     -- Healthy Menu
                                                     (2, 1), -- Broccoli
                                                     (2, 2), -- Apple
                                                     (2, 3), -- Chicken Breast
                                                     (2, 5), -- Milk

                                                     -- Fitness Menu
                                                     (3, 3), -- Chicken Breast
                                                     (3, 4), -- Salmon
                                                     (3, 6), -- Oats

                                                     -- Kids Menu
                                                     (4, 2), -- Apple
                                                     (4, 7), -- Mineral Water

                                                     -- Gourmet Menu
                                                     (5, 3), -- Chicken Breast
                                                     (5, 4), -- Salmon
                                                     (5, 8); -- Walnuts

INSERT INTO app_user (phone_number, email, last_name, name, username, password) VALUES
                                                                                                                                        --Passwords are the same as usernames
                                                                    ('5551234567', 'juan.perez@gmail.com', 'Pérez', 'Juan', 'jperez', '$2y$10$6c1lSv7gBYdcrbYn7vFrvefVXZvBrJhqnMG/AohodrP73NxDF5QHO'),
                                                                    ('5559876543', 'ana.gomez@hotmail.com', 'Gómez', 'Ana', 'agomez', '$2y$10$XJr8j0mOPN8UaGg5FjC6HOPo3iJMW7IlBvJHP43FzgXp6IT6mPizi'),
                                                                    ('5551112233', 'carlos.lopez@outlook.com', 'López', 'Carlos', 'clopez', '$2y$10$jReiLsz/LHjFUSDWm8hKJO4EUG8RPZY/7YAdS9Y8AQNrKmERupR2C');

INSERT INTO food_order (id, menu_id, order_number, user_id, address) VALUES
                                                                         (0, 1, 0, 1, 'Main ST, City'),
                                                                         (1, 2, 1, 1, 'Rural ST, City'),
                                                                         (2, 2, 2, 2, 'Maple ST, Town'),
                                                                         (3, 3, 3, 3, 'Pine ST, Village');

INSERT INTO role (id, name) VALUES
                                (0, 'ADMIN'),
                                (1, 'USER');

INSERT INTO users_roles (role_id, user_id) VALUES
                                               -- jperez
                                               (0, 1), -- admin
                                               -- agomez
                                               (1, 2), -- user
                                               -- clopez
                                               (0, 3), -- admin
                                               (1, 3); -- user