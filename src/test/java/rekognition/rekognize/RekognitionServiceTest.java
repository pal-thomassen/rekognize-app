package rekognition.rekognize;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rekognition.Application;

import static rekognition.common.ParseDataImage.parseDataImage;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RekognitionServiceTest {

    private final String dataImage = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAZAAAAEsCAYAAADtt+XCAAAgAElEQVR4XqS9+bOu2XUW9p7x9qSWZA3tCYyEXeWyKcoQ54f4T0ylKiGAjWOGhE";
    private final String imageBase64encoded = "iVBORw0KGgoAAAANSUhEUgAAAZAAAAEsCAYAAADtt+XCAAAgAElEQVR4XqS9+bOu2XUW9p7x9qSWZA3tCYyEXeWyKcoQ54f4T0ylKiGAjWOGhE";

    @Test
    public void parseImageTest() {
        String image = parseDataImage(dataImage);

        Assert.assertEquals(imageBase64encoded, image);
    }
}