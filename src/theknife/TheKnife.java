package theknife;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import entita.Ristorante;
import java.io.FileNotFoundException;
import gestioneFile.FileRistorante;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
//        FileRistorante.scritturaSuFile();
        Scanner scanner = new Scanner(System.in);
        List<List<String>> lista = new ArrayList<>();
//        try {
//            lista = FileRistorante.letturaCsv();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(TheKnife.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println(lista.getLast());
        System.out.println();
        lista = Ristorante.cercaRistorante(scanner.nextInt());
        System.out.println(lista);
        System.out.println("Benvenuto su The Knife");
    }

}
