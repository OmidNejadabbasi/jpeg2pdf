package net.omidn.pdfcreator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JPEG2PDFTest {

    @Test
    public void testConvertingBigImageFiles() {
        List<String> files = new ArrayList<String>();
        files.add("src/test/resources/img1.jpg");
        files.add("src/test/resources/img2.jpg");

        File destFile = new File("src/test/resources/test.pdf");
        try {
            JPEG2PDFCore.saveFileAsPDF(files, destFile);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        destFile.delete();
    }

}
