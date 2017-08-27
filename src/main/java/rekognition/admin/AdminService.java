package rekognition.admin;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static java.util.stream.Collectors.toList;

@Service
public class AdminService {

    public String uploadImage(ImageToUpload imageToUpload) {
        final String uri = "";
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(uri, imageToUpload, String.class);
            return response.getBody();
        } catch(HttpClientErrorException clientException) {
            throw new RuntimeException(clientException.getResponseBodyAsString());
        }
    }

    public List<IndexedFace> listFacesIndexed() {
        final String uri = "";

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<IndexedFace[]> response = restTemplate.getForEntity(uri, IndexedFace[].class);
            List<IndexedFace> indexedFaces = Arrays.stream(response.getBody()).collect(toList());
            return indexedFaces;
        } catch(HttpClientErrorException clientException) {
            throw new RuntimeException(clientException.getResponseBodyAsString());
        }
    }

    public void deleteImage(ImageToDelete imageToDelete) {
        final String uri = "";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ImageToDelete> entity = new HttpEntity<>(imageToDelete);

        try {
            restTemplate.exchange(uri, HttpMethod.DELETE, entity, String.class);
        } catch(HttpClientErrorException clientException) {
            throw new RuntimeException(clientException.getResponseBodyAsString());
        }
    }
}
