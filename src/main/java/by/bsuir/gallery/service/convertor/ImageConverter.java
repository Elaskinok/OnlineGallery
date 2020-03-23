package by.bsuir.gallery.service.convertor;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class ImageConverter {
    private ImageConverter() {
    }

    public static String convertBytesToString(byte[] imageBytes) throws UnsupportedEncodingException {
        byte[] convertedBytes = Base64.encodeBase64(imageBytes);
        String stringToReturn = new String(convertedBytes, StandardCharsets.UTF_8);
        return stringToReturn;
    }
}
