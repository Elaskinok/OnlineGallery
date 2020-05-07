package by.bsuir.OnlineGallery.repository;

import by.bsuir.OnlineGallery.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findAlbumById(Long albumId);

    Optional<Album> findById(Long albumId);
}
