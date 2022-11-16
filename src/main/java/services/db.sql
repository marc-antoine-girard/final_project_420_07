/* Reset database */
drop database if exists farm_db;
create database farm_db;

use farm_db;

create table farm
(
    farm_id   bigint not null auto_increment, /* auto-increment id */
    farm_name varchar(20) default null, /* default value to null */
    country   varchar(15) default null,

    primary key (farm_id)
);

insert into farm (farm_name, country)
values ('GlennFarm', 'Canada'),
       ('AppleFarm', 'US');

