/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioneFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class fileGestoreRistorante extends GestioneFile {

    protected static String percorsoFile = "username_ristoranti.csv";

    public static void associaGestore(String username, String nomeRistorante) {
        List<List<String>> gestori = new ArrayList<>();
        try {
            gestori = FileUtenti.letturaCsv(FileUtenti.getPercorsoFile());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(fileGestoreRistorante.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 1; i < gestori.size(); i++) {
            if (gestori.get(i).get(2).equals(username)) {
                String campo=gestori.get(i).get(1).concat("$ "+nomeRistorante);
                 gestori.get(i).set(1, campo);
                sovraScriFile(getPercorsoFile(), gestori);
            } else {
                //aggiungere una riga username, e suo ristorante
                List<String> gestore = new ArrayList<>();
                gestore.add(username);
                gestore.add(nomeRistorante);
                fileGestoreRistorante.scritturaSuFile(getPercorsoFile(), gestore);

            }
        }
    }

    public static String getPercorsoFile() {
        return percorsoFile;
    }

    public static void sovraScriFile(String percorsoFile, List<List<String>> oggetti) {
        File file = new File(percorsoFile);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(percorsoFile))) {
            for (List<String> oggetto : oggetti) {
                String linea = String.join(",", oggetto);
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
