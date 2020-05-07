package by.bsuir.OnlineGallery.repository;

import by.bsuir.OnlineGallery.model.Album;
import by.bsuir.OnlineGallery.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByAlbum(Album album);
}
