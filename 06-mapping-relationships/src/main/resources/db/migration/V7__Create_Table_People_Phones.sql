CREATE TABLE IF NOT EXISTS people_phones (
  person_id BIGINT NOT NULL,
  phone_id BIGINT NOT NULL,
  "primary" BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (person_id, phone_id),
  FOREIGN KEY (person_id) REFERENCES people(id),
  FOREIGN KEY (phone_id) REFERENCES phones(id)
);