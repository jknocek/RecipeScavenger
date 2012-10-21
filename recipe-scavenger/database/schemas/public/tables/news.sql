-- Table: news

-- DROP TABLE news;

CREATE TABLE news
(
  id bigint NOT NULL,
  title character varying(255) NOT NULL,
  "text" text NOT NULL,
  author bigint,
  created_date timestamp without time zone NOT NULL DEFAULT now(),
  author_id bigint,
  CONSTRAINT news_pk PRIMARY KEY (id),
  CONSTRAINT fk338ad34ef2942e FOREIGN KEY (author)
      REFERENCES user_account (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk338ad3fab952f2 FOREIGN KEY (author_id)
      REFERENCES user_account (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT new_user_account_fk FOREIGN KEY (id)
      REFERENCES user_account (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE news OWNER TO rec;
