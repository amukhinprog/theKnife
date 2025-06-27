package entita.dominio;

import entita.dominio.Utente;
import java.time.LocalDate;
import java.util.*;
import eccezioni.LocaleGiaPresenteException;
import gestioneFile.FileGestoreRistorante;
import gestioneFile.FileRistorante;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
