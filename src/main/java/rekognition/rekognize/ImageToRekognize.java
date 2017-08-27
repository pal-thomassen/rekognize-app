package rekognition.rekognize;

public class ImageToRekognize {

    private String base64EncodedImage;

    public ImageToRekognize() {
    }

    public ImageToRekognize(String base64EncodedImage) {
        this.base64EncodedImage = base64EncodedImage;
    }

    public String getBase64EncodedImage() {
        return base64EncodedImage;
    }

    public void setBase64EncodedImage(String base64EncodedImage) {
        this.base64EncodedImage = base64EncodedImage;
    }
}
