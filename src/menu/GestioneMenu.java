/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import entita.Ristorante;
import entita.Utente;
import java.util.Scanner;
import entita.*;
import gestioneFile.FileGestoreRistorante;
import repository.AssGestoreRistorantiService;
import repository.GestoreService;
import repository.PreferitiClienteService;
import repository.RecensioneService;
import repository.RistoranteService;
import repository.UtenteService;

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

    public GestioneMenu() {
        benvenuto();
    }

    public void benvenuto() {
        int scelta;

        do {
            System.out.println("Benvenuto su The Knife, scegliere l'opzione");
            System.out.println("1. Registrati");
            System.out.println("2. Login");
            System.out.println("3. Entra come guest");
            System.out.println("0. Esci");
            Scanner scanner = new Scanner(System.in);
            scelta = scanner.nextInt();
            Utente u;
            switch (scelta) {
                case 1:
                    u = utenteUI.registrazione();
                    if (u != null) {
                        benvenutoUtente(u);
                    }
                    break;
                case 2:
                    u = utenteUI.login();
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
        int scelta;
        do {

            System.out.println("1. Visualizza ristoranti");
            System.out.println("0. Esci");
            scelta = scanner.nextInt();
            switch (scelta) {
                case 1:
                    ristoranteUI.cerca();
                    break;
            }
        } while (scelta != 0);
    }

    public void benvenutoGestore(Gestore utente) {
        int scelta;
        String nomeRistorante = "";
        do {
            System.out.println("1. Aggiungi ristorante");
            System.out.println("2. Ricerca ristoranti");
            System.out.println("3. Visualizza media valutazioni");
            System.out.println("0. Esci");
            scelta = scanner.nextInt();
            switch (scelta) {
                case 1:/*aggiungere ristorante esistente in csv o non esistente? qui non esistente*/
                    assGestoreRistoranteUI.add(utente);
                    break;
                case 2:
                    ristoranteUI.cerca();
                    break;
                case 3:
                    recensioneUI.mediaStelle(utente);
                    break;
                default:
                    System.out.println("Inserisci il numero corretto");
                    break;
            }
        } while (scelta != 0);
    }

    public void benvenutoCliente(Cliente utente) {
        int scelta;
        do {
            System.out.println("1. Aggiungi ristorante ai preferiti");
            System.out.println("2. Rimuovi ristorante dai preferiti");
            System.out.println("3. Visualizza preferiti");
            System.out.println("4. Aggiungi recensione");
            System.out.println("5. Modifica recensione");
            System.out.println("6. Elimina recensione");
            System.out.println("7. Ricerca ristoranti");
            System.out.println("0. Esci");
            Ristorante r;
            scelta = scanner.nextInt();
            scanner.nextLine();
            switch (scelta) {
                case 1:
                    preferitiClienteUI.add(utente);
                    break;
                case 2:
                    preferitiClienteUI.put(utente);
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
                    ristoranteUI.cerca();
                    break;
                default:
                    System.out.println("inserisci il numero corretto");
                    break;
            }
        } while (scelta != 0);
    }

}
