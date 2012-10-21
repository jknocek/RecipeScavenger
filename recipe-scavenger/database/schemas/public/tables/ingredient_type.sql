-- Table: ingredient_type

-- DROP TABLE ingredient_type;

CREATE TABLE ingredient_type
(
  id bigint NOT NULL,
  "name" text NOT NULL,
  base_uom character varying(25) NOT NULL,
  CONSTRAINT ingredient_type_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ingredient_type OWNER TO rec;
