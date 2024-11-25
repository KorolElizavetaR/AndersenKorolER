CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');

CREATE TABLE "user" (
	user_id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	"name" varchar(50) NOT NULL,
	creation_date timestamp NOT NULL DEFAULT NOW()
)

CREATE TABLE ticket (
	ticket_id char(4) PRIMARY KEY,
	user_id int REFERENCES "user" (user_id),
	"type" ticket_type NOT NULL DEFAULT 'DAY',
	ticket_creation_date timestamp NOT NULL DEFAULT NOW()
)

ALTER TABLE ticket DROP CONSTRAINT ticket_user_id_fkey;

ALTER TABLE ticket ADD CONSTRAINT ticket_user_id_fkey
	FOREIGN KEY (user_id)
	REFERENCES "user" (user_id)
	ON DELETE CASCADE;