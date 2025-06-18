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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikoro02
 */
public class FileRecensioni extends GestioneFile <Integer,Recensione> {
    private static String percorsofile = "..\\theKnife\\data\\recensioni_ristoranti.csv";
 
    @Override
    public HashMap<Integer, Recensione> ottieniHashMap() { 
        List<List<String>> recensioniList = new ArrayList<>();
        HashMap<Integer, Recensione> recension = new HashMap<Integer, Recensione>();
        try {
            recensioniList=FileRecensioni.letturaCsv(FileRecensioni.getPercorsoFile());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileRecensioni.class.getName()).log(Level.SEVERE, null, ex);
        }
         for(List<String>campo : recensioniList){
             int id = Integer.parseInt(campo.get(0));
             String utente = campo.get(1);
             short stelle = Short.parseShort(campo.get(2));
             LocalDate data = LocalDate.parse(campo.get(3));
             String testo = campo.get(4);
             String ristorante = campo.get(4);
             Recensione recensione1= new Recensione(id, utente, stelle, testo, data, ristorante);
              recension.put(recensione1.getID(), recensione1);
         }
         return recension;
           }


    
    @Override
    public void scritturaSuFile(Recensione recensione){
        List<String>RecensioniLista= new ArrayList<>();
        RecensioniLista.add(String.valueOf(recensione.getID()));
        RecensioniLista.add(recensione.getUtente());
        RecensioniLista.add(String.valueOf(recensione.getstelle()));
        RecensioniLista.add(recensione.gettesto());
        RecensioniLista.add(String.valueOf(recensione.getdata()));
        GestioneFile.scritturaSuFile(getPercorsoFile(), RecensioniLista);
        Recensione.setRecensioniHashMap(ottieniHashMap());
        
      
    }
    public static String getPercorsoFile(){
        return percorsofile;
    }
}
