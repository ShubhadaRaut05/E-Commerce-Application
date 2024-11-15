create database
CREATE DATABASE productservice3oct23;
CREATE USER productservice IDENTIFIED BY 'prod123';// create user with password

GRANT ALL privileges ON productservice3oct23.* to productservice;
