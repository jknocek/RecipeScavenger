-- Table: user_account

-- DROP TABLE user_account;

CREATE TABLE user_account
(
  id bigint NOT NULL,
  email character varying(255) NOT NULL,
  "password" character varying(255) NOT NULL,
  "admin" boolean,
  username character varying(36) NOT NULL,
  CONSTRAINT user_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_account OWNER TO rec;
