/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.22-log : Database - secdemo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `log` */

DROP TABLE IF EXISTS `log`;

CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `module` varchar(30) DEFAULT NULL,
  `action` varchar(30) DEFAULT NULL,
  `actionTime` varchar(30) DEFAULT NULL,
  `userIP` varchar(30) DEFAULT NULL,
  `operTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `log` */

/*Table structure for table `resources` */

DROP TABLE IF EXISTS `resources`;

CREATE TABLE `resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `parentId` int(11) DEFAULT '0' COMMENT '上级资源id',
  `resKey` varchar(50) DEFAULT NULL COMMENT '资源权限标示',
  `type` varchar(10) DEFAULT NULL COMMENT '资源类型：1菜单2页面3操作',
  `resUrl` varchar(200) DEFAULT NULL COMMENT '资源请求路径url',
  `level` int(11) DEFAULT '0' COMMENT '资源等级：0 >1 > 2 >3',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `resources` */

insert  into `resources`(`id`,`name`,`parentId`,`resKey`,`type`,`resUrl`,`level`,`description`) values (0,'未定义资源',0,'sysUndefine','0','undefine',0,'未定义资源都转为获取此权限'),(1,'权限资源管理',0,'resManage','1','/resManage',1,'权限资源管理菜单'),(2,'权限资源查询',1,'resQuery','2','/resManage/resQuery.do',2,'权限资源查看'),(3,'权限资源添加',1,'resAdd','3','/resManage/resAdd.do',2,'权限资源添加'),(4,'权限资源获取',1,'resFind','3','/resManage/resFind.do',2,'获取单个权限资源信息'),(5,'用户管理',0,'userManage','1','userManage',1,'用户模块'),(7,'用户查询',5,'userQuery','2','/user/query.do',2,'查询用户信息'),(8,'权限资源修改',1,'resUpdate','3','/resManage/resUpdate.do',2,'修改资源权限信息'),(9,'用户查看',5,'userFind','3','/user/find.do',2,'获取用户信息'),(10,'用户修改',5,'userUpdate','3','/user/update.do',2,'修改用户信息'),(11,'角色管理',0,'roleManage','1','roleManage',1,'角色管理菜单'),(12,'角色列表查询',11,'roleQuery','2','/role/query.do',2,'角色列表信息获取'),(13,'角色添加',11,'roleAdd','3','/role/add.do',2,'添加角色'),(14,'角色查看',11,'roleFind','3','/role/find.do',2,'查看指定角色信息'),(15,'角色修改',11,'roleUpdate','3','/role/update.do',2,'修改角色定义'),(16,'角色授权',11,'bindRoleRes','3','/role/bindRoleRes.do',2,'角色与权限的绑定'),(17,'用户角色绑定',5,'userBindRole','3','/user/bindRole.do',2,'给用户添加角色');

/*Table structure for table `resources_role` */

DROP TABLE IF EXISTS `resources_role`;

CREATE TABLE `resources_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resc_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8;

/*Data for the table `resources_role` */

insert  into `resources_role`(`id`,`resc_id`,`role_id`) values (8,1,2),(9,2,2),(10,3,2),(11,4,2),(12,5,2),(175,1,1),(176,2,1),(177,3,1),(178,5,1),(179,4,1),(180,16,1),(181,12,1),(182,14,1),(183,13,1),(184,8,1),(185,7,1),(186,17,1),(187,9,1),(188,11,1),(189,10,1);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `roleKey` varchar(50) DEFAULT NULL COMMENT '角色标志',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `enable` int(11) DEFAULT '1' COMMENT '1=可用，0不可用，-1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`roleKey`,`description`,`enable`) values (1,'超级管理员1','ROLE_USER','admin',1),(2,'系统管理者','sysManage','系统配置',1),(3,'角色管理者','roleManage','配置角色',1),(8,'test3','aa','aa',1);

/*Table structure for table `serverinfo` */

DROP TABLE IF EXISTS `serverinfo`;

CREATE TABLE `serverinfo` (
  `id` int(10) NOT NULL DEFAULT '0',
  `cpuUsage` varchar(255) DEFAULT NULL,
  `setCpuUsage` varchar(255) DEFAULT NULL,
  `jvmUsage` varchar(255) DEFAULT NULL,
  `setJvmUsage` varchar(255) DEFAULT NULL,
  `ramUsage` varchar(255) DEFAULT NULL,
  `setRamUsage` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `operTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `mark` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `serverinfo` */

insert  into `serverinfo`(`id`,`cpuUsage`,`setCpuUsage`,`jvmUsage`,`setJvmUsage`,`ramUsage`,`setRamUsage`,`email`,`operTime`,`mark`) values (1,'9.3','20','64.0','80','75.0','80','1212614949@qq.com','2013-11-19 11:31:04','<font color=\'red\'>内存当前：75.0%,超出预设值  60%</font>'),(2,'0.8','20','60.0','80','75.0','80','1212614949@qq.com','2013-11-19 11:32:02','<font color=\'red\'>内存当前：75.0%,超出预设值  60%</font>'),(3,'1.5','20','59.0','80','75.0','80','1212614949@qq.com','2013-11-19 11:33:03','<font color=\'red\'>内存当前：75.0%,超出预设值  60%</font>'),(4,'0.7','20','57.0','80','75.0','80','1212614949@qq.com','2013-11-19 11:34:02','<font color=\'red\'>内存当前：75.0%,超出预设值  60%</font>'),(5,'2.3','20','57.0','80','75.0','80','1212614949@qq.com','2013-11-19 11:35:02','<font color=\'red\'>内存当前：75.0%,超出预设值  60%</font>'),(6,'17.9','20','57.0','80','77.0','80','1212614949@qq.com','2013-11-19 11:36:02','<font color=\'red\'>内存当前：77.0%,超出预设值  60%</font>');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `userPassword` varchar(40) DEFAULT NULL,
  `userRealName` varchar(20) DEFAULT NULL,
  `userPhone` varchar(30) DEFAULT NULL,
  `userQQ` varchar(30) DEFAULT NULL,
  `regTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`userId`,`userName`,`userPassword`,`userRealName`,`userPhone`,`userQQ`,`regTime`,`status`) values (1,'admin','admin','admin','0253526','32432','2016-10-24 16:23:53','1'),(2,'test','test','测试号','aaa','aa','2016-10-24 16:23:45','1');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `role_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`user_id`,`role_id`) values (2,1,1),(3,1,2),(4,2,3),(5,2,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
