/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entita;
/**
 *
 * @author Utente
 */
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
public class Recensione {
    private int ID;
    private short stelle;
    private String testo;
    private LocalDate data;
    List<String>recensioni= new ArrayList<>();
//    private static HashMap<String,Recensione>recensioni= new FileRecensioni().ottieniHashMap(); 
    Ristorante ristoranteRecensito;

    public Recensione(int ID, short stelle, String testo, LocalDate data, Ristorante ristoranteRecensito) {
        this.ID = ID;
        this.stelle = stelle;
        this.testo = testo;
        this.data = data;
        this.ristoranteRecensito=ristoranteRecensito;
    }
    public void aggiungiRecensione(String testo,Utente utente){
      
        
        
    }
 
}
