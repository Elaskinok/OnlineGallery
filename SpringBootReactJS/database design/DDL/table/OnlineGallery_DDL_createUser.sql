use OnlineGallery;

drop table if exists User;

create table User(
    idUser   int             primary key auto_increment,
    username varchar(100)    not null
);