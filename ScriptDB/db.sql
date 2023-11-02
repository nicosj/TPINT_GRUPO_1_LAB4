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
    fechaNacimiento varchar(10) not null,
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

CREATE TABLE `banco`.`cuenta` (
  `idCliente` int DEFAULT NULL,
  `FechaCreacion` date NOT NULL,
  `TipoCuenta` varchar(2) NOT NULL,
  `CBU` varchar(22) NOT NULL,
  `Saldo` decimal(20,2) NOT NULL,
  `numero_Cuenta` varchar(22) DEFAULT("NULL"),
  PRIMARY KEY (`numero_Cuenta`),
  UNIQUE KEY `CBU` (`CBU`),
  KEY `idCliente` (`idCliente`),
  CONSTRAINT `cuenta_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`)
);
    
insert into `banco`.`cuenta` (`idCliente`, `FechaCreacion`, `TipoCuenta`, `CBU`, `Saldo`) values ('2', '2023-11-01', 'CA', '0000000000123456789012', '9500.50');

CREATE TABLE `banco`.`intereses` (
  `idinteres` INT NOT NULL AUTO_INCREMENT,
  `Cuotas` INT NOT NULL,
  `Porcentaje` FLOAT NOT NULL,
  PRIMARY KEY (`idinteres`));


CREATE TABLE `prestamos` (
  `idprestamo` int NOT NULL AUTO_INCREMENT,
  `numero_Cuenta` varchar(22) NOT NULL,
  `Fecha_Pedido` date NOT NULL,
  `Importe_Cuota` decimal(10,0) NOT NULL,
  `Importe_Total` decimal(10,0) NOT NULL,
  `Cuotas` int NOT NULL,
  `idIntereses` int NOT NULL,
  PRIMARY KEY (`idprestamo`),
  KEY `FK_Intereses_idx` (`idIntereses`),
  KEY `FK_numero_Cuenta_idx` (`numero_Cuenta`),
  CONSTRAINT `FK_Intereses` FOREIGN KEY (`idIntereses`) REFERENCES `intereses` (`idinteres`),
  CONSTRAINT `FK_numero_Cuenta` FOREIGN KEY (`numero_Cuenta`) REFERENCES `cuenta` (`numero_Cuenta`)
);

CREATE TABLE `pago_prestamo` (
  `idpago_prestamo` int NOT NULL AUTO_INCREMENT,
  `numero_Cuenta` varchar(22) NOT NULL,
  `Fecha_Pago` date NOT NULL,
  `Importe_Cuota` decimal(10,0) NOT NULL,
  `Impote_Restante` decimal(10,0) NOT NULL,
  `Cuotas_Restantes` int NOT NULL,
  `idPrestamo` int NOT NULL,
  PRIMARY KEY (`idpago_prestamo`),
  KEY `FK_Prestamo_idx` (`idPrestamo`),
  KEY `FK_numero_Cuenta_Pago_idx` (`numero_Cuenta`),
  CONSTRAINT `FK_numero_Cuenta_Pago` FOREIGN KEY (`numero_Cuenta`) REFERENCES `cuenta` (`numero_Cuenta`),
  CONSTRAINT `FK_Prestamo` FOREIGN KEY (`idPrestamo`) REFERENCES `prestamos` (`idprestamo`)
);

CREATE TABLE `movimiento` (
  `idMovimiento` int NOT NULL AUTO_INCREMENT,
  `numero_Cuenta` varchar(22) not null,
  `Fecha` date NOT NULL,
  `Detalle_Concepto` varchar(50) NOT NULL,
  `Importe` decimal(20,2) NOT NULL,
  `Tipo_Movimiento` varchar(50) NOT NULL,
  PRIMARY KEY (`idMovimiento`),
  FOREIGN KEY (`numero_Cuenta`) REFERENCES `cuenta`(`numero_Cuenta`)
);

