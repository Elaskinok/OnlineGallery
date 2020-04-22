package by.bsuir.gallery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Setter
@Getter
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
}
