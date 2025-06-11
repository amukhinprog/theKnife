/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioneFile;

import java.io.FileInputStream;
import java.io.IOException;
import entita.Recensione;
import entita.Ristorante;
import static gestioneFile.FileRistorante.getPercorsoFile;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikoro02
 */
public abstract class FileRecensioni extends GestioneFile <String,Recensione> {
    private static String percorsofile = "..\\theKnife\\data\\recensioni_ristoranti.csv";
 
    private static final Logger logger=Logger.getLogger(FileRecensioni.class.getName());
    @Override
    public HashMap ottieniHashMap() { // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        List<List<String>> recensioniList = new ArrayList<>();
        HashMap<String, Recensione> recension = new HashMap<String, Recensione>();
         try (FileInputStream fis = new FileInputStream(percorsofile)){
             recensioniList=FileRecensioni.letturaCsv(FileRecensioni.getPercorsoFile());
             }catch(FileNotFoundException e){
                   logger.log(Level.WARNING, "Errore durante la lettura del file: " + percorsofile, e);
             }catch(IOException ex){
                 logger.log(Level.SEVERE, "Errore durante la chiusura del file: " + percorsofile, ex);
             }
         for(List<String>campo : recensioniList){
             Recensione recensione1= new Recensione((recensioniList.get(0), recensioniList.get(1),
                    Integer.parseInt( recensioniList.get(2),recensioniList.get(3)));
              recension.put(String.valueOf(recensione1.getID()), recensione1);
         }
         return recension;
           }


    
    public void scritturaSuFileRecensioni(Recensione recensione){
        List<String>RecensioniLista= new ArrayList<>();
        RecensioniLista.add(String.valueOf(recensione.getID()));
        RecensioniLista.add(String.valueOf(recensione.getstelle()));
        RecensioniLista.add(recensione.gettesto());
        RecensioniLista.add(String.valueOf(recensione.getdata()));
        GestioneFile.scritturaSuFile(getPercorsoFile(), RecensioniLista);
        Recensione.setRecensione(new FileRecensioni().ottieniHashMap());
        
      
    }
    public static String getPercorsoFile(){
        return percorsofile;
    }
}
