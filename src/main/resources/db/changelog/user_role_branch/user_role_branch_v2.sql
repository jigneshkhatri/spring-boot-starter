

ALTER TABLE `user` 
ADD COLUMN `active_branch_id` BIGINT NULL AFTER `password`;
