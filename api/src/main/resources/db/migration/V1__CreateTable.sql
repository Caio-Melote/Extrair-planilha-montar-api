create table segmentos(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    primary key(id)
    );

create table franquias(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    totalUnidades bigint not null,
    estadoSede varchar(50) not null,
    email varchar(100) not null,
    investimentoInicial DECIMAL(17, 2) not null,
    subsegmento varchar(100) not null,
    tipoNegocio varchar(100) not null,
    ultimaAtualizacao datetime not null,
    url varchar(100) not null,
    ativo tinyint not null,
    segmentoId bigint,
    primary key (id),
    foreign key (segmentoId) references segmentos(id)
);

