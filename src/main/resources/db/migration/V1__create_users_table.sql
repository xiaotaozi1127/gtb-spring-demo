CREATE TABLE users (
    id int not null auto_increment primary key,
    age int not null ,
    username varchar(20) not null unique,
    password varchar(200),
    enabled boolean NOT NULL DEFAULT true
)
