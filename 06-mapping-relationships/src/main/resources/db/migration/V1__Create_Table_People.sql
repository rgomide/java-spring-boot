CREATE TABLE IF NOT EXISTS people (
  id bigint PRIMARY KEY,
  first_name VARCHAR(80) NOT NULL,
  last_name VARCHAR(80) NOT NULL,
  address VARCHAR(100),
  gender VARCHAR(6)
);  