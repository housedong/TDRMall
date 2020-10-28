/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50722
Source Host           : 127.0.0.1:3306
Source Database       : mall

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-09-27 18:58:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('1', 'admin', 'admin');
INSERT INTO `admin_user` VALUES ('2', 'hfb', 'hfb');

-- ----------------------------
-- Table structure for classification
-- ----------------------------
DROP TABLE IF EXISTS `classification`;
CREATE TABLE `classification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classification
-- ----------------------------
INSERT INTO `classification` VALUES ('1', '平板电视', '0', '1');
INSERT INTO `classification` VALUES ('2', '电脑', null, '1');
INSERT INTO `classification` VALUES ('3', '手机', null, '1');
INSERT INTO `classification` VALUES ('4', '空调', '0', '1');
INSERT INTO `classification` VALUES ('5', '笔记本', '2', '2');
INSERT INTO `classification` VALUES ('6', '4K超高清', '1', '2');
INSERT INTO `classification` VALUES ('7', '台式机a', '2', '2');
INSERT INTO `classification` VALUES ('9', '洗衣机', '0', '1');
INSERT INTO `classification` VALUES ('10', '智能', '4', '2');
INSERT INTO `classification` VALUES ('11', '智能手机', '3', '2');
INSERT INTO `classification` VALUES ('12', '海尔', '4', '2');
INSERT INTO `classification` VALUES ('14', '滚筒', '9', '2');
INSERT INTO `classification` VALUES ('15', '冰箱', '0', '1');
INSERT INTO `classification` VALUES ('16', '双开门', '15', '2');

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addr` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `vip` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('1', '广州商学院', '1367837161@qq.com', '孙德望', '13600069406', '0');
INSERT INTO `client` VALUES ('2', '黄浦区', 'happysundewang@163.com', '杨顺东', '17306697905', '0');
INSERT INTO `client` VALUES ('3', '黄浦区', 'happysundewang@163.com', '杨顺东', '17306697905', '1');
INSERT INTO `client` VALUES ('4', '12', '957778483@qq.com', '张越', '18578899568', '1');
INSERT INTO `client` VALUES ('5', '广东省潮州市', '957778483@qq.com', '叶依侬', '13652824915', '1');
INSERT INTO `client` VALUES ('6', '广东省潮州市', '957778483@qq.com', '叶依侬', '13652824915', '1');
INSERT INTO `client` VALUES ('7', '广东潮州', '9577784833@qq.com', '叶依侬', '13652824915', '1');

-- ----------------------------
-- Table structure for count
-- ----------------------------
DROP TABLE IF EXISTS `count`;
CREATE TABLE `count` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `earnings` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of count
-- ----------------------------
INSERT INTO `count` VALUES ('1', '3', '曾涛涛', '2019-11-25 00:00:00', '26988', '41962', '14974');
INSERT INTO `count` VALUES ('2', '3', '曾涛涛', '2019-11-26 00:00:00', '5000', '5999', '999');
INSERT INTO `count` VALUES ('3', '4', '杨顺东', '2020-06-09 00:00:00', '12000', '14887', '2887');
INSERT INTO `count` VALUES ('4', '4', '杨顺东', '2020-07-24 00:00:00', '5000', '5999', '999');
INSERT INTO `count` VALUES ('5', '4', '杨顺东', '2020-09-15 00:00:00', '5000', '8888', '3888');
INSERT INTO `count` VALUES ('6', '4', '杨顺东', '2020-09-21 00:00:00', '15030', '26773', '11743');
INSERT INTO `count` VALUES ('18', '4', '杨顺东', '2020-09-22 00:00:00', '20030', '35661', '15631');
INSERT INTO `count` VALUES ('27', '3', '曾涛涛', '2020-09-24 00:00:00', '42988', '61958', '18970');
INSERT INTO `count` VALUES ('28', '4', '杨顺东', '2020-09-24 00:00:00', '62284', '118947', '56663');
INSERT INTO `count` VALUES ('29', '4', '杨顺东', '2020-09-27 00:00:00', '10000', '16709', '6709');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', '广州商学院', '小明', '2019-11-25 19:23:48', '12345654111', '4', '8888', '3', '5000');
INSERT INTO `order` VALUES ('2', '广州商学院', 'tom', '2019-11-25 22:10:39', '12345689411', '3', '17998', '3', '10000');
INSERT INTO `order` VALUES ('4', '广州商学院', 'tomcat', '2019-11-25 23:35:01', '12345678781', '4', '8999', '3', '7000');
INSERT INTO `order` VALUES ('5', '广州商学院', 'Hfb', '2019-11-26 02:53:14', '18679657949', '4', '5999', '3', '5000');
INSERT INTO `order` VALUES ('12', '广东省惠州市', '杨女士', '2020-06-09 21:40:54', '19924229857', '4', '14887', '4', '12000');
INSERT INTO `order` VALUES ('17', '广州商学院', '杨先生', '2020-07-24 22:47:38', '12345648979', '3', '5999', '4', '5000');
INSERT INTO `order` VALUES ('18', '广州商学院', '陈哲', '2020-09-24 20:10:54', '12345678900', '1', '8888', '4', '5000');
INSERT INTO `order` VALUES ('19', '广州商学院', '张婷婷', '2020-09-15 09:30:45', '12345678911', '1', '8888', '4', '5000');
INSERT INTO `order` VALUES ('20', '广州商学院', '杨先生', '2020-09-21 09:32:44', '19924229857', '1', '17885', '4', '10030');
INSERT INTO `order` VALUES ('21', '广州商学院', '孙德望', '2020-09-21 20:03:12', '12344566711', '1', '8888', '4', '5000');
INSERT INTO `order` VALUES ('23', '广州商学院', '杨同学', '2020-09-22 13:37:53', '12345678901', '1', '8888', '4', '5000');
INSERT INTO `order` VALUES ('24', '广东', 'dood', '2020-09-22 13:38:44', '21878219468', '1', '17776', '4', '10000');
INSERT INTO `order` VALUES ('25', '广东', '张三', '2020-09-22 16:48:47', '12345678900', '1', '5999', '3', '5000');
INSERT INTO `order` VALUES ('29', '广州商学院', 'yangshu', '2020-09-22 17:59:47', '12345678900', '1', '2998', '4', '1998');
INSERT INTO `order` VALUES ('31', '广州商学院', '张先生', '2020-09-26 20:18:33', '13652824915', '2', '10987.68', '69', '7000');
INSERT INTO `order` VALUES ('32', '广州商学院', '张老板', '2020-09-26 20:19:04', '12345678900', '4', '10987.68', '69', '7000');
INSERT INTO `order` VALUES ('33', '广州商学院', '叶子', '2020-09-26 20:22:23', '12345678900', '4', '6332.48', '69', '4000');
INSERT INTO `order` VALUES ('34', '广东', '杨顺东', '2020-09-26 20:35:06', '16371101231', '1', '1583.12', '70', '1000');
INSERT INTO `order` VALUES ('35', '广州商学院', 'das', '2020-09-26 20:37:10', '12314567891', '1', '1799', '4', '1000');
INSERT INTO `order` VALUES ('36', '广东', '杨顺东', '2020-09-26 20:59:17', '12345678910', '1', '8888', '70', '5000');
INSERT INTO `order` VALUES ('37', '广东', '杨顺东', '2020-09-27 00:14:35', '18578899568', '4', '3166.2400000000002', '69', '2000');
INSERT INTO `order` VALUES ('38', '广商', '叶', '2020-09-27 00:52:00', '2323', '3', '5804.4800000000005', '69', '3998');
INSERT INTO `order` VALUES ('39', '非会员测试', '非会员测试', '2020-09-27 14:55:30', '13899999999', '3', '8888', '4', '5000');
INSERT INTO `order` VALUES ('40', '会员测试', '会员测试', '2020-09-27 15:00:33', '17399999999', '1', '7821.44', '4', '5000');

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `sub_total` double DEFAULT NULL,
  `sub_cost` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES ('1', '1', '1', '10', '8888', '5000');
INSERT INTO `order_item` VALUES ('2', '2', '2', '9', '17998', '10000');
INSERT INTO `order_item` VALUES ('3', '2', '3', '11', '78', '60');
INSERT INTO `order_item` VALUES ('4', '1', '3', '13', '5999', '5000');
INSERT INTO `order_item` VALUES ('5', '1', '4', '9', '8999', '5000');
INSERT INTO `order_item` VALUES ('6', '1', '5', '13', '5999', '5000');
INSERT INTO `order_item` VALUES ('7', '1', '6', '12', '3999', '3000');
INSERT INTO `order_item` VALUES ('8', '1', '7', '12', '3999', '3000');
INSERT INTO `order_item` VALUES ('9', '1', '8', '12', '3999', '3000');
INSERT INTO `order_item` VALUES ('10', '1', '9', '10', '8888', '5000');
INSERT INTO `order_item` VALUES ('11', '1', '9', '13', '5999', '5000');
INSERT INTO `order_item` VALUES ('12', '1', '10', '9', '1', '1');
INSERT INTO `order_item` VALUES ('13', '1', '11', '9', '1', '1');
INSERT INTO `order_item` VALUES ('14', '1', '10', '13', '5999', '5000');
INSERT INTO `order_item` VALUES ('15', '1', '11', '13', '5999', '5000');
INSERT INTO `order_item` VALUES ('16', '1', '12', '10', '8888', '5000');
INSERT INTO `order_item` VALUES ('17', '1', '13', '9', '1', '1');
INSERT INTO `order_item` VALUES ('18', '1', '14', '9', '1', '1');
INSERT INTO `order_item` VALUES ('19', '1', '15', '9', '1', '1');
INSERT INTO `order_item` VALUES ('20', '1', '9', '9', '1', '1');
INSERT INTO `order_item` VALUES ('21', '1', '10', '10', '8888', '5000');
INSERT INTO `order_item` VALUES ('22', '1', '11', '13', '5999', '5000');
INSERT INTO `order_item` VALUES ('23', '1', '12', '10', '8888', '5000');
INSERT INTO `order_item` VALUES ('24', '1', '12', '13', '5999', '5000');
INSERT INTO `order_item` VALUES ('25', '1', '13', '10', '8888', '5000');
INSERT INTO `order_item` VALUES ('26', '1', '13', '13', '5999', '5000');
INSERT INTO `order_item` VALUES ('27', '1', '14', '13', '5999', '5000');
INSERT INTO `order_item` VALUES ('28', '1', '15', '13', '5999', '5000');
INSERT INTO `order_item` VALUES ('29', '1', '16', '13', '5999', '5000');
INSERT INTO `order_item` VALUES ('30', '1', '17', '13', '5999', '5000');
INSERT INTO `order_item` VALUES ('31', '1', '18', '10', '8888', '5000');
INSERT INTO `order_item` VALUES ('32', '1', '19', '10', '8888', '5000');
INSERT INTO `order_item` VALUES ('33', '1', '20', '9', '2998', '1998');
INSERT INTO `order_item` VALUES ('34', '1', '20', '10', '8888', '5000');
INSERT INTO `order_item` VALUES ('35', '1', '20', '13', '5999', '5000');
INSERT INTO `order_item` VALUES ('36', '1', '21', '10', '8888', '5000');
INSERT INTO `order_item` VALUES ('37', '1', '23', '10', '8888', '5000');
INSERT INTO `order_item` VALUES ('38', '2', '24', '10', '17776', '10000');
INSERT INTO `order_item` VALUES ('39', '1', '25', '13', '5999', '5000');
INSERT INTO `order_item` VALUES ('42', '1', '29', '9', '2998', '1998');
INSERT INTO `order_item` VALUES ('53', '1', '31', '10', '8888', '5000');
INSERT INTO `order_item` VALUES ('54', '2', '32', '21', '3598', '2000');
INSERT INTO `order_item` VALUES ('55', '1', '32', '10', '8888', '5000');
INSERT INTO `order_item` VALUES ('56', '3', '33', '21', '7196', '4000');
INSERT INTO `order_item` VALUES ('57', '1', '34', '21', '1799', '1000');
INSERT INTO `order_item` VALUES ('58', '1', '35', '21', '1799', '1000');
INSERT INTO `order_item` VALUES ('59', '1', '36', '10', '8888', '5000');
INSERT INTO `order_item` VALUES ('60', '2', '37', '21', '3598', '2000');
INSERT INTO `order_item` VALUES ('61', '2', '38', '21', '3598', '2000');
INSERT INTO `order_item` VALUES ('62', '1', '38', '9', '2998', '1998');
INSERT INTO `order_item` VALUES ('63', '1', '39', '10', '8888', '5000');
INSERT INTO `order_item` VALUES ('64', '1', '40', '10', '8888', '5000');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `csid` int(11) DEFAULT NULL,
  `desc` varchar(1000) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `is_hot` int(11) DEFAULT NULL,
  `market_price` double DEFAULT NULL,
  `pdate` datetime DEFAULT NULL,
  `shop_price` bigint(20) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('9', '5', '新一代 Surface Pro 比以往更出色，它不仅仅是一台笔记本，还能在工作室模式和平板间灵活切换.\r\n\r\n随心所欲，百变菁英 震撼的 PixelSense™ 显示屏支持触控笔* 和触摸操作，设计方面也有所改进，与 Surface Pro 4 相比，速度和性能都得到了进一步提升，电池续航能力有较大提高。\r\n\r\n无风扇，更安静 灵感随意 随手拈来 \r\n\r\n快捷刷脸登陆 保护隐私 轻松唤醒刷脸登陆 充分保护您的私人数据**** 无论您喜欢摄影、绘画、音乐或创作\r\n\r\n精彩视频\r\nSurface Pro总能满足您诸多创作需求 Surface Pro 酷睿 m3 和 i5 型号配备全新无风扇冷却系统***，\r\ni7 型号改进了混合冷却系统，您可以更安静地工作或播放喜欢的节目。', '/mall/admin/product/img/0B1CDC0C82A79A25A4BA159D88D8AC.jpg', '0', '3998', '2020-09-27 12:17:36', '2998', '101', '微软（Microsoft）新Surface Pro 二合一平板电脑 12.3英寸（Intel Core i5 8G内存 256G存储 ）', '1998');
INSERT INTO `product` VALUES ('10', '11', '一直以来，我们都心存一个设想，期待着能够打造出这样一部 iPhone：它有整面的屏幕，能让你在使用时完全沉浸其中，仿佛忘记了它的存在。它是如此智能，你的一触、一碰、一言、一语，哪怕是轻轻一瞥，都会得到它心有灵犀的回应。而这个设想，终于随着 iPhone X 的到来成为了现实。现在，就跟未来见个面吧。', '/mall/admin/product/img/E98ECEAC9E68BE31BB623419FD0C9E.png', '0', '9999', '2020-09-25 16:30:05', '8888', '95', 'Apple iPhone X (A1865) 64GB 银色 移动联通电信4G手机', '5000');
INSERT INTO `product` VALUES ('13', '7', '原本就拥有强劲的基础性能，能够轻松通吃时下的主流电竞游戏；外观方面整机采用多面切割搭配碳纤铠甲风格，搭配“胜利之眼”游戏氛围灯，凸显电竞元素；最主要的是这是一款UIY电竞主机，机箱内部已经给升级留足了接口和空间，在官方配置的基础上我们还可以进行性能和外观方面的额升级，而且官方配件仍然能在保修范围内。品牌PC厂商参与到PC个性化定制和部件升级服务中来，同时提供品牌厂商一贯的服务优势，完全解决了DIY模式下遇到的种种痛点。不得不说联想拯救者刃 7000的出现，开启了PC UIY时代。', '/mall/admin/product/img/9F6B955F4C732FF96793FC8BB2F244.jpg', '0', '6499', '2020-09-25 16:30:12', '5999', '100', '联想（Lenovo）拯救者刃7000 UIY主机( i7-7700 8G 128G SSD 1T硬盘 GTX1060 Win10)', '5000');
INSERT INTO `product` VALUES ('17', '12', '外观材质：质量不错结实耐用，颜色很正很好看。￼ 静音效果：开了都没有噪音的很安静，特别喜欢海尔大品牌用着都安心，放心，大品牌就是不一样，好好好 功率大小：节能省电，物超所值，电费都省了很多钱呢 制冷 热效果：打开一会就冷了，速度特别的快还有杀菌消毒作用，家里都是用海尔牌的，相信老牌子就是好，服务也特别好！', '/mall/admin/product/img/294243D7100AB5D084E018A0B895DA.jpg', '0', '9999', '2020-09-25 16:33:50', '7999', '99', 'Haier/海尔3匹新一级变频立式家用空调柜机客厅节能自清洁72eds81', '5989');
INSERT INTO `product` VALUES ('18', '14', '颜值被吸引，收到后非常惊喜，声音很小，操作便捷，多种模式，深入人心。还有智能模式，设计的很合理，性价比很高。物超所值的一款洗衣机，容量大，外观漂亮，静音效果也不错，很满意。', '/mall/admin/product/img/4729E705624AE79CA9BC07665D165C.jpg', '1', '2599', '2020-09-25 16:20:18', '1699', '100', '美的全自动家用洗衣机10kg公斤变频滚筒大容量洗脱一体MG100V11D', '1000');
INSERT INTO `product` VALUES ('19', '16', '美的双开冰箱真真是集美貌与质量与一身的冰箱。冰箱特别有质感，还很漂亮，用了一段时间，无论是冷藏还是冷冻都很好用，空间很大，还没有异味，特别是冷冻真的不结霜~~~我们家六楼，是真真的送货上门啊~快递师傅很负责来电话预约送货时间。', '/mall/admin/product/img/E4FC0C840B7BE7C371B1064B48F1E3.jpg', '1', '3599', '2020-09-25 16:22:54', '2799', '100', 'Midea/美的 BCD-450WKZM(E)智能对开门双门家用节能无霜电冰箱', '1899');
INSERT INTO `product` VALUES ('20', '6', '咱们店铺会在特殊节日推出一些大型优惠活动，除此之外店铺不定期也会有一些优惠活动哦，您可以多多关注咱们店铺信息呢，相信您可以享受到咱们的优惠活动呢。很抱歉因为降价问题给您带来了不便，对于您说的问题，您可以来访旺旺客服为您核实您的订单情况哦。您在使用产品过程中有任何问题都可以联系咱们旺旺客服为您解答哦~感谢您的支持，祝您生活愉快~', '/mall/admin/product/img/10FC0E3F4F003E608C0D9242BC940D.jpg', '1', '3999', '2020-09-25 16:31:07', '2999', '100', '小米电视4A65英寸4k超高清液晶屏智能平板家用电视机官方', '1999');
INSERT INTO `product` VALUES ('21', '6', '咱们电视支持2种安装方式，咱们的页面详情也是有说明的，小米电视普通安装非常简单，可自行根据说明书安装。另外支持挂架安装挂在墙上的，申请电视上门挂装需要另外付费，为了节约您的宝贵时间，如您有电视安装的需要，可以来找咱们客服妹子给您预约安装的哦，感谢您对小米的支持，永远相信美好的事情即将发生~', '/mall/admin/product/img/18A92B195F4832A25323FBB1EBA528.jpg', '1', '1999', '2020-09-25 16:32:47', '1799', '106', '【热销爆款】小米电视40英寸Redmi 40 全高清屏智能家用电视官方', '1000');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addr` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `department` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', '江西省 吉安市 泰和县', '8976677657@qq.com', '曾涛涛', '123456', '12345678941', 'ztt', '1');
INSERT INTO `user` VALUES ('4', '广州', '1216735300@qq.com', '杨顺东', '123456', '19924229857', 'ysd', '1');
INSERT INTO `user` VALUES ('66', '广州商学院', '1367837161@qq.com', '孙德望', '123456', '13600069406', 'sdw', '3');
INSERT INTO `user` VALUES ('68', '广东省', '1216735300@qq.com', '黄家豪', '123456', '12345678910', 'hjh', '2');
INSERT INTO `user` VALUES ('69', 'test11', 'test11@qq.com', '张越', '123456', '18578899568', 'test11', '1');
INSERT INTO `user` VALUES ('70', '广东省', '12167353002qq.com', '陈哲', '123456', '12345678910', 'cz', '1');
