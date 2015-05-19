DROP TABLE IF EXISTS `user_entity`;

CREATE TABLE `user_entity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `email_address` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


INSERT INTO `user_entity` VALUES (1,'Los Angeles','johncoltrane@gmail.com','John','Coltrane','jcoltrane','CA','jcoltrane'),(2,'New York','sonnyrollins@gmail.com','Sonny','Rollins','srollins','NY','srollins'),(3,'Culver City','milesdavis@aol.com','Miles','Davis','mdavis','CA','mdavis'),(4,'Culver City','sonnystitt@yahoo.com','Sonny','Stitt','sstitt','CA','sstitt'),(5,'New York','chrispotter@gmail.com','Chris','Potter','cpotter','NY','cpotter'),(6,'Santa Monica','herbiehancock@gmail.com','Herbie','Hancock','hhancock','CA','hhancock');
