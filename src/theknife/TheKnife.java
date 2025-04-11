package theknife;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import entita.Ristorante;
import java.io.FileNotFoundException;

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
        Ristorante r = new Ristorante();
        try {
            r.aggiuntaDeiCampi();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Benvenuto su The Knife");
    }

}
