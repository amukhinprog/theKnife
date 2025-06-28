package entita.dominio;

import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author armuh
 */
public class Gestore extends Utente {

    public Gestore(String nome, String cognome, String username, String password, LocalDate data, String luogodomicilio, String ruolo) {
        super(nome, cognome, username, password, data, luogodomicilio, ruolo);
    }

}
