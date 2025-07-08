
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
import java.util.NoSuchElementException;

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
        try {
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

            String password = chiediPassword();
            LocalDate dataNascita = chiediDataNascita();

            System.out.print("Domicilio: ");
            String luogoDomicilio = scanner.nextLine();

            String ruolo = inserisciRuolo();

            /*Scrivere il nuovo utente in file csv usando gli opportuni metodi*/
            Utente utente;
            if (ruolo.equals("gestore")) {
                utente = new Gestore(nome, cognome, username, password, dataNascita, luogoDomicilio, ruolo);
            } else {
                utente = new Cliente(nome, cognome, username, password, dataNascita, luogoDomicilio, ruolo);
            }
            return utente;
        } catch (NoSuchElementException e) {
            System.out.println("Input interrotto. Registrazione annullata.");
            return null;
        }
    }

    private String chiediPassword() {
        String password;
        while (true) {
            System.out.print("Password: \n (almeno 1 maiuscola, 8 caratteri, 1 numero, 1 speciale (.!?)): ");

            if (console != null) {
                char[] passwordChars = console.readPassword();
                password = new String(passwordChars);
            } else {
                password = scanner.nextLine();
            }

            if (validaPassword(password)) {
                break;
            } else {
                System.out.println("Password non valida. Riprova.");
            }
        }
        return password;
    }

    private boolean validaPassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean haMaiuscola = false;
        boolean haNumero = false;
        boolean haSpeciale = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                haMaiuscola = true;
            } else if (Character.isDigit(c)) {
                haNumero = true;
            } else if (".!?".indexOf(c) >= 0) {
                haSpeciale = true;
            }
        }

        return haMaiuscola && haNumero && haSpeciale;
    }

    private LocalDate chiediDataNascita() {
        boolean dataNascitaValida = false;
        LocalDate dataNascita = null;
        while (!dataNascitaValida) {
            System.out.print("Data di nascita (YYYY-MM-DD): ");
            String dataProvvisoria = scanner.nextLine();
            try {
                dataNascita = LocalDate.parse(dataProvvisoria);
                dataNascitaValida = true;
            } catch (DateTimeException e) {
                System.out.println("Formato data non valido. Riprova.");
            }
        }
        return dataNascita;
    }

    private String inserisciRuolo() {
        Ruolo ruolo = null;

        while (ruolo == null) {
            System.out.print("Inserisci il tuo ruolo (gestore o cliente): ");
            String input = scanner.nextLine().toUpperCase();

            try {
                ruolo = Ruolo.valueOf(input);
                System.out.println("Ruolo selezionato: " + ruolo.toString().toLowerCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Ruolo non valido. Riprova.");
            }
        }
        return ruolo.toString().toLowerCase();
    }

    private Utente login() {
        try {
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

        Utente utente = utenteServ.get(username);
        if (utente != null && utente.getPassword().equals(password)) {
            return utente;
        } else {
            System.out.println("Credenziali errate.");
            return null;
        }
    }catch (NoSuchElementException e) {
            System.out.println("Login interrotto.");
        return null;
    }
}

    @Override
    public Utente add() {
        Utente utente = registrazione();
        if (utente != null) {
        utenteServ.add(utente);
        }
        return utente;
    }

    @Override
    public Utente get() {
        return login ();
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
