package theknife;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import entita.Ristorante;
import entita.Utente;
import gestioneFile.*;
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
//        System.out.println(Utente.inserisciRuolo());
        FileGestoreRistorante g = new FileGestoreRistorante();
        g.ottieniHashMap();
        GestioneMenu menu = new GestioneMenu();
//        List<String> lista = new ArrayList<>();
//        lista.add("marco");
//        lista.add("gamba");
//        lista.add("mgamba");
//        lista.add("pippo1");
//        lista.add("11/5/1995");
//        lista.add("Dongo");
//        lista.add("gestore");
//        FileUtenti.scritturaSuFile(FileUtenti.getPercorsoFile(), lista);
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println(FileUtenti.letturaCsv(FileUtenti.getPercorsoFile()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TheKnife.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
