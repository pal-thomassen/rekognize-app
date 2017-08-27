package rekognition.rekognize;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class RekognizedImagesException extends RuntimeException {

    RekognizedImagesException(String message) {
        super(message);
    }
}
