/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioneFile;

import entita.associazioni.RispostaRecensioni;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author armuh
 */
public class FileRispostaRecensioni extends GestioneFile<Integer, RispostaRecensioni> {

    private static String percorsoFile = "..\\theKnife\\data\\risposta_recensioni.csv";

    @Override
    public HashMap<Integer, RispostaRecensioni> ottieniHashMap() {
        List<List<String>> risposteList = new ArrayList<>();
        HashMap<Integer, RispostaRecensioni> risposteMap = new HashMap<Integer, RispostaRecensioni>();
        try {
            risposteList = FileRecensioni.letturaCsv(percorsoFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileRecensioni.class.getName()).log(Level.SEVERE, null, ex);
        }
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            for (List<String> campo : risposteList) {
                int ID = Integer.parseInt(campo.get(0));
                int idRif = Integer.parseInt(campo.get(1));
                String username = campo.get(2);
                String testo = campo.get(3);
                LocalDate data = LocalDate.parse(campo.get(4));
                RispostaRecensioni rispostaRecensioni = new RispostaRecensioni(ID, idRif, username, testo, data);
                risposteMap.put(ID, rispostaRecensioni);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return risposteMap;
    }

    @Override
    public void scrittura(RispostaRecensioni oggetto) {
        List<String> rispostaRecensioniLista = new ArrayList<>();
        rispostaRecensioniLista.add(String.valueOf(oggetto.getID()));
        rispostaRecensioniLista.add(String.valueOf(oggetto.getIdRif()));
        rispostaRecensioniLista.add(oggetto.getUsername());
        rispostaRecensioniLista.add(oggetto.getTesto());
        rispostaRecensioniLista.add(String.valueOf(oggetto.getData()));
        GestioneFile.scrittura(percorsoFile, rispostaRecensioniLista);
    }

    @Override
    public void sovraScrivi(HashMap<Integer, RispostaRecensioni> map) {
        LinkedList<List<String>> rispostaRecensioniLista = new LinkedList<>();
        for (RispostaRecensioni risposta : map.values()) {
            List<String> rispostaList = new ArrayList<>();
            rispostaList.add(String.valueOf(risposta.getID()));
            rispostaList.add(String.valueOf(risposta.getIdRif()));
            rispostaList.add(risposta.getUsername());
            rispostaList.add(risposta.getTesto());
            rispostaList.add(String.valueOf(risposta.getData()));
            rispostaRecensioniLista.add(rispostaList);
        }
        GestioneFile.sovraScrivi(percorsoFile, rispostaRecensioniLista);
    }

}
