/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import entita.Cliente;
import entita.Gestore;
import entita.Ruolo;
import entita.Utente;
import gestioneFile.FileUtenti;
import java.time.LocalDate;
import java.util.Scanner;
import repository.UtenteService;

/**
 *
 * @author armuh
 */
public class UtenteUI {

    private static FileUtenti fileUtenti = new FileUtenti();
    private Scanner scanner;
    private UtenteService utenteServ;

    public UtenteUI(Scanner scanner, UtenteService utenteServ) {
        this.scanner = scanner;
        this.utenteServ = utenteServ;
    }

    public Utente registrazione() {
        String dataProvvisoria;

        System.out.println("Nome: ");
        String nome = scanner.next();

        System.out.println("Cognome: ");
        String cognome = scanner.next();

        String username;
        do {
            System.out.println("Username (inesistente): ");
            username = scanner.next();
        } while (utenteServ.containsKey(username));

        System.out.println("Password: ");
        String password = scanner.next();

        System.out.println("Data di nascita (YYYY-MM-DD): ");
        dataProvvisoria = scanner.next();
        LocalDate dataNascita = LocalDate.parse(dataProvvisoria);

        System.out.println("Domicilio: ");
        String luogoDomicilio = scanner.next();

        System.out.println("Ruolo: ");//impreciso
        String ruolo = inserisciRuolo();

        /*Scrivere il nuovo utente in file csv usando gli opportuni metodi*/
        Utente utente = new Utente();
        if (ruolo.compareTo("gestore") == 0) {
            utente = new Gestore(nome, cognome, username, password, dataNascita, luogoDomicilio, ruolo);
        } else if (ruolo.compareTo("cliente") == 0) {
            utente = new Cliente(nome, cognome, username, password, dataNascita, luogoDomicilio, ruolo);
        }
        fileUtenti.scrittura(utente);
        return utente;
    }

    public Utente login() {

        System.out.println("Username: ");
        String username = scanner.next();

        System.out.println("Password: ");
        String password = scanner.next();

        if ((utenteServ.get(username) != null & (utenteServ.get(username).getPassword().compareTo(password)) == 0)) {
            return utenteServ.get(username);
        } else {
            return null;
        }
    }

    public String inserisciRuolo() {
        Ruolo ruolo = null;

        while (ruolo == null) {
            System.out.println("Inserisci il tuo ruolo (gestore o cliente): ");
            String input = scanner.next().toUpperCase();

            try {
                ruolo = Ruolo.valueOf(input);
                System.out.println("Ruolo selezionato: " + ruolo);
            } catch (IllegalArgumentException e) {
                System.out.println("Ruolo non valido.");
            }
        }

        return ruolo.toString().toLowerCase();
    }
}
