## mySQl database initiazation 
## initialize database with Admin only 

Drop  DATABASE IF EXISTS gateway;
CREATE DATABASE gateway;
USE gateway;

CREATE TABLE user(
id int(20) AUTO_INCREMENT PRIMARY KEY,
username  VARCHAR(255) NOT NULL UNIQUE,
password VARCHAR(255)  NOT NULL,
assigned_token TEXT 
);

## Make Admin fixed id
INSERT INTO user VALUES(1,'admin','admin',NULL);
