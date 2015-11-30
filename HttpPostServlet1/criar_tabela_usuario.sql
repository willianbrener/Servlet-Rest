CREATE TABLE  usuario   
   (    id serial,   
    nome VARCHAR(20),   
    password VARCHAR(200),   
     CONSTRAINT "pk_usuario" PRIMARY KEY (id)   
   ) ;
   
   ALTER TABLE usuario
  ADD CONSTRAINT "UK_USUARIO_NOME" UNIQUE (nome);