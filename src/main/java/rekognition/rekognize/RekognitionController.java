package rekognition.rekognize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import rekognition.common.ParseDataImage;


@RestController
@CrossOrigin(origins = "*")
public class RekognitionController {

    private final RekognitionService rekognitionService;

    @Autowired
    public RekognitionController(RekognitionService rekognitionService) {
        this.rekognitionService = rekognitionService;
    }

    @RequestMapping(value = "/rekognize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RekognizedImages> rekognizeImage(@RequestBody ImageToRekognize imageToRekognize) {

        final String base64EncodedImage = ParseDataImage.parseDataImage(imageToRekognize.getBase64EncodedImage());
        final RekognizedImages rekognizedImages = rekognitionService.getFaceMatches(base64EncodedImage);
        System.out.println(rekognizedImages.getNum_rekognized());

        return new ResponseEntity<>(rekognizedImages, HttpStatus.OK);
    }

}
