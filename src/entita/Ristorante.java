package entita;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.*;
import java.io.*;
//import java.util.ArrayList;

/**
 *
 * @author armuh
 */
public class Ristorante {

    public Ristorante() {
    }

    public void aggiuntaDeiCampi() throws FileNotFoundException {

        int posizioneCampoPrezzo = 3;
        int posizioneCampoDelivery = 14;
        int posizioneCampoPrenotazione = 15;
        List<List<String>> frasi = new ArrayList<>();
        frasi = letturaCsv();
        frasi.get(0).add("Delivery");
        frasi.get(0).add("PrenotazioneOnline");
        for (int i = 1; i < 2; i++) {
            System.out.println(frasi.get(i).get(2));
            int r = (int) (Math.random() * 2);
            frasi.get(i).addLast(String.valueOf(r));
            r = (int) (Math.random() * 2);
            frasi.get(i).addLast(String.valueOf(r));
            System.out.println(frasi.get(i));
        }

        System.out.println(frasi.get(0));
    }

    public List<List<String>> letturaCsv() throws FileNotFoundException {
        List<List<String>> frasi = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("..\\theKnife\\data\\michelin_my_maps.csv"))) {
            while (scanner.hasNext()) {
                frasi.add(leggiRiga(scanner.nextLine()));
                //System.out.println(frasi.getLast());

            }
        }
        return frasi;
    }

    private List<String> leggiRiga(String riga) {
        List<String> rigaSpezzata = new ArrayList<>();
        try (Scanner leggiRiga = new Scanner(riga)) {
            leggiRiga.useDelimiter(",");
            while (leggiRiga.hasNext()) {
                if (leggiRiga.next().startsWith("\"") && leggiRiga.next().contains(",")) {
                    while(leggiRiga.has)
                    rigaSpezzata.add()
                } else {
                    rigaSpezzata.add(leggiRiga.next());
                }
            }
        }
        return rigaSpezzata;
    }
}
