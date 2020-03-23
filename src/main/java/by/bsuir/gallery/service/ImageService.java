package by.bsuir.gallery.service;

import by.bsuir.gallery.model.Image;

import java.util.List;

public interface ImageService {
    boolean saveImage(Image image);
    List<Image> allImages();
}
