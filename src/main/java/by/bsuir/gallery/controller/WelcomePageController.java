package by.bsuir.gallery.controller;

import by.bsuir.gallery.model.Image;
import by.bsuir.gallery.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

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

    @RequestMapping(value = "/save-image", method = RequestMethod.GET)
    public ModelAndView saveImage() {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("ImageLoader");
        byte[] imageToSave = getImage();
        Image image = new Image();
        image.setImageBytes(imageToSave);
        if (imageToSave != null) {
            if (service.saveImage(image)) {
                System.out.println("Saved successfully");
            } else {
                System.out.println("No possibility to save");
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/upload-image", method = RequestMethod.GET)
    public ModelAndView getAllImages() {
        var modelAndView = new ModelAndView();
        var images = service.allImages();
        for(var image : images) {
            System.out.println(image.getId() + ": " + image.getImageBytes().length);
        }
        modelAndView.setViewName("WelcomePage");
        return modelAndView;
    }

    private static byte[] getImage() {
        File file = new File("E:\\GitHub\\OnlineGallery\\ImageToSave.jpg");
        if (file.exists()) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", byteOutStream);
                return byteOutStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
