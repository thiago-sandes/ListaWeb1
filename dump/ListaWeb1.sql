CREATE DATABASE  IF NOT EXISTS `listaweb1` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `listaweb1`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: listaweb1
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alunos`
--

DROP TABLE IF EXISTS `alunos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alunos` (
  `cod_disciplina` varchar(8) DEFAULT NULL,
  `cod_turma` varchar(3) DEFAULT NULL,
  `nome_aluno` varchar(40) DEFAULT NULL,
  `curso` int(3) DEFAULT NULL,
  `matricula_aluno` varchar(12) NOT NULL,
  PRIMARY KEY (`matricula_aluno`),
  KEY `cod_disciplina_idx` (`cod_disciplina`),
  KEY `cod_turma_idx` (`cod_turma`),
  KEY `cod_curso_idx` (`curso`),
  CONSTRAINT `cod_curso` FOREIGN KEY (`curso`) REFERENCES `cursos` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cod_turma` FOREIGN KEY (`cod_turma`) REFERENCES `turmas` (`cod_turma`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `codfk_disciplina` FOREIGN KEY (`cod_disciplina`) REFERENCES `disciplinas` (`cod_disciplina`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alunos`
--

LOCK TABLES `alunos` WRITE;
/*!40000 ALTER TABLE `alunos` DISABLE KEYS */;
INSERT INTO `alunos` VALUES ('COMP0200','T01','Thiago José Sandes Melo',170,'201600092557'),('COMP0210','T02','José Unias Alves de Melo',171,'201600092558'),('COMP0230','T03','Vinicius de Sandes Melo',172,'201600092559'),('MAT00200','T04','Rosenete da Silva Sandes Melo',170,'201600092560'),('COMP0200','T01','Zacarias',171,'201600092561'),('COMP0200','T01','Simão',171,'201600092562'),('COMP0200','T01','Pedro',171,'201600092563'),('COMP0200','T01','Zé',172,'201600092564'),('COMP0200','T01','Toni',170,'201600092565'),('COMP0210','T02','João',170,'201600092566'),('COMP0210','T02','Maria',170,'201600092567');
/*!40000 ALTER TABLE `alunos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursos` (
  `codigo` int(3) NOT NULL,
  `nome_curso` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos`
--

LOCK TABLES `cursos` WRITE;
/*!40000 ALTER TABLE `cursos` DISABLE KEYS */;
INSERT INTO `cursos` VALUES (170,'Ciência da Comput.'),(171,'Engenharia da Comp.'),(172,'Sistemas de Inform.');
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disciplinas`
--

DROP TABLE IF EXISTS `disciplinas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disciplinas` (
  `cod_disciplina` varchar(8) NOT NULL,
  `nome_disciplina` varchar(20) DEFAULT NULL,
  `carga_horaria` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`cod_disciplina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disciplinas`
--

LOCK TABLES `disciplinas` WRITE;
/*!40000 ALTER TABLE `disciplinas` DISABLE KEYS */;
INSERT INTO `disciplinas` VALUES ('COMP0200','Programação Web','2'),('COMP0210','Tópicos especiais','4'),('COMP0230','Programação paralela','6'),('MAT00200','Cálculo 1','6');
/*!40000 ALTER TABLE `disciplinas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turmas`
--

DROP TABLE IF EXISTS `turmas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turmas` (
  `cod_disciplina` varchar(8) DEFAULT NULL,
  `cod_turma` varchar(3) NOT NULL,
  `diahorario1_turma` varchar(6) DEFAULT NULL,
  `diahorario2_turma` varchar(6) DEFAULT NULL,
  `diahorario3_turma` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`cod_turma`),
  KEY `cod_disciplina_idx` (`cod_disciplina`),
  CONSTRAINT `cod_disciplina` FOREIGN KEY (`cod_disciplina`) REFERENCES `disciplinas` (`cod_disciplina`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turmas`
--

LOCK TABLES `turmas` WRITE;
/*!40000 ALTER TABLE `turmas` DISABLE KEYS */;
INSERT INTO `turmas` VALUES ('COMP0200','T01','6T56','6T34','6T12'),('COMP0210','T02','26N34','26N12','26T34'),('COMP0230','T03','256T12','256M12','256N12'),('MAT00200','T04','256T34','256N34','256M34');
/*!40000 ALTER TABLE `turmas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-03  1:07:42
