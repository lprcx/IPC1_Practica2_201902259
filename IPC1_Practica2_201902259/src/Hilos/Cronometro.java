package Hilos;

import static Interfaz.VentanaP.cronom;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cronometro extends Thread {

    int minutos;
    int segundos;

    public Cronometro() {
        this.minutos = 0;
        this.segundos = 0;
    }
    public static boolean ban = true;

    @Override
    public void run() {
        if (ban) {
            try {
                while (this.minutos != 100) {
                    this.segundos = 0;
                    while (this.segundos != 60) {
                        if (this.minutos < 10) {
                            if (this.segundos < 10) {
                                System.out.println("0" + this.minutos + ":0" + this.segundos);
                                cronom.setText("0" + this.minutos + ":0" + this.segundos);

                            } else {
                                System.out.println("0" + this.minutos + ":" + this.segundos);
                                cronom.setText("0" + this.minutos + ":" + this.segundos);

                            }
                        } else {

                            if (this.segundos < 10) {
                                System.out.println(this.minutos + ":0" + this.segundos);
                                cronom.setText( this.minutos + ":0" + this.segundos);

                            } else {
                                System.out.println(this.minutos + ":" + this.segundos);
                                cronom.setText(this.minutos + ":" + this.segundos);

                            }
                        }
                        this.segundos++;
                        sleep(1000);

                    }
                    this.minutos++;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    public static void parar(){
        ban = false;
    }

}
