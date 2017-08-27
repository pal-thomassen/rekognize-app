package rekognition.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rekognition.common.ParseDataImage;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @RequestMapping(value = "/admin/upload", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity uploadImage(@RequestBody ImageFromUser imageFromUser) {

        ImageToUpload imageToUpload = new ImageToUpload();
        imageToUpload.setImageKey(imageFromUser.getImageId());
        imageToUpload.setImage(ParseDataImage.parseDataImage(imageFromUser.getImageSrc()));
        String response = adminService.uploadImage(imageToUpload);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/admin/listfaces", method = RequestMethod.GET)
    public ResponseEntity<List<IndexedFace>> listFaces() {
        return ResponseEntity.ok(adminService.listFacesIndexed());
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteImage(@RequestBody ImageToDelete imageToDelete) {
        adminService.deleteImage(imageToDelete);

        return ResponseEntity.ok().build();
    }

}
