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
    Primary Key (`idCliente`), 
    UNIQUE (`DNI`),
    UNIQUE(`CUIL`),
    UNIQUE(`correo`)
);

Create Table `banco`.`usuario`
(
    idUsuario int auto_increment Not NULL,
    usuario   varchar(50) not null,
    clave     varchar(50) not null,
    tipoUsuario int not null,
    idCliente int not null,
    Primary Key (`idUsuario`),
    foreign key (idCliente) references Cliente(idCliente),
    UNIQUE(`usuario`)
);

insert into `banco`.`cliente` (`DNI`, `CUIL`, `nombre`, `apellido`, `sexo`, `nacionalidad`, `fechaNacimiento`, `direccion`, `localidad`, `provincia`, `correo`, `telefono`) values ('12345678', '20345678901', 'admin', 'admin', 'M', 'Argentina', '1990-01-01', 'avenida siempre viva', 'caucete', 'san juan', 'admin@admin', '2644400400');
insert into `banco`.`usuario` (`usuario`, `clave`, `tipoUsuario`, `idCliente`) values ('admin', 'admin', 1, 1);
insert into `banco`.`cliente` (`DNI`, `CUIL`, `nombre`, `apellido`, `sexo`, `nacionalidad`, `fechaNacimiento`, `direccion`, `localidad`, `provincia`, `correo`, `telefono`) values ('87654321', '20876543218', 'Ben', 'Tennyson', 'M', 'Argentina', '2005-10-09', 'Av. Cabildo', 'Necochea', 'Buenos Aires', 'Ben10@gmail.com', '0303456');
insert into `banco`.`usuario` (`usuario`, `clave`, `tipoUsuario`, `idCliente`) values ('Ben10', 'omnitrix', 2, 2);

Create Table `banco`.`cuenta`
(
    idCliente int null,
    FechaCreacion date not null,
    TipoCuenta varchar(2) not null,
    CBU varchar (22) not null,
    Saldo decimal(20,2) not null,
Primary Key (`CBU`),
foreign key (idCliente) references Cliente(idCliente),
UNIQUE(`CBU`)
    );
    
insert into `banco`.`cuenta` (`idCliente`, `FechaCreacion`, `TipoCuenta`, `CBU`, `Saldo`) values ('2', '2023-11-01', 'CA', '0000000000123456789012', '9500.50');
