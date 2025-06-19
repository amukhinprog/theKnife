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
import repository.RistoranteService;

/**
 *
 * @author armuh
 */
public class GestioneMenu {

    Scanner scanner = new Scanner(System.in);
    UtenteUI utenteUI = new UtenteUI(scanner);
    RistoranteUI ristoranteUI = new RistoranteUI(scanner);
    RistoranteService ristoranteServ = new RistoranteService();

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
                    benvenutoUtente(u);
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
            Scanner scanner = new Scanner(System.in);

            System.out.println("1. Visualizza ristoranti");
            System.out.println("0. Esci");
            scelta = scanner.nextInt();
            switch (scelta) {
                case 1:
                    ristoranteServ.cercaRistorante();
                    break;
            }
        } while (scelta != 0);
    }

    public void benvenutoGestore(Gestore utente) {
        int scelta;
        String nomeRistorante = "";
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("1. Aggiungi ristorante");
            System.out.println("2. Ricerca ristoranti");
            System.out.println("0. Esci");
            scelta = scanner.nextInt();
            switch (scelta) {
                case 1:/*aggiungere ristorante esistente in csv o non esistente? qui non esistente*/
                    Ristorante r = ristoranteUI.inserisciGenericoRistorante(utente);
                    AssGestoreRistoranti.assRistoranteAGestore(utente.getUsername(), r);
                    break;
                case 2:
                    ristoranteServ.cercaRistorante();
                    break;
                default:
                    System.out.println("Inserisci il numero corretto");
                    break;
            }
        } while (scelta != 0);
    }

    public void benvenutoCliente(Cliente utente) {
        int scelta;
        String nomeRistorante = "";
        Scanner scanner = new Scanner(System.in);
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
            switch (scelta) {
                case 1:
                    r = ristoranteUI.chiediInformazioniRistorante();
                    PreferitiCliente.aggiungiPreferito(nomeRistorante, r);
                    break;
                case 2:
                    r = ristoranteUI.chiediInformazioniRistorante();
                    PreferitiCliente.rimuoviPreferito(nomeRistorante, r);
                    break;
                case 3:
                    PreferitiCliente.visualizzaPreferiti(utente.getUsername());
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    ristoranteServ.cercaRistorante();
                    break;
                default:
                    System.out.println("inserisci il numero corretto");
                    break;
            }
        } while (scelta != 0);
    }

}
