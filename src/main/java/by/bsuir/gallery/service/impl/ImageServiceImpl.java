package by.bsuir.gallery.service.impl;

import by.bsuir.gallery.model.Photo;
import by.bsuir.gallery.repository.ImageRepository;
import by.bsuir.gallery.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    private ImageRepository repository;

    @Autowired
    public void setRepository(ImageRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public boolean saveImage(Photo photo) {
        return repository.saveImage(photo);
    }

    @Transactional
    public List<Photo> allImages() {
        return repository.allImages();
    }
}
