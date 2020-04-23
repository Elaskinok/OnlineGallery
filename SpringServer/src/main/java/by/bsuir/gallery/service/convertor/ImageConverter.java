package by.bsuir.gallery.service.convertor;

import by.bsuir.gallery.model.Photo;
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

    public static List<String> convertImagesToString(List<Photo> photos) throws UnsupportedEncodingException {
        List<String> listToReturn = new LinkedList<>();

        for (Photo photo : photos) {
//            var currentValue = convertBytesToString(photo.getImageBytes());
//            listToReturn.add(currentValue);
        }

        return listToReturn;
    }
}
