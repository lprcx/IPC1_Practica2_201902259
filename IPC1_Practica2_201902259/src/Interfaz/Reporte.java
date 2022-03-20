/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import static Interfaz.VentanaP.cmov;
import static Interfaz.VentanaP.cronom;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author Compufire
 */
public class Reporte {

    public void reporte() {
        String nombreReporte;
        File reporte;
        FileWriter fw;
        BufferedWriter br;
        String cadenaHTML;

        try {
            DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
            String fecha = dtf3.format(LocalDateTime.now());
            nombreReporte = fecha + ".html";
            reporte = new File(nombreReporte);
            fw = new FileWriter(reporte);
            br = new BufferedWriter(fw);

            cadenaHTML = "<!doctype html>\n"
                    + "<html lang=\"en\">\n"
                    + "  <head>\n"
                    + "    <!-- Required meta tags -->\n"
                    + "    <meta charset=\"utf-8\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                    + "\n"
                    + "    <!-- Bootstrap CSS -->\n"
                    + "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n"
                    + "\n"
                    + "  </head>"
                    + "    <body>"
                    + "     <h1><center>Reporte</center></h1>"
                    + "         <h3>Lourdes Patricia Reyes Castillo 201902259</h3>"
                    + "         <h3>Algoritmo: insercion</h3>"
                    + "         <h3>Tiempo transcurrido:" + cronom.getText() + "</h3>"
                    + "         <h3>Cantidad de movimientos:" + cmov.getText() + "</h3>"
                    + "         <h2>Datos obtenidos del Json:</h2>"
                    + "       <table class=\"table table-dark table-striped\">\n"
                    + "        <thead>\n"
                    + "            <tr>\n"
                    + "              <th scope=\"col\">No.</th>\n"
                    + "              <th scope=\"col\">Numero</th>\n"
                    + "            </tr>\n"
                    + "          </thead>\n"
                    + "          <tbody>";

            for (int i = 0; i < VentanaP.numerosd.length; i++) {
                cadenaHTML += "<tr>\n"
                        + "              <td>" + String.valueOf(i + 1) + "</td>\n"
                        + "              <td>" + String.valueOf(VentanaP.numerosd[i]) + "</td>\n"
                        + "            </tr>";
            }

            cadenaHTML += "</tbody>\n"
                    + "      </table>\n"
                    + "\n"
                    + "         <h2>Ordenamiento por pasos:</h2>";
            cadenaHTML += Insercion(VentanaP.numerosd);
            cadenaHTML += "         <h2>Datos Ordenados:</h2>"
                    + "       <table class=\"table table-dark table-striped\">\n"
                    + "        <thead>\n"
                    + "            <tr>\n"
                    + "              <th scope=\"col\">No.</th>\n"
                    + "              <th scope=\"col\">Numero</th>\n"
                    + "            </tr>\n"
                    + "          </thead>\n"
                    + "          <tbody>";
            for (int i = 0; i < VentanaP.numeros.length; i++) {
                cadenaHTML += "<tr>\n"
                        + "              <td>" + String.valueOf(i + 1) + "</td>\n"
                        + "              <td>" + String.valueOf(VentanaP.numeros[i]) + "</td>\n"
                        + "            </tr>";
            }
            cadenaHTML += "</tbody>\n"
                    + "      </table>\n";
            cadenaHTML
                    += "  </body>\n"
                    + "</html>";

            br.write(cadenaHTML);

            br.close();
            fw.close();
            crearPdf(cadenaHTML, fecha);

        } catch (IOException ex) {

        }
    }

    public void crearPdf(String contenido, String nombre) {
        try {

            Document document = new Document(PageSize.LETTER);
            PdfWriter.getInstance(document, new FileOutputStream(nombre + ".pdf"));

            document.open();
            document.addAuthor("Patty");
            document.addCreator("Patty");
            document.addSubject("Reporte de Grafica");
            document.addCreationDate();
            document.addTitle("Reporte de Grafica");

            HTMLWorker htmlWorker = new HTMLWorker(document);
            htmlWorker.parse(new StringReader(contenido));

            document.close();
            abrirarchivo(nombre + ".pdf");
            abrirarchivo(nombre + ".html");
        } catch (Exception e) {

        }
    }

    public void abrirarchivo(String ruta) {
        try {
            File rep = new File(ruta);
            Desktop.getDesktop().open(rep);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo");
        }
    }

    public String Insercion(int[] A) {
        int aux;
        String tabla = "";
        for (int i = 1; i < A.length; i++) {
            aux = A[i];
            int j = i - 1;
            while ((j >= 0) && (aux < A[j])) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = aux;
            tabla += "       <table class=\"table table-dark table-striped\">\n"
                    + "        <thead>\n"
                    + "            <tr>\n"
                    + "              <th scope=\"col\">No.</th>\n"
                    + "              <th scope=\"col\">Numero</th>\n"
                    + "            </tr>\n"
                    + "          </thead>\n"
                    + "          <tbody>";
            int contador = 1;
            for (int k = 0; k < A.length; k++) {
                tabla += "<tr>\n"
                        + "              <td>" + String.valueOf(contador) + "</td>\n"
                        + "              <td>" + String.valueOf(A[k]) + "</td>\n"
                        + "            </tr>"
                        + "<br>";
                contador++;
            }
            tabla += "</tbody>\n"
                    + "      </table>\n";
        }
        return tabla;
    }
}
