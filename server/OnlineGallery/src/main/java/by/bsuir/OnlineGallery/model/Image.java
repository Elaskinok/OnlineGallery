package by.bsuir.OnlineGallery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @Column(columnDefinition = "MEDIUMBLOB")
    @NotBlank
    private String byteArray;

    private boolean isPrivate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getByteArray() {
        return byteArray;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void setByteArray(String byteArray) {
        this.byteArray = byteArray;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (isPrivate != image.isPrivate) return false;
        if (!Objects.equals(id, image.id)) return false;
        if (!Objects.equals(name, image.name)) return false;
        if (!Objects.equals(byteArray, image.byteArray)) return false;
        return Objects.equals(album, image.album);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (byteArray != null ? byteArray.hashCode() : 0);
        result = 31 * result + (isPrivate ? 1 : 0);
        result = 31 * result + (album != null ? album.hashCode() : 0);
        return result;
    }
}
