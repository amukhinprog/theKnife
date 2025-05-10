package entita;

import gestioneFile.FileUtenti;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
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
public class Utente {

    private static FileUtenti fileUtenti = new FileUtenti();
    private static HashMap<String, Utente> utenti = fileUtenti.ottieniHashMap();
    protected String nome;
    protected String cognome;
    protected String username;
    protected String password;
    protected LocalDate dataNascita;
    protected String luogoDomicilio;
    protected String ruolo;

    public Utente(String nome, String cognome, String username, String password, LocalDate Data, String luogoDomicilio, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.dataNascita = Data;
        this.luogoDomicilio = luogoDomicilio;
        this.ruolo = ruolo;
    }

    private Utente() {
    }

    public static Utente registrazione() {
        Scanner scanner = new Scanner(System.in);
        String dataProvvisoria;

        System.out.println("Nome: ");
        String nome = scanner.next();

        System.out.println("Cognome: ");
        String cognome = scanner.next();

        String username;
        do {
            System.out.println("Username (inesistente): ");
            username = scanner.next();
        } while (utenti.containsKey(username));

        System.out.println("Password: ");
        String password = scanner.next();

        System.out.println("Data di nascita (YYYY-MM-DD): ");
        dataProvvisoria = scanner.next();
        LocalDate dataNascita = LocalDate.parse(dataProvvisoria);

        System.out.println("Domicilio: ");
        String luogoDomicilio = scanner.next();

        System.out.println("Ruolo: ");//impreciso
        String ruolo = scanner.next();

        /*Scrivere il nuovo utente in file csv usando gli opportuni metodi*/
        Utente utente = new Utente();
        if (ruolo.compareTo("gestore") == 0) {
            utente = new Gestore(nome, cognome, username, password, dataNascita, luogoDomicilio, ruolo);
        } else if (ruolo.compareTo("cliente") == 0) {
            utente = new Cliente(nome, cognome, username, password, dataNascita, luogoDomicilio, ruolo);
        }
        fileUtenti.scritturaSuFile(utente);
        return utente;
    }

    public static Utente login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Username: ");
        String username = scanner.next();

        System.out.println("Password: ");
        String password = scanner.next();

        if ((utenti.get(username) != null & (utenti.get(username).getPassword().compareTo(password)) == 0)) {
            return utenti.get(username);
        } else {
            return null;
        }
    }

    public static HashMap<String, Utente> getUtenti() {
        return utenti;
    }

    public static void setUtenti(HashMap<String, Utente> utenti) {
        Utente.utenti = utenti;
    }

    
    public String getLuogoDomicilio() {
        return luogoDomicilio;
    }

    public void setLuogoDomicilio(String luogoDomicilio) {
        this.luogoDomicilio = luogoDomicilio;
    }

    public String getNome() {
        return nome;
    }

    protected void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    protected void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    protected void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getLuogodomicilio() {
        return luogoDomicilio;
    }

    protected void setLuogodomicilio(String luogoDomicilio) {
        this.luogoDomicilio = luogoDomicilio;
    }

    public String getRuolo() {
        return ruolo;
    }

    protected void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

}
