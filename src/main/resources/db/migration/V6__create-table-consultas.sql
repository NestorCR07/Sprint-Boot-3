create table consultas(
    id bigint not null auto_increment,
    medico_id bigint not null,
    paciente_id bigint not null,
    data datetime not null,

    primary key(id),

    CONSTRAINT fk_consultas_medico_id FOREIGN key(medico_id) REFERENCES medicos(id),
    CONSTRAINT fk_consultas_paciente_id FOREIGN key(paciente_id) REFERENCES pacientes(id)

);