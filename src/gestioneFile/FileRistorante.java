/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioneFile;

import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author armuh
 */
public class FileRistorante extends GestioneFile {

    private static String percorsoFile = "..\\theKnife\\data\\michelin_my_maps2.csv";

    private static List<List<String>> aggiungiEliminaCampi(List<List<String>> frasi) throws FileNotFoundException {

        int posizioneCampoPrezzo = 3;
        int posizioneCampoDelivery = 8;
        int posizioneCampoPrenotazione = 11;
        frasi.get(0).remove(10);
        frasi.get(0).remove(10);
        frasi.get(0).remove(10);
        frasi.get(0).add(8, "Delivery");
        frasi.get(0).add(11, "PrenotazioneOnline");
        for (int i = 1; i < frasi.size(); i++) {
//            System.out.println(frasi.get(i).get(posizioneCampoPrezzo));
            frasi.get(i).remove(10);
            frasi.get(i).remove(10);
            frasi.get(i).remove(10);
            int r = (int) (Math.random() * 2);
            frasi.get(i).add(posizioneCampoDelivery, String.valueOf(r));
            r = (int) (Math.random() * 2);
            frasi.get(i).add(posizioneCampoPrenotazione, String.valueOf(r));
            float prezzoMedio = (float) (Math.random() * 15 + Math.random() * 10 + 13);
            float offset = (float) ((Math.random() > 0.5) ? 5 : -5);
            prezzoMedio = Math.round(prezzoMedio * 10 + offset) / 10.0f;
            frasi.get(i).set(3, String.valueOf(prezzoMedio));
//            System.out.println(frasi.get(i));
        }

        System.out.println(frasi.get(0));
        return frasi;
    }

    public static String getPercorsoFile() {
        return percorsoFile;
    }

    private void setPercorsoFile(String percorsoFile) {
        this.percorsoFile = percorsoFile;
    }

}
