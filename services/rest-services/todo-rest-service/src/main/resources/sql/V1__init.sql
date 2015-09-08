CREATE TABLE ${schema}.todolist
(
  todolistid bigint NOT NULL,
  inserteddate bytea NOT NULL,
  updateddate timestamp without time zone NOT NULL,
  active boolean NOT NULL,
  todolistname character varying(255) NOT NULL,
  CONSTRAINT todolist_pkey PRIMARY KEY (todolistid),
  CONSTRAINT uk_2wakc6hk6xoaood535al2xw23 UNIQUE (todolistname)
);

CREATE TABLE ${schema}.todoitem
(
  id bigint NOT NULL,
  inserteddate bytea NOT NULL,
  updateddate timestamp without time zone NOT NULL,
  active boolean NOT NULL,
  value character varying(255) NOT NULL,
  todolistentity_todolistid bigint NOT NULL,
  todoitems_id bigint NOT NULL,
  CONSTRAINT todoitem_pkey PRIMARY KEY (id),
  CONSTRAINT fk_6j30r0vqcb1vqgbw306e0g1ka FOREIGN KEY (todoitems_id)
      REFERENCES ${schema}.todoitem (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_c3hlt3bqlr5ehhn9u922pyyd5 FOREIGN KEY (todolistentity_todolistid)
      REFERENCES ${schema}.todolist (todolistid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_6j30r0vqcb1vqgbw306e0g1ka UNIQUE (todoitems_id)
);

CREATE SEQUENCE ${schema}.sq_todolist
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE SEQUENCE ${schema}.sq_todoitem
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
