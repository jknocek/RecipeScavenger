-- Table: contact_type_selection

-- DROP TABLE contact_type_selection;

CREATE TABLE contact_type_selection
(
  id bigint NOT NULL,
  contact bigint NOT NULL,
  contact_type bigint NOT NULL,
  CONSTRAINT contact_type_selection_pkey PRIMARY KEY (id),
  CONSTRAINT fke19e40c617b1a707 FOREIGN KEY (contact)
      REFERENCES contact (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fke19e40c652f472ba FOREIGN KEY (contact_type)
      REFERENCES contact_type (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE contact_type_selection OWNER TO rec;
