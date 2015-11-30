ALTER TABLE amigo
  ADD COLUMN username character varying(20);
ALTER TABLE amigo
  ADD COLUMN password character varying(200);
ALTER TABLE amigo
  ADD CONSTRAINT "UK_AMIGO_USERNAME" UNIQUE (username);