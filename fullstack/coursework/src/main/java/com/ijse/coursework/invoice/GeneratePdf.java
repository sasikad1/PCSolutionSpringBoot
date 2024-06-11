package com.ijse.coursework.invoice;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import com.ijse.coursework.controller.UserController;
import com.ijse.coursework.entity.Order;
import com.ijse.coursework.entity.OrderItem;
import com.ijse.coursework.entity.User;
import com.ijse.coursework.service.OrderItemService;
import com.ijse.coursework.service.OrderService;
import com.ijse.coursework.service.UserService;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.time.LocalDate;

import jakarta.persistence.Entity;

@Configuration
@EnableMethodSecurity
public class GeneratePdf {
   @Autowired
   private UserService userService;

   @Autowired
   private OrderService orderService;

   @Autowired
   private OrderItemService orderItemService;

   @Autowired
   private OrderItem orderItem;

    public void pdfGenerate(Long userid, Long orderId) throws FileNotFoundException, MalformedURLException {

      //get User
      User user = userService.getUserById(userid);
      Order order = orderService.getOrderById(orderId);
      List<OrderItem> orderItems =orderItemService.getOrderItemById(orderId);
      System.out.println(user.getAddress());
      System.out.println(order.getOrderDate());
      //end user

        String path = "fullstack\\coursework\\src\\main\\resources\\temp_pdf\\invoice.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
  
        
        Document document = new Document(pdfDocument);
        //img
        String imagePath="D:\\IJSE\\CourseWork3\\SpringBoot\\fullstack\\coursework\\src\\main\\resources\\img\\logo.png";
        ImageData imageData = ImageDataFactory.create(imagePath);
        Image image = new Image(imageData);
  
        float x=pdfDocument.getDefaultPageSize().getWidth()/2;
        float y=pdfDocument.getDefaultPageSize().getHeight()/2;
        image.setFixedPosition(x-150, y-170);
        image.setOpacity(0.1f);
        document.add(image);
        //end img
  
        float threecol = 190f;
        float twocol = 285f;
        float twocol150 = twocol + 150f;
        float twocolumnWidth[] = { twocol150, twocol };
        float threeColumnWidth[] = { threecol, threecol, threecol };
        float fullwidth[] = { threecol * 3 };
        Paragraph onesp = new Paragraph("\n");

        Table table = new Table(twocolumnWidth);
        table.addCell(new Cell().add("Invoice").setFontSize(10f).setBorder(Border.NO_BORDER).setBold());
        Table nestedTable = new Table(new float[] { twocol / 2, twocol / 2 });
        nestedTable.addCell(getHeaderTextCell("Invoice No."));
        nestedTable.addCell(getHeaderTextCellValue("ord"+String.valueOf(order.getId())));
        nestedTable.addCell(getHeaderTextCell("Invoice Date"));
      //   nestedTable.addCell(getHeaderTextCellValue("15/05/2024"));
      nestedTable.addCell(getHeaderTextCellValue(String.valueOf(LocalDate.now())));
      
        table.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));
  
        Border gb = new SolidBorder(Color.GRAY, 2f);
        Table divider = new Table(fullwidth);
        divider.setBorder(gb);
        document.add(table);
        document.add(onesp);
        document.add(divider);
        document.add(onesp);
  
        Table twoColTable = new Table(twocolumnWidth);
        twoColTable.addCell(getBillingandShippingCell("Billing Information"));
      //   twoColTable.addCell(getBillingandShippingCell("Customer Information"));
        document.add(twoColTable.setMarginBottom(12f));
  
        Table twoColTable2 = new Table(twocolumnWidth);
        twoColTable2.addCell(getCell10fLeft("Company", true));
        twoColTable2.addCell(getCell10fLeft("Address", true));
        twoColTable2.addCell(getCell10fLeft("PC Solution", false));
        twoColTable2.addCell(getCell10fLeft("137/2, Embaraluwa North, Weliweriya", false));
        document.add(twoColTable2);
  
        Table twoColTable3 = new Table(twocolumnWidth);
        twoColTable3.addCell(getCell10fLeft("Employe Name", true));
        twoColTable3.addCell(getCell10fLeft("Email", true));
        twoColTable3.addCell(getCell10fLeft(user.getUsername(), false));
        twoColTable3.addCell(getCell10fLeft("pcsolution@gmail.com", false));
        document.add(twoColTable3);
  
        float oneColumnwidth[] = { twocol150 };
  
      //   Table oneCoTable1 = new Table(oneColumnwidth);
      //   oneCoTable1.addCell(getCell10fLeft("Address", true));
      //   oneCoTable1.addCell(getCell10fLeft("137/2, Embaraluwa North, Weliweriya", false));
      //   oneCoTable1.addCell(getCell10fLeft("Email", true));
      //   oneCoTable1.addCell(getCell10fLeft("pcsolutiongmail.com", false));
      //   document.add(oneCoTable1.setMarginBottom(10f));
  
        Table tableDivider2 = new Table(fullwidth);
        Border dgb = new DashedBorder(Color.GRAY, 0.5f);
        document.add(tableDivider2.setBorder(dgb));
        Paragraph producPara = new Paragraph("Items");
  
        document.add(producPara.setBold());
        Table treeColTable1 = new Table(threeColumnWidth);
        treeColTable1.setBackgroundColor(Color.BLACK, 0.7f);
  
        treeColTable1
              .addCell(new Cell().add("Description").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
        treeColTable1.addCell(new Cell().add("Quantity").setBold().setFontColor(Color.WHITE)
              .setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        treeColTable1.addCell(new Cell().add("Price").setBold().setFontColor(Color.WHITE)
              .setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(15f));
        document.add(treeColTable1);

        
        
      //   List<Product> productList = new ArrayList<>();
      //   productList.add(new Product("apple", 2, 159));
      //   productList.add(new Product("mango", 4, 205));
      //   productList.add(new Product("banana", 2, 90));
      //   productList.add(new Product("grapers", 2, 10));
      //   productList.add(new Product("coconut", 2, 61));
      //   productList.add(new Product("cherry", 1, 1000));
      //   productList.add(new Product("kiwi", 3, 30));
  
        Table treeColTable2 = new Table(threeColumnWidth);
  
        List<OrderItem> orderItem2 =orderItemService.getOrderItemById(orderId);
        double totalSum=0f;
        for (OrderItem orderItem : orderItem2) {
           double total=orderItem.getQty()*orderItem.getItem().getPrice();
           totalSum += total;
           treeColTable2.addCell(new Cell().add(orderItem.getItem().getName()).setBorder(Border.NO_BORDER)).setMarginLeft(10f);
           treeColTable2.addCell(new Cell().add(String.valueOf(orderItem.getQty())).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
           treeColTable2.addCell(new Cell().add(String.valueOf(total)).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(15f);
        }
        document.add(treeColTable2.setMarginBottom(20f));
        float onetwo[]={threecol+125f,threecol*2};
        Table threeColTable4 = new Table(onetwo);
        threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        threeColTable4.addCell(new Cell().add(tableDivider2)).setBorder(Border.NO_BORDER);
        document.add(threeColTable4);
  
        Table threeColTable3 = new Table(threeColumnWidth);
        threeColTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER)).setMarginLeft(10f);
        threeColTable3.addCell(new Cell().add("Total").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        threeColTable3.addCell(new Cell().add(String.valueOf(totalSum)).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(15f);
  
        document.add(threeColTable3);
        document.add(tableDivider2);
        document.add(new Paragraph("\n"));
        document.add(divider.setBorder(new SolidBorder(Color.GRAY, 1)).setMarginBottom(15f));
        Table tb =new Table(fullwidth);
        tb.addCell(new Cell().add("TERMS AND CONDITIONS\n").setBold().setBorder(Border.NO_BORDER));
        List<String> TncList = new ArrayList<>();
        TncList.add("1. The Seller shall not be to liable to the Buyer directly or indirectly for ant loss or damage suffered by the Buyer.");
        TncList.add("2. The Seller warrants the product for product for onr (1) year from the date of shipment");
  
        for(String tnc:TncList){
           tb.addCell(new Cell().add(tnc).setBorder(Border.NO_BORDER));
        }
        document.add(tb);
  
  
        document.close();
  
        System.out.println("pdf generated");
     }

     static Cell getHeaderTextCell(String textValue) {
        return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
     }
  
     static Cell getHeaderTextCellValue(String textValue) {
        return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
     }
  
     static Cell getBillingandShippingCell(String textValue) {
        return new Cell().add(textValue).setFontSize(12f).setBold().setBorder(Border.NO_BORDER)
              .setTextAlignment(TextAlignment.LEFT);
     }
  
     static Cell getCell10fLeft(String textValue, Boolean isBold) {
        Cell myCell = new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER)
              .setTextAlignment(TextAlignment.LEFT);
        return isBold ? myCell.setBold() : myCell;
     }
}
/////////////////////////////////////
