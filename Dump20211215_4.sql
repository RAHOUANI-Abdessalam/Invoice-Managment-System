-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: gestion_stock2
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `numeroClient` int NOT NULL AUTO_INCREMENT,
  `raisonSociale` varchar(254) DEFAULT NULL,
  `adresse` varchar(254) DEFAULT NULL,
  `matriculeFiscale` varchar(254) DEFAULT NULL,
  `nArticle` varchar(254) DEFAULT NULL,
  `registreDeCommerce` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`numeroClient`),
  UNIQUE KEY `CLIENT_PK` (`numeroClient`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'abdessalam rahouani','Tiaret','145875454','1245798','21154589'),(2,'Blaha farouk','Tiaret','215654898','59419','9785'),(4,'karima','frenda','45985','624556','65265'),(5,'oihi','tireifd','ojn','pijpoi','piojpj'),(7,'orrp','dpdp','pdpdsp','pfpdp','pdpd'),(8,'ooooo','ooooo','ooooo','ooooo','ooooo'),(11,'haidari','frenda','132658943','159753','14/00-211689a99'),(12,'uuuuu','mmmm','15478855','1548877','145687'),(15,'lolo','tiaret','1960654789','11555555','14/00-21121211');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facture`
--

DROP TABLE IF EXISTS `facture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facture` (
  `numeroFacture` int NOT NULL AUTO_INCREMENT,
  `numeroClient` int DEFAULT NULL,
  `date` varchar(254) DEFAULT NULL,
  `modeDeReglement` varchar(254) DEFAULT NULL,
  `numeroCheaque` varchar(50) DEFAULT NULL,
  `totalHT` varchar(254) DEFAULT NULL,
  `totalTVA` varchar(254) DEFAULT NULL,
  `totalTTC` varchar(45) DEFAULT NULL,
  `remise` varchar(45) DEFAULT NULL,
  `montantTotale` varchar(254) DEFAULT NULL,
  `totaleEnLettres` varchar(254) DEFAULT NULL,
  `tva` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`numeroFacture`),
  UNIQUE KEY `FACTURE_PK` (`numeroFacture`),
  KEY `FK_regleFacture` (`numeroClient`),
  CONSTRAINT `FK_regleFacture` FOREIGN KEY (`numeroClient`) REFERENCES `client` (`numeroClient`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facture`
--

LOCK TABLES `facture` WRITE;
/*!40000 ALTER TABLE `facture` DISABLE KEYS */;
INSERT INTO `facture` VALUES (1,1,'11/11/2021','chèque','1215487515','2154','100','215987','20','19549','odjncojznezknec','19%'),(2,1,'12/03/2020','espèces',NULL,'21584','2842','85569','88','8458','yivugv','0%'),(3,2,'09/04/2021','chèque','1548879897','25454','5984','598','55','5854','efrtdisj','19%'),(4,4,'15/05/2021','espèces','','88255','8468','548','40','5889','uriozoeju','0%'),(5,2,'18/06/2021','espèces','','52165','895','5871','20','5589','urydsts','0%'),(6,4,'09-12-2021','Chèque','123456','3552103.00','184210.32','3736313.32','40','2241787.99','deux million deux cent quarante et un mille sept cent quatre-vingt-sept Dinar quatre-vingt-dix-neuf','19%'),(7,5,'10-12-2021','Espèces','','291500.00','52250.00','343750.00','20','275000.00','deux cent soixante-quinze milles Dinar','0%'),(8,4,'10-12-2021','Espèces','','3428808.00','167545.80','3596353.80','','','','0%'),(9,5,'10-12-2021','Espèces','','','','','','','','0%'),(10,11,'10-12-2021','Chèque','437581','196500.00','35340.00','231840.00','0','231840.00','deux cent trente et un mille huit cent quarante Dinar','19%'),(11,1,'10-12-2021','Chèque','1958785421','88500.00','13490.00','101990.00','10','91791.00','quatre-vingt-onze mille sept cent quatre-vingt-onze Dinar','19%'),(12,2,'10-12-2021','Espèces','','26500.00','3990.00','30490.00','0','30490.00','trente mille quatre cent quatre-vingt-dix Dinar','0%'),(13,1,'10-12-2021','Espèces','','17000.00','2850.00','19850.00','0','19850.00','dix-neuf mille huit cent cinquante Dinar','0%'),(14,5,'10-12-2021','Espèces','','9500.00','1140.00','10640.00','0','10640.00','dix mille six cent quarante Dinar','0%'),(15,7,'10-12-2021','Espèces','','9500.00','1140.00','10640.00','0','10640.00','dix mille six cent quarante Dinar','0%'),(16,8,'10-12-2021','Espèces','','9500.00','1140.00','10640.00','0','10640.00','dix mille six cent quarante Dinar','0%'),(17,5,'10-12-2021','Espèces','','9500.00','1140.00','10640.00','0','10640.00','dix mille six cent quarante Dinar','0%'),(18,1,'13/12/2021','Chèque','195485745478 79','2105501.00','375105.60','2480606.60','0','2480606.60','deux million quatre cent quatre-vingts mille six cent six Dinar soixante','19%'),(19,1,'13/12/2021','Espèces','','2783195.00','491205.86','3274400.86','0','3274400.86','trois million deux cent soixante-quatorze mille quatre cents Dinar huit cent soixante','0%'),(20,1,'14/12/2021','Chèque','125478 12','751500.00','135850.00','887350.00','015','754247.50','sept cent cinquante-quatre mille deux cent quarante-sept Dinar cinquante','19%'),(21,15,'14/12/2021','Chèque','1251468','885500.00','160550.00','1046050.00','0','1046050.00','un million quarante-six mille cinquante Dinar','19%');
/*!40000 ALTER TABLE `facture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produit`
--

DROP TABLE IF EXISTS `produit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produit` (
  `codeProduit` int NOT NULL,
  `designation` varchar(254) DEFAULT NULL,
  `prixTransport` varchar(254) DEFAULT NULL,
  `prixUnitaire` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`codeProduit`),
  UNIQUE KEY `codeProduit_UNIQUE` (`codeProduit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produit`
--

LOCK TABLES `produit` WRITE;
/*!40000 ALTER TABLE `produit` DISABLE KEYS */;
INSERT INTO `produit` VALUES (1,'beton dose a 150','3500.00','6000'),(2,'fenetre','2000.00','15000.00'),(3,'portail','12000.00','50000.00'),(4,'otjls','21587.00','25680.00'),(5,'45847','4444','4444'),(6,'6666','6666','6666'),(7,'7777','7777','7777');
/*!40000 ALTER TABLE `produit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quantite`
--

DROP TABLE IF EXISTS `quantite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quantite` (
  `numeroFacture` int NOT NULL,
  `codeProduit` int NOT NULL,
  `qteProduit` varchar(50) DEFAULT NULL,
  `totalP` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`numeroFacture`,`codeProduit`),
  KEY `FK_quantiteP` (`codeProduit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quantite`
--

LOCK TABLES `quantite` WRITE;
/*!40000 ALTER TABLE `quantite` DISABLE KEYS */;
INSERT INTO `quantite` VALUES (1,1,'50','15485'),(1,2,'45','56698'),(1,3,'100','6988'),(2,2,'60','97985'),(2,4,'150','98789'),(2,5,'20','95659'),(2,6,'80','87959'),(3,1,'120','54598'),(4,2,'50','8487'),(4,4,'20','95847'),(5,1,'20','94848'),(5,3,'50','6458478'),(5,4,'70','969458'),(5,5,'80','8174855'),(6,2,'4','95847'),(6,3,'6','178184'),(6,4,'10','94584'),(6,5,'6','88474'),(7,1,'1','14400.0'),(7,2,'1','19850.0'),(7,3,'5','309500.0'),(10,1,'10','74900.0'),(11,1,'1','10640.0'),(11,2,'1','19850.0'),(11,3,'1','71500.0'),(12,1,'1','10640.0'),(12,2,'1','19850.0'),(13,2,'1','19850.0'),(14,1,'1','10640.0'),(15,1,'1','10640.0'),(16,1,'1','10640.0'),(17,1,'1','10640.0'),(18,2,'1','19850.0'),(19,1,'1','10640.0'),(19,2,'10','182500.0'),(19,3,'4','250000.0'),(19,4,'5','174383.0'),(19,5,'6','36174.16'),(19,6,'8','76792.32'),(19,7,'6','63304.78'),(20,1,'15','117600.0'),(20,2,'5','91250.0'),(20,3,'11','678500.0'),(21,1,'20','153300.0'),(21,2,'15','273750.0'),(21,3,'10','619000.0');
/*!40000 ALTER TABLE `quantite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `idUser` int NOT NULL AUTO_INCREMENT,
  `username` varchar(254) DEFAULT NULL,
  `password` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `USER_PK` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'farouk','admin'),(2,'abdessalam','admin'),(3,'admin','admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-15 13:35:06
