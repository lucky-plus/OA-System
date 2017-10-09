/*
SQLyog v10.2 
MySQL - 5.7.9-log : Database - oa_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oa_system` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `oa_system`;

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values (1);

/*Table structure for table `t_asset` */

DROP TABLE IF EXISTS `t_asset`;

CREATE TABLE `t_asset` (
  `assetId` int(11) NOT NULL AUTO_INCREMENT,
  `assetsName` varchar(255) DEFAULT NULL,
  `assetsPrice` double DEFAULT NULL,
  `assetsState` varchar(255) DEFAULT NULL,
  `assetsType` varchar(255) DEFAULT NULL,
  `user_userId` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`assetId`),
  KEY `FKgeqkjyef8oeql7c28e5ol60nl` (`user_userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_asset` */

/*Table structure for table `t_dept` */

DROP TABLE IF EXISTS `t_dept`;

CREATE TABLE `t_dept` (
  `deptId` int(11) NOT NULL AUTO_INCREMENT,
  `creatBy` varchar(255) DEFAULT NULL,
  `deptName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`deptId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_dept` */

/*Table structure for table `t_module` */

DROP TABLE IF EXISTS `t_module`;

CREATE TABLE `t_module` (
  `moduleId` int(11) NOT NULL AUTO_INCREMENT,
  `creatBy` varchar(255) DEFAULT NULL,
  `modelName` varchar(255) DEFAULT NULL,
  `parentId` varchar(255) DEFAULT NULL,
  `parentName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`moduleId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_module` */

/*Table structure for table `t_module_t_role` */

DROP TABLE IF EXISTS `t_module_t_role`;

CREATE TABLE `t_module_t_role` (
  `Module_moduleId` int(11) NOT NULL,
  `roles_roleId` int(11) NOT NULL,
  KEY `FKky9doua03vvue0u3ctybr239t` (`roles_roleId`),
  KEY `FKq9kke001dansl2ste476nhj5f` (`Module_moduleId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_module_t_role` */

/*Table structure for table `t_notice` */

DROP TABLE IF EXISTS `t_notice`;

CREATE TABLE `t_notice` (
  `noticeId` int(11) NOT NULL AUTO_INCREMENT,
  `noticeName` varchar(255) DEFAULT NULL,
  `noticeText` longtext,
  `noticeTime` datetime DEFAULT NULL,
  `user_userId` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`noticeId`),
  KEY `FKravl2pvn85dsukin1q86yxhan` (`user_userId`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_notice` */

insert  into `t_notice`(`noticeId`,`noticeName`,`noticeText`,`noticeTime`,`user_userId`) values (2,'123','12<div>21.</div>','2017-10-06 20:58:33','1'),(3,'wqe','rqwr','2017-10-06 20:58:40','1'),(5,'576','786','2017-10-04 20:58:40','1');

/*Table structure for table `t_post` */

DROP TABLE IF EXISTS `t_post`;

CREATE TABLE `t_post` (
  `postId` int(11) NOT NULL AUTO_INCREMENT,
  `creatBy` varchar(255) DEFAULT NULL,
  `postName` varchar(255) DEFAULT NULL,
  `department_deptId` int(11) DEFAULT NULL,
  PRIMARY KEY (`postId`),
  KEY `FK6e5ri2tploes2cshxmggvch3j` (`department_deptId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_post` */

/*Table structure for table `t_resources` */

DROP TABLE IF EXISTS `t_resources`;

CREATE TABLE `t_resources` (
  `resId` int(11) NOT NULL AUTO_INCREMENT,
  `resIdentify` varchar(255) DEFAULT NULL,
  `resName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`resId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_resources` */

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleLevel` int(11) DEFAULT NULL,
  `roleName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`roleId`,`roleLevel`,`roleName`) values (1,1,'r1'),(3,2,'r2'),(4,2,'r3'),(5,4,'r4');

/*Table structure for table `t_role_module` */

DROP TABLE IF EXISTS `t_role_module`;

CREATE TABLE `t_role_module` (
  `roleId` int(11) NOT NULL,
  `moduleId` int(11) NOT NULL,
  KEY `FKncblpa027rvpht3k807kk2d3` (`moduleId`),
  KEY `FK6pqs4cfu9yvv7nw8kkd8oqkl8` (`roleId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_role_module` */

insert  into `t_role_module`(`roleId`,`moduleId`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(3,9),(3,8),(3,6),(3,5),(3,4),(3,3),(3,1),(4,1),(4,2),(4,4),(4,8),(4,9),(5,5),(5,4),(5,2),(5,1);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `userId` varchar(8) NOT NULL,
  `birthday` datetime DEFAULT NULL,
  `dept` varchar(255) DEFAULT NULL,
  `home` varchar(255) DEFAULT NULL,
  `idNumber` varchar(255) DEFAULT NULL,
  `idType` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `mobilePhone` varchar(255) DEFAULT NULL,
  `nativePlace` varchar(255) DEFAULT NULL,
  `onDutDate` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `qq_number` int(11) NOT NULL,
  `realName` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `wechatNumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`userId`,`birthday`,`dept`,`home`,`idNumber`,`idType`,`mail`,`mobilePhone`,`nativePlace`,`onDutDate`,`password`,`qq_number`,`realName`,`sex`,`userName`,`wechatNumber`) values ('1',NULL,'财务部',NULL,NULL,NULL,'934789892@qq.com','13631781026','广东东莞',NULL,'123',934789892,'方振庭','男','fzt',NULL),('2','2017-10-07 21:57:10','市场部','','','','934789892@qq.com','13631781026','广东东莞','2017-10-10 21:57:17','123',934789892,'123','男','1',NULL),('3',NULL,'财务部',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,934789892,'456','男','2',NULL),('4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123',0,NULL,NULL,'zjk',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
