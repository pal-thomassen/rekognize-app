package rekognition.admin;

public class ImageToUpload {

    private String imageKey;
    private String image;

    public ImageToUpload() {
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
