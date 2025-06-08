/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioneFile;

import entita.Recensione;
import entita.Ristorante;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Utente
 */
public abstract class FileRecensioni extends GestioneFile {
    private static String percorsofile = "..\\theKnife\\data\\recensioni.csv";

    @Override
    public HashMap ottieniHashMap() { // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public void scritturaSuFile(Object oggetto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
