package by.bsuir.gallery.service.convertor;

import by.bsuir.gallery.model.Image;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class ImageConverter {
    private ImageConverter() {
    }

    public static String convertBytesToString(byte[] imageBytes) throws UnsupportedEncodingException {
        byte[] convertedBytes = Base64.encodeBase64(imageBytes);
        String stringToReturn = new String(convertedBytes, StandardCharsets.UTF_8);
        return stringToReturn;
    }

    public static List<String> convertImagesToString(List<Image> images) throws UnsupportedEncodingException {
        List<String> listToReturn = new LinkedList<>();

        for (Image image : images) {
            var currentValue = convertBytesToString(image.getImageBytes());
            listToReturn.add(currentValue);
        }

        return listToReturn;
    }
}
