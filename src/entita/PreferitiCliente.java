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
        }
    }
}