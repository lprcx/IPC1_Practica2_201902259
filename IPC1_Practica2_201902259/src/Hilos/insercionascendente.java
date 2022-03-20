/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hilos;

import Interfaz.Reporte;
import static Interfaz.VentanaP.cmov;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author
 */
public class insercionascendente extends Thread {

    JPanel panel;
    int[] arreglo;
    Cronometro cr;
    int movimientos;

    public insercionascendente(JPanel panel, int[] arreglo, Cronometro cr) {
        this.panel = panel;
        this.arreglo = arreglo;
        this.cr = cr;
        this.movimientos = 0;
    }
    public boolean state = true;

    @Override
    public void run() {
        if (state) {
            try {
                Insercion(this.arreglo);
                this.cr.stop();
                Reporte r = new Reporte();
                r.reporte();
            } catch (InterruptedException ex) {

            }
        }

    }

    public void parar() {
        state = false;
    }

    public void Insercion(int[] A) throws InterruptedException {
        int aux;
        this.cr.start();
        for (int i = 1; i < A.length; i++) {
            aux = A[i];
            int j = i - 1;
            while ((j >= 0) && (aux < A[j])) {
                A[j + 1] = A[j];
                j--;
                /*this.panel.removeAll();
                grafica(A);
                this.panel.repaint();*/
                sleep(500);

            }
            A[j + 1] = aux;
            this.panel.removeAll();
            grafica(A);
            this.movimientos++;
            this.panel.repaint();
            cmov.setText(String.valueOf(this.movimientos));
        }
        parar();
    }

    public void grafica(int[] numeros) {
        //grafica de barras de prestamos
        String c = "";
        DefaultCategoryDataset datos2 = new DefaultCategoryDataset();
        for (int i = 0; i < numeros.length; i++) {
            datos2.addValue(numeros[i], String.valueOf(numeros[i]), "");
        }

        JFreeChart barras = ChartFactory.createBarChart("", "numeros", "", datos2, PlotOrientation.VERTICAL, true, true, false);
        barras.setBackgroundPaint(Color.PINK);
        ChartPanel p2 = new ChartPanel(barras);
        p2.setBounds(0, 0, 600, 350);
        p2.setVisible(true);
        this.panel.add(p2);

    }
}
