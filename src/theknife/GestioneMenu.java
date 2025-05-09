/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theknife;

import entita.Ristorante;
import entita.Utente;
import java.util.Scanner;
import entita.*;
import gestioneFile.fileGestoreRistorante;

/**
 *
 * @author armuh
 */
public class GestioneMenu {

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
                    u = new Utente();
                    u.registrazione();
                    benvenutoUtente(u);
                    break;
                case 2:
                    Utente utente = Utente.login();
                    if (utente != null) {
                        benvenutoUtente(utente);
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
            benvenutoGestore(utente);
        } else {
            System.out.println("Benvenuto " + utente.getNome());
            benvenutoCliente(utente);
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
                    Ristorante.cercaRistorante();
                    break;
            }
        } while (scelta != 0);
    }

    public void benvenutoGestore(Utente utente) {
        int scelta;
        String nomeRistorante = "";
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("1. aggiungi ristorante");
            System.out.println("0. Esci");
            scelta = scanner.nextInt();
            switch (scelta) {
                case 1:
                    fileGestoreRistorante.associaGestore(utente.getUsername(), nomeRistorante);
                    break;
                default:
                    System.out.println("inserisci il numero corretto");
                    break;
            }
        } while (scelta != 0);
    }
    
    public void benvenutoCliente(Utente utente){
        
       
    }
}
