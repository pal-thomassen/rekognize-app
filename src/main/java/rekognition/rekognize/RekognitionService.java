package rekognition.rekognize;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class RekognitionService {

    RekognizedImages getFaceMatches(String base64ImageEncoded) {
        final String uri = "";
        RestTemplate restTemplate = new RestTemplate();
        FaceToMatch faceToMatch = new FaceToMatch(base64ImageEncoded);

        try {
            return restTemplate.postForObject(uri, faceToMatch, RekognizedImages.class);
        } catch(HttpClientErrorException clientException) {
            throw new RekognizedImagesException(clientException.getResponseBodyAsString());
        }
    }
}
