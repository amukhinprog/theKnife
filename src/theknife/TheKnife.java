package theknife;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import entita.Ristorante;
import java.io.FileNotFoundException;
import gestioneFile.FileRistorante;
/**
 *
 * @author armuh
 */
public class TheKnife {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FileRistorante r = new FileRistorante();
        r.scritturaSuFile();
        
        System.out.println("Benvenuto su The Knife");
    }

}
