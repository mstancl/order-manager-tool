package com.mstancl.ordermanagertool.util.export;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.mstancl.ordermanagertool.data.pojo.Order;

import java.io.FileOutputStream;
import java.io.IOException;

public class AddContentToPDF {

    public static void main(String[] args) throws DocumentException, IOException {
        writeToPDF("C:\\Users\\mstan\\IdeaProjects\\order-manager-tool\\src\\main\\resources\\userStory\\test.pdf", "C:\\Users\\mstan\\IdeaProjects\\order-manager-tool\\src\\main\\resources\\userStory\\testTest.pdf", null);
    }

    public static void writeToPDF(String pathToOriginalTemplate, String nameOfNewFile, Order order) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(pathToOriginalTemplate);
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream(nameOfNewFile));
        BaseFont bf = BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); // set font

        //loop on pages (1-based)
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {

            // get object for writing over the existing content;
            // you can also use getUnderContent for writing in the bottom layer
            PdfContentByte over = stamper.getOverContent(i);

            // name
            over.beginText();
            over.setFontAndSize(bf, 10);
            over.setTextMatrix(175, 576);
            over.showText(order.getCustomer().getFirstName() + " " + order.getCustomer().getSurname());
            over.endText();

            over.beginText();
            over.setFontAndSize(bf, 10);
            over.setTextMatrix(175, 552);
            over.showText(order.getCustomer().getPhoneNumber() + " " + order.getCustomer().getEmail());
            over.endText();

            over.beginText();
            over.setFontAndSize(bf, 10);
            over.setTextMatrix(175, 525);
            over.showText(order.getDateWhenReceived().toString());
            over.endText();

            over.beginText();
            over.setFontAndSize(bf, 10);
            over.setTextMatrix(175, 500);
            over.showText(order.getDueDate().toString());
            over.endText();

            over.beginText();
            over.setFontAndSize(bf, 10);
            over.setTextMatrix(175, 475);
            over.showText(order.getOrderType());
            over.endText();

            over.beginText();
            over.setFontAndSize(bf, 10);
            over.setTextMatrix(175, 450);
            over.showText("N/A");
            over.endText();

            over.beginText();
            over.setFontAndSize(bf, 10);
            over.setTextMatrix(175, 425);
            over.showText(Long.toString(order.getEstimatedPrice()));
            over.endText();

            over.beginText();
            over.setFontAndSize(bf, 10);
            over.setTextMatrix(175, 400);
            over.showText(order.getDescriptionOfOrder());
            over.endText();

            over.beginText();
            over.setFontAndSize(bf, 10);
            over.setTextMatrix(175, 360);
            over.showText(order.getSolutionForOrder());
            over.endText();


           /* // draw a red circle
            over.setRGBColorStroke(0xFF, 0x00, 0x00);
            over.setLineWidth(5f);
            over.ellipse(250, 450, 350, 550);
            over.stroke();*/
        }

        stamper.close();
    }

    /*  public static void main(String[] args) throws IOException, DocumentException {

     *//* example inspired from "iText in action" (2006), chapter 2 *//*

        PdfReader reader = new PdfReader("C:\\Users\\mstan\\IdeaProjects\\order-manager-tool\\src\\main\\resources\\userStory\\test.pdf"); // input PDF
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream("C:\\Users\\mstan\\IdeaProjects\\order-manager-tool\\src\\main\\resources\\userStory\\testTest.pdf")); // output PDF
        BaseFont bf = BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); // set font

        //loop on pages (1-based)
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {

            // get object for writing over the existing content;
            // you can also use getUnderContent for writing in the bottom layer
            PdfContentByte over = stamper.getOverContent(i);

            // write text
            over.beginText();
            over.setFontAndSize(bf, 10);    // set font and size
            over.setTextMatrix(107, 740);   // set x,y position (0,0 is at the bottom left)
            over.showText("I can write at page " + i);  // set text
            over.endText();

            // draw a red circle
            over.setRGBColorStroke(0xFF, 0x00, 0x00);
            over.setLineWidth(5f);
            over.ellipse(250, 450, 350, 550);
            over.stroke();
        }

        stamper.close();

    }*/
}
