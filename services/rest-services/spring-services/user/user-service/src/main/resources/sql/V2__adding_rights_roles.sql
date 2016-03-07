CREATE TABLE ${schema}.rights
(
  id bigint NOT NULL,
  inserteddate timestamp without time zone NOT NULL,
  updateddate timestamp without time zone NOT NULL,
  active boolean NOT NULL,
  name character varying(255) NOT NULL,
  CONSTRAINT rights_pkey PRIMARY KEY (id),
  CONSTRAINT uk_d4sdwh5s4ibfis4quwluf6sl9 UNIQUE (name)
);

CREATE TABLE ${schema}.roles
(
  id bigint NOT NULL,
  inserteddate timestamp without time zone NOT NULL,
  updateddate timestamp without time zone NOT NULL,
  active boolean NOT NULL,
  name character varying(255) NOT NULL,
  CONSTRAINT roles_pkey PRIMARY KEY (id),
  CONSTRAINT uk_e6cf0lv6p03mexusakgr5s134 UNIQUE (name)
);

CREATE TABLE ${schema}.roles_rights
(
  roles_id bigint NOT NULL,
  rights_id bigint NOT NULL,
  CONSTRAINT fk_2r91ne5lnxlkyykxakaecfqvh FOREIGN KEY (rights_id)
      REFERENCES ${schema}.rights (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_qrvg1axhmqtehblbv3ta0jxhv FOREIGN KEY (roles_id)
      REFERENCES ${schema}.roles (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE ${schema}.userentity_roles
(
  userentity_id bigint NOT NULL,
  roles_id bigint NOT NULL,
  CONSTRAINT fk_lp4ory4bu9lu4d5t6brmvq9dx FOREIGN KEY (userentity_id)
      REFERENCES ${schema}.userentity (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_rqfwnmr98f16d6qjre5dtop1b FOREIGN KEY (roles_id)
      REFERENCES ${schema}.roles (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE SEQUENCE ${schema}.sq_rights
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE SEQUENCE ${schema}.sq_role
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;