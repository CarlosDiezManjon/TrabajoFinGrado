-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 16-02-2019 a las 23:13:29
-- Versión del servidor: 5.7.23
-- Versión de PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdeasyski`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calendario`
--

DROP TABLE IF EXISTS `calendario`;
CREATE TABLE IF NOT EXISTS `calendario` (
  `profesor_ID` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `disponible` bit(1) NOT NULL,
  PRIMARY KEY (`profesor_ID`,`fecha`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clases`
--

DROP TABLE IF EXISTS `clases`;
CREATE TABLE IF NOT EXISTS `clases` (
  `clase_ID` int(11) NOT NULL AUTO_INCREMENT,
  `profesor_ID` int(11) NOT NULL,
  `nombre_alumno` varchar(20) NOT NULL,
  `horaIni` int(5) NOT NULL,
  `horaFin` int(5) NOT NULL,
  `tlfno_alumno` varchar(10) NOT NULL,
  `tipo_clase` varchar(20) NOT NULL,
  `modalidad_ID` int(10) NOT NULL,
  `zona_clase` varchar(10) NOT NULL,
  `numero_alumnos` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `estado` varchar(10) NOT NULL,
  PRIMARY KEY (`clase_ID`),
  UNIQUE KEY `Unicidad_clase` (`clase_ID`,`profesor_ID`,`fecha`,`horaIni`),
  KEY `profesor_ID` (`profesor_ID`),
  KEY `clase_mod` (`modalidad_ID`),
  KEY `clase_tarifa` (`tipo_clase`,`numero_alumnos`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

DROP TABLE IF EXISTS `facturas`;
CREATE TABLE IF NOT EXISTS `facturas` (
  `factura_ID` int(11) NOT NULL AUTO_INCREMENT,
  `clase_ID` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `importe_persona` double NOT NULL,
  `importe_total` double NOT NULL,
  PRIMARY KEY (`factura_ID`),
  UNIQUE KEY `clase_ID` (`clase_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modalidades`
--

DROP TABLE IF EXISTS `modalidades`;
CREATE TABLE IF NOT EXISTS `modalidades` (
  `modalidad_ID` int(11) NOT NULL AUTO_INCREMENT,
  `modalidad` varchar(15) NOT NULL,
  PRIMARY KEY (`modalidad_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `modalidades`
--

INSERT INTO `modalidades` (`modalidad_ID`, `modalidad`) VALUES
(1, 'Esqui'),
(2, 'Snowboard');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesores`
--

DROP TABLE IF EXISTS `profesores`;
CREATE TABLE IF NOT EXISTS `profesores` (
  `profesor_ID` int(11) NOT NULL AUTO_INCREMENT,
  `dni` varchar(15) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellido1` varchar(20) NOT NULL,
  `apellido2` varchar(20) NOT NULL,
  `fechaReg` date NOT NULL,
  `fechaBaja` int(11) DEFAULT NULL,
  PRIMARY KEY (`profesor_ID`),
  UNIQUE KEY `nif` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prof_modalidad`
--

DROP TABLE IF EXISTS `prof_modalidad`;
CREATE TABLE IF NOT EXISTS `prof_modalidad` (
  `profesor_ID` int(11) NOT NULL,
  `modalidad_ID` int(11) NOT NULL,
  `fecha_alta` date NOT NULL,
  `fecha_baja` date DEFAULT NULL,
  PRIMARY KEY (`profesor_ID`,`modalidad_ID`),
  KEY `mod_mod` (`modalidad_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarifas`
--

DROP TABLE IF EXISTS `tarifas`;
CREATE TABLE IF NOT EXISTS `tarifas` (
  `tipo_clase` varchar(20) NOT NULL,
  `num_alumnos` int(11) NOT NULL,
  `precio_persona` double DEFAULT NULL,
  `precio_total` double NOT NULL,
  PRIMARY KEY (`tipo_clase`,`num_alumnos`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tarifas`
--

INSERT INTO `tarifas` (`tipo_clase`, `num_alumnos`, `precio_persona`, `precio_total`) VALUES
('Grupal', 5, 10, 50),
('Grupal', 6, 10, 60),
('Grupal', 7, 10, 70),
('Grupal', 8, 10, 80),
('Particular', 1, NULL, 35),
('Particular', 2, NULL, 38),
('Particular', 3, NULL, 40),
('Particular', 4, NULL, 40);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `usuario` varchar(20) NOT NULL,
  `contrasena` varchar(20) NOT NULL,
  `rol` varchar(10) DEFAULT NULL,
  `profesor_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`usuario`),
  KEY `fk_usuarios_profesores1_idx` (`profesor_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`usuario`, `contrasena`, `rol`, `profesor_ID`) VALUES
('admin', 'admin', 'admin', NULL);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `calendario`
--
ALTER TABLE `calendario`
  ADD CONSTRAINT `calendario_prof` FOREIGN KEY (`profesor_ID`) REFERENCES `profesores` (`profesor_ID`);

--
-- Filtros para la tabla `clases`
--
ALTER TABLE `clases`
  ADD CONSTRAINT `clase_mod` FOREIGN KEY (`modalidad_ID`) REFERENCES `modalidades` (`modalidad_ID`),
  ADD CONSTRAINT `clase_profesor` FOREIGN KEY (`profesor_ID`) REFERENCES `profesores` (`profesor_ID`),
  ADD CONSTRAINT `clase_tarifa` FOREIGN KEY (`tipo_clase`,`numero_alumnos`) REFERENCES `tarifas` (`tipo_clase`, `num_alumnos`);

--
-- Filtros para la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD CONSTRAINT `factura_clase` FOREIGN KEY (`clase_ID`) REFERENCES `clases` (`clase_ID`);

--
-- Filtros para la tabla `prof_modalidad`
--
ALTER TABLE `prof_modalidad`
  ADD CONSTRAINT `mod_mod` FOREIGN KEY (`modalidad_ID`) REFERENCES `modalidades` (`modalidad_ID`),
  ADD CONSTRAINT `prof_mod` FOREIGN KEY (`profesor_ID`) REFERENCES `profesores` (`profesor_ID`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuario_profesor` FOREIGN KEY (`profesor_ID`) REFERENCES `profesores` (`profesor_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
