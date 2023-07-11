CREATE TABLE pessoa (
   id BIGSERIAL NOT NULL,
   nome VARCHAR(255) NOT NULL,
   departamento_id BIGINT,
   CONSTRAINT pk_pessoa PRIMARY KEY (id)
);

ALTER TABLE pessoa ADD CONSTRAINT FK_PESSOA_ON_DEPARTAMENTO FOREIGN KEY (departamento_id) REFERENCES departamento (id);