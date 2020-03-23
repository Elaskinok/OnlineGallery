package by.bsuir.gallery.repository;

import by.bsuir.gallery.model.Image;

import java.util.List;

public interface ImageRepository {
    boolean saveImage(Image image);
    List<Image> allImages();
}
