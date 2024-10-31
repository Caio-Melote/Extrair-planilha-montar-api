create table segmentos(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    primary key(id)
    );

create table franquias(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    total_unidades bigint not null,
    estado_sede varchar(50) not null,
    email varchar(100) not null,
    investimento_inicial DECIMAL(17, 2) not null,
    subsegmento varchar(100) not null,
    tipo_negocio varchar(200) not null,
    ultima_atualizacao datetime not null,
    url varchar(1000) not null,
    ativo tinyint not null,
    segmento_id bigint,
    primary key (id),
    foreign key (segmento_id) references segmentos(id)
);

