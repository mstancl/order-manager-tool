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

    public static void writeLineToPDF(PdfContentByte pdf, String text, int x, int y, BaseFont baseFont, int fontSize) {
        List<String> listOfWords = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        for (String word : text.split(" ")) {
            if (line.length() + word.length() > 60) {
                listOfWords.add(line.toString());
                line = new StringBuilder();
            }
            line.append(word);
            line.append(" ");
        }
        listOfWords.add(line.toString());

        for (String word : listOfWords) {
            pdf.beginText();
            pdf.setFontAndSize(baseFont, fontSize);
            pdf.setTextMatrix(x, y);
            pdf.showText(word);
            pdf.endText();
            y = y - 15;
        }

    }

    public static void writeToPDF(String pathToOriginalTemplate, String nameOfNewFile, Order order) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(pathToOriginalTemplate);
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream(nameOfNewFile));
        BaseFont font = BaseFont.createFont(
                BaseFont.HELVETICA,
                BaseFont.CP1252,
                BaseFont.NOT_EMBEDDED);

        PdfContentByte over = stamper.getOverContent(1);

        writeLineToPDF(over, order.getCustomer().getFirstName() + " " + order.getCustomer().getSurname(), 175, 576, font, 10);
        writeLineToPDF(over, order.getCustomer().getPhoneNumber() + " " + order.getCustomer().getEmail(), 175, 552, font, 10);
        writeLineToPDF(over, order.getDateWhenReceived().toString(), 175, 525, font, 10);
        writeLineToPDF(over, order.getDueDate().toString(), 175, 500, font, 10);
        writeLineToPDF(over, order.getOrderType(), 175, 475, font, 10);
        writeLineToPDF(over, "N/A", 175, 450, font, 10);
        writeLineToPDF(over, Long.toString(order.getEstimatedPrice()), 175, 425, font, 10);
        writeLineToPDF(over, order.getDescriptionOfOrder(), 175, 400, font, 10);
        writeLineToPDF(over, order.getSolutionForOrder(), 175, 360, font, 10);

        stamper.close();
    }
      /* // draw a red circle
            over.setRGBColorStroke(0xFF, 0x00, 0x00);
            over.setLineWidth(5f);
            over.ellipse(250, 450, 350, 550);
            over.stroke();*/

}
