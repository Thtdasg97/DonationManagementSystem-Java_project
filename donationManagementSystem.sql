drop schema if exists `asm-01-spring-boot`;

create schema `asm-01-spring-boot`;

use `asm-01-spring-boot`;

set FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `role` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `role_name` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=LATIN1;

CREATE TABLE `user` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `address` VARCHAR(255) DEFAULT NULL,
    `email` VARCHAR(255) DEFAULT NULL,
    `full_name` VARCHAR(255) DEFAULT NULL,
    `note` VARCHAR(255) DEFAULT NULL,
    `password` VARCHAR(255) DEFAULT NULL,
    `phone_number` VARCHAR(255) DEFAULT NULL,
    `status` INT(11) DEFAULT 1,
    `user_name` VARCHAR(255) DEFAULT NULL,
    `created` VARCHAR(255) DEFAULT NULL,
    `role_id` INT(11) DEFAULT NULL,
    FOREIGN KEY (role_id)
        REFERENCES role (id),
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=LATIN1;

insert into role (role_name) values ('Admin');
insert into role (role_name) values ('User');

INSERT INTO user (address, email, full_name, password, phone_number, user_name, role_id)
VALUES ('123 Main St', 'huythanhAdmin@example.com', 'Xter Admin', 'password123', '1234567890', 'admin', 1);

INSERT INTO user (address, email, full_name, password, phone_number, user_name, role_id)
VALUES ('456 Elm St', 'huythanhUser@example.com', 'Xter User', 'password123', '9876543210', 'user', 2);

INSERT INTO user (address, email, full_name, password, phone_number, user_name, role_id)
VALUES ('789 Oak St', 'mentor@example.com', 'Funix Mentor', 'password123', '4567891230', 'mentor', 2);

INSERT INTO user (address, email, full_name, password, phone_number, user_name, role_id)
VALUES ('321 Pine St', 'amy@example.com', 'Amy Wilson', 'password321', '7891234560', 'amywilson', 2);

INSERT INTO user (address, email, full_name, password, phone_number, user_name, role_id)
VALUES ('654 Cedar St', 'alex@example.com', 'Alex Brown', 'password654', '3216549870', 'alexbrown', 1);

INSERT INTO user (address, email, full_name, password, phone_number, user_name, role_id)
VALUES ('987 Maple St', 'sara@example.com', 'Sara Davis', 'password987', '6549873210', 'saradavis', 2);

INSERT INTO user (address, email, full_name, password, phone_number, user_name, role_id)
VALUES ('147 Elmwood Ave', 'chris@example.com', 'Chris Lee', 'password147', '9873216540', 'chrislee', 1);

INSERT INTO user (address, email, full_name, password, phone_number, user_name, role_id)
VALUES ('258 Oakwood Ave', 'emily@example.com', 'Emily Taylor', 'password258', '3216549870', 'emilytaylor', 2);

INSERT INTO user (address, email, full_name, password, phone_number, user_name, role_id)
VALUES ('369 Walnut St', 'david@example.com', 'David Johnson', 'password369', '6549873210', 'davidjohnson', 1);

INSERT INTO user (address, email, full_name, password, phone_number, user_name, role_id)
VALUES ('741 Cedar Ave', 'lisa@example.com', 'Lisa Anderson', 'password741', '9873216540', 'lisaanderson', 2);

CREATE TABLE donation (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `code` varchar(255) default null,
    `created` VARCHAR(255) DEFAULT NULL,
    `description` VARCHAR(255) DEFAULT NULL,
    `end_date` VARCHAR(255) DEFAULT NULL,
    `money` INT DEFAULT NULL,
    `name` VARCHAR(255) DEFAULT NULL,
    `organization_name` VARCHAR(255) DEFAULT NULL,
    `phone_number` VARCHAR(255) DEFAULT NULL,
    `start_date` VARCHAR(255) DEFAULT NULL,
    `status` INT(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=LATIN1;

INSERT INTO donation (code, created, description, end_date, money, name, organization_name, phone_number, start_date, status)
VALUES ('ABC123', '2023-07-01', 'Donation for Education', '2023-08-31', 500, 'Education for All', 'ABC Organization', '1234567890', '2023-07-01', 1);

INSERT INTO donation (code, created, description, end_date, money, name, organization_name, phone_number, start_date, status)
VALUES ('DEF456', '2023-07-02', 'Medical Relief Fund', '2023-09-15', 1000, 'Healthcare Initiative', 'XYZ Foundation', '9876543210', '2023-07-02', 2);

INSERT INTO donation (code, created, description, end_date, money, name, organization_name, phone_number, start_date, status)
VALUES ('GHI789', '2023-07-03', 'Animal Shelter Support', '2023-08-31', 250, 'Paws and Claws Rescue', 'Animal Rescue League', '5551234567', '2023-07-03', 2);

INSERT INTO donation (code, created, description, end_date, money, name, organization_name, phone_number, start_date, status)
VALUES ('JKL012', '2023-07-04', 'Disaster Relief Fund', '2023-07-31', 1000, 'Emergency Aid Society', 'Red Cross', '8889876543', '2023-07-04', 3);

INSERT INTO donation (code, created, description, end_date, money, name, organization_name, phone_number, start_date, status)
VALUES ('MNO345', '2023-07-05', 'Environmental Conservation', '2023-08-15', 500, 'Green Earth Initiative', 'Green Earth Foundation', '5557891234', '2023-07-05', 1);

INSERT INTO donation (code, created, description, end_date, money, name, organization_name, phone_number, start_date, status)
VALUES ('PQR678', '2023-07-06', 'Supporting Orphanage', '2023-09-30', 750, 'Hope for Kids', 'Hope for Children', '9876543210', '2023-07-06', 4);

INSERT INTO donation (code, created, description, end_date, money, name, organization_name, phone_number, start_date, status)
VALUES ('STU901', '2023-07-07', 'Cancer Research Fund', '2023-10-31', 2000, 'Cancer Cure Foundation', 'Cancer Society', '1237894560', '2023-07-07', 1);

INSERT INTO donation (code, created, description, end_date, money, name, organization_name, phone_number, start_date, status)
VALUES ('VWX234', '2023-07-08', 'Food Drive for the Needy', '2023-08-31', 300, 'Community Food Relief', 'Community Food Bank', '4567891230', '2023-07-08', 4);

INSERT INTO donation (code, created, description, end_date, money, name, organization_name, phone_number, start_date, status)
VALUES ('YZA567', '2023-07-09', 'Art Education Program', '2023-09-15', 500, 'Artistic Expression Foundation', 'Art Foundation', '7891234560', '2023-07-09', 1);

INSERT INTO donation (code, created, description, end_date, money, name, organization_name, phone_number, start_date, status)
VALUES ('BCD890', '2023-07-10', 'Sports Equipment for School', '2023-08-31', 400, 'Sports Development Initiative', 'Sports Development Association', '3216549870', '2023-07-10', 1);

CREATE TABLE user_donation (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `user_id` INT(11) DEFAULT NULL,
    `donation_id` INT(11) DEFAULT NULL,
    `created` VARCHAR(255) DEFAULT NULL,
    `money` INT(11) DEFAULT NULL,
    `name` VARCHAR(255) DEFAULT NULL,
    `status` INT(11) DEFAULT NULL,
    `text` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_USER_idx` (`user_id`),
    CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`)
        REFERENCES `user` (`id`)
        ON DELETE SET NULL ON UPDATE NO ACTION,
    KEY `FK_DONATION_idx` (`donation_id`),
    CONSTRAINT `FK_DONATION` FOREIGN KEY (`donation_id`)
        REFERENCES `donation` (`id`)
        ON DELETE SET NULL ON UPDATE NO ACTION
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=LATIN1;

INSERT INTO user_donation (user_id, donation_id, created, money, name, status, text) VALUES
(2, 1, '2023-07-01', 100, 'Xter Huy Thanh User', 0, 'Thank you for your support.'),
(3, 2, '2023-07-02', 50, 'Mentor Funix', 0, 'Your contribution is greatly appreciated.');
-- (3, 1, '2023-07-03', 200, 'Michael Johnson', 0, 'Were grateful for your generous donation.'),
-- (4, 3, '2023-07-04', 75, 'Emily Brown', 0, 'Thank you for making a difference.'),
-- (5, 2, '2023-07-05', 30, 'David Wilson', 0, 'Your support means a lot to us.'),
-- (1, 4, '2023-07-06', 150, 'Sarah Thompson', 0, 'We couldnt do it without you.'),
-- (3, 5, '2023-07-07', 100, 'Robert Davis', 0, 'Thank you for standing with us.'),
-- (2, 6, '2023-07-08', 80, 'Jennifer Lee', 0, 'Your donation is helping us achieve our goals.'),
-- (4, 4, '2023-07-09', 120, 'Daniel Garcia', 0, 'We appreciate your continued support.'),
-- (5, 7, '2023-07-10', 50, 'Olivia Martin', 0, 'Together, we can make a difference.');

SET FOREIGN_KEY_CHECKS=1;