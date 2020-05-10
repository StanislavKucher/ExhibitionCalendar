-- DROP DATABASE IF EXISTS expo2020_db;

CREATE DATABASE expo2020_db;
/*--------------------------------------------------------------*/
/*                       Table: accounts                        */
/*==============================================================*/
CREATE TABLE accounts (
  account_id int NOT NULL AUTO_INCREMENT,
  first_name varchar(55) NOT NULL,
  last_name varchar(55) NOT NULL,
  login varchar(55) NOT NULL,
  password varchar(55) NOT NULL,
  phone varchar (17) NOT NULL,
  role ENUM ('admin', 'visitor', 'tenant') NOT NULL,
  email varchar(55) NOT NULL,
  UNIQUE (login),
  PRIMARY KEY (account_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*--------------------------------------------------------------*/
/*                        Table: Cards                          */
/*==============================================================*/
CREATE TABLE cards (
  card_id int NOT NULL AUTO_INCREMENT,
  card_number varchar(19) NOT NULL,
  cvv int NOT NULL,
  balance decimal(9,2) NOT NULL DEFAULT 1000.00,
  expire_month int NOT NULL,
  expire_year int NOT NULL,
  account_id int NOT NULL,
  UNIQUE (card_number),
  PRIMARY KEY (card_id),
  CONSTRAINT account_id FOREIGN KEY (account_id) REFERENCES accounts (account_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*--------------------------------------------------------------*/
/*                        Table: Halls                          */
/*==============================================================*/
CREATE TABLE halls (
  hall_id int NOT NULL AUTO_INCREMENT,
  name varchar(55) NOT NULL,
  address varchar (200) NOT NULL,
  PRIMARY KEY (hall_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*--------------------------------------------------------------*/
/*                        Table: Expositions                    */
/*==============================================================*/
CREATE TABLE expositions (
  exposition_id int NOT NULL AUTO_INCREMENT,
  title varchar(55) NOT NULL,
  theme tinytext NOT NULL,
  date_from date NOT NULL,
  date_to date NOT NULL,
  ticket_price decimal(6,2) NOT NULL,
  hall_id int DEFAULT NULL,
  description text NOT NULL,
  PRIMARY KEY (exposition_id),
  CONSTRAINT hall_id FOREIGN KEY (hall_id) REFERENCES halls (hall_id) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*--------------------------------------------------------------*/
/*                        Table: Orders                         */
/*==============================================================*/
CREATE TABLE orders (
  order_id int NOT NULL AUTO_INCREMENT,
  account_id int NOT NULL,
  exposition_id int NOT NULL,
  order_date date NOT NULL,
  PRIMARY KEY (order_id),
  CONSTRAINT account_id FOREIGN KEY (account_id) REFERENCES accounts (account_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT exposition_id FOREIGN KEY (exposition_id) REFERENCES expositions (exposition_id) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
