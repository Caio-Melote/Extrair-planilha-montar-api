create table usuarios(

    id bigint not null auto_increment,
    login varchar(100) not null,
    senha varchar(255) not null,
    role varchar(15) not null,
    ativo tinyint not null,

    primary key(id)

);