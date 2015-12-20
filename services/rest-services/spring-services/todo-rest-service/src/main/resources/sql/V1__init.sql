CREATE TABLE ${schema}.todolistentity
(
  todolistid bigint NOT NULL,
  inserteddate timestamp without time zone NOT NULL,
  updateddate timestamp without time zone NOT NULL,
  active boolean NOT NULL,
  todolistname character varying(255) NOT NULL,
  CONSTRAINT todolist_pkey PRIMARY KEY (todolistid),
  CONSTRAINT uk_todolistname_unique UNIQUE (todolistname)
);

CREATE TABLE ${schema}.todoentity
(
  id bigint NOT NULL,
  inserteddate timestamp without time zone NOT NULL,
  updateddate timestamp without time zone NOT NULL,
  active boolean NOT NULL,
  value character varying(255) NOT NULL,
  listEntity_todolistid bigint NOT NULL,
  CONSTRAINT todoitem_pkey PRIMARY KEY (id),
  CONSTRAINT fk_todoitem_todolist FOREIGN KEY (listEntity_todolistid)
      REFERENCES ${schema}.todolistentity (todolistid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



CREATE SEQUENCE ${schema}.sq_todoitem
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE SEQUENCE ${schema}.sq_todolist
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;