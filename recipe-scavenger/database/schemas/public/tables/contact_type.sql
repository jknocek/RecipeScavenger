-- Table: contact_type

-- DROP TABLE contact_type;

CREATE TABLE contact_type
(
  id bigint NOT NULL,
  "type" character varying(255) NOT NULL,
  CONSTRAINT contact_type_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE contact_type OWNER TO rec;
