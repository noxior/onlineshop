DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS fuel_stations_fuels;
DROP TABLE IF EXISTS fuel_stations;
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS order_details;
DROP TABLE IF EXISTS fuels;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;


CREATE SEQUENCE global_seq
  START 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  login            VARCHAR                 NOT NULL,
  first_name       VARCHAR                 NOT NULL,
  last_name        VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL,
  calories_per_day INTEGER DEFAULT 2000    NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE orders (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id       INTEGER        NOT NULL,
  order_time    TIMESTAMP      NOT NULL,
  order_address VARCHAR        NOT NULL,
  status_id     INTEGER        NOT NULL,
  amount        NUMERIC(13, 2) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX orders_unique_user_order_time_idx
  ON orders (user_id, order_time);

CREATE TABLE fuel_stations (
  id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR NOT NULL
);
CREATE UNIQUE INDEX fuel_station_unique_name_idx
  ON fuel_stations (name);

CREATE TABLE fuels
(
  id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name    VARCHAR                 NOT NULL,
  price   NUMERIC(13, 2)          NOT NULL,
  enabled BOOL DEFAULT TRUE       NOT NULL
);
CREATE UNIQUE INDEX fuels_unique_name_idx
  ON fuels (name);

CREATE TABLE fuel_stations_fuels (
  fuel_id         INTEGER NOT NULL   REFERENCES fuels (id) ON UPDATE CASCADE ON DELETE CASCADE,
  fuel_station_id INTEGER NOT NULL   REFERENCES fuel_stations (id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fuels_fuel_stations_pkey PRIMARY KEY (fuel_id, fuel_station_id)
);

CREATE TABLE order_details (
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  amount   NUMERIC(13, 2) NOT NULL,
  quantity INTEGER        NOT NULL,
  order_id INTEGER        NOT NULL,
  fuel_id  INTEGER        NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
  FOREIGN KEY (fuel_id) REFERENCES fuels (id)
);
CREATE UNIQUE INDEX order_details_order_name_idx
  ON order_details (order_id, fuel_id);


CREATE TABLE order_status (
  order_id INTEGER NOT NULL,
  status VARCHAR NOT NULL,
  CONSTRAINT order_status_idx UNIQUE (order_id, status),
  FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);