
drop table PERSON;

create table PERSON (
	id integer NOT NULL AUTO_INCREMENT, 
	email varchar(255), 
	firstName varchar(255) , 
	middleName varchar(255) , 
	lastName varchar(255) NOT NULL, 
	displayName varchar(255) NOT NULL, 
	password varchar(255) NOT NULL, 
	lastupdated TIMESTAMP , 
	username varchar(255) NOT NULL UNIQUE, 
	primary key (id)
);
	
create table APP_DEF (
	id integer NOT NULL AUTO_INCREMENT, 
	description varchar(255), 
	name varchar(255) NOT NULL UNIQUE, 
	primary key (id)
);

create table APP_DEF_PARAMS (
	id integer NOT NULL AUTO_INCREMENT,
	app_def_id integer NOT NULL,
	defalutValue varchar(255), 
	description varchar(255), 
	name varchar(255) NOT NULL, 
	type varchar(255) NOT NULL, 
	primary key (id),
	FOREIGN KEY (app_def_id) REFERENCES APP_DEF(id)
);

create table APPLICATION (
	id integer NOT NULL AUTO_INCREMENT, 
	description varchar(255), 
	name varchar(255) NOT NULL, 
	app_def_id integer NOT NULL, 
	primary key (id),
	FOREIGN KEY (app_def_id) REFERENCES APP_DEF(id)
);

create table APP_PARAMS (
	id integer NOT NULL AUTO_INCREMENT,
	app_id integer NOT NULL,
	value varchar(255), 
	description varchar(255), 
	name varchar(255) NOT NULL, 
	type varchar(255) NOT NULL, 
	primary key (id),
	FOREIGN KEY (app_id) REFERENCES APPLICATION(id)
);

