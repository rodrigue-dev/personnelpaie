/* 
CREATE TABLE `authority` (
  `authority` varchar(50) NOT NULL,
  PRIMARY KEY (`authority`)
) ;
CREATE TABLE `avantage` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `type_avantage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
CREATE TABLE `departement` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `nom_departement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;
CREATE TABLE `fiche_presence` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `heure_debut` time DEFAULT NULL,
  `heure_fin` time DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;
CREATE TABLE `fonction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `heure_travaille` int NOT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `salaire_suppl` float NOT NULL,
  `type_fonction` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;
CREATE TABLE `heure_supplementaire` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `heure_debut` time DEFAULT NULL,
  `heure_fin` time DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;
CREATE TABLE `type_plaining` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `nom_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `avantages_fonctions` (
  `fonction_id` bigint NOT NULL,
  `avantage_id` bigint NOT NULL,
  PRIMARY KEY (`fonction_id`,`avantage_id`),
  KEY `FKk0txdvgd3xma9ydcbpq5ti8po` (`fonction_id`),
  KEY `FK5lajjafvkpuyq4ymgy8hgn1hh` (`avantage_id`),
  CONSTRAINT `FK1isqsgqgyk98xi0kspo5mlyvp` FOREIGN KEY (`fonction_id`) REFERENCES `fonction` (`id`),
  CONSTRAINT `FK5lajjafvkpuyq4ymgy8hgn1hh` FOREIGN KEY (`avantage_id`) REFERENCES `avantage` (`id`)
);
CREATE TABLE `departements_fonctions` (
  `fonction_id` bigint NOT NULL,
  `departement_id` bigint NOT NULL,
  PRIMARY KEY (`fonction_id`,`departement_id`),
  KEY `FKdq1xlvjv0va7664mjxwwnkjnt` (`fonction_id`),
  KEY `FK7rpv59dnrrjc12u6emfivt2ux` (`departement_id`),
  CONSTRAINT `FK7rpv59dnrrjc12u6emfivt2ux` FOREIGN KEY (`departement_id`) REFERENCES `departement` (`id`),
  CONSTRAINT `FKjqhsmh076f7bwmkw4d3msyf94` FOREIGN KEY (`fonction_id`) REFERENCES `fonction` (`id`)
);
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activated` bit(1) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `lang_key` varchar(10) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `privatekey` varchar(255) DEFAULT NULL,
  `secretkey` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `compte_iban` varchar(255) DEFAULT NULL,
  `etat_civil` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `matricule` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `authority_authority` varchar(60) DEFAULT NULL,
  `departement_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjlcr9ajtfw2rmceomtoj2thll` (`authority_authority`),
  CONSTRAINT `FKjlcr9ajtfw2rmceomtoj2thll` 
  FOREIGN KEY (`authority_authority`) REFERENCES `authority` (`authority`),
  KEY `FKjlcr9ajtfw2rmceomtoj2thkj` (`departement_id`),
  CONSTRAINT `FKjlcr9ajtfw2rmceomtoj2thkj` 
  FOREIGN KEY (`departement_id`) REFERENCES `departement` (`id`)
);
CREATE TABLE `paiement` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cotisation_cnss` float NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `date_paie` date DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `precompte_pro` float NOT NULL,
  `retenu_chomage` float NOT NULL,
  `retenu_retraite` float NOT NULL,
  `total_jour` int NOT NULL,
  `createby_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdclegvsc5jtmhywwpxbcixnga` (`createby_id`),
  KEY `FKd5lx47jgtix6rh8cvj22wq8or` (`user_id`),
  CONSTRAINT `FKd5lx47jgtix6rh8cvj22wq8or` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKdclegvsc5jtmhywwpxbcixnga` FOREIGN KEY (`createby_id`) REFERENCES `user` (`id`)
) ;
CREATE TABLE `planinig` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `date_debut` datetime(6) DEFAULT NULL,
  `date_fin` datetime(6) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `fonction_id` bigint DEFAULT NULL,
  `type_plaining_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK11ed6fnx7mx63qsj59xc9acri` (`fonction_id`),
  KEY `FKjwyhc530qjy2u6yswup3qfhx` (`type_plaining_id`),
  KEY `FK3y0i3895kwk8m536j2dea9m75` (`user_id`),
  CONSTRAINT `FK11ed6fnx7mx63qsj59xc9acri` FOREIGN KEY (`fonction_id`) REFERENCES `fonction` (`id`),
  CONSTRAINT `FK3y0i3895kwk8m536j2dea9m75` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKjwyhc530qjy2u6yswup3qfhx` FOREIGN KEY (`type_plaining_id`) REFERENCES `type_plaining` (`id`)
);
CREATE TABLE `user_authority` (
  `user_id` bigint NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `FK6ktglpl5mjosa283rvken2py5` (`authority_name`),
  CONSTRAINT `FK6ktglpl5mjosa283rvken2py5` FOREIGN KEY (`authority_name`) REFERENCES `authority` (`authority`),
  CONSTRAINT `FKpqlsjpkybgos9w2svcri7j8xy` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);
 */