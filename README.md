# spring-boot-starter
* Spring boot project, with interceptor to secure endpoints according to organization -> branch (multi level) roles and permissions. 
* It uses Spring boot OAuth2 security.
* All the primary keys are encrypted/decrypted using JSON serializers/de-serializers at DTO level.
* All other foriegn keys are encrypted/decrypted using @Convert annotation at entity level. Example in given for **User** entity.
