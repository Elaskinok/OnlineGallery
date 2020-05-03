use OnlineGallery;

drop table if exists Password;

create table Password(
    idPassword       int             primary key auto_increment,
    passwordHash     varchar(300)    not null
);