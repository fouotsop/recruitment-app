CREATE DATABASE IF NOT EXISTS candidate_evaluation_db;

CREATE USER 'evaluation_service'@'%' IDENTIFIED BY 'password';

GRANT ALL ON candidate_evaluation_db.* TO 'evaluation_service'@'%';

FLUSH PRIVILEGES;