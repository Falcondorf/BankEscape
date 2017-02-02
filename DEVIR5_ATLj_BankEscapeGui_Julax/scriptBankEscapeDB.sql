-- RESET DB --
alter table LEVELELEMENT
drop primary key;
alter table LEVELELEMENT
drop foreign key fkLev;
alter table PLAYER
drop foreign key fkPlay;
alter table LEVELS
drop primary key;
alter table PLAYER
drop primary key;
alter table SEQUENCES
drop primary key;


drop table PLAYER;
drop table LEVELS;
drop table LEVELELEMENT;
drop table SEQUENCES;

-- CREATE DB --
create table LEVELS (
     lId numeric(10) not null,
     lName varchar(50) not null,
	 width numeric(10) not null,
	 height numeric(10) not null,
	 nextLevel varchar(100) not null,
     constraint IDLEVELS primary key (lId),
     constraint uLName unique (lName));

create table LEVELELEMENT (
     leId numeric(10) not null,
     levId numeric(10) not null,
     nomElem varchar(50) not null,
     posX numeric(10) not null,
     posY numeric(10) not null,
	 constraint IDLEVELEMENT primary key (leId));

create table PLAYER (
     pId numeric(10) not null,
     pName varchar(50) not null,
     lvId numeric(10) not null,
     haskey boolean not null,
     hasdrill boolean not null,
     hasMoney boolean not null,
	 constraint IDPLAYER primary key (pId)
	 ); 
    
create table SEQUENCES (
     id varchar(50) not null,
     sValue numeric(10) not null,
     constraint IDSEQUENCE primary key (id));


-- Constraints Section
-- ___________________ 

alter table LEVELELEMENT add constraint fkLev
     foreign key (levId)
     references LEVELS(lId);

alter table PLAYER add constraint fkPlay
    foreign key (lvId)
    references LEVELS(lId);

	 
----------------------------------------------------- INSERTS --------------------------------------------------------


-- 
-- Insert Into LVL Values(3, 'Gold','Troisiéme niveau de base du jeu',null,'0');
-- Insert Into LVL Values(2, 'Silver','Deuxiéme niveau de base du jeu',3,'0');
-- Insert Into LVL Values(1, 'Bronze','Premier niveau de base du jeu',2,'1');


--  PLAYER
--Insert Into PLAYER Values(1, 'jules');
--Insert Into PLAYER Values(2, 'alex');


-- SEQUENCES

Insert Into SEQUENCES Values('LEVELS', 0);
Insert Into SEQUENCES Values('LEVELELEMENT', 0);
Insert Into SEQUENCES Values('PLAYER', 0);

----  LVLELEMENT
-- Mon level 1 de base
--Insert Into LVLELEMENT Values(1, 1, 1, 0, 680);