package gestioneFile;

import static gestioneFile.FileRistorante.leggiRiga;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;

/**
 *
 * @author armuh
 */
public abstract class GestioneFile<K, V> {

    protected static void scrittura(String percorsoFile, List<String> oggetto) {
        File file = new File(percorsoFile);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(percorsoFile, true))) {

            List<String> campiCorretti = new ArrayList<>();
            for (String campo : oggetto) {
                campiCorretti.add(escapeCSV(campo));
            }
            String linea = String.join(",", campiCorretti);
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String escapeCSV(String campo) {
        if (campo.contains(",") || campo.contains("\"") || campo.contains("\n")) {
            campo = campo.replace("\"", "\"\"");
            return "\"" + campo + "\"";
        }
        return campo;
    }

    public static List<String> letturaIntestazione(String percorsoFile) throws FileNotFoundException {
        List<String> intestazione = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(percorsoFile))) {
            intestazione.add(scanner.nextLine());
        }
        return intestazione;
    }

    public static List<List<String>> letturaCsv(String percorsoFile) throws FileNotFoundException {
        List<List<String>> frasi = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(percorsoFile))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNext()) {
                frasi.add(leggiRiga(scanner.nextLine()));
//                System.out.println(frasi.getLast());
            }
        }
//        frasi = aggiungiEliminaCampi(frasi);
        return frasi;
    }

    protected static List<String> leggiRiga(String riga) {
        List<String> rigaSpezzata = new ArrayList<>();
        String campo;
        String parole;
        try (Scanner leggiRiga = new Scanner(riga)) {
            leggiRiga.useDelimiter(",");
            while (leggiRiga.hasNext()) {
                campo = leggiRiga.next();
                if (campo.startsWith("\"") && !campo.endsWith("\"")) {
                    do {
                        parole = leggiRiga.next();
                        campo = campo + "," + parole;
                    } while (!parole.endsWith("\""));
                }
                rigaSpezzata.add(campo);
            }
        }
        return rigaSpezzata;
    }

    public static void sovraScrivi(String percorsoFile, LinkedList<List<String>> oggetti) {
        File file = new File(percorsoFile);
        List<String> intestazione = null;
        try {
            intestazione = letturaIntestazione(percorsoFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestioneFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        oggetti.addFirst(intestazione);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(percorsoFile))) {
            for (List<String> oggetto : oggetti) {
                String riga = String.join(",", oggetto);
                writer.write(riga);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    abstract public HashMap<K, V> ottieniHashMap();

    abstract public void scrittura(V oggetto);

    abstract public void sovraScrivi(HashMap<K, V> map);
}
