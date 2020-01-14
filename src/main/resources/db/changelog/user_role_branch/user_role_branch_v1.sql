-- MySQL Script generated by MySQL Workbench
-- Sun Aug 18 11:47:13 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema new_schema1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `organization`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `organization` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `status` TINYINT(2) NOT NULL,
  `created_by` BIGINT NOT NULL,
  `created_on` DATETIME NOT NULL DEFAULT NOW(),
  `updated_by` BIGINT NOT NULL,
  `updated_on` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  `contact_number` VARCHAR(15) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `status` TINYINT(2) NOT NULL,
  `created_by` BIGINT NOT NULL,
  `created_on` DATETIME NOT NULL DEFAULT NOW(),
  `updated_by` BIGINT NOT NULL,
  `updated_on` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `contact_number_UNIQUE` (`contact_number` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `code` VARCHAR(50) NOT NULL,
  `status` TINYINT(2) NOT NULL,
  `created_by` BIGINT NOT NULL,
  `created_on` DATETIME NOT NULL DEFAULT NOW(),
  `updated_by` BIGINT NOT NULL,
  `updated_on` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `branch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `branch` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `created_by` BIGINT NOT NULL,
  `updated_by` BIGINT NOT NULL,
  `created_on` DATETIME NOT NULL DEFAULT NOW(),
  `updated_on` DATETIME NOT NULL DEFAULT NOW(),
  `status` TINYINT(2) NOT NULL,
  `organization_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_branch_organization1_idx` (`organization_id` ASC),
  CONSTRAINT `fk_branch_organization1`
    FOREIGN KEY (`organization_id`)
    REFERENCES `organization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_role` (
  `id` BIGINT NOT NULL,
  `status` TINYINT(2) NOT NULL,
  `created_by` BIGINT NOT NULL,
  `created_on` DATETIME NOT NULL,
  `updated_by` BIGINT NOT NULL,
  `updated_on` DATETIME NOT NULL,
  `role_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `branch_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_role_role1_idx` (`role_id` ASC),
  INDEX `fk_user_role_user1_idx` (`user_id` ASC),
  INDEX `fk_user_role_branch1_idx` (`branch_id` ASC),
  CONSTRAINT `fk_user_role_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_branch1`
    FOREIGN KEY (`branch_id`)
    REFERENCES `branch` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
