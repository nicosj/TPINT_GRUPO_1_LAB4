CREATE SCHEMA `banco`;
USE `banco`;
Create TABLE `banco`.`cliente`
(
    idCliente       int auto_increment NOT NULL,
    DNI             varchar(8)  not null,
    CUIL            varchar(11) not null,
    nombre          varchar(50) not null,
    apellido        varchar(50) not null,
    sexo            varchar(1)  not null,
    nacionalidad    varchar(50) not null,
    fechaNacimiento date        not null,
    direccion       varchar(50) not null,
    localidad       varchar(50) not null,
    provincia       varchar(50) not null,
    correo          varchar(50) not null,
    telefono        varchar(50) not null,
    Primary Key (`idCliente`)
);

Create Table `banco`.`usuario`
(
    idUsuario int auto_increment Not NULL,
    usuario   varchar(50) not null,
    clave     varchar(50) not null,
    tipoUsuario int not null,
    idCliente int not null,
    Primary Key (`idUsuario`),
    foreign key (idCliente) references Cliente(idCliente)
);

insert into `banco`.`cliente` (`DNI`, `CUIL`, `nombre`, `apellido`, `sexo`, `nacionalidad`, `fechaNacimiento`, `direccion`, `localidad`, `provincia`, `correo`, `telefono`) values ('12345678', '20345678901', 'admin', 'admin', 'M', 'Argentina', '1990-01-01', 'admin', 'admin', 'admin', 'admin', 'admin');
insert into `banco`.`usuario` (`usuario`, `clave`, `tipoUsuario`, `idCliente`) values ('admin', 'admin', 1, 1);

