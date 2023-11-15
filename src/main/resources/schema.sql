DROP TABLE IF EXISTS "products";
DROP TABLE IF EXISTS "orders";

CREATE SEQUENCE orders_id_seq;

CREATE TABLE orders (
  id bigint DEFAULT nextval('orders_id_seq') NOT NULL,
  description text,
  CONSTRAINT orders_primary_key PRIMARY KEY (id)
);

CREATE SEQUENCE products_id_seq;

CREATE TABLE products (
  id bigint DEFAULT nextval('products_id_seq') NOT NULL,
  order_id bigint,
  name text,
  CONSTRAINT products_primary_key PRIMARY KEY (id),
  CONSTRAINT foreign_key_order FOREIGN KEY (id) REFERENCES orders(id)
);