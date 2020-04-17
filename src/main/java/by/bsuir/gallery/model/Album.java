package by.bsuir.gallery.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "Album")
public class Album implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAlbum")
    private int idAlbum;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "idUser", nullable = false)
    private int idUser;

    @Column(name = "creationDate", nullable = false)
    private Date creationDate;

    @Column(name = "descrAlbum")
    private String albumDescription;

    @ManyToMany
    @JoinTable(name = "Album_Photo_xref",
            joinColumns = {@JoinColumn(referencedColumnName = "idAlbum")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "idPhoto")})
    List<Photo> photoSet = new LinkedList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "idUser")
    private User user;

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getAlbumDescription() {
        return albumDescription;
    }

    public void setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
    }
}
