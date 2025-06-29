package gestioneFile;

import entita.dominio.Ristorante;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author armuh
 */
public class FileRistorante extends GestioneFile<String, Ristorante> {

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

    private static void sostituisciValoriBooleani() {
        List<List<String>> frasi = new ArrayList<>();
        try {
            frasi = FileRistorante.letturaCsv(percorsoFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileRistorante.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (List<String> frase : frasi) {
            if (Integer.parseInt(frase.get(8)) == 0) {
                frase.set(8, "false");
            } else if (Integer.parseInt(frase.get(8)) == 1) {
                frase.set(8, "true");
            }
            if (Integer.parseInt(frase.get(11)) == 0) {
                frase.set(11, "false");
            } else if (Integer.parseInt(frase.get(11)) == 1) {
                frase.set(11, "true");
            }
        }
        File file = new File(percorsoFile);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(percorsoFile))) {
            for (List<String> riga : frasi) {
                String linea = String.join(",", riga);
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getPercorsoFile() {
        return percorsoFile;
    }

    @Override
    public HashMap<String, Ristorante> ottieniHashMap() {
        List<List<String>> ristorantiList = new ArrayList<>();
        try {
            ristorantiList = FileRistorante.letturaCsv(FileRistorante.getPercorsoFile());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileRistorante.class.getName()).log(Level.SEVERE, null, ex);
        }
        HashMap<String, Ristorante> ristoranti = new HashMap<String, Ristorante>();
        for (List<String> ristoranteList : ristorantiList) {
            Ristorante ristorante = new Ristorante(ristoranteList.get(0), ristoranteList.get(1),
                    ristoranteList.get(2), Float.parseFloat(ristoranteList.get(3)),
                    ristoranteList.get(4), Float.parseFloat(ristoranteList.get(5)),
                    Float.parseFloat(ristoranteList.get(6)), ristoranteList.get(7),
                    Boolean.parseBoolean(ristoranteList.get(8))/*rivedere*/, ristoranteList.get(9),
                    ristoranteList.get(10), Boolean.parseBoolean(ristoranteList.get(11))/*rivedere*/,
                    ristoranteList.get(12)/*, Short.parseShort(ristoranteList.get(13))*/);
            ristoranti.put(ristorante.getNome(), ristorante);
        }
        return ristoranti;
    }

    @Override
    public void scrittura(Ristorante ristorante) {
        List<String> ristoranteList = new ArrayList<>();
        ristoranteList.add(ristorante.getNome());
        ristoranteList.add(ristorante.getIndirizzo());
        ristoranteList.add(ristorante.getLocazione());
        ristoranteList.add(String.valueOf(ristorante.getPrezzo()));
        ristoranteList.add(ristorante.getCucina());
        ristoranteList.add(String.valueOf(ristorante.getLongitudine()));
        ristoranteList.add(String.valueOf(ristorante.getLatitudine()));
        ristoranteList.add(ristorante.getNumeroTelefono());
        ristoranteList.add(Boolean.toString(ristorante.isDelivery()));
        ristoranteList.add(ristorante.getUrl());
        ristoranteList.add(ristorante.getWebSiteUrl());
        ristoranteList.add(Boolean.toString(ristorante.isPrenotazione()));
        ristoranteList.add(ristorante.getDescrizione());
        //ristoranteList.add(String.valueOf(ristorante.getStelle()));

        GestioneFile.scrittura(getPercorsoFile(), ristoranteList);
    }

    @Override
    public void sovraScrivi(HashMap<String, Ristorante> map) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
