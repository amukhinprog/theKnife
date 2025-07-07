/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package menu;

import entita.dominio.Cliente;
import entita.dominio.Gestore;
import entita.Ruolo;
import entita.dominio.Utente;
import gestioneFile.FileUtenti;
import java.io.Console;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;
import repository.UtenteService;

/**
 *
 * @author armuh
 */
public class UtenteUI implements ComandiUISenzaParametri<Utente> {

    private static FileUtenti fileUtenti = new FileUtenti();
    private Scanner scanner;
    private UtenteService utenteServ;
    private Console console = System.console();

    public UtenteUI(Scanner scanner, UtenteService utenteServ) {
        this.scanner = scanner;
        this.utenteServ = utenteServ;
    }

    private Utente registrazione() {
        String dataProvvisoria;
        scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Cognome: ");
        String cognome = scanner.nextLine();

        String username;
        do {
            System.out.print("Username: ");
            username = scanner.nextLine();
        } while (utenteServ.containsKey(username));
        
        System.out.print("Password: ");
        String password;
        if (console == null) {
            password = scanner.nextLine();

        } else {
            char[] passwordTemp = console.readPassword();
            for (int i = 0; i < passwordTemp.length; i++) {
                System.out.print("*");
            }
            System.out.println("");
            password = new String(passwordTemp);
        }
        scanner.nextLine();
        boolean dataNascitaValida = false;
        LocalDate dataNascita = null;
        do {
            System.out.print("Data di nascita (YYYY-MM-DD): ");
            dataProvvisoria = scanner.next();
            try {
                dataNascita = LocalDate.parse(dataProvvisoria);
                dataNascitaValida = true;
            } catch (DateTimeException e) {
                System.out.println(e.getMessage());
            }
        } while (!dataNascitaValida);
        scanner.nextLine();
        System.out.print("Domicilio: ");
        String luogoDomicilio = scanner.nextLine();

        System.out.print("Ruolo: ");//impreciso
        String ruolo = inserisciRuolo();

        /*Scrivere il nuovo utente in file csv usando gli opportuni metodi*/
        Utente utente = new Utente();
        if (ruolo.compareTo("gestore") == 0) {
            utente = new Gestore(nome, cognome, username, password, dataNascita, luogoDomicilio, ruolo);
        } else if (ruolo.compareTo("cliente") == 0) {
            utente = new Cliente(nome, cognome, username, password, dataNascita, luogoDomicilio, ruolo);
        }
        return utente;
    }

    private Utente login() {

        System.out.print("Username: ");
        String username = scanner.next();

        System.out.print("Password: ");
        String password;
        if (console == null) {
            password = scanner.nextLine();

        } else {
            char[] passwordTemp = console.readPassword();
            for (int i = 0; i < passwordTemp.length; i++) {
                System.out.print("*");
            }
            System.out.println("");
            password = new String(passwordTemp);
        }

        if ((utenteServ.get(username) != null & (utenteServ.get(username).getPassword().compareTo(password)) == 0)) {
            return utenteServ.get(username);
        } else {
            return null;
        }
    }

    private String inserisciRuolo() {
        Ruolo ruolo = null;

        while (ruolo == null) {
            System.out.print("Inserisci il tuo ruolo (gestore o cliente): ");
            String input = scanner.next().toUpperCase();

            try {
                ruolo = Ruolo.valueOf(input);
                System.out.println("Ruolo selezionato: " + ruolo);
            } catch (IllegalArgumentException e) {
                System.out.print("Ruolo non valido. inserire di nuovo: ");
            }
        }

        return ruolo.toString().toLowerCase();
    }

    @Override
    public Utente add() {
        Utente utente = registrazione();
        utenteServ.add(utente);
        return utente;
    }

    @Override
    public Utente get() {
        Utente utente = null;
        try {
            utente = login();
        } catch (NullPointerException e) {
            System.out.println("Riprovare");
        }
        return utente;
    }

    @Override
    public Utente remove() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Utente put() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void visualizza() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void visualizza(Utente valore) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
