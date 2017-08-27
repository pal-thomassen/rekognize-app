package rekognition.rekognize;

public class FaceToMatch {

    private String image;

    public FaceToMatch(String image) {
        this.image = image;
    }

    public FaceToMatch() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
