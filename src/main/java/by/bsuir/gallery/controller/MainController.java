package by.bsuir.gallery.controller;

import by.bsuir.gallery.model.Image;
import by.bsuir.gallery.service.ImageService;
import by.bsuir.gallery.service.convertor.ImageConverter;
import by.bsuir.gallery.service.parser.PictureParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class MainController {
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

    @RequestMapping(value = "/save-image", method = RequestMethod.POST)
    public ModelAndView saveImage(@RequestParam("picture") MultipartFile picture) throws IOException {
        var modelAndView = new ModelAndView();
        Image image = new Image();
        image.setName(picture.getName());
        image.setImageBytes(picture.getBytes());
        service.saveImage(image);
        modelAndView.setViewName("WelcomePage");
        return modelAndView;
    }

    @RequestMapping(value = "/save-image", method = RequestMethod.GET)
    public ModelAndView saveNewPicture() {
        var modelAndView = new ModelAndView();
        byte[] imageBytes = PictureParser.grabPictureByPath("E:\\GitHub\\OnlineGallery\\pictures\\upload.jpg",
                "jpg");
        try {
            String formatToRepresent = ImageConverter.convertBytesToString(imageBytes);
        } catch (UnsupportedEncodingException e) {
            System.err.println(e);
        }
        modelAndView.setViewName("ImageLoader");
        return modelAndView;
    }

    @RequestMapping(value = "/show-all-images", method = RequestMethod.GET)
    public ModelAndView getAllImages() throws UnsupportedEncodingException {
        var modelAndView = new ModelAndView();
        var images = service.allImages();
        List<String> stringImages = ImageConverter.convertImagesToString(images);

        modelAndView.addObject("stringImages", stringImages);
        modelAndView.setViewName("ImagePresentation");
        return modelAndView;
    }
}
