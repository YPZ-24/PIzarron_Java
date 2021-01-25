SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname='pizarron';
DROP DATABASE IF EXISTS "pizarron";
CREATE DATABASE "pizarron";
\c pizarron
CREATE TABLE diagrama (id_diagrama SERIAL NOT NULL, id_usuario int4 NOT NULL, fh_creacion timestamp NOT NULL, fh_modificacion timestamp NOT NULL, PRIMARY KEY (id_diagrama));
CREATE TABLE figura (
    id_figura SERIAL NOT NULL, 
    id_diagrama int4 NOT NULL, 
    tx_json text NOT NULL,
    tool int4 NOT NULL, 
    PRIMARY KEY (id_figura)
    );
CREATE TABLE usuario (id_usuario SERIAL NOT NULL, tx_nombre varchar(100) NOT NULL, tx_primer_apellido varchar(100) NOT NULL, tx_segundo_apellido varchar(100), tx_login varchar(255) NOT NULL UNIQUE, tx_password varchar(128) NOT NULL, PRIMARY KEY (id_usuario));
ALTER TABLE diagrama ADD CONSTRAINT FKdiagrama769056 FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario);
ALTER TABLE figura ADD CONSTRAINT FKfigura393873 FOREIGN KEY (id_diagrama) REFERENCES diagrama (id_diagrama);
