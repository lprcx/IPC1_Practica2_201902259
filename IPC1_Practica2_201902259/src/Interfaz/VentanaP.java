/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import Hilos.Cronometro;
import Hilos.insercionascendente;
import Hilos.inserciondescendente;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Compufire
 */
public class VentanaP extends JFrame implements ActionListener {

    JButton examinar, generarg, ordenar;
    JRadioButton asc, desc, alg1, alg2, alg3;
    ButtonGroup ascydesc, algoritmos;
    JTextField titulo, ruta;
    public JPanel graf;
    public ChartPanel p2;
    public static JLabel cronom, tiempo, mov, cmov;

    public VentanaP() {
        //Radio Button ascendente y descendente
        ascydesc = new ButtonGroup();
        asc = new JRadioButton();
        asc.setText("Ascendente");
        asc.setBounds(720, 100, 120, 30);
        asc.setBackground(Color.PINK);

        desc = new JRadioButton();
        desc.setText("Descendente");
        desc.setBounds(720, 120, 120, 30);
        desc.setBackground(Color.PINK);

        ascydesc.add(asc);
        ascydesc.add(desc);
        add(asc);
        add(desc);

        //Radio Button algoritmos
        algoritmos = new ButtonGroup();
        alg1 = new JRadioButton();
        alg1.setText("Quicksort");
        alg1.setBounds(720, 150, 120, 30);
        alg1.setBackground(Color.PINK);
        alg1.setSelected(true);

        algoritmos.add(alg1);
        add(alg1);

        //texfield 
        ruta = new JTextField();
        ruta.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        ruta.setBounds(100, 20, 550, 25);
        ruta.setVisible(true);
        ruta.setEditable(false);
        ruta.addActionListener(this);
        this.add(ruta);

        //texfield 
        titulo = new JTextField();
        titulo.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        titulo.setBounds(100, 55, 550, 25);
        titulo.setVisible(true);
        titulo.setEditable(false);
        titulo.addActionListener(this);
        this.add(titulo);

        //Boton de examinar
        examinar = new JButton("Examinar");
        examinar.setBounds(700, 20, 150, 25);
        examinar.setFont(new Font("Consolas", Font.BOLD, 14));
        examinar.addActionListener(this);
        examinar.setBackground(Color.LIGHT_GRAY);
        this.add(examinar);

        //Boton de generar grafica
        generarg = new JButton("Generar gráfica");
        generarg.setBounds(700, 60, 170, 25);
        generarg.setFont(new Font("Consolas", Font.BOLD, 14));
        generarg.addActionListener(this);
        generarg.setBackground(Color.LIGHT_GRAY);
        this.add(generarg);

        //Boton de ordenar
        ordenar = new JButton("Ordenar");
        ordenar.setBounds(700, 240, 150, 25);
        ordenar.setFont(new Font("Consolas", Font.BOLD, 14));
        ordenar.addActionListener(this);
        ordenar.setBackground(Color.LIGHT_GRAY);
        this.add(ordenar);

        //JLabel
        cronom = new JLabel("00:00");
        cronom.setBounds(770, 270, 80, 30);
        cronom.setFont(new Font("Consolas", Font.BOLD, 14));
        cronom.setVisible(true);
        this.add(cronom);
        
        //JLabel
        tiempo = new JLabel("Tiempo:");
        tiempo.setBounds(710, 270, 80, 30);
        tiempo.setFont(new Font("Consolas", Font.BOLD, 14));
        tiempo.setVisible(true);
        this.add(tiempo);
        
        //JLabel
        mov = new JLabel("Movimientos:");
        mov.setBounds(710, 320, 100, 30);
        mov.setFont(new Font("Consolas", Font.BOLD, 14));
        mov.setVisible(true);
        this.add(mov);
        
          //JLabel
        cmov = new JLabel("0");
        cmov.setBounds(820, 320, 100, 30);
        cmov.setFont(new Font("Consolas", Font.BOLD, 14));
        cmov.setVisible(true);
        this.add(cmov);

        graf = new JPanel();
        //graf.setBackground(Color.BLACK);
        graf.setBounds(75, 95, 600, 350);
        graf.setLayout(null);
        this.add(graf);

        //ICONO DE LA APLICACION
        this.setIconImage(new ImageIcon(getClass().getResource("Usac_logo.png")).getImage());

        //ventana
        this.setTitle("Practica 2");
        this.setLayout(null);
        this.setBounds(190, 100, 900, 500);
        this.setResizable(false);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.PINK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    String textcont = "";
    File archivo;
    FileReader lector;
    BufferedReader buff;

    public void cargararchivo() {
        try {
            JFileChooser busc = new JFileChooser();
            int o = busc.showOpenDialog(this);
            if (o == JFileChooser.APPROVE_OPTION) {
                System.out.println(busc.getSelectedFile());
                archivo = busc.getSelectedFile();
                System.out.println(archivo);
                ruta.setEditable(true);
                ruta.setText(String.valueOf(busc.getSelectedFile()));
                ruta.setEditable(false);
            }

        } catch (Exception e) {
            System.out.println("Hubo un error :c");

        }
    }

    int[] numeros;

    public void leerarchivo() {
        try {
            lector = new FileReader(archivo);
            buff = new BufferedReader(lector);
            String contline;
            while ((contline = buff.readLine()) != null) {
                textcont += contline;
            }
            System.out.println(textcont);
            JsonParser JSONValue = new JsonParser();
            Object objeto = JSONValue.parse(textcont);

            JsonObject ob = (JsonObject) objeto;
            String Titulo = ob.get("title").getAsString();
            titulo.setEditable(true);
            titulo.setText(Titulo);
            titulo.setEditable(false);
            Object datos = ob.get("dataset");
            JsonArray arreglo = (JsonArray) datos;
            numeros = new int[arreglo.size()];
            for (int i = 0; i < arreglo.size(); i++) {
                System.out.println("numero " + i + " : " + arreglo.get(i).getAsInt());
                numeros[i] = arreglo.get(i).getAsInt();
            }

        } catch (Exception e) {
            System.out.println("Hubo un error :c");
        } finally {
            try {
                if (null != lector) {
                    lector.close();
                }
            } catch (Exception e2) {
                System.out.println(e2);
            }
        }
    }

    public void grafica() {
        //grafica de barras de prestamos
        DefaultCategoryDataset datos2 = new DefaultCategoryDataset();
        for (int i = 0; i < numeros.length; i++) {
            datos2.addValue(numeros[i], String.valueOf(numeros[i]), "");
        }

        JFreeChart barras = ChartFactory.createBarChart("", "numeros", "", datos2, PlotOrientation.VERTICAL, true, true, false);
        barras.setBackgroundPaint(Color.PINK);
        barras.getTitle().setPaint(Color.BLACK);
        barras.getTitle().setFont(new Font("Century Gothic", Font.PLAIN, 15));
        p2 = new ChartPanel(barras);
        p2.setBounds(0, 0, 600, 350);
        p2.setVisible(true);
        graf.add(p2);
        graf.repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == examinar) {
            cargararchivo();
        } else if (e.getSource() == generarg) {
            leerarchivo();
            grafica();
        } else if (e.getSource() == ordenar) {
            Cronometro c = new Cronometro();
            if (asc.isSelected() == true) {
                insercionascendente i = new insercionascendente(graf, numeros, c);
                i.start();
            }
            else if (desc.isSelected()==true) {
                inserciondescendente d = new inserciondescendente(graf, numeros, c);
                d.start();
            }
        }

    }
}
