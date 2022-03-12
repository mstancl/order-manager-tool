package com.mstancl.ordermanagertool.util.export;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.mstancl.ordermanagertool.data.enums.Status;
import com.mstancl.ordermanagertool.data.pojo.Customer;
import com.mstancl.ordermanagertool.data.pojo.Order;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddContentToPDF {

    public static void main(String[] args) throws DocumentException, IOException {
        Order testOrder = new Order(
                5, new Customer("Martin", "Stancl", "732166248", "mstancl@email.com"),
                LocalDate.now(), LocalDate.now(), "m4", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Cras pede libero, dapibus nec, pretium sit amet, te", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Cras pede libero, dapibus nec, pretium sit amet, tempor quis. Fusce consectetuer risus a nunc. Neque porro quisquam est,", 3000, Status.NEW
        );


        writeToPDF(
                "pdfs/originalTemplate.pdf",
                "pdfs/test.pdf",
                testOrder);
    }

    public static void writeToPDF(String pathToOriginalTemplate, String nameOfNewFile, Order order) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(pathToOriginalTemplate);
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream(nameOfNewFile));
        BaseFont bf = BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); // set font


        // get object for writing over the existing content;
        // you can also use getUnderContent for writing in the bottom layer
        PdfContentByte over = stamper.getOverContent(1);

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

        List<String> listOfDescriptionLines = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        for (String wordOfDescription : order.getDescriptionOfOrder().split(" ")) {
            if (line.length() + wordOfDescription.length() > 60) {
                listOfDescriptionLines.add(line.toString());
                line = new StringBuilder();
            }
            line.append(wordOfDescription);
            line.append(" ");
        }
        listOfDescriptionLines.add(line.toString());

        int y = 400;
        for (String lineOfDescription : listOfDescriptionLines){
            over.beginText();
            over.setFontAndSize(bf, 10);
            over.setTextMatrix(175, y);
            over.showText(lineOfDescription);
            over.endText();

            y=y-15;
        }


        List<String> listOfSolutionLines = new ArrayList<>();
        StringBuilder solutionLine = new StringBuilder();
        for (String wordOfSolution : order.getSolutionForOrder().split(" ")) {
            if (solutionLine.length() + wordOfSolution.length() > 60) {
                listOfSolutionLines.add(solutionLine.toString());
                solutionLine = new StringBuilder();
            }
            solutionLine.append(wordOfSolution);
            solutionLine.append(" ");
        }
        listOfSolutionLines.add(solutionLine.toString());

        int solutionY = 360;
        for (String lineOfSolution : listOfSolutionLines){
            over.beginText();
            over.setFontAndSize(bf, 10);
            over.setTextMatrix(175, solutionY);
            over.showText(lineOfSolution);
            over.endText();
            solutionY=solutionY-15;
        }

           /* // draw a red circle
            over.setRGBColorStroke(0xFF, 0x00, 0x00);
            over.setLineWidth(5f);
            over.ellipse(250, 450, 350, 550);
            over.stroke();*/


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
