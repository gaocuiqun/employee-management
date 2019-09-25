-----------------------------------------------------
-- This file is 100% ***GENERATED***, DO NOT EDIT! --
-----------------------------------------------------

-- Using 'employeedb' as database name, schema name, login name, user name and role name.
-- For Test/Demo purpose only.
-- Fine-tuning is need for production.

DROP DATABASE IF EXISTS [employeedb]
GO

CREATE DATABASE [employeedb] ON
  PRIMARY (
    NAME       = 'employeedb_data',
    FILENAME   = '/var/opt/mssql/data/employeedb_data.mdf',
    SIZE       = 10MB,
    MAXSIZE    = UNLIMITED,
    FILEGROWTH = 10MB
  )
  LOG ON (
    NAME       = 'employeedb_log',
    FILENAME   = '/var/opt/mssql/data/employeedb_log.ldf',
    SIZE       = 10MB,
    MAXSIZE    = UNLIMITED,
    FILEGROWTH = 10MB
  )
  COLLATE Chinese_PRC_CI_AS
GO

-- The following options is executed on database 'employeedb'

USE [employeedb]
GO

DROP SCHEMA IF EXISTS [employeedb]
GO

CREATE SCHEMA [employeedb] AUTHORIZATION dbo
GO

DROP USER IF EXISTS [employeedb]
GO

-- DROP LOGIN IF EXISTS [employeedb]
DROP LOGIN [employeedb]
GO

-- Change it for production since password is sensitive data.
CREATE LOGIN [employeedb] WITH PASSWORD='my-Secret-pw'
GO

CREATE USER [employeedb] FOR LOGIN [employeedb] WITH DEFAULT_SCHEMA=[employeedb]
GO

DROP ROLE IF EXISTS [employee_management]
GO

CREATE ROLE [employee_management] AUTHORIZATION dbo
GO

ALTER ROLE [employee_management] ADD MEMBER [employeedb]
GO

-- ALTER ROLE [employee_management] DROP MEMBER [employeedb]
-- GO

GRANT SELECT, UPDATE, DELETE ON SCHEMA::[employeedb] to [employeedb]

CREATE TABLE [employeedb].[user] (
  [name] VARCHAR(64) NOT NULL,
  [id] VARCHAR(64) NOT NULL,
  [password] VARCHAR(64) NOT NULL
)
GO

CREATE TABLE [employeedb].[Gender] (
  [id] INT NOT NULL,
  [name] VARCHAR(64)
)
GO

CREATE TABLE [employeedb].[employee_department] (
  [employee_id] VARCHAR(64) NOT NULL,
  [department_id] VARCHAR(64) NOT NULL
)
GO

CREATE TABLE [employeedb].[employee] (
  [id] VARCHAR(64) NOT NULL,
  [name] VARCHAR(128) NOT NULL,
  [gender] INT NOT NULL,
  [employed_date] DATETIME NOT NULL
)
GO

CREATE TABLE [employeedb].[department] (
  [id] VARCHAR(64) NOT NULL,
  [name] VARCHAR(128) NOT NULL,
  [desc] VARCHAR(128) NOT NULL
)
GO

ALTER TABLE [employeedb].[user] WITH CHECK
ADD CONSTRAINT [user_pk] PRIMARY KEY ([name])
GO

ALTER TABLE [employeedb].[Gender] WITH CHECK
ADD CONSTRAINT [EntityTypePk] PRIMARY KEY ([id])
GO

ALTER TABLE [employeedb].[employee_department] WITH CHECK
ADD CONSTRAINT [employee_department_pk] PRIMARY KEY ([employee_id], [department_id])
GO

ALTER TABLE [employeedb].[employee] WITH CHECK
ADD CONSTRAINT [employee_pk] PRIMARY KEY ([id])
GO

ALTER TABLE [employeedb].[department] WITH CHECK
ADD CONSTRAINT [department_pk] PRIMARY KEY ([id])
GO

ALTER TABLE [employeedb].[employee_department] WITH CHECK
ADD CONSTRAINT [employee_department_employee_fk] FOREIGN KEY ([employee_id])
REFERENCES [employeedb].[employee]([id]);
GO

ALTER TABLE [employeedb].[employee_department] WITH CHECK
ADD CONSTRAINT [employee_department_department_fk] FOREIGN KEY ([department_id])
REFERENCES [employeedb].[department]([id]);
GO

PRINT "Finished."
GO
