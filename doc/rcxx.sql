# MySQL-Front 5.1  (Build 4.2)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: rcxx
# ------------------------------------------------------
# Server version 5.5.15

DROP DATABASE IF EXISTS `rcxx`;
CREATE DATABASE `rcxx` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `rcxx`;

#
# Source for table csr
#

DROP TABLE IF EXISTS `csr`;
CREATE TABLE `csr` (
  `csrbm` char(1) NOT NULL DEFAULT '' COMMENT '纯收入编码',
  `csrmc` varchar(20) DEFAULT NULL COMMENT '纯收入名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='纯收入编码表';

#
# Dumping data for table csr
#

INSERT INTO `csr` VALUES ('0','<2万元');
INSERT INTO `csr` VALUES ('1','2-3万元');
INSERT INTO `csr` VALUES ('2','3-5万元');
INSERT INTO `csr` VALUES ('3','5-10万元');
INSERT INTO `csr` VALUES ('4','10万元以上');
INSERT INTO `csr` VALUES ('5','20万元以上');
INSERT INTO `csr` VALUES ('6','50万元以上');

#
# Source for table cyrc
#

DROP TABLE IF EXISTS `cyrc`;
CREATE TABLE `cyrc` (
  `cyrcid` int(11) NOT NULL AUTO_INCREMENT COMMENT '创业人才',
  `yrzjg` varchar(60) DEFAULT '' COMMENT '原任职机构',
  `yzw` varchar(20) DEFAULT '' COMMENT '原职务',
  `zhgzsj` varchar(10) DEFAULT '' COMMENT '在黄工作时间',
  `email` varchar(30) DEFAULT '' COMMENT '电子邮箱',
  `yjzdz` varchar(255) DEFAULT '' COMMENT '原居住地址',
  `zyry` text COMMENT '重要荣誉或重要奖项',
  `ryid` int(11) DEFAULT NULL COMMENT '人员ID',
  `username` varchar(20) DEFAULT '' COMMENT '操作人员用户名',
  PRIMARY KEY (`cyrcid`),
  KEY `ryid` (`ryid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='创业人才';

#
# Dumping data for table cyrc
#

INSERT INTO `cyrc` VALUES (1,'湖北师范学院','大学生','4年','789789789@qq.com','湖北孝感','湖北十佳青年',1,'');
INSERT INTO `cyrc` VALUES (7,'湖北黄石教育局','局长','10年','9876787678@193.com','湖北武汉','没有',24,'');
INSERT INTO `cyrc` VALUES (8,'xxxx','xxx','xxx','xxx','xxx','我就回复的时间啊付款了地方见识到发的都是福建阿里大家发了快递使肌肤爱看电视了飞机快冻死了姐夫电视看了附件是打开了附件收到了客服经理看到附件抵抗力都发生是的第三方就到了基地上课了基地克里夫基地上课了房间都是理科氨基丁酸克里夫啊江诗丹顿克拉短时间了可视对讲快乐撒大家快乐到底是即可了第三方基地克里夫就看到了伐简单快乐基地上课了简单快乐附件都快疯了基地克里夫基地客单价快乐迪接客帝基地上课了jdsklfjklgjfdlgklkjf师大附近第十六啊看见到了发送我就回复的时间啊付款了地方见识到发的都是福建阿里大家发了快递使肌肤爱看电视了飞机快冻死了姐夫电视看了附件是打开了附件收到了客服经理看到附件抵抗力都发生是的第三方就到了基地上课了基地克里夫基地上课了房间都是理科氨基丁酸克里夫啊江诗丹顿克拉短时间了可视对讲快乐撒大家快乐到底是即可了第三方基地克里夫就看到了伐简单快乐基地上课了简单快乐附件都快疯了基地克里夫基地客单价快乐迪接客帝基地上课了jdsklfjklgjfdlgklkjf师大附近第十六啊看见到了发送到阿什顿机锋了sd卡附件；我就回复的时间啊付款了地方见识到发的都是福建阿里大家发了快递使肌肤爱看电视了飞机快冻死了姐夫电视看了附件是打开了附件收到了客服经理看到附件抵抗力都发生是的第三方就到了基地上课了基地克里夫基地上课了房间都是理科氨基丁酸克里夫啊江诗丹顿克拉短时间了可视对讲快乐撒大家快乐到底是即可了第三方基地克里夫就看到了伐简单快乐基地上课了简单快乐附件都快疯了基地克里夫基地客单价快乐迪接客帝基地上课了jdsklfjklgjfdlgklkjf师大附近第十六啊看见到了发送到阿什顿机锋了sd卡附件；我就回复的时间啊付款了地方见识到发的都是福建阿里大家发了快递使肌肤爱看电视了飞机快冻死了姐夫电视看了附件是打开了附件收到了客服经理看到附件抵抗力都发生是的第三方就到了基地上课了基地克里夫基地上课了房间都是理科氨基丁酸克里夫啊江诗丹顿克拉短时间了可视对讲快乐撒大家快乐到底是即可了第三方基地克里夫就看到了伐简单快乐基地上课了简单快乐附件都快疯了基地克里夫基地客单价快乐迪接客帝基地上课了jdsklfjklgjfdlgklkjf师大附近第十六啊看见到了发送到阿什顿机锋了sd卡附件；到阿什顿机锋了sd卡附件；',32,'');
INSERT INTO `cyrc` VALUES (9,'比高集团有限公司','董事长','3年','changjiang7hao@zhouxingchi.com','香港','▪\t2010   \t参加两岸三地“演艺界512关爱行动”大汇演   （获奖）\r\n▪\t2006   \t亚洲卓越奖杰出电影表演者   （获奖）\r\n▪\t2005   \t电影《功夫》入选美国《时代》周刊评选出的2005年世界上最好的10部电影之一   （获奖）\r\n▪\t2005   \t入选美国权威文化杂志《人物》杂志的五位“全球最热门单身汉”之一   （获奖）\r\n▪\t2003   \t入选美国《时代》周刊举办的“2003届时代亚洲英雄”,成为获选的29位全球出色华人之中   （获奖）\r\n▪\t2003   \t中国寻星之旅》最心仪明星第一名   （获奖）\r\n▪\t2002   \t荣登《纽约时报》9月号时尚专栏封面,文中称周星驰为“来自香港的赤脚汤姆·汉克斯”   （获奖）\r\n▪\t2001   \t最突出电影幕后精英《明周》致敬大奖   （获奖）\r\n▪\t2001   \t《明报周刊》第33周年演艺动力大奖   （获奖）\r\n▪\t2000   \tTVB千禧台庆颁发的终身成就奖   （获奖）\r\n▪\t1999   \t香港无线电视台1999年台庆《翡翠星辉杰出成就大奖》   （获奖）\r\n▪\t1999   \t香港观众心目中的偶像第三名   （获奖）\r\n▪\t1998   \t国际杰人会港澳杰人之星奖   （获奖）\r\n▪\t1997   \t台湾娱节目《我猜我猜我猜猜猜》评选的最好笑艺人第一名   （获奖）\r\n▪\t1994   \t香港无线电视台评选的第一届十大红人第三名。   （获奖）\r\n▪\t1993   \t《卓越杂志》十大电影明星   （获奖）\r\n▪\t1993   \t五大片商票选票房巨星第三名   （获奖）\r\n▪\t1993   \t台湾影剧记者票选四大天王第三名   （获奖）\r\n▪\t1992   \t香港十大最受欢迎艺人第二名   （获奖）\r\n▪\t1991   \t银色世界杂志十大皮蛋奖第九名   （获奖）\r\n▪\t1991   \t台湾报社票选十大港星风云人物第二名   （获奖）',26,'');
INSERT INTO `cyrc` VALUES (10,'湖北师范学院','小学生','213年','1184664458@qq.com','湖北','湖北黄石最佳小学生',27,'');
INSERT INTO `cyrc` VALUES (13,'湖北师范',NULL,NULL,NULL,NULL,NULL,4,'');

#
# Source for table dwlb
#

DROP TABLE IF EXISTS `dwlb`;
CREATE TABLE `dwlb` (
  `dwlbbm` char(1) NOT NULL DEFAULT '' COMMENT '单位类别编码',
  `dwlbmc` char(20) DEFAULT NULL COMMENT '单位类别名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单位类别编码表';

#
# Dumping data for table dwlb
#

INSERT INTO `dwlb` VALUES ('0',NULL);
INSERT INTO `dwlb` VALUES ('1','市直');
INSERT INTO `dwlb` VALUES ('2','县区（市）');
INSERT INTO `dwlb` VALUES ('3','乡镇（街道）');
INSERT INTO `dwlb` VALUES ('4','社区村');

#
# Source for table dwxz
#

DROP TABLE IF EXISTS `dwxz`;
CREATE TABLE `dwxz` (
  `dwxzbm` char(1) NOT NULL DEFAULT '' COMMENT '单位性质编码',
  `dwxzmc` char(20) DEFAULT '' COMMENT '单位性质名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单位性质编码表';

#
# Dumping data for table dwxz
#

INSERT INTO `dwxz` VALUES ('1','党政机关');
INSERT INTO `dwxz` VALUES ('2','事业单位');
INSERT INTO `dwxz` VALUES ('3','科研机构');
INSERT INTO `dwxz` VALUES ('4','企业');
INSERT INTO `dwxz` VALUES ('5','社会团体');

#
# Source for table dzrc
#

DROP TABLE IF EXISTS `dzrc`;
CREATE TABLE `dzrc` (
  `dzrcid` int(11) NOT NULL AUTO_INCREMENT COMMENT '党政人才ID',
  `rxzsj` varchar(10) DEFAULT '' COMMENT '任现职时间',
  `khqk1` varchar(10) DEFAULT '' COMMENT '近一年（考核情况）',
  `khqk2` varchar(4) DEFAULT '' COMMENT '近二年（考核情况）',
  `khqk3` varchar(10) DEFAULT '' COMMENT '近三年（考核情况）',
  `ryid` int(11) DEFAULT NULL COMMENT '人员ID',
  `username` varchar(20) DEFAULT '' COMMENT '操作人员用户名',
  PRIMARY KEY (`dzrcid`),
  KEY `ryid` (`ryid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='党政人才';

#
# Dumping data for table dzrc
#

INSERT INTO `dzrc` VALUES (3,'4年','优秀','优秀','差评',2,'');
INSERT INTO `dzrc` VALUES (5,'优秀','优秀','优秀','优秀',27,'');
INSERT INTO `dzrc` VALUES (6,'三个月','优秀','优秀','优秀',26,'');
INSERT INTO `dzrc` VALUES (9,'一个月','无','无','无',30,'');
INSERT INTO `dzrc` VALUES (10,'一年','优秀','无','无',32,'');
INSERT INTO `dzrc` VALUES (20,'三年','优秀','良','三好',1,'');
INSERT INTO `dzrc` VALUES (21,'一个月','1','1','1',4,'');

#
# Source for table gzdw
#

DROP TABLE IF EXISTS `gzdw`;
CREATE TABLE `gzdw` (
  `gzdwid` int(11) NOT NULL AUTO_INCREMENT COMMENT '工作单位ID',
  `dwmc` varchar(50) NOT NULL DEFAULT '' COMMENT '单位名称',
  `dwxzbm` char(1) DEFAULT NULL COMMENT '单位性质编码',
  `dwlbbm` char(1) DEFAULT NULL COMMENT '单位类别编码',
  `ssxtbm` char(2) DEFAULT NULL COMMENT '所属系统编码',
  `sshybm` char(2) DEFAULT NULL COMMENT '所属行业编码',
  `dwdh` char(20) DEFAULT '' COMMENT '单位电话',
  `dwdz` varchar(60) DEFAULT '' COMMENT '单位地址',
  `hypy` varchar(20) DEFAULT '' COMMENT '单位名称汉语拼音首字母',
  `sjdwid` int(11) DEFAULT NULL COMMENT '上级单位ID :市级单位的上级为空',
  `lxr` varchar(20) DEFAULT '' COMMENT '联系人',
  `dzyx` varchar(60) DEFAULT '' COMMENT '电子邮箱',
  `dwjj` text COMMENT '单位简介',
  `username` varchar(20) DEFAULT NULL COMMENT '操作员用户姓名',
  `ssdqbm` char(1) DEFAULT NULL,
  PRIMARY KEY (`gzdwid`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='工作单位';

#
# Dumping data for table gzdw
#

INSERT INTO `gzdw` VALUES (2,'西塞山高技能人才实训基地1','4','2','01','04','0761-2872119','黄石港区区政府','xssgjnrcsxjd1',23,'李四','897929321@qq.com','','焦娟平(西塞山)','2');
INSERT INTO `gzdw` VALUES (3,'西塞山高技能人才实训基地','2','2','01','02','0761-2872119','黄石港区区政府','hsgjnrcsxjd',23,'','',NULL,NULL,'2');
INSERT INTO `gzdw` VALUES (4,'西塞山高薪技术人才就业基地','3','2','01','04','021-2872113','大冶人民政府','dygxjsrcjyjd',23,'','',NULL,NULL,'2');
INSERT INTO `gzdw` VALUES (6,'西塞山区人才看管所','3','2','02','04','18271623561','下陆','xlqrcs',23,'','',NULL,NULL,'2');
INSERT INTO `gzdw` VALUES (7,'西塞山人才中介所','2','1','02','03','0763-2872562','大冶市中心','xssrczjs',23,'李四','897929321@qq.com','大型企业','焦娟平(西塞山)','2');
INSERT INTO `gzdw` VALUES (8,'十三排棚户区户口调查办事处','5','3','01','05','0716-2872110','下陆政府区','sspphqhkdcbsc',28,'李四','897929321@qq.com','Dadadasdfas','黄婷','2');
INSERT INTO `gzdw` VALUES (9,'下陆区人才服务管理办公室','1','2','01','01','0216-88661280','高明区荷城街道莲花路25号','hsrcfwglbgs',24,'','',NULL,NULL,'3');
INSERT INTO `gzdw` VALUES (10,'铁山区人社局','1','2','03','01','15107233333','湖北省黄石市西部','tsqrsj',29,'','',NULL,NULL,'4');
INSERT INTO `gzdw` VALUES (12,'阳新县农村实用人才领导小组办公室','2','2','05','05','15107233945','阳新县政府旁','yxncsyrcldxzbgs',30,'','',NULL,NULL,'7');
INSERT INTO `gzdw` VALUES (18,'阳新县中外合资人才中介机构管理','1','2','01','01','0213-2563470','黄石武汉路102号','yxxzwhzrczjjggl',30,'','',NULL,NULL,'7');
INSERT INTO `gzdw` VALUES (20,'十三排人棚户区力资源和社会保障部','1','2','01','01','032-5416015','大冶一中斜对面','dyrlzyhshbzb',28,'','',NULL,'员星博','0');
INSERT INTO `gzdw` VALUES (21,'阳新县中组部','1','2','01','01','15123069744','阳新市政府旁1-09号','yxxzzb',30,'','',NULL,'焦娟平','7');
INSERT INTO `gzdw` VALUES (22,'黄石市','1','1','02','03','15107233644','黄石市级工作单位','hss',-1,'','',NULL,NULL,'0');
INSERT INTO `gzdw` VALUES (23,'西塞山区','5','2','03','04','0234-51263987','西塞山区区级工作单位','xssq',NULL,'李四','897929321@qq.com','','焦娟平(西塞山)','2');
INSERT INTO `gzdw` VALUES (24,'下陆区','3','1','05','06','0321-15639715','下陆区区级工作单位','xlq',NULL,'李四','','','员星博','3');
INSERT INTO `gzdw` VALUES (25,'黄石港区','4','2','04','01','0125-36571298','黄石港区区级工作单位','hsgq',NULL,'','',NULL,NULL,'1');
INSERT INTO `gzdw` VALUES (27,'企业级别','1','2','02','03','0125-3657412','企业级别工作单位','qyjb',7,'','',NULL,NULL,'5');
INSERT INTO `gzdw` VALUES (28,'十三排棚户区','1','4','01','03','15107233944','十三排棚户区','sspphq',NULL,'李四','','','员星博','0');
INSERT INTO `gzdw` VALUES (29,'铁山区','2','2','03','04','15274631234','铁山区区级工作单位','tsq',NULL,'','',NULL,NULL,'4');
INSERT INTO `gzdw` VALUES (30,'阳新县','3','2','02','01','0712-25896123','阳新县区级工作单位','yxx',NULL,'','',NULL,NULL,'7');
INSERT INTO `gzdw` VALUES (31,'西塞山区特高新人才发展管理有限公司','1','2','01','01','0236-12563987','西塞山高新园区高新街3号(创业中心1号楼)501室','xssqtgxrcfzglyxgs',23,'','',NULL,'焦娟平(西塞山)','2');
INSERT INTO `gzdw` VALUES (33,'大冶区','1','3','01','01','0157-1256398','大冶','dyq',NULL,'李四','','','员星博','6');
INSERT INTO `gzdw` VALUES (34,'测试','1','1','01','01','0761-2836542','湖北师范学院','cs',NULL,'李四','897929321@qq.com','adsfasfasdf','黄婷','4');
INSERT INTO `gzdw` VALUES (35,'测试添加','1','1','01','01','18271643153','123','cstj',NULL,'18271623360','897929321@qq.com','fasdfasdfasf','黄婷','6');
INSERT INTO `gzdw` VALUES (36,'特使工业','1','2','01','01','0761-2836542','湖北黄石','tsgy',23,'张武','897929321@qq.com','SASf','焦娟平(西塞山)','2');
INSERT INTO `gzdw` VALUES (37,'特使工业2','1','1','01','01','12121212121','湖北黄石','tsgy2',23,'张武','897929321@qq.com','adadsfadsfasd','焦娟平(西塞山)','2');
INSERT INTO `gzdw` VALUES (38,'华东融化','5','4','03','05','12121212121','湖北黄石','hdrh',NULL,'李四','897929321@qq.com','Dadadasdfas','黄婷','2');

#
# Source for table jbxx
#

DROP TABLE IF EXISTS `jbxx`;
CREATE TABLE `jbxx` (
  `ryid` int(11) NOT NULL AUTO_INCREMENT COMMENT '人员ID',
  `zjhm` varchar(18) NOT NULL DEFAULT '' COMMENT '证件号码',
  `xm` varchar(30) NOT NULL DEFAULT '' COMMENT '姓名',
  `xb` char(1) NOT NULL DEFAULT '1' COMMENT '性别',
  `csrq` date DEFAULT NULL COMMENT '出生日期',
  `mz` char(1) DEFAULT '1' COMMENT '民族 1.汉族 2.少数民族',
  `jg` varchar(20) DEFAULT '' COMMENT '籍贯',
  `gzdwid` int(11) DEFAULT NULL COMMENT '工作单位ID',
  `gzsj` varchar(10) DEFAULT '' COMMENT '工作时间',
  `zzmmbm` char(2) DEFAULT NULL COMMENT '政治面貌编码',
  `rdsj` date DEFAULT NULL COMMENT '入党时间',
  `zjbm` char(1) DEFAULT NULL COMMENT '职级编码',
  `zw` varchar(20) DEFAULT '' COMMENT '职务',
  `xxxs` char(1) DEFAULT '1' COMMENT '学习形式 1.全日制 2.在职',
  `xlbm` char(1) DEFAULT NULL COMMENT '学历编码',
  `xwbm` char(1) DEFAULT NULL COMMENT '学位编码',
  `byxx` varchar(40) DEFAULT '' COMMENT '毕业学校',
  `zymc` varchar(40) DEFAULT '' COMMENT '专业名称',
  `hjszd` varchar(255) DEFAULT '' COMMENT '户籍所在地',
  `jkzk` varchar(30) DEFAULT '' COMMENT '健康状况',
  `txdz` varchar(255) DEFAULT '' COMMENT '通信地址',
  `yzbm` char(6) DEFAULT '' COMMENT '邮政编码',
  `lxdh` varchar(50) DEFAULT '' COMMENT '联系电话',
  `hyzkbm` char(1) DEFAULT '1' COMMENT '婚姻状况编码 1.未婚 2. 已婚 3.离异',
  `sfdzrc` bit(1) DEFAULT b'0' COMMENT '是否党政人才',
  `sfglrc` bit(1) DEFAULT b'0' COMMENT '是否管理人才',
  `sfzjrc` bit(1) DEFAULT b'0' COMMENT '是否专技人才',
  `sfgjnrc` bit(1) DEFAULT b'0' COMMENT '是否高技能人才',
  `sfncsyrc` bit(1) DEFAULT b'0' COMMENT '是否农村实用人才',
  `sfshgzrc` bit(1) DEFAULT b'0' COMMENT '是否社会工作人才',
  `sfcyrc` bit(1) DEFAULT b'0' COMMENT '是否创业人才',
  `xmflbm` char(2) DEFAULT NULL COMMENT '项目分类编码',
  `zp` varchar(255) DEFAULT NULL COMMENT '照片存储路径',
  `username` varchar(255) DEFAULT NULL COMMENT '操作员用户名',
  `sfzc` bit(1) DEFAULT b'1' COMMENT '是否在册',
  `bzcyy` varchar(255) DEFAULT NULL COMMENT '不在册原因',
  PRIMARY KEY (`ryid`),
  UNIQUE KEY `zjhm` (`zjhm`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COMMENT='基本信息';

#
# Dumping data for table jbxx
#

INSERT INTO `jbxx` VALUES (1,'310115198007136824','赵兰瑾','女','2012-10-13','1','湖北孝感',3,'14','01','2012-10-13','1','CEO','1','1','1','湖北师范学院','计算机科学与技术','湖北黄石','非常好','湖北师范学院计算机科学与技术学院','435002','12345678912','1',b'1',b'0',b'1',b'0',b'1',b'0',b'1','','310115198007136824.jpg',NULL,b'1',NULL);
INSERT INTO `jbxx` VALUES (2,'500228199006993322','李梦2','女','2012-10-13','2','湖北孝感',2,'12','01','2012-10-13','2','CEO','1','1','1','湖北师范学院','计算机科学与技术','湖北黄石','非常好','湖北师范学院计算机科学与技术学院','435002','12345678912','1',b'0',b'1',b'1',b'0',b'1',b'1',b'0','01','500228199006993322.jpg','',b'1',NULL);
INSERT INTO `jbxx` VALUES (4,'422205552313132222','黄婷','男','1993-12-10','1','湖北孝感',4,'21','01','2012-10-13','1','CEO','2','1','1','湖北师范学院','计算机科学与技术','湖北黄石','非常好','湖北师范学院计算机科学与技术学院','435002','12345678912','2',b'1',b'1',b'0',b'0',b'0',b'1',b'1','01','422205552313132222.jpg',NULL,b'1',NULL);
INSERT INTO `jbxx` VALUES (24,'42220219930820381x','赵信','男','2013-11-17','1','湖北孝感',3,'22','01','2012-10-13','2','CEO','1','1','1','湖北师范学院','计算机科学与技术','湖北黄石','非常好','湖北师范学院计算机科学与技术学院','435002','12345678912','1',b'0',b'0',b'1',b'0',b'1',b'0',b'1',NULL,'42220219930820381x.jpg','',b'1',NULL);
INSERT INTO `jbxx` VALUES (26,'42220219930820381y','周星驰','男','2013-11-17','1','湖北孝感',3,'23','01','2012-10-13','0','CEO','1','1','1','湖北师范学院','计算机科学与技术','湖北黄石','非常好','湖北师范学院计算机科学与技术学院','435002','12345678912','1',b'1',b'0',b'1',b'0',b'1',b'1',b'1','01','42220219930820381y.jpg','',b'1',NULL);
INSERT INTO `jbxx` VALUES (27,'422202199308203811','张丹','男','2012-10-13','2','湖北孝感',3,'24','01','2013-11-19','1','书记','1','1','1','湖北师范学院','计算机科学与技术','湖北孝感','非常好','湖北师范学院计算机科学与技术学院','435002','12345678912','1',b'1',b'1',b'1',b'1',b'0',b'1',b'1','03','422202199308203811.png','',b'1',NULL);
INSERT INTO `jbxx` VALUES (30,'111','李奇','男','2012-10-13','1','',2,'','01','2012-10-13','1','','1','1','1','','','','','','','','1',b'1',b'1',b'1',b'0',b'0',b'1',b'1','01','111.jpg','',b'1',NULL);
INSERT INTO `jbxx` VALUES (31,'123456','李奇','男','1994-06-01','1','湖北',2,'','02','2014-06-13','1','省长','1','1','5','湖北师范学院','计算机科学与技术（日语双学位）','湖北武汉','非常好','湖北武汉','430119','','1',b'1',b'0',b'0',b'1',b'1',b'0',b'0','','123456.jpg',NULL,b'1',NULL);
INSERT INTO `jbxx` VALUES (32,'42112573192371237','华仔','男','2010-11-10','1','湖北黄冈',2,'','02','2012-10-10','3','CEO','2','1','1','湖师','计算机日语','湖北黄冈','健康','湖北黄冈浠水','435200','12345678912','1',b'1',b'0',b'0',b'0',b'0',b'0',b'0',NULL,'42112573192371237.jpg',NULL,b'1',NULL);
INSERT INTO `jbxx` VALUES (33,'4222021991082038','李萌','女','2010-11-10','1','重庆梁平',2,'25','02','2012-10-10','2','主席','1','1','1','湖北师范学院','计日','重庆梁平','非常好','湖北师范学院计算机科学与技术学院','400502','00000001','1',b'0',b'1',b'0',b'1',b'0',b'1',b'0','01','42112573192371237.jpg',NULL,b'1',NULL);
INSERT INTO `jbxx` VALUES (34,'454355654654656565','龚德兴','男','1993-10-05','1','重庆梁平',3,'','03',NULL,'1','大学生','1','3','5','湖北师范学院','计算机科学与技术（日语双学位）','重庆梁平','健健康康','湖北师范学院计算机科学与技术学院1202班','435000','','1',b'1',b'1',b'1',b'1',b'1',b'1',b'0','04','310115198007136824.jpg',NULL,b'1',NULL);
INSERT INTO `jbxx` VALUES (37,'123456789','邹灿','男','2013-11-05','1','荆州',1,'22','01','2013-10-29','4','','1','2','1','湖北师范','','荆州','荆州','荆州','荆州','','1',b'0',b'0',b'0',b'0',b'1',b'0',b'0',NULL,'123456789.jpg',NULL,b'1',NULL);
INSERT INTO `jbxx` VALUES (47,'422202199111111111','张三','男','2013-12-04','2','湖北应城',3,'','02','2013-12-04','4','','1','3','5','湖北师范学院','计算机科学与技术','湖北应城','非常好','湖北师范学院','435002','','2',b'0',b'1',b'0',b'0',b'0',b'1',b'0','01','422202199111111111.jpg',NULL,b'1',NULL);
INSERT INTO `jbxx` VALUES (49,'222222222222','刘雷','女','2015-04-30','2','应城',6,'','03',NULL,'6','','1','3','5','湖北师范','计算机','孝感','','','','','3',b'0',b'0',b'1',b'1',b'0',b'1',b'0','03','222222222222.jpg',NULL,b'1',NULL);

#
# Source for table jl
#

DROP TABLE IF EXISTS `jl`;
CREATE TABLE `jl` (
  `jlid` int(11) NOT NULL AUTO_INCREMENT COMMENT '简历ID',
  `xxjl` text COMMENT '学习简历',
  `gzjl` text COMMENT '工作简历',
  `jcqk` text COMMENT '奖惩情况',
  `ryid` int(11) DEFAULT NULL COMMENT '人员ID',
  `username` varchar(20) DEFAULT NULL COMMENT '操作员用户名',
  PRIMARY KEY (`jlid`),
  KEY `ryid` (`ryid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='简历';

#
# Dumping data for table jl
#

INSERT INTO `jl` VALUES (1,'<h1 style=\"text-align: center;\">学习简历</h1>\r\n\r\n<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"background-color:#FFFF00;\">本人就读于湖北师范学院，积极参加各种活动，品学兼优。</span></p>\r\n\r\n<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color:#FF0000;\">不可多得的人才！！！！！</span></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n','<p>工作简历</p>\r\n','<p>奖惩情况</p>\r\n',27,'无名氏');
INSERT INTO `jl` VALUES (7,'<p>1</p>\r\n','<p>2</p>\r\n',NULL,26,NULL);
INSERT INTO `jl` VALUES (9,'<p>2011-2015年 就读于湖北师范学院</p>\r\n','<p>2015年-2016年黄石港区政府工作（科员）</p>\r\n','<p>在大学期间，多次荣获学习奖学金</p>\r\n',2,NULL);
INSERT INTO `jl` VALUES (11,'<p>研究生毕业于湖北师范学院</p>\r\n','<p>在百度有过一年实习经验</p>\r\n','<p>获得过各种国家型奖项</p>\r\n',4,NULL);
INSERT INTO `jl` VALUES (12,'<p>1</p>\r\n','<p>2</p>\r\n','<p>3</p>\r\n',1,NULL);

#
# Source for table jldj
#

DROP TABLE IF EXISTS `jldj`;
CREATE TABLE `jldj` (
  `jldjbm` char(1) NOT NULL DEFAULT '' COMMENT '奖励等级编码',
  `jldjmc` varchar(20) DEFAULT NULL COMMENT '奖励等级名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='奖励等级编码表';

#
# Dumping data for table jldj
#

INSERT INTO `jldj` VALUES ('0',NULL);
INSERT INTO `jldj` VALUES ('1','国家级');
INSERT INTO `jldj` VALUES ('2','省（部级）');
INSERT INTO `jldj` VALUES ('3','市级');

#
# Source for table jsdj
#

DROP TABLE IF EXISTS `jsdj`;
CREATE TABLE `jsdj` (
  `jsdjbm` char(1) NOT NULL DEFAULT '' COMMENT '技术等级编码',
  `jsdjmc` varchar(20) DEFAULT NULL COMMENT '技术等级名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='技术等级编码表';

#
# Dumping data for table jsdj
#

INSERT INTO `jsdj` VALUES ('1','技师');
INSERT INTO `jsdj` VALUES ('2','高级工');
INSERT INTO `jsdj` VALUES ('3','中级工');
INSERT INTO `jsdj` VALUES ('4','初级工');
INSERT INTO `jsdj` VALUES ('5','普工');

#
# Source for table jtcy
#

DROP TABLE IF EXISTS `jtcy`;
CREATE TABLE `jtcy` (
  `jtcyid` int(11) NOT NULL AUTO_INCREMENT COMMENT '家庭成员ID',
  `cw` varchar(8) DEFAULT '' COMMENT '称谓',
  `xm` varchar(30) DEFAULT '' COMMENT '姓名',
  `csny` varchar(10) DEFAULT '' COMMENT '出生年月',
  `zzmm` char(2) DEFAULT '' COMMENT '政治面貌编码',
  `gzdw` varchar(30) DEFAULT '' COMMENT '工作单位',
  `zw` varchar(20) DEFAULT '' COMMENT '职务',
  `ryid` int(11) DEFAULT NULL COMMENT '人员ID',
  `username` varchar(20) DEFAULT '' COMMENT '操作员用户名',
  PRIMARY KEY (`jtcyid`),
  KEY `ryid` (`ryid`)
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8 COMMENT='家庭成员';

#
# Dumping data for table jtcy
#

INSERT INTO `jtcy` VALUES (62,'cc','cc','1999.12.12','群众','cxxz','xc',1,'');
INSERT INTO `jtcy` VALUES (81,'cc','cc','1999-12-12','02','ccc','ccc',1,'');
INSERT INTO `jtcy` VALUES (83,'寻影','寻影','1998.1.1','01','寻影','寻影',1,'');
INSERT INTO `jtcy` VALUES (84,'xx','zz','1997.2.2','01','zz','zz',2,'');
INSERT INTO `jtcy` VALUES (109,'影子','寻影','1999-12-12','01','中国电信','职工',1,'');
INSERT INTO `jtcy` VALUES (110,'影子1','寻影','1999-12-12','02','中国电信','职工',1,'');
INSERT INTO `jtcy` VALUES (135,'弟弟','李冬','2000.12.12','02','卫生所','所长',27,'');
INSERT INTO `jtcy` VALUES (136,'','','','02','','',1,'');
INSERT INTO `jtcy` VALUES (137,'','','','02','','',1,'');
INSERT INTO `jtcy` VALUES (138,'','','','02','','',1,'');
INSERT INTO `jtcy` VALUES (139,'','','','','','',1,'');
INSERT INTO `jtcy` VALUES (140,'','','','','','',1,'');
INSERT INTO `jtcy` VALUES (141,'','','','02','','',1,'');
INSERT INTO `jtcy` VALUES (142,'','','','02','','',24,'');
INSERT INTO `jtcy` VALUES (143,'','','','02','','',24,'');
INSERT INTO `jtcy` VALUES (144,'妻子','张三','65.09','03','asdasdasd','',4,'');
INSERT INTO `jtcy` VALUES (145,'父亲','周润发','1956-3-23','03','','',26,'');
INSERT INTO `jtcy` VALUES (146,'母亲','张一花','1957-12-20','03','','',26,'');

#
# Source for table ncrclb
#

DROP TABLE IF EXISTS `ncrclb`;
CREATE TABLE `ncrclb` (
  `ncrclbbm` char(1) NOT NULL DEFAULT '' COMMENT '农村人才类别编码',
  `ncrclbmc` varchar(20) DEFAULT NULL COMMENT '农村人才类别名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='农村人才类别';

#
# Dumping data for table ncrclb
#

INSERT INTO `ncrclb` VALUES ('1','生产型');
INSERT INTO `ncrclb` VALUES ('2','经营型');
INSERT INTO `ncrclb` VALUES ('3','技能服务型');
INSERT INTO `ncrclb` VALUES ('4','技能带动型');
INSERT INTO `ncrclb` VALUES ('5','社会服务型');

#
# Source for table ncsyrc
#

DROP TABLE IF EXISTS `ncsyrc`;
CREATE TABLE `ncsyrc` (
  `ncsyrcid` int(11) NOT NULL AUTO_INCREMENT COMMENT '农村实用人才ID',
  `sfwcdg` bit(1) DEFAULT b'0' COMMENT '是否外出务工半年',
  `jldjbm` char(1) DEFAULT '' COMMENT '奖励等级编码',
  `rclbbm` char(1) DEFAULT '' COMMENT '人才类别编码',
  `sncsrbm` char(1) DEFAULT '' COMMENT '上年纯收入编码',
  `ryid` int(11) DEFAULT NULL COMMENT '人员ID',
  `username` varchar(20) DEFAULT '' COMMENT '操作人员用户名',
  PRIMARY KEY (`ncsyrcid`),
  KEY `ryid` (`ryid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='农村实用人才';

#
# Dumping data for table ncsyrc
#

INSERT INTO `ncsyrc` VALUES (5,b'1','2','1','6',1,'');
INSERT INTO `ncsyrc` VALUES (10,b'1','1','2','4',2,'');
INSERT INTO `ncsyrc` VALUES (13,b'0','3','5','6',24,'');
INSERT INTO `ncsyrc` VALUES (15,b'1','2','2','4',26,'');
INSERT INTO `ncsyrc` VALUES (16,b'1','1','2','1',30,'');

#
# Source for table qx
#

DROP TABLE IF EXISTS `qx`;
CREATE TABLE `qx` (
  `qxid` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `userid` varchar(11) DEFAULT NULL COMMENT '用户ID',
  `czdwid` int(11) DEFAULT NULL COMMENT '有权操作的单位ID',
  PRIMARY KEY (`qxid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

#
# Dumping data for table qx
#


#
# Source for table qyglrc
#

DROP TABLE IF EXISTS `qyglrc`;
CREATE TABLE `qyglrc` (
  `qyglrcid` int(11) NOT NULL AUTO_INCREMENT COMMENT '企业管理人才id',
  `xxjl` text COMMENT '学习简历',
  `gzjl` text COMMENT '工作简历',
  `zyjcqk` text COMMENT '主要奖惩情况',
  `ryid` int(11) DEFAULT NULL COMMENT '人员id',
  `username` varchar(20) DEFAULT NULL COMMENT '操作员姓名',
  PRIMARY KEY (`qyglrcid`),
  KEY `ryid` (`ryid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='企业管理人才';

#
# Dumping data for table qyglrc
#

INSERT INTO `qyglrc` VALUES (1,NULL,NULL,NULL,47,NULL);
INSERT INTO `qyglrc` VALUES (2,NULL,NULL,NULL,26,'');

#
# Source for table ryjl
#

DROP TABLE IF EXISTS `ryjl`;
CREATE TABLE `ryjl` (
  `ryjlid` int(11) NOT NULL AUTO_INCREMENT COMMENT '荣誉奖励ID',
  `ryjlmc` varchar(60) DEFAULT '' COMMENT '荣誉奖励名称',
  `syjg` varchar(30) DEFAULT '' COMMENT '授予机关',
  `sj` varchar(10) DEFAULT '' COMMENT '时间',
  `ryid` int(11) DEFAULT NULL COMMENT '人员ID',
  `username` varchar(20) DEFAULT NULL COMMENT '操作员用户名',
  PRIMARY KEY (`ryjlid`),
  KEY `ryid` (`ryid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='荣誉奖励';

#
# Dumping data for table ryjl
#

INSERT INTO `ryjl` VALUES (10,'技能一等奖','黄石技能学校','2012-11-10',1,NULL);
INSERT INTO `ryjl` VALUES (11,'优秀员工','湖北师范','2015-4',27,NULL);

#
# Source for table shgzrc
#

DROP TABLE IF EXISTS `shgzrc`;
CREATE TABLE `shgzrc` (
  `shgzrcid` int(11) NOT NULL AUTO_INCREMENT COMMENT '社会工作人才id',
  `cszy` varchar(30) DEFAULT NULL COMMENT '从事专业',
  `gzgw` varchar(30) DEFAULT NULL COMMENT '工作岗位',
  `bz` varchar(255) DEFAULT NULL COMMENT '备注',
  `ryid` int(11) DEFAULT NULL COMMENT '人员id',
  `username` varchar(20) DEFAULT NULL COMMENT '操作员姓名',
  PRIMARY KEY (`shgzrcid`),
  KEY `ryid` (`ryid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='社会工作人才';

#
# Dumping data for table shgzrc
#

INSERT INTO `shgzrc` VALUES (1,NULL,NULL,NULL,47,NULL);
INSERT INTO `shgzrc` VALUES (2,NULL,NULL,NULL,27,NULL);

#
# Source for table sshy
#

DROP TABLE IF EXISTS `sshy`;
CREATE TABLE `sshy` (
  `sshybm` char(2) NOT NULL DEFAULT '' COMMENT '所属行业编码',
  `sshymc` varchar(20) DEFAULT '' COMMENT '所属行业名称',
  PRIMARY KEY (`sshybm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='所属行业';

#
# Dumping data for table sshy
#

INSERT INTO `sshy` VALUES ('00','');
INSERT INTO `sshy` VALUES ('01','工业');
INSERT INTO `sshy` VALUES ('02','建筑业');
INSERT INTO `sshy` VALUES ('03','旅游业');
INSERT INTO `sshy` VALUES ('04','交通运输业');
INSERT INTO `sshy` VALUES ('05','商贸流通');
INSERT INTO `sshy` VALUES ('06','其他');

#
# Source for table ssxt
#

DROP TABLE IF EXISTS `ssxt`;
CREATE TABLE `ssxt` (
  `ssxtbm` char(2) NOT NULL DEFAULT '' COMMENT '所属系统编码',
  `ssxtmc` varchar(20) DEFAULT '' COMMENT '所属系统名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='所属系统';

#
# Dumping data for table ssxt
#

INSERT INTO `ssxt` VALUES ('00','');
INSERT INTO `ssxt` VALUES ('01','党委');
INSERT INTO `ssxt` VALUES ('02','政府');
INSERT INTO `ssxt` VALUES ('03','司法');
INSERT INTO `ssxt` VALUES ('04','卫生');
INSERT INTO `ssxt` VALUES ('05','教育');

#
# Source for table xl
#

DROP TABLE IF EXISTS `xl`;
CREATE TABLE `xl` (
  `xlbm` char(1) DEFAULT '' COMMENT '学历编码',
  `xlmc` varchar(20) DEFAULT NULL COMMENT '学历名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学历编码表';

#
# Dumping data for table xl
#

INSERT INTO `xl` VALUES ('1','博士研究生');
INSERT INTO `xl` VALUES ('2','硕士研究生');
INSERT INTO `xl` VALUES ('3','本科');
INSERT INTO `xl` VALUES ('4','专科');
INSERT INTO `xl` VALUES ('5','高中（中专）');
INSERT INTO `xl` VALUES ('6','初中以下');

#
# Source for table xmfl
#

DROP TABLE IF EXISTS `xmfl`;
CREATE TABLE `xmfl` (
  `xmflbm` char(2) NOT NULL DEFAULT '' COMMENT '项目分类编码',
  `xmflmc` varchar(20) DEFAULT '' COMMENT '项目分类名称',
  PRIMARY KEY (`xmflbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目分类';

#
# Dumping data for table xmfl
#

INSERT INTO `xmfl` VALUES ('00','');
INSERT INTO `xmfl` VALUES ('01','民政系统');
INSERT INTO `xmfl` VALUES ('02','律师');
INSERT INTO `xmfl` VALUES ('03','社会管理');
INSERT INTO `xmfl` VALUES ('04','其他');

#
# Source for table xqgw
#

DROP TABLE IF EXISTS `xqgw`;
CREATE TABLE `xqgw` (
  `xqgwid` int(11) NOT NULL AUTO_INCREMENT COMMENT '需求岗位ID',
  `dwid` int(11) DEFAULT NULL COMMENT '单位ID',
  `fbsj` date DEFAULT NULL COMMENT '发布时间',
  `xqgw` varchar(100) DEFAULT NULL COMMENT '需求岗位',
  `zy` varchar(100) DEFAULT NULL COMMENT '专业',
  `xlbm` char(1) DEFAULT NULL COMMENT '学历编码',
  `xwbm` char(1) DEFAULT NULL COMMENT '学位编码',
  `rs` int(5) DEFAULT '0' COMMENT '人数',
  `yjfs` varchar(255) DEFAULT '' COMMENT '引进方式',
  `gwyq` varchar(255) DEFAULT '' COMMENT '岗位要求',
  `dy` varchar(255) DEFAULT '' COMMENT '待遇',
  `username` varchar(20) DEFAULT NULL COMMENT '操作员姓名',
  PRIMARY KEY (`xqgwid`),
  KEY `gzdw` (`dwid`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='需求岗位';

#
# Dumping data for table xqgw
#

INSERT INTO `xqgw` VALUES (3,2,'2012-03-03','教师','IT','1','3',0,'dfd','15','dfd',NULL);
INSERT INTO `xqgw` VALUES (17,2,'2012-03-03','仙人','好好','1','1',4,'hhhhhhhh','mmmmmmmm','111111',NULL);
INSERT INTO `xqgw` VALUES (18,2,'2012-01-07','IT民工','计算机','3','1',0,'外院','不许吃饭','饿两天',NULL);
INSERT INTO `xqgw` VALUES (21,2,NULL,'adfADdfasgafsgdsgfg','fd','1','1',14,'ASDFSAD','SADFAS','ASDFAD',NULL);
INSERT INTO `xqgw` VALUES (22,23,'2015-05-01','IT','计算机','1','1',2,'ASDFSAD','精通java web','面议',NULL);

#
# Source for table xw
#

DROP TABLE IF EXISTS `xw`;
CREATE TABLE `xw` (
  `xwbm` char(1) NOT NULL DEFAULT '' COMMENT '学位名称',
  `xwmc` varchar(20) DEFAULT '' COMMENT '学位名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学位编码表';

#
# Dumping data for table xw
#

INSERT INTO `xw` VALUES ('0','');
INSERT INTO `xw` VALUES ('1','博士');
INSERT INTO `xw` VALUES ('3','硕士');
INSERT INTO `xw` VALUES ('5','学士');

#
# Source for table yh
#

DROP TABLE IF EXISTS `yh`;
CREATE TABLE `yh` (
  `userid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(20) DEFAULT '' COMMENT '用户名',
  `jb` varchar(1) DEFAULT NULL COMMENT '级别 1.市级  2:区级 3:企业级',
  `lb` char(1) DEFAULT NULL COMMENT '类别 1.市级管理员 2.普通用户',
  `mm` varchar(32) DEFAULT NULL COMMENT '密码',
  `gzdwid` int(11) DEFAULT NULL COMMENT '工作单位ID',
  `xm` varchar(20) DEFAULT '' COMMENT '姓名',
  `lxdh` varchar(20) DEFAULT '' COMMENT '联系电话',
  PRIMARY KEY (`userid`),
  KEY `gzdwid` (`gzdwid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Dumping data for table yh
#

INSERT INTO `yh` VALUES (1,'xlqputong','2','2','e10adc3949ba59abbe56e057f20f883e',24,'赵壁泉','');
INSERT INTO `yh` VALUES (2,'super','1','1','e10adc3949ba59abbe56e057f20f883e',2,'员星博','');
INSERT INTO `yh` VALUES (3,'xssqadmin','2','1','e10adc3949ba59abbe56e057f20f883e',23,'焦娟平(西塞山)','');
INSERT INTO `yh` VALUES (4,'shiputong','1','2','e10adc3949ba59abbe56e057f20f883e',22,'百里璋','');
INSERT INTO `yh` VALUES (5,'xssqputong','2','2','e10adc3949ba59abbe56e057f20f883e',23,'郑韩韩(西塞山)','');
INSERT INTO `yh` VALUES (6,'qiyeputong','3','2','e10adc3949ba59abbe56e057f20f883e',27,'姒冰巧','');
INSERT INTO `yh` VALUES (7,'xlqadmin','2','1','e10adc3949ba59abbe56e057f20f883e',24,'郎晓蕾(下陆)','');
INSERT INTO `yh` VALUES (8,'xlqputong','2','2','e10adc3949ba59abbe56e057f20f883e',24,'宁雯艳(下陆)','');
INSERT INTO `yh` VALUES (11,'xssqadmin1','2','1','e10adc3949ba59abbe56e057f20f883e',23,'弓泊宁',NULL);
INSERT INTO `yh` VALUES (12,'shiputong1','1','2','e10adc3949ba59abbe56e057f20f883e',22,'骑俊博','024-2569341');
INSERT INTO `yh` VALUES (13,'xlqputong','2','1','e10adc3949ba59abbe56e057f20f883e',24,'杨曦山',NULL);
INSERT INTO `yh` VALUES (16,'shiputong2','1','2','e10adc3949ba59abbe56e057f20f883e',22,'刘石东',NULL);
INSERT INTO `yh` VALUES (17,'qiyeadmin','3','1','e10adc3949ba59abbe56e057f20f883e',27,'赵天驰',NULL);
INSERT INTO `yh` VALUES (18,'admin','1','1','e10adc3949ba59abbe56e057f20f883e',2,'黄婷','');
INSERT INTO `yh` VALUES (20,'dfasdfa','1','2','e10adc3949ba59abbe56e057f20f883e',22,'Hello','15107233955');
INSERT INTO `yh` VALUES (21,'AAB','2','1','670b14728ad9902aecba32e22fa4f6bd',23,'张三','18271621111');

#
# Source for table zc
#

DROP TABLE IF EXISTS `zc`;
CREATE TABLE `zc` (
  `zcbm` char(1) NOT NULL DEFAULT '' COMMENT '职称编码',
  `zcmc` varchar(20) DEFAULT NULL COMMENT '职称名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职称编码表';

#
# Dumping data for table zc
#

INSERT INTO `zc` VALUES ('1','正高级');
INSERT INTO `zc` VALUES ('2','副高级');
INSERT INTO `zc` VALUES ('3','中级');
INSERT INTO `zc` VALUES ('4','初级');
INSERT INTO `zc` VALUES ('5','未评定职称');

#
# Source for table zclb
#

DROP TABLE IF EXISTS `zclb`;
CREATE TABLE `zclb` (
  `zclbbm` char(2) NOT NULL DEFAULT '' COMMENT '职称类别编码',
  `zclbmc` varchar(20) DEFAULT NULL COMMENT '职称类别名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职称类别编码表';

#
# Dumping data for table zclb
#

INSERT INTO `zclb` VALUES ('00',NULL);
INSERT INTO `zclb` VALUES ('01','工程');
INSERT INTO `zclb` VALUES ('02','卫生');
INSERT INTO `zclb` VALUES ('03','教育');
INSERT INTO `zclb` VALUES ('04','其他');

#
# Source for table zgzs
#

DROP TABLE IF EXISTS `zgzs`;
CREATE TABLE `zgzs` (
  `zgzsid` int(11) NOT NULL AUTO_INCREMENT COMMENT '资格证书ID',
  `gz` varchar(30) DEFAULT '' COMMENT '工种',
  `dj` varchar(20) DEFAULT '' COMMENT '等级',
  `zsh` varchar(20) DEFAULT '' COMMENT '证书号',
  `fzsj` varchar(10) DEFAULT '' COMMENT '发证时间',
  `fzjg` varchar(30) DEFAULT '' COMMENT '发证机关',
  `ryid` int(11) DEFAULT NULL COMMENT '人员ID',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`zgzsid`),
  KEY `ryid` (`ryid`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='资格证书';

#
# Dumping data for table zgzs
#

INSERT INTO `zgzs` VALUES (23,'技能2','3级','1111','2012-11-11','大冶集团',1,NULL);
INSERT INTO `zgzs` VALUES (27,'的方法方法','的','是打发斯蒂芬','的方法方法','的方法方法',26,NULL);
INSERT INTO `zgzs` VALUES (28,'教师','高级','123124','2015-4','湖北电力',27,NULL);
INSERT INTO `zgzs` VALUES (29,'12','高级','123123','2015-5','人事局',33,NULL);

#
# Source for table zj
#

DROP TABLE IF EXISTS `zj`;
CREATE TABLE `zj` (
  `zjbm` char(1) NOT NULL DEFAULT '' COMMENT '职级编码',
  `zjmc` varchar(20) DEFAULT NULL COMMENT '职级名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职级编码编码表';

#
# Dumping data for table zj
#

INSERT INTO `zj` VALUES ('0',NULL);
INSERT INTO `zj` VALUES ('1','地市级');
INSERT INTO `zj` VALUES ('2','副地市级');
INSERT INTO `zj` VALUES ('3','县处级');
INSERT INTO `zj` VALUES ('4','副县处级');
INSERT INTO `zj` VALUES ('5','乡镇（科）');
INSERT INTO `zj` VALUES ('6','乡镇（副科）');

#
# Source for table zyjsrc
#

DROP TABLE IF EXISTS `zyjsrc`;
CREATE TABLE `zyjsrc` (
  `zyjsrcid` int(11) NOT NULL AUTO_INCREMENT COMMENT '专业技术人才ID',
  `cszy` varchar(30) DEFAULT '' COMMENT '从事专业',
  `gzgw` varchar(20) DEFAULT '' COMMENT '工作岗位',
  `zclbbm` char(2) DEFAULT '' COMMENT '职称类别编码',
  `jszcbm` char(1) DEFAULT NULL COMMENT '技术职称编码',
  `hjcq` text COMMENT '获奖成果',
  `ryid` int(11) DEFAULT NULL COMMENT '人员ID',
  `username` varchar(20) DEFAULT '' COMMENT '操作员用户名',
  PRIMARY KEY (`zyjsrcid`),
  KEY `ryid` (`ryid`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='专业技术人才';

#
# Dumping data for table zyjsrc
#

INSERT INTO `zyjsrc` VALUES (2,'计算机','为全文','02','4','<p>2222222222</p>\n',2,'222222222');
INSERT INTO `zyjsrc` VALUES (3,'计算机','软件开发','',NULL,'1dfdsfdfaaaaaaaaaaaaaa',4,'');
INSERT INTO `zyjsrc` VALUES (34,'222222','222222222','','1','2222222222222',27,'');
INSERT INTO `zyjsrc` VALUES (39,'1','IY','01','1','<p style=\"text-align: center;\"><strong>二等奖</strong></p>\n',24,'');
INSERT INTO `zyjsrc` VALUES (40,'javaweb','studio','02','4','<p style=\"text-align: center;\"><span style=\"color:#FF0000;\">曾经把全国各等奖都拿了&nbsp; 就是没拿到一等奖</span></p>\n',30,'');
INSERT INTO `zyjsrc` VALUES (42,'java','java','03','1','<p><strong>ffffadfa</strong></p>\n',34,'');
INSERT INTO `zyjsrc` VALUES (43,'1','2','02','1','<p>获得许多项国家奖项</p>\n',1,'');

#
# Source for table zzmm
#

DROP TABLE IF EXISTS `zzmm`;
CREATE TABLE `zzmm` (
  `zzmmbm` char(2) NOT NULL DEFAULT '' COMMENT '政治面貌',
  `zzmmmc` varchar(10) DEFAULT '' COMMENT '政治面貌名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='政治面貌编码表';

#
# Dumping data for table zzmm
#

INSERT INTO `zzmm` VALUES ('01','中共党员');
INSERT INTO `zzmm` VALUES ('02','民主党派');
INSERT INTO `zzmm` VALUES ('03','群众');

#
# Source for procedure proc_test
#

DROP PROCEDURE IF EXISTS `proc_test`;
CREATE DEFINER=`root`@`%` PROCEDURE `proc_test`()
BEGIN
  INSERT into gzdw(dwmc) values('11151');  
  select last_insert_id();
END;


#
#  Foreign keys for table cyrc
#

ALTER TABLE `cyrc`
ADD CONSTRAINT `cyrc_ibfk_1` FOREIGN KEY (`ryid`) REFERENCES `jbxx` (`ryid`) ON DELETE CASCADE ON UPDATE CASCADE;

#
#  Foreign keys for table dzrc
#

ALTER TABLE `dzrc`
ADD CONSTRAINT `dzrc_ibfk_1` FOREIGN KEY (`ryid`) REFERENCES `jbxx` (`ryid`) ON DELETE CASCADE ON UPDATE CASCADE;

#
#  Foreign keys for table jl
#

ALTER TABLE `jl`
ADD CONSTRAINT `jl_ibfk_1` FOREIGN KEY (`ryid`) REFERENCES `jbxx` (`ryid`) ON DELETE CASCADE ON UPDATE CASCADE;

#
#  Foreign keys for table jtcy
#

ALTER TABLE `jtcy`
ADD CONSTRAINT `jtcy_ibfk_1` FOREIGN KEY (`ryid`) REFERENCES `jbxx` (`ryid`) ON DELETE CASCADE ON UPDATE CASCADE;

#
#  Foreign keys for table ncsyrc
#

ALTER TABLE `ncsyrc`
ADD CONSTRAINT `ncsyrc_ibfk_1` FOREIGN KEY (`ryid`) REFERENCES `jbxx` (`ryid`) ON DELETE CASCADE ON UPDATE CASCADE;

#
#  Foreign keys for table qyglrc
#

ALTER TABLE `qyglrc`
ADD CONSTRAINT `qyglrc_ibfk_1` FOREIGN KEY (`ryid`) REFERENCES `jbxx` (`ryid`) ON DELETE CASCADE ON UPDATE CASCADE;

#
#  Foreign keys for table ryjl
#

ALTER TABLE `ryjl`
ADD CONSTRAINT `ryjl_ibfk_1` FOREIGN KEY (`ryid`) REFERENCES `jbxx` (`ryid`) ON DELETE CASCADE ON UPDATE CASCADE;

#
#  Foreign keys for table shgzrc
#

ALTER TABLE `shgzrc`
ADD CONSTRAINT `shgzrc_ibfk_1` FOREIGN KEY (`ryid`) REFERENCES `jbxx` (`ryid`) ON DELETE CASCADE ON UPDATE CASCADE;

#
#  Foreign keys for table xqgw
#

ALTER TABLE `xqgw`
ADD CONSTRAINT `gzdw` FOREIGN KEY (`dwid`) REFERENCES `gzdw` (`gzdwid`);

#
#  Foreign keys for table yh
#

ALTER TABLE `yh`
ADD CONSTRAINT `gzdwid` FOREIGN KEY (`gzdwid`) REFERENCES `gzdw` (`gzdwid`);

#
#  Foreign keys for table zgzs
#

ALTER TABLE `zgzs`
ADD CONSTRAINT `zgzs_ibfk_1` FOREIGN KEY (`ryid`) REFERENCES `jbxx` (`ryid`) ON DELETE CASCADE ON UPDATE CASCADE;

#
#  Foreign keys for table zyjsrc
#

ALTER TABLE `zyjsrc`
ADD CONSTRAINT `zyjsrc_ibfk_1` FOREIGN KEY (`ryid`) REFERENCES `jbxx` (`ryid`) ON DELETE CASCADE ON UPDATE CASCADE;


/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
