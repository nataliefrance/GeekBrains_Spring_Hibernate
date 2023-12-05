BEGIN;

DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customers (name) VALUES
('Alica Milano'),
('Bred Pitt'),
('Harry Potter'),
('Santa Claus');

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), cost bigint);
INSERT INTO products (title, cost) VALUES
('coca-cola', 100),
('water', 150),
('bread', 99);


DROP TABLE IF EXISTS customers_products CASCADE;
CREATE TABLE customers_products (customer_id bigint, product_id bigint,
FOREIGN KEY (customer_id) REFERENCES customers (id), FOREIGN KEY (product_id) REFERENCES products (id));
INSERT INTO customers_products (customer_id, product_id) VALUES
(1, 3),
(2, 1),
(3, 3),
(4, 3),
(1, 2),
(2, 2);

COMMIT;