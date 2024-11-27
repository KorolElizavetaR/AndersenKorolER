CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');

CREATE TABLE users (
	"id" int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	"name" varchar(50) NOT NULL,
	creation_date timestamp NOT NULL DEFAULT NOW()
);

CREATE TABLE ticket (
	"id" int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	user_id int REFERENCES users ("id") ON DELETE CASCADE,
	"type" ticket_type NOT NULL DEFAULT 'DAY',
	ticket_creation_date timestamp NOT NULL DEFAULT NOW()
);

INSERT INTO users ("name") VALUES ('AAA'), ('BBB');
INSERT INTO ticket (user_id) VALUES (1), (2);
INSERT INTO ticket (user_id, "type") VALUES (1, 'MONTH'), (1, 'YEAR');
INSERT INTO ticket ("type") VALUES ('WEEK'), ('MONTH');