package com.example.gpaie.Utils;

import org.springframework.data.geo.Point;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.pdf.PdfPCell;

public class PdfUtil {

 /*    public void addCell(Phrase content, Point location) throws BadElementException {
        Cell cell = new Cell(content);
        cell.setBorder( defaultCell.getBorder());
        cell.setBorderWidth(defaultCell.getBorderWidth());
        cell.setBorderColor(defaultCell.getBorderColor());
        cell.setBackgroundColor(defaultCell.getBackgroundColor());
        cell.setHorizontalAlignment(defaultCell.getHorizontalAlignment());
        cell.setVerticalAlignment(defaultCell.getVerticalAlignment());
        cell.setColspan(defaultCell.getColspan());
        cell.setRowspan(defaultCell.getRowspan());
      }  */
      public static PdfPCell addCell(Phrase content,boolean isHeader){
        
        PdfPCell cell = new PdfPCell(content);
        cell.setPadding(2);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(20f);
       if(isHeader){
        cell.setFixedHeight(40f);
       }
        return cell;

      }
      public static Cell addCell1(Phrase content,double with){
        
        Cell cell = new Cell(content);
        cell.setWidth(((float)with));
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cell.setBorder(cell.getBorder());
        return cell;

      }
}
