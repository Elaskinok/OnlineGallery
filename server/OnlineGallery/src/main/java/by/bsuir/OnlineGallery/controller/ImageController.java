package by.bsuir.OnlineGallery.controller;

import by.bsuir.OnlineGallery.model.Album;
import by.bsuir.OnlineGallery.model.Image;
import by.bsuir.OnlineGallery.payload.ImageRequest;
import by.bsuir.OnlineGallery.sercurity.CurrentUser;
import by.bsuir.OnlineGallery.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping(path = "/api/images")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    @RequestMapping
    public ResponseEntity<Image> addImage(@Valid @RequestBody ImageRequest imageRequest) {
        //        TODO default album id
        Album album = new Album();
        album.setName("default");
        album.setDescription("default");
        album.setImages(Collections.emptyList());

        Image image = imageService.addImage(imageRequest);
        System.out.println(image);
//        TODO final return value
        return null;
    }
}
