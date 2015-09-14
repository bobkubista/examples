CREATE TABLE ${schema}.todolist
(
  todolistid bigint NOT NULL,
  inserteddate bytea NOT NULL,
  updateddate timestamp without time zone NOT NULL,
  active boolean NOT NULL,
  todolistname character varying(255) NOT NULL,
  CONSTRAINT todolist_pkey PRIMARY KEY (todolistid),
  CONSTRAINT uk_todolistname_unique UNIQUE (todolistname)
);

CREATE TABLE ${schema}.todoitem
(
  id bigint NOT NULL,
  inserteddate bytea NOT NULL,
  updateddate timestamp without time zone NOT NULL,
  active boolean NOT NULL,
  value character varying(255) NOT NULL,
  todolistid bigint NOT NULL,
  CONSTRAINT todoitem_pkey PRIMARY KEY (id),
  CONSTRAINT fk_todoitem_todolist FOREIGN KEY (todolistid)
      REFERENCES ${schema}.todolist (todolistid) MATCH SIMPLE
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