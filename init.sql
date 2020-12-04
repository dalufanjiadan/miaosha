-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
	`count` int NOT NULL,
	`sale` int NOT NULL,
	`created_at` datetime(6) DEFAULT CURRENT_TIMESTAMP(6),
	`updated_at` datetime(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
	`deleted_at` datetime(6) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO
	`product` (`id`, `name`, `count`, `sale`)
VALUES
	('1', 'iphone', '50', '0'),
	('2', 'mac', '10', '0');

-- ----------------------------
-- Table structure for order
-- ----------------------------
CREATE TABLE `order` (
	`id` int NOT NULL AUTO_INCREMENT,
	`product_id` int NOT NULL,
	`created_at` datetime(6) DEFAULT CURRENT_TIMESTAMP(6),
	`updated_at` datetime(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
	`deleted_at` datetime(6) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
