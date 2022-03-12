CREATE TABLE users (
    id int not null auto_increment primary key,
    age int not null ,
    username varchar(20) not null unique,
    password varchar(50) default 'password123'
)
