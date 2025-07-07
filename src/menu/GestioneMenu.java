/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package menu;

import entita.dominio.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import repository.*;
import java.util.NoSuchElementException;

/**
 *
 * @author armuh
 */
public class GestioneMenu {

    private Scanner scanner = new Scanner(System.in);
    private RistoranteService ristoranteServ = new RistoranteService();
    private UtenteService utenteServ = new UtenteService();
    private PreferitiClienteService prefClienteServ = new PreferitiClienteService();
    private UtenteUI utenteUI = new UtenteUI(scanner, utenteServ);
    private RistoranteUI ristoranteUI = new RistoranteUI(scanner, ristoranteServ);
    private PreferitiClienteUI preferitiClienteUI = new PreferitiClienteUI(scanner, prefClienteServ, ristoranteServ);
    private RecensioneService recensioneServ = new RecensioneService();
    private RecensioneUI recensioneUI = new RecensioneUI(scanner, recensioneServ, ristoranteServ);
    private AssGestoreRistorantiService assGestoreRistorantiServ = new AssGestoreRistorantiService();
    private GestoreService gestoreServ = new GestoreService();
    private AssGestoreRistoranteUI assGestoreRistoranteUI = new AssGestoreRistoranteUI(scanner, assGestoreRistorantiServ, ristoranteServ);
    private RispostaRecensioniService rispostaRecensioniServ = new RispostaRecensioniService();
    private RispostaRecensioniUI rispostaRecensioniUI = new RispostaRecensioniUI(scanner, rispostaRecensioniServ, recensioneServ, assGestoreRistorantiServ);

    public GestioneMenu() {
         try {
        benvenuto();
    } catch (NoSuchElementException e) {
        System.out.println("Input interrotto (es. con Ctrl+C). Uscita dal programma.");
    }
}
    

    public void benvenuto() {
        int scelta = 0;
        boolean valido = false;
        do {
            System.out.println("Benvenuto su The Knife, scegliere l'opzione");
            System.out.println("1. Registrati");
            System.out.println("2. Login");
            System.out.println("3. Entra come guest");
            System.out.println("0. Esci");
            do {
                try {
                    System.out.print("Inserire il numero corrispondente al menu voluto e premere invio: ");
                    scelta = scanner.nextInt();
                    valido = true;
                } catch (InputMismatchException e) {
                    System.out.println("Errore: inserire un numero valido. \n");
                    scanner.nextLine();
                }
            } while (!valido);

            Utente u;
            switch (scelta) {
                case 1:
                    u = utenteUI.add();
                    if (u != null) {
                        benvenutoUtente(u);
                    }
                    break;
                case 2:
                    u = utenteUI.get();
                    if (u != null) {
                        benvenutoUtente(u);
                    }
                    break;
                case 3:
                    benvenutoGuest();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Scegliere l'opzione corretta");
                    break;
            }
        } while (scelta != 0);
    }

    public void benvenutoUtente(Utente utente) {/*controllo oggetto creato(in reg. o login) se istanza di gestore o cliente*/
        if (utente instanceof Gestore) {
            System.out.println("Benvenuto sig. " + utente.getCognome());
            benvenutoGestore((Gestore) utente);
        } else {
            System.out.println("Benvenuto " + utente.getNome());
            benvenutoCliente((Cliente) utente);
        }
    }

    public void benvenutoGuest() {
        int scelta = 0;
        boolean valido = false;
        do {

            System.out.println("1. Visualizza ristoranti");
            System.out.println("0. Esci");
            do {
                try {
                    System.out.print("inserire il numero corrispondente al menu voluto e premere invio: ");
                    scelta = scanner.nextInt();
                    valido = true;
                } catch (InputMismatchException e) {
                    System.out.println("Errore: inserire un numero valido. \n");
                    scanner.nextLine();
                }
            } while (!valido);
            switch (scelta) {
                case 1:
                    ristoranteUI.cerca();
                    break;
                case 0:
                    break;
            }
        } while (scelta != 0);
    }

    public void benvenutoGestore(Gestore utente) {
        int scelta = 0;
        boolean valido = false;
        do {
            System.out.println("1. Aggiungi ristorante di proprietà (esistenti nel database)");
            System.out.println("2. Ricerca ristoranti");
            System.out.println("3. Visualizza media valutazioni");
            System.out.println("4. Rispondi alle recensioni");
            System.out.println("0. Esci");
            do {
                try {
                    System.out.print("inserire il numero corrispondente al menu voluto e premere invio: ");
                    scelta = scanner.nextInt();
                    valido = true;
                } catch (InputMismatchException e) {
                    System.out.println("Errore: inserire un numero valido. \n");
                    scanner.nextLine();
                }
            } while (!valido);
            switch (scelta) {
                case 1:
                    assGestoreRistoranteUI.aggiungi(utente);
                    break;
                case 2:
                    ristoranteUI.cerca();
                    break;
                case 3:
                    recensioneUI.mediaStelle(utente);
                    break;
                case 4:
                    rispostaRecensioniUI.add(utente);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Inserire il numero corretto");
                    break;
            }
        } while (scelta != 0);
    }

    public void benvenutoCliente(Cliente utente) {
        int scelta = 0;
        boolean valido = false;
        do {
            System.out.println("1. Aggiungi ristorante ai preferiti");
            System.out.println("2. Rimuovi ristorante dai preferiti");
            System.out.println("3. Visualizza preferiti");
            System.out.println("4. Aggiungi recensione");
            System.out.println("5. Modifica recensione");
            System.out.println("6. Elimina recensione");
            System.out.println("7. Visualizza recensioni");
            System.out.println("8. Ricerca ristoranti");
            System.out.println("0. Esci");
            do {
                try {
                    System.out.print("inserire il numero corrispondente al menu voluto e premere invio: ");
                    scelta = scanner.nextInt();
                    valido = true;
                } catch (RuntimeException e) {
                    System.out.println("Errore: inserire un numero valido. \n");
                    scanner.nextLine();
                }
            } while (!valido);

            switch (scelta) {
                case 1:
                    preferitiClienteUI.aggiungi(utente);
                    break;
                case 2:
                    preferitiClienteUI.remove(utente);
                    break;
                case 3:
                    preferitiClienteUI.visualizza(utente);
                    break;
                case 4:
                    recensioneUI.add(utente);
                    break;
                case 5:
                    recensioneUI.put(utente);
                    break;
                case 6:
                    recensioneUI.remove(utente);
                    break;
                case 7:
                    recensioneUI.visualizza(utente);
                    break;
                case 8:
                    ristoranteUI.cerca();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("inserire il numero corretto");
                    break;
            }
        } while (scelta != 0);
    }

}
