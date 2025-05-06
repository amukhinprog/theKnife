package entita;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author armuh
 */
public class Utente {
     private String nome;
   private String cognome;
   private String username;
   private String password;
   private LocalDate dataNascita;
   private String luogodomicilio;
 
   public Utente(String nome, String cognome, String username, String password, LocalDate Data,String luogodomicilio){
     this.nome=nome;
     this.cognome=cognome;
     this.username=username;
     this.password=password;
     this.dataNascita=Data;
     this.luogodomicilio=luogodomicilio;
 }  
   
}
