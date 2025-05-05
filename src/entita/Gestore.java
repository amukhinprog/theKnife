package entita;
import java.time.LocalDate;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author armuh
 */
public class Gestore extends Utente{
   private String nome;
   private String cognome;
   private String username;
   private String password;
   private LocalDate dataDiNascita;
   private String luogodomicilio;
   List<Ristorante> ristoranti = new ArrayList<>();

   
   
 public Gestore (String nome, String cognome, String username, String password, LocalDate data,String luogodomicilio){
     this.nome=nome;
     this.cognome=cognome;
     this.username=username;
     this.password=password;
     this.data=dataDiNascita;
     this.luogodimicilio=luogodomicilio;
 }  
    void aggiungiRistorante(Ristorante r) {
        ristoranti.add(r);
    }
}
 
}
