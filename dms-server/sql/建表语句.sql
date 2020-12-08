-- 建库
CREATE SCHEMA `dms` ;

-- 建表
-- 用户信息表
CREATE TABLE `dms`.`tb_user` (
  `userid` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `confirmpwd` VARCHAR(45) NULL,
  `role` VARCHAR(45) NULL,
  `department` VARCHAR(45) NULL,
  `sex` CHAR(1) NULL,
  `birthday` DATE NULL,
  `idtype` VARCHAR(45) NULL,
  `idno` VARCHAR(45) NULL,
  `mobile` CHAR(20) NULL,
  `email` VARCHAR(45) NULL,
  `makedate` DATE NULL,
  `maketime` VARCHAR(8) NULL,
  `modifydate` DATE NULL,
  `modifytime` VARCHAR(8) NULL,
  `ext1` DATETIME NULL,
  `ext2` VARCHAR(45) NULL,
  `ext3` VARCHAR(45) NULL,
  PRIMARY KEY (`userid`))
COMMENT = '用户信息表';
