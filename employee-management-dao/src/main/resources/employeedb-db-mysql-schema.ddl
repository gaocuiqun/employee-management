/*****************************************************
 ** This file is 100% ***GENERATED***, DO NOT EDIT! **
 *****************************************************/

DROP USER IF EXISTS 'employeedb';
DROP DATABASE IF EXISTS employeedb;

CREATE DATABASE employeedb DEFAULT CHARACTER SET 'UTF8' DEFAULT COLLATE utf8_unicode_ci;

CREATE USER 'employeedb' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'employeedb' WITH GRANT OPTION;

USE employeedb;

CREATE TABLE employeedb.employee_department (
  employee_id VARCHAR(64) NOT NULL,
  department_id VARCHAR(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE employeedb.employee (
  id VARCHAR(64) NOT NULL,
  name VARCHAR(128) NOT NULL,
  password VARCHAR(64) NOT NULL,
  desc VARCHAR(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE employeedb.department (
  id VARCHAR(64) NOT NULL,
  name VARCHAR(128) NOT NULL,
  desc VARCHAR(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE employeedb.employee_department ADD CONSTRAINT employee_department_pk PRIMARY KEY(employee_id, department_id);

ALTER TABLE employeedb.employee ADD CONSTRAINT employee_pk PRIMARY KEY(id);

ALTER TABLE employeedb.department ADD CONSTRAINT department_pk PRIMARY KEY(id);

ALTER TABLE employeedb.employee_department ADD CONSTRAINT employee_department_employee_fk FOREIGN KEY(employee_id) REFERENCES employeedb.employee(id);

ALTER TABLE employeedb.employee_department ADD CONSTRAINT employee_department_department_fk FOREIGN KEY(department_id) REFERENCES employeedb.department(id);