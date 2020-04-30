use OnlineGallery;

drop table if exists Album_Photo_xref;

create table Album_Photo_xref(
    idAlbum    int    not null,
    idPhoto    int    not null,
    
    primary key PK_Album_Photo_xref(idAlbum, idPhoto)
);