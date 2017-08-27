package rekognition.common;

public class ParseDataImage {

    public static String parseDataImage(String base64EncodedImage) {
        final String[] split = base64EncodedImage.split(",");

        return split[split.length - 1];
    }
}
