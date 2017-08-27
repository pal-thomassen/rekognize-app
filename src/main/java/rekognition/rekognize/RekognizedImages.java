package rekognition.rekognize;

import java.util.List;

public class RekognizedImages {

    private int num_rekognized;
    private List<FaceMatch> matches;

    public RekognizedImages(int num_rekognized, List<FaceMatch> matches) {
        this.num_rekognized = num_rekognized;
        this.matches = matches;
    }

    public RekognizedImages() {
    }

    public int getNum_rekognized() {
        return num_rekognized;
    }

    public void setNum_rekognized(int num_rekognized) {
        this.num_rekognized = num_rekognized;
    }

    public List<FaceMatch> getMatches() {
        return matches;
    }

    public void setMatches(List<FaceMatch> matches) {
        this.matches = matches;
    }
}
