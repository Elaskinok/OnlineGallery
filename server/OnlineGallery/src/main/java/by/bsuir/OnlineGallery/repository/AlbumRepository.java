package by.bsuir.OnlineGallery.repository;

import by.bsuir.OnlineGallery.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Modifying(clearAutomatically = true)
    @Query("update Album set isPrivate = :isPrivate where id = :albumId")
    int updateAlbumPrivacy(@Param("isPrivate") Boolean isPrivate, @Param("albumId") Long albumId);

//    TODO: userId -> userAlbum ?
    List<Album> findAlbumByCreatedBy(Long userId);

    Optional<Album> findAlbumById(Long id);

    Page<Album> findAlbumByCreatedBy(Long userId, Pageable pageable);

    void deleteAlbumById(Long id);

    boolean existsByName(String albumName);

    Optional<Album> findById(Long albumId);

    boolean existsById(Long albumId);
}
