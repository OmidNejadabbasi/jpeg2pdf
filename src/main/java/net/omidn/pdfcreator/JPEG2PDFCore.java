package net.omidn.pdfcreator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JPEG2PDFCore {


    /**
     * Creates a PDF file from input images and saves it in the <code>destFile</code>.
     * Each page in the PDF has a width of a A4 paper but the height may vary.
     * @param files the name of the input files
     * @param destFile destination file
     * @throws IOException if any read or write error occurs.
     */
    public static void saveFileAsPDF(List<String> files, File destFile) throws IOException {
        PDDocument pdfFile = new PDDocument();

        if (files.isEmpty())
            throw new IllegalArgumentException("There are no input files!");

        for (String file : files) {

            PDImageXObject image;
            try {
                image = PDImageXObject.createFromFile(file, pdfFile);
                PDRectangle mediaBox = new PDRectangle(PDRectangle.A4.getWidth(), image.getHeight() * (PDRectangle.A4.getWidth() / image.getWidth()));
                PDPage pdPage = new PDPage(mediaBox);
                PDPageContentStream contentStream = new PDPageContentStream(pdfFile, pdPage);
                contentStream.drawImage(image, 0, 0, mediaBox.getWidth(), mediaBox.getHeight());
                contentStream.close();
                pdfFile.addPage(pdPage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

       pdfFile.save(destFile);

    }

}
