SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `bank` ;
CREATE SCHEMA IF NOT EXISTS `bank` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `bank` ;

-- -----------------------------------------------------
-- Table `bank`.`tbClient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`tbClient` ;

CREATE  TABLE IF NOT EXISTS `bank`.`tbClient` (
  `cid` INT NOT NULL AUTO_INCREMENT ,
  `fname` VARCHAR(45) NOT NULL ,
  `mname` VARCHAR(45) NULL ,
  `lname` VARCHAR(45) NOT NULL ,
  `gender` VARCHAR(6) NOT NULL ,
  `birthday` DATE NOT NULL ,
  `tel` VARCHAR(45) NOT NULL ,
  `add1` VARCHAR(45) NOT NULL ,
  `add2` VARCHAR(45) NULL ,
  `zip` VARCHAR(10) NOT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  `username` VARCHAR(45) NOT NULL ,
  `pw` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`cid`) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bank`.`tbAccountType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`tbAccountType` ;

CREATE  TABLE IF NOT EXISTS `bank`.`tbAccountType` (
  `typeid` INT NOT NULL AUTO_INCREMENT ,
  `typename` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`typeid`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bank`.`tbAccount`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`tbAccount` ;

CREATE  TABLE IF NOT EXISTS `bank`.`tbAccount` (
  `aid` INT NOT NULL AUTO_INCREMENT ,
  `cid` INT NOT NULL ,
  `typeid` INT NOT NULL ,
  `acnumber` VARCHAR(45) NOT NULL ,
  `balance` DECIMAL(20,2) NOT NULL ,
  PRIMARY KEY (`aid`) ,
  UNIQUE INDEX `acnumber_UNIQUE` (`acnumber` ASC) ,
  INDEX `fk_tbAccount_tbClient_idx` (`cid` ASC) ,
  INDEX `fk_tbAccount_tbAccountType1_idx` (`typeid` ASC) ,
  CONSTRAINT `fk_tbAccount_tbClient`
    FOREIGN KEY (`cid` )
    REFERENCES `bank`.`tbClient` (`cid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbAccount_tbAccountType1`
    FOREIGN KEY (`typeid` )
    REFERENCES `bank`.`tbAccountType` (`typeid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bank`.`tbTransactionType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`tbTransactionType` ;

CREATE  TABLE IF NOT EXISTS `bank`.`tbTransactionType` (
  `trtid` INT NOT NULL AUTO_INCREMENT ,
  `trtname` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`trtid`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bank`.`tbTransaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`tbTransaction` ;

CREATE  TABLE IF NOT EXISTS `bank`.`tbTransaction` (
  `trid` INT NOT NULL AUTO_INCREMENT ,
  `aid` INT NOT NULL ,
  `trtime` TIMESTAMP NOT NULL ,
  `trtype` INT NOT NULL ,
  `amount` DECIMAL(20,2) NOT NULL ,
  `description` VARCHAR(45) NULL ,
  PRIMARY KEY (`trid`) ,
  INDEX `fk_tbTransaction_tbAccount1_idx` (`aid` ASC) ,
  INDEX `fk_tbTransaction_tbTransactionType1_idx` (`trtype` ASC) ,
  CONSTRAINT `fk_tbTransaction_tbAccount1`
    FOREIGN KEY (`aid` )
    REFERENCES `bank`.`tbAccount` (`aid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbTransaction_tbTransactionType1`
    FOREIGN KEY (`trtype` )
    REFERENCES `bank`.`tbTransactionType` (`trtid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bank`.`tbAdmin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`tbAdmin` ;

CREATE  TABLE IF NOT EXISTS `bank`.`tbAdmin` (
  `adminid` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `pw` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`adminid`) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) )
ENGINE = InnoDB;

USE `bank` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;










--  Client
INSERT INTO `bank`.`tbclient` (`fname`, `lname`, `gender`, `birthday`, `tel`, `add1`, `add2`, `zip`, `email`, `username`, `pw`) VALUES ('Li', 'Huang', 'M', '1985-09-04', '505-224-0333', '2299 Brodhead Rd.', 'Bathlehem,PA', '40292', 'huangli@gmail.com', 'huangli', '1');
INSERT INTO `bank`.`tbclient` (`fname`, `lname`, `gender`, `birthday`, `tel`, `add1`, `add2`, `zip`, `email`, `username`, `pw`) VALUES ('Ben', 'Millar', 'F', '1999-03-02', '511-222-3333', '1355 Main St.', 'New York.NY', '21003', 'ben@gmail.com', 'ben', '2');



-- admin
INSERT INTO `bank`.`tbadmin` (`adminid`, `username`, `pw`) VALUES ('1', 'admin', '1');

-- account type
INSERT INTO `bank`.`tbaccounttype` (`typeid`, `typename`) VALUES ('1', 'checking');
INSERT INTO `bank`.`tbaccounttype` (`typeid`, `typename`) VALUES ('2', 'saving');


-- account
INSERT INTO `bank`.`tbaccount` (`cid`, `typeid`, `acnumber`, `balance`) VALUES ('1', '1', '1001', '500');
INSERT INTO `bank`.`tbaccount` (`cid`, `typeid`, `acnumber`, `balance`) VALUES ('1', '2', '1002', '200');
INSERT INTO `bank`.`tbaccount` (`cid`, `typeid`, `acnumber`, `balance`) VALUES ('2', '1', '2001', '300');
INSERT INTO `bank`.`tbaccount` (`cid`, `typeid`, `acnumber`, `balance`) VALUES ('2', '2', '2002', '800');

-- transfer type
INSERT INTO `bank`.`tbtransactiontype` (`trtid`, `trtname`) VALUES ('1', 'deposit');
INSERT INTO `bank`.`tbtransactiontype` (`trtid`, `trtname`) VALUES ('2', 'withdraw');
INSERT INTO `bank`.`tbtransactiontype` (`trtid`, `trtname`) VALUES ('3', 'transfer');

-- transfer
INSERT INTO `bank`.`tbtransaction` (`aid`, `trtype`, `amount`, `description`) VALUES ('2', '2', '30', 'withdraw 30 at ATM');
INSERT INTO `bank`.`tbtransaction` (`aid`, `trtype`, `amount`,`description`) VALUES ('3', '1', '22', 'deposit 22 from check');
INSERT INTO `bank`.`tbtransaction` (`aid`, `trtype`, `amount`,`description`) VALUES ('4', '3', '44','transfer 44  to 1002');
