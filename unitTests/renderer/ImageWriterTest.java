package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void writeToImage() {
        ImageWriter imageWriter=new ImageWriter("myFirstPhoto",800,500);
        int nX=imageWriter.getNx();
        int nY=imageWriter.getNy();

        for(int i=0;i<nY;++i)
            for (int j=0;j<nX;++j) {
                imageWriter.writePixel(j, i,
                        j % 50 == 0 || i % 50 == 0 ? new Color(java.awt.Color.blue) : new Color(java.awt.Color.pink));
            }
       imageWriter.writeToImage();
    }
}