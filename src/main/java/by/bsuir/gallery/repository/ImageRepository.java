package by.bsuir.gallery.repository;

import by.bsuir.gallery.model.Photo;

import java.util.List;

public interface ImageRepository {
    boolean saveImage(Photo photo);
    List<Photo> allImages();
}
