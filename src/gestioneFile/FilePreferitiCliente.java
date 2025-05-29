/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioneFile;

import entita.PreferitiCliente;
import entita.Ristorante;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 *
 * @author filod
 */
public class FilePreferitiCliente extends GestioneFile<String, PreferitiCliente> {
    
    private static String percorsoFile = "..\\theKnife\\data\\preferiti.csv";
    
    public static String getPercorsoFile(){
        return percorsoFile;
    }
    
@Override
    public HashMap<String, PreferitiCliente> ottieniHashMap() {
        HashMap<String, PreferitiCliente> preferitiMap = new HashMap<>();
        HashMap<String, Ristorante> ristorantiMap = new FileRistorante().ottieniHashMap();

        List<List<String>> righe = new ArrayList<>();
        try {
            righe = FilePreferitiCliente.letturaCsv(getPercorsoFile());
        } catch (FileNotFoundException e) {
            System.out.println("File preferiti non trovato: " + e.getMessage());
        }

        for (List<String> riga : righe) {
            if (riga.size() < 2) continue;

            String usernameCliente = riga.get(0);
            String[] nomiRistoranti = riga.get(1).split("\\$");

            List<Ristorante> preferiti = new ArrayList<>();
            for (String nome : nomiRistoranti) {
                Ristorante r = ristorantiMap.get(nome);
                if (r != null) preferiti.add(r);
            }

            PreferitiCliente pc = new PreferitiCliente(usernameCliente, preferiti);
            preferitiMap.put(usernameCliente, pc);
        }

        return preferitiMap;
    }

    @Override
    public void scritturaSuFile(PreferitiCliente oggetto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
