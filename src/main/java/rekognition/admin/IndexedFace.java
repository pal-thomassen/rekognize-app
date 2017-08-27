package rekognition.admin;

public class IndexedFace {

    private String awsId;
    private String externalId;

    public String getAwsId() {
        return awsId;
    }

    public void setAwsId(String awsId) {
        this.awsId = awsId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
