package com.mycompany.reportespdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;

public class ReportesPdf {

    public static void main(String[] args) {
        String nombreArchivo = "reporte_intelaf.pdf";
        Document documento = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            // Título
            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.RED);
            Paragraph titulo = new Paragraph("Reporte Intelaf", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            documento.add(new Paragraph(" ")); // Espacio

            // Subtítulo
            Font subTituloFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Paragraph subTitulo = new Paragraph("Resumen de Resultados - Agosto 2025", subTituloFont);
            subTitulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(subTitulo);

            documento.add(new Paragraph(" ")); // Espacio

            // Tabla de ejemplo
            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(100);
            tabla.setSpacingBefore(10f);
            tabla.setSpacingAfter(10f);

            // Encabezados
            Font headFont = new Font(Font.FontFamily.HELVETICA, 60, Font.BOLD, BaseColor.ORANGE);
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Producto", headFont));
            cell.setBackgroundColor(BaseColor.GRAY);
            tabla.addCell(cell);

            cell = new PdfPCell(new Phrase("Cantidad", headFont));
            cell.setBackgroundColor(BaseColor.GRAY);
            tabla.addCell(cell);

            cell = new PdfPCell(new Phrase("Total", headFont));
            cell.setBackgroundColor(BaseColor.GRAY);
            tabla.addCell(cell);

            // Datos de ejemplo
            tabla.addCell("Laptop");
            tabla.addCell("5");
            tabla.addCell("$10,000");

            tabla.addCell("Mouse");
            tabla.addCell("15");
            tabla.addCell("$300");

            tabla.addCell("Teclado");
            tabla.addCell("10");
            tabla.addCell("$500");

            documento.add(tabla);

            // Pie de página
            Font pieFont = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC);
            Paragraph pie = new Paragraph("Reporte generado automáticamente por el sistema.", pieFont);
            pie.setAlignment(Element.ALIGN_RIGHT);
            documento.add(pie);
            
            System.out.println("Reporte PDF generado exitosamente: " + nombreArchivo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            documento.close();
        }
    }
}