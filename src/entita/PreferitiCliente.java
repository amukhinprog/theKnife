/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entita;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import gestioneFile.FilePreferitiCliente;

/**
 *
 * @author filod
 */
public class PreferitiCliente {
    
    private String usernameCliente;
    private List<Ristorante> ristorantiPreferiti = new ArrayList<>();
    
    private static HashMap<String, AssPreferitiCliente> preferitiMap = new FilePreferitiCliente().ottieniHashMap();
    
    public PreferitiCliente(String usernameCliente, List<Ristorante> ristorantiPreferiti){
        
        this.usernameCliente = usernameCliente;
        this.ristorantiPreferiti = ristorantiPreferiti;
    }
    
    public static void aggiungiPreferito(String username, Ristorante ristorante){
        FilePreferitiCliente file = new FilePreferitiCliente();
        
        if(preferitiMap.containsKey(username)){
            AssPreferitiCliente preferenze = preferitiMap.getClass(username);
            
            if (!preferenze.ristorantiPreferiti.contains(ristorante)){
                preferenze.addRistorantePreferito(ristorante);
                file.scritturaSuFile(preferenze);
            }
        }else {
            List<Ristorante> nuoviPreferiti = new ArrayList<>();
            nuoviPreferiti.add(ristorante);
            
            AssPreferitiCliente nuovaAss = new AssPreferitiCliente(username, nuoviPreferiti);
            preferitiMap.put(username, nuovaAss);
            
            file.scritturaSuFile(nuovaAss);
        }
    }
    
    public static void rimuoviPreferito(String username, Ristorante ristorante){
        FilePreferitiCliente file = new FilePreferitiCliente();
        
        if(preferenze.ristorantiPreferiti.contains(ristorante)){
            preferenze.ristorantiPreferiti.remove(ristorante);
            file.scritturaSuFile(preferenze);
        }
    }
    
    public static List<Ristorante> visualizzaPreferiti(String username){
        if(preferitiMap.containsKey(username)){
            return preferitiMap.get(username).getRistorantiPreferiti();
        }else {
            return new ArrayList<>();
        }
    }
    
    public List<Ristorante> getRistorantiPreferiti() {
        return ristorantiPreferiti;
    }
        
    public void setRistorantiPreferiti(List<Ristorante> ristorantiPreferiti) {
        this.ristorantiPreferiti = ristorantiPreferiti;
    }
    
    public void addRistorantePreferito(Ristorante ristorante) {
        this.ristorantiPreferiti.add(ristorante);
    }
    
    public String getUsernameCliente() {
        return usernameCliente;
    }

    public void setUsernameCliente(String usernameCliente) {
        this.usernameCliente = usernameCliente;
    }
    
    public static HashMap<String, AssPreferitiCliente> getPreferitiMap() {
        return preferitiMap;
    }

    public static void setPreferitiMap(HashMap<String, AssPreferitiCliente> preferitiMap) {
        AssPreferitiCliente.preferitiMap = preferitiMap;
    }
}