package entita;
import java.time.LocalDate;
import java.util.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author armuh
 */
public class Gestore extends Utente{
   
   List<Ristorante> ristoranti = new ArrayList<>();

    public Gestore(String nome, String cognome, String username, String password, LocalDate Data, String luogodomicilio, String ruolo) {
        super(nome, cognome, username, password, Data, luogodomicilio, ruolo);
    }

   
    
    void aggiungiRistorante(Ristorante r) {
        ristoranti.add(r);
    }
}
 
