/**Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
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
public abstract class GestioneFile<K, V> /*K: chiave, V: valore della chiave*/{

    protected static void scrittura(String percorsoFile, List<String> oggetto) 
 /*Altri metodi e classi che estendono la classe in cui 
questo metodo è definito, o che appartengono allo stesso 
pacchetto, possono utilizzare questa funzione.
List<String> oggetto: lista (List) di stringhe (String) 
che contiene i dati che devono essere scritti nel file. 
la lista viene convertita in formato CSV e poi scritta nel file*/
    {
        File file = new File(percorsoFile); //crea un oggetto file che rappresenta il file che si vuole modificare
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(percorsoFile, true))) {
//bufferwriter: permette di raccogliere i dati in memoria
//prima di scriverli nel file.
//FileWriter(percorsoFile, true): Crea un FileWriter per 
//scrivere nel file specificato dal percorso percorsoFile. 
//L'argomento true indica che il file verrà aperto in modalità append, 
//cioè verranno aggiunti nuovi dati alla fine del file 
//senza sovrascrivere quelli già esistenti.
            List<String> campiCorretti = new ArrayList<>();
            /* si crea una lista vuota che contterrà
            le versione corrette dei campi della lista oggetto
            in formato CSV.*/
            for (String campo : oggetto) {
                campiCorretti.add(escapeCSV(campo));
            }
            /*Cicla su ogni elemento (campo) della lista 
            oggetto e lo passa al metodo escapeCSV(campo).
            escapeCSV(campo) è una funzione che:
            aggiunge virgolette attorno a un campo se 
            contiene virgole, virgolette o a capo,
            raddoppia eventuali virgolette interne.
            Ogni campo corretto viene poi aggiunto alla 
            nuova lista campiCorretti.*/
            
            String linea = String.join(",", campiCorretti);
            /*Unisce tutti gli elementi della lista 
            campiCorretti in un'unica stringa separata 
            da virgole, creando così una riga in formato 
           CSV.*/
            writer.write(linea);
            writer.newLine();
            /*crive la stringa linea nel file 
            (cioè una riga completa CSV), 
            e poi va a capo (newLine()) 
            per preparare il file alla scrittura 
            della prossima riga.*/
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }/*Se qualcosa va storto (es. il file non si 
        trova, o non può essere scritto), 
        lancia un’eccezione IOException, 
        che viene catturata e stampata a video 
        con il suo messaggio di errore.*/
    }

    private static String escapeCSV(String campo) {
        if (campo.contains(",") || campo.contains("\"") || campo.contains("\n")) {
            campo = campo.replace("\"", "\"\"");
            return "\"" + campo + "\"";
        }
        return campo;
    }

    public static List<String> letturaIntestazione(String percorsoFile) throws FileNotFoundException {
        /*legge la prima riga di un file CSV 
        (che di solito è l'intestazione, 
        cioè i nomi delle colonne) e la 
        restituisce come lista con una sola stringa.
*/
        List<String> intestazione = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(percorsoFile))) {
            /*apre un file scanner per leggere riga per riga il file.
            try chiude automaticamente lo scanner alla fine.
            */
            
            intestazione.add(scanner.nextLine());
            //Legge la prima riga del file e la aggiunge alla lista intestazione.
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
                List<String> campiCorretti = new ArrayList<>();
                if (oggetti.indexOf(oggetto) != oggetti.indexOf(intestazione)) {
                    for (String campo : oggetto) {
                        campiCorretti.add(escapeCSV(campo));
                    }
                } else {
                    for (String campo : oggetto) {
                        campiCorretti.add(campo);
                    }
                }
                String linea = String.join(",", campiCorretti);
                writer.write(linea);
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

//gestisce le operazioni di lettura e scrittura su file CSV.