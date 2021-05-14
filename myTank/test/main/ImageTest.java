package main;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * @Author:Administrator
 **/
public class ImageTest {

    @Test
    public void test(){
        try {
            BufferedImage image2=ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("main/Resource/images/bulletD.gif"));
            assertNotNull(image2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
