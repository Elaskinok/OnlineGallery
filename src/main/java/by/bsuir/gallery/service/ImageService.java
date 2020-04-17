package by.bsuir.gallery.service;

import by.bsuir.gallery.model.Photo;

import java.util.List;

public interface ImageService {
    boolean saveImage(Photo photo);
    List<Photo> allImages();
}
