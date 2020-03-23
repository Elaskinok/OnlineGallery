package by.bsuir.gallery.controller;

import by.bsuir.gallery.model.Image;
import by.bsuir.gallery.service.ImageService;
import by.bsuir.gallery.service.convertor.ImageConverter;
import by.bsuir.gallery.service.parser.PictureParser;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@Controller
public class WelcomePageController {
    private ImageService service;

    @Autowired
    public void setService(ImageService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView testController() {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("WelcomePage");
        return modelAndView;
    }

    @RequestMapping(value = "/drag-image", method = RequestMethod.POST)
    public ModelAndView saveImage(@RequestParam("file") MultipartFile file) throws IOException {
        var modelAndView = new ModelAndView();
        System.out.println(Arrays.toString(file.getBytes()));
        modelAndView.setViewName("ImageLoader");
        return modelAndView;
    }

    @RequestMapping(value = "/drag-image", method = RequestMethod.GET)
    public ModelAndView saveNewPicture() {
        var modelAndView = new ModelAndView();
        byte[] imageBytes = PictureParser.grabPictureByPath("E:\\GitHub\\OnlineGallery\\pictures\\upload.jpg",
                "jpg");
        try {
            String formatToRepresent = ImageConverter.convertBytesToString(imageBytes);
            modelAndView.addObject("dragPicture", formatToRepresent);
        } catch (UnsupportedEncodingException e) {
            System.err.println(e);
        }
        modelAndView.setViewName("ImageLoader");
        return modelAndView;
    }

    @RequestMapping(value = "/upload-image", method = RequestMethod.GET)
    public ModelAndView getAllImages() throws UnsupportedEncodingException {
        var modelAndView = new ModelAndView();
        var images = service.allImages();
        for(var image : images) {
            System.out.println(image.getId() + ": " + image.getImageBytes().length);
        }
        modelAndView.addObject("images", images);
        modelAndView.addObject("Photo", ImageConverter.convertBytesToString(images.get(0).getImageBytes()));
        modelAndView.setViewName("ImagePresentation");
        return modelAndView;
    }
}
