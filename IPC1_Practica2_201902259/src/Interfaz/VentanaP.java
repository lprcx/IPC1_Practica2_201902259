/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

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
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Compufire
 */
public class VentanaP extends JFrame implements ActionListener {

    JButton examinar, generarg, ordenar;
    JRadioButton asc, desc, alg1, alg2, alg3;
    ButtonGroup ascydesc, algoritmos;
    JTextField titulo, ruta;

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
        alg1.setText("Algoritmo 1");
        alg1.setBounds(720, 150, 120, 30);
        alg1.setBackground(Color.PINK);

        alg2 = new JRadioButton();
        alg2.setText("Algoritmo 2");
        alg2.setBounds(720, 170, 120, 30);
        alg2.setBackground(Color.PINK);

        alg3 = new JRadioButton();
        alg3.setText("Algoritmo 3");
        alg3.setBounds(720, 190, 120, 30);
        alg3.setBackground(Color.PINK);

        algoritmos.add(alg1);
        algoritmos.add(alg2);
        algoritmos.add(alg3);
        add(alg1);
        add(alg2);
        add(alg3);

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

    }
    catch (Exception e

    
        ) {
            System.out.println("Hubo un error :c");
    }

    
        finally {
            try {
            if (null != lector) {
                lector.close();
            }
        } catch (Exception e2) {
            System.out.println(e2);
        }
    }
}

@Override
        public void actionPerformed(ActionEvent e) {
        if (e.getSource() == examinar) {
            cargararchivo();
        } else if (e.getSource() == generarg) {
            leerarchivo();
        }

    }
}
