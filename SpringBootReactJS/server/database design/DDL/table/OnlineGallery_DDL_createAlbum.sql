use OnlineGallery;

drop table if exists Album;

create table Album(
    idAlbum          int             not null auto_increment,
    name             varchar(100)    not null,
    idUser           int             not null,
    creationDate     date            not null,
    descrAlbum       varchar(255)    not null,
    
    primary key PK_Album(idAlbum)
);