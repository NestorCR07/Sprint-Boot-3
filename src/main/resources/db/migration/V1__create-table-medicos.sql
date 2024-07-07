create table medicos(

    id bigint not null auto_increment,
    nombre varchar(100)not null,
    email varchar(100)not null,
    documento varchar(6)not null,
    especialidad varchar(100)not null,
    calle varchar(100)not null,
    distrito varchar(100)not null,
    complemento varchar(100)not null,
    numero varchar(20)not null,
    ciudad  varchar(100)not null,

    primary key(id)

);