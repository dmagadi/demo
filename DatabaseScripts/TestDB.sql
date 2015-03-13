#create database
create database aamir;



# create table

CREATE TABLE `aamir`.`test` (
      `id` INT NOT NULL AUTO_INCREMENT,
       `FirstName` VARCHAR(255) NULL,
       `LastName` VARCHAR(255) NOT NULL,
      PRIMARY KEY (`id`));



#CRUD

#Create

INSERT INTO `aamir`.`test`
(
`FirstName`,
`LastName`)
VALUES (
NULL,
'Godil');


#Retreive

SELECT id,FirstName,LastName from `aamir`.`test`;


#Update

Update `aamir`.`test` set FirstName = 'Aamir' where id =1;

#Delete 

Delete from `aamir`.`test` where FirstName is null;