

CREATE TABLE `app_entity` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `code` VARCHAR(45) NOT NULL,
  `status` TINYINT(1) NOT NULL,
  `created_by` BIGINT NOT NULL,
  `created_on` DATETIME NOT NULL DEFAULT NOW(),
  `updated_by` BIGINT NOT NULL,
  `updated_on` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`id`));

CREATE TABLE `role_entity_access` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `role_id` BIGINT NOT NULL,
  `entity_id` BIGINT NOT NULL,
  `permitted_operations` VARCHAR(200) NOT NULL COMMENT 'Multiple - GET,POST,PUT,DELETE',
  `has_all_data_access` TINYINT(1) NOT NULL DEFAULT 0 COMMENT 'Is this role allowed to view all data for stated entity?',
  `status` TINYINT(2) NOT NULL,
  `created_by` BIGINT NOT NULL,
  `created_on` DATETIME NOT NULL DEFAULT NOW(),
  `updated_by` BIGINT NOT NULL,
  `updated_on` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`id`));

ALTER TABLE `role_entity_access` ADD FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
ALTER TABLE `role_entity_access` ADD FOREIGN KEY (`entity_id`) REFERENCES `app_entity` (`id`);