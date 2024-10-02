CREATE TABLE IF NOT EXISTS emails (
  id serial PRIMARY KEY,
  person_id int NOT NULL,
  email VARCHAR(250) NOT NULL,
  FOREIGN KEY (person_id) REFERENCES people(id)
);  