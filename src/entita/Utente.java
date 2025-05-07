package entita;

import gestioneFile.FileUtenti;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public Utente() {
    }

    public void registrazione() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nome: ");
        setNome(scanner.next());

        System.out.println("Cognome: ");
        this.cognome = scanner.next();

        System.out.println("Username: ");
        this.username = scanner.next();

        System.out.println("Password: ");
        this.password = scanner.next();

        System.out.println("Data di nascita: ");
        //sistemare input per data
//        this.dataNascita = new LocalDate(1990, 12, 30);
        
        System.out.println("Domicilio: ");
        this.luogoDomicilio = scanner.next();

        System.out.println("Ruolo: ");//impreciso
        this.ruolo = scanner.next();
        
        /*Scrivere il nuovo utente in file csv usando gli opportuni metodi*/
//        Utente utente;
//        if(this.ruolo.compareTo("gestore") == 0){
//            utente = Gestore(this.nome, this.cognome, this.username, this.password, this.dataNascita, this.luogoDomicilio, this.ruolo);
//        }else if(this.ruolo.compareTo("cliente") == 0){
//            utente = Cliente(this.nome, this.cognome, this.username, this.password, this.dataNascita, this.luogoDomicilio, this.ruolo);
//        }
    }

    public static Utente login() {
        Scanner scanner = new Scanner(System.in);
        String username;
        String password;

        System.out.println("Username: ");
        username = scanner.next();

        System.out.println("Password: ");
        password = scanner.next();

        int posizioneUsernameInCsv = 2;
        int posizionePasswordInCsv = 3; //da non fare
        int posizioneRuolo = 6;
        List<List<String>> utenti = new ArrayList<>();
        try {
            utenti = FileUtenti.letturaCsv(FileUtenti.getPercorsoFile());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utente.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean loggato = false;
        int posizioneUtente = -1;
        for (int i = 1; i < utenti.size() || !loggato; i++) {
            if (utenti.get(i).get(posizioneUsernameInCsv).compareTo(username) == 0
                    && utenti.get(i).get(posizionePasswordInCsv).compareTo(password) == 0) {
                System.out.println("Utente loggato");
                loggato = true;
                posizioneUtente = i;
            }
        }
        Utente utente = null;
        if (loggato) {
            List<String> utenteStringa = utenti.get(posizioneUtente);
            if (utenti.get(posizioneUtente).get(posizioneRuolo).compareTo("gestore") == 0) {
                utente = new Gestore(utenteStringa.get(0), utenteStringa.get(1), username, password, LocalDate.parse(utenteStringa.get(4)), utenteStringa.get(5), utenteStringa.get(6));
            } else if (utenti.get(posizioneUtente).get(posizioneRuolo).compareTo("cliente") == 0) {
                utente = new Cliente(utenteStringa.get(0), utenteStringa.get(1), username, password, LocalDate.parse(utenteStringa.get(4)), utenteStringa.get(5), utenteStringa.get(6));
            }
        } else {
            System.out.println("Nome Utente o password errati");

        }
        return utente;
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

    private String getLuogodomicilio() {
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
