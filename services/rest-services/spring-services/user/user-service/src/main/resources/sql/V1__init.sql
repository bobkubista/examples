CREATE TABLE ${schema}.userentity
(
  id bigint NOT NULL,
  inserteddate timestamp with time zone NOT NULL,
  updateddate timestamp with time zone NOT NULL,
  active boolean NOT NULL,
  email character varying(255) NOT NULL,
  encryptedpassword character varying(255),
  name character varying(255),
  CONSTRAINT userentity_pkey PRIMARY KEY (id),
  CONSTRAINT uk_email UNIQUE (email)
);

CREATE SEQUENCE ${schema}.sq_user
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  