use OnlineGallery;

drop table if exists Photo;

create table Photo(
    idPhoto       int             primary key auto_increment,
    name          varchar(100)    not null,
    byteArray     blob            not null,
    isPrivate     boolean         not null,
    creationDate  date            not null,
    descrPhoto    varchar(255)    not null
);