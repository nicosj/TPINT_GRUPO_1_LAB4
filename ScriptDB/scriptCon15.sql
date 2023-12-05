-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: banco2
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `banco2`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `banco2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `banco2`;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `idCliente` int NOT NULL AUTO_INCREMENT,
  `DNI` varchar(8) NOT NULL,
  `CUIL` varchar(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `sexo` varchar(1) NOT NULL,
  `nacionalidad` varchar(50) NOT NULL,
  `fechaNacimiento` varchar(10) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `localidad` varchar(50) NOT NULL,
  `provincia` varchar(50) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `telefono` varchar(50) NOT NULL,
  `estado` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`idCliente`),
  UNIQUE KEY `DNI` (`DNI`),
  UNIQUE KEY `CUIL` (`CUIL`),
  UNIQUE KEY `correo` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (31,'11111110','20111111110','Carlitos','Gardel','M','Argentino','1890-12-11','Calle Tango 123','Buenos Aires','Buenos Aires','carlitos@gmail.com','123456789',1),(32,'22222220','30222222220','Mafalda','Quino','F','Argentina','1964-09-29','Calle San Telmo 456','Buenos Aires','Buenos Aires','mafalda@gmail.com','987654321',1),(33,'33333330','40333333330','Zamba','Mateyko','M','Argentino','1952-03-01','Avenida Folklore 789','Salta','Salta','zamba@gmail.com','111222333',1),(34,'44444440','50444444440','Doña','Flor','F','Argentina','1948-07-20','Calle Jardín 101','Rosario','Santa Fe','flor@gmail.com','444555666',1),(35,'55555550','60555555550','Patoruzú','Patoruzito','M','Argentino','1928-05-10','Calle Tehuelche 202','Viedma','Río Negro','patoruzu@gmail.com','999888777',1),(36,'66666660','70666666660','Clemente','López','M','Argentino','1973-11-20','Calle Humor 303','Tandil','Buenos Aires','clemente@gmail.com','123123123',1),(37,'77777770','80777777770','Renata','Fabián','F','Argentina','1985-12-11','Avenida Rock 404','La Plata','Buenos Aires','renata@gmail.com','555444333',1),(38,'88888880','90888888880','Fito','Páez','M','Argentino','1963-03-13','Calle Música 505','Rosario','Santa Fe','fito@gmail.com','777888999',1),(39,'99999990','10999999990','Merlina','Addams','F','Argentina','1989-06-06','Mansión Siniestra','Cementerio Gómez','Buenos Aires','merlina@gmail.com','456789012',1),(40,'10101010','11101010100','Capitán','Beto','M','Argentino','1975-02-15','Calle Aventura 606','Mar del Plata','Buenos Aires','capitanbeto@gmail.com','101112131',1),(41,'11111111','12111111110','Mónica','Arriaga','F','Argentina','1982-08-25','Calle Cómics 707','Córdoba','Córdoba','monica@gmail.com','131415161',1),(42,'12121212','13121212120','Don','Segundo Sombra','M','Argentino','1926-09-01','Estancia 808','San Antonio de Areco','Buenos Aires','donsegundo@gmail.com','161718192',1),(43,'13131313','14131313130','Gardelito','Garca','M','Argentino','1890-06-11','Calle Tango 909','Buenos Aires','Buenos Aires','gardelito@gmail.com','919293949',1),(44,'14141414','15141414140','Isidoro','Cañones','M','Argentino','1969-04-17','Calle Humor 1010','San Carlos de Bolívar','Buenos Aires','isidoro@gmail.com','949596979',1),(45,'15151515','16151515150','Atahualpa','Yupanqui','M','Argentino','1908-01-31','Calle Guitarra 1111','Pergamino','Buenos Aires','atahualpa@gmail.com','999000111',1),(46,'66666666','66666666666','Glenn','Danzig','M','Argentino','1708-01-31','Hell 666','Hell','Buenos Aires','GD666@gmail.com','666999666',1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta` (
  `idCliente` int DEFAULT NULL,
  `FechaCreacion` date NOT NULL,
  `TipoCuenta` varchar(2) NOT NULL,
  `CBU` varchar(22) NOT NULL,
  `Saldo` decimal(20,2) NOT NULL,
  `numero_Cuenta` int NOT NULL AUTO_INCREMENT,
  `estado` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`numero_Cuenta`),
  UNIQUE KEY `CBU` (`CBU`),
  KEY `idCliente` (`idCliente`),
  CONSTRAINT `FK_cliente_cuenta` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=67953783 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta`
--

LOCK TABLES `cuenta` WRITE;
/*!40000 ALTER TABLE `cuenta` DISABLE KEYS */;
INSERT INTO `cuenta` VALUES (31,'2023-01-01','CA','1234567890123456789012',1000.00,1,1),(32,'2023-01-02','CC','2345678901234567890123',500.50,2,1),(33,'2023-01-03','CA','3456789012345678901234',1500.75,3,1),(34,'2023-01-04','CC','4567890123456789012345',200.25,4,1),(35,'2023-01-05','CA','5678901234567890123456',3000.00,5,1),(36,'2023-01-06','CC','6789012345678901234567',800.90,6,1),(37,'2023-01-07','CA','7890123456789012345678',1200.30,7,1),(38,'2023-01-08','CC','8901234567890123456789',50.75,8,1),(39,'2023-01-09','CA','9012345678901234567890',400.50,9,1),(40,'2023-01-10','CC','0123456789012345678901',600.25,10,1);
/*!40000 ALTER TABLE `cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimiento`
--

DROP TABLE IF EXISTS `movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimiento` (
  `idMovimiento` int NOT NULL AUTO_INCREMENT,
  `numero_Cuenta` int NOT NULL,
  `fecha` text NOT NULL,
  `Detalle_Concepto` varchar(50) NOT NULL,
  `Importe` decimal(20,2) NOT NULL,
  `Tipo_Movimiento` varchar(50) NOT NULL,
  PRIMARY KEY (`idMovimiento`),
  KEY `numero_Cuenta` (`numero_Cuenta`),
  CONSTRAINT `FK_cuenta_movimiento` FOREIGN KEY (`numero_Cuenta`) REFERENCES `cuenta` (`numero_Cuenta`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento`
--

LOCK TABLES `movimiento` WRITE;
/*!40000 ALTER TABLE `movimiento` DISABLE KEYS */;
INSERT INTO `movimiento` VALUES (6,1,'2023-01-15','Depósito Inicial',1000.00,'Crédito'),(7,1,'2023-01-20','Compra en Tienda XYZ',50.00,'Débito'),(8,1,'2023-01-25','Transferencia a Cuenta 2',200.00,'Débito'),(9,2,'2023-01-18','Depósito Inicial',500.50,'Crédito'),(10,2,'2023-01-22','Compra en Tienda ABC',20.50,'Débito'),(11,2,'2023-01-28','Transferencia a Cuenta 1',100.00,'Débito');
/*!40000 ALTER TABLE `movimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamos`
--

DROP TABLE IF EXISTS `prestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamos` (
  `idprestamo` int NOT NULL AUTO_INCREMENT,
  `numero_Cuenta` int NOT NULL,
  `Fecha_Pedido` date NOT NULL,
  `Importe_Cuota` decimal(20,2) NOT NULL,
  `Importe_Total` decimal(20,2) NOT NULL,
  `Cuotas` int NOT NULL,
  `estado` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`idprestamo`),
  KEY `FK_numero_Cuenta_idx` (`numero_Cuenta`),
  CONSTRAINT `FK_cuenta_prestamo` FOREIGN KEY (`numero_Cuenta`) REFERENCES `cuenta` (`numero_Cuenta`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamos`
--

LOCK TABLES `prestamos` WRITE;
/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
INSERT INTO `prestamos` VALUES (25,1,'2023-02-01',150.00,1000.00,6,1),(26,2,'2023-02-02',75.00,500.00,8,1),(27,3,'2023-02-03',200.00,1200.00,10,0),(28,4,'2023-02-04',25.00,200.00,12,0),(29,5,'2023-02-05',250.00,1500.00,8,0),(30,6,'2023-02-06',100.00,800.00,6,0),(31,7,'2023-02-07',150.00,1000.00,10,0),(32,8,'2023-02-08',10.00,50.00,5,0),(33,9,'2023-02-09',50.00,400.00,8,0),(34,10,'2023-02-10',75.00,600.00,6,0);
/*!40000 ALTER TABLE `prestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago_prestamo`
--

DROP TABLE IF EXISTS `pago_prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pago_prestamo` (
  `idpago_prestamo` int NOT NULL AUTO_INCREMENT,
  `numero_Cuenta` int NOT NULL,
  `Fecha_Pago` text NOT NULL,
  `Importe_Cuota` decimal(20,2) NOT NULL,
  `Impote_Restante` decimal(20,2) NOT NULL,
  `Cuotas_Restantes` int NOT NULL,
  `idPrestamo` int NOT NULL,
  PRIMARY KEY (`idpago_prestamo`),
  KEY `FK_Prestamo_idx` (`idPrestamo`),
  KEY `FK_numero_Cuenta_Pago_idx` (`numero_Cuenta`),
  CONSTRAINT `FK_cuenta_pago_prestamo` FOREIGN KEY (`numero_Cuenta`) REFERENCES `cuenta` (`numero_Cuenta`) ON DELETE CASCADE,
  CONSTRAINT `FK_prestamo_pago_prestamo` FOREIGN KEY (`idPrestamo`) REFERENCES `prestamos` (`idprestamo`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago_prestamo`
--

LOCK TABLES `pago_prestamo` WRITE;
/*!40000 ALTER TABLE `pago_prestamo` DISABLE KEYS */;
INSERT INTO `pago_prestamo` VALUES (7,1,'2023-03-01',150.00,850.00,5,25),(8,2,'2023-03-02',75.00,425.00,7,34);
/*!40000 ALTER TABLE `pago_prestamo` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(50) NOT NULL,
  `clave` varchar(50) NOT NULL,
  `tipoUsuario` int NOT NULL,
  `idCliente` int NOT NULL,
  `estado` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `usuario` (`usuario`),
  KEY `idCliente` (`idCliente`),
  CONSTRAINT `FK_cliente_usuario` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (44,'carlitos_user','password123',2,31,1),(45,'mafalda_user','password456',2,32,1),(46,'zamba_user','password789',2,33,1),(47,'flor_user','passwordabc',2,34,1),(48,'patoruzu_user','passworddef',2,35,1),(49,'clemente_user','passwordghi',2,36,1),(50,'renata_user','passwordjkl',2,37,1),(51,'fito_user','passwordmno',2,38,1),(52,'merlina_user','passwordpqr',2,39,1),(53,'capitanbeto_user','passwordstu',2,40,1),(54,'monica_user','passwordvwx',2,41,1),(55,'donsegundo_user','passwordyz',2,42,1),(56,'gardelito_user','password12345',2,43,1),(57,'isidoro_user','password67890',2,44,1),(58,'atahualpa_user','passwordabcde',2,45,1),(59,'admin','admin',1,46,1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-02  8:24:52
