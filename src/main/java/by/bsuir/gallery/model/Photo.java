package by.bsuir.gallery.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "photo")
public class Photo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPhoto")
    private long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "byteArray", nullable = false)
    private byte[] byteArray;

    @Column(name = "isPrivate", nullable = false)
    private boolean isPrivate;

    @Column(name = "creationDate", nullable = false)
    private Date creationDate;

    @Column(name = "descrPhoto")
    private String photoDescription;

    @ManyToMany
    @JoinTable(name = "Album_Photo_xref",
            joinColumns = {@JoinColumn(name = "idPhoto")},
            inverseJoinColumns = {@JoinColumn(name = "idAlbum")})
    private List<Album> albumSet = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getByteArray() {
        return byteArray;
    }

    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getPhotoDescription() {
        return photoDescription;
    }

    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
    }

    public List<Album> getAlbumSet() {
        return albumSet;
    }

    public void setAlbumSet(List<Album> albumSet) {
        this.albumSet = albumSet;
    }
}
