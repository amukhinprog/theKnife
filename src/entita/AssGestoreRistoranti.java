/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entita;

import gestioneFile.FileGestoreRistorante;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author armuh
 */
public class AssGestoreRistoranti {

    private String usernameRistoratore;
    private List<Ristorante> ristorantiList = new ArrayList<>();
    
    private static HashMap<String, AssGestoreRistoranti> ristorantiMap = new FileGestoreRistorante().ottieniHashMap();
    private static List<Ristorante> ristorantiInPossesso = new FileGestoreRistorante().ottieniListaRistorantiPossedutiUtenti();
    
    public AssGestoreRistoranti(String usernameRistoratore, List<Ristorante> ristorantiList) {
        this.usernameRistoratore = usernameRistoratore;
        this.ristorantiList = ristorantiList;
    }

    public static void assRistoranteAGestore(String username, Ristorante ristorante) {
        FileGestoreRistorante fileAss = new FileGestoreRistorante();
        if (ristorantiMap.containsKey(username)) {
            AssGestoreRistoranti g = ristorantiMap.get(username);
            g.addRistorantiList(ristorante);
            ristorantiMap.replace(username, g);
            
            
            fileAss.scritturaSuFile(g);
        } else {
            List<Ristorante> l = new ArrayList<>();
            l.add(ristorante);
            AssGestoreRistoranti assGestore = new AssGestoreRistoranti(username, l);
            ristorantiMap.put(username, assGestore);
            
            fileAss.sovraScriFile(ristorantiMap);
        }
    }

    public static boolean ristoranteInPossesso(Ristorante r){
        return ristorantiInPossesso.contains(r);
    }
    public List<Ristorante> getRistorantiList() {
        return ristorantiList;
    }

    public void setRistorantiList(List<Ristorante> ristorantiList) {
        this.ristorantiList = ristorantiList;
    }
    
    public void addRistorantiList(Ristorante ristorante){
        this.ristorantiList.add(ristorante);
    }

    public String getUsernameRistoratore() {
        return usernameRistoratore;
    }

    public void setUsernameRistoratore(String usernameRistoratore) {
        this.usernameRistoratore = usernameRistoratore;
    }

    public static HashMap<String, AssGestoreRistoranti> getRistorantiMap() {
        return ristorantiMap;
    }

    public static void setRistorantiMap(HashMap<String, AssGestoreRistoranti> ristorantiMap) {
        AssGestoreRistoranti.ristorantiMap = ristorantiMap;
    }
}
