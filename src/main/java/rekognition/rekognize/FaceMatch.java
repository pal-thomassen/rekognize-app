package rekognition.rekognize;

public class FaceMatch {

    private String id;
    private int similarity;

    public FaceMatch(String id, int similarity) {
        this.id = id;
        this.similarity = similarity;
    }

    public FaceMatch() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSimilarity() {
        return similarity;
    }

    public void setSimilarity(int similarity) {
        this.similarity = similarity;
    }
}
