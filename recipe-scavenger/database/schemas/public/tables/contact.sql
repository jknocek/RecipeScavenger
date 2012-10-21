-- Table: contact

-- DROP TABLE contact;

CREATE TABLE contact
(
  id bigint NOT NULL,
  description character varying(255) NOT NULL,
  contact_email character varying(255) NOT NULL,
  user_id bigint,
  CONSTRAINT contact_pkey PRIMARY KEY (id),
  CONSTRAINT fk38b724209a0060b2 FOREIGN KEY (user_id)
      REFERENCES user_account (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE contact OWNER TO rec;
