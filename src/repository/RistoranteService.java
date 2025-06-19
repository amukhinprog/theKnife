/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entita.AssGestoreRistoranti;
import entita.Gestore;
import entita.Ristorante;
import gestioneFile.FileRistorante;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import menu.RistoranteUI;

/**
 *
 * @author armuh
 */
public class RistoranteService {

    private static FileRistorante fileRistorante = new FileRistorante();
    private HashMap<String, Ristorante> ristoranti = new FileRistorante().ottieniHashMap();
    private Scanner scanner = new Scanner(System.in);
    private RistoranteUI ristoranteUI = new RistoranteUI(scanner);
    
    public HashMap<String, Ristorante> getRistoranti() {
        return ristoranti;
    }

    public void setRistoranti(HashMap<String, Ristorante> ristoranti) {
        this.ristoranti = ristoranti;
    }

    public boolean containsKey(String chiave) {
        return ristoranti.containsKey(chiave);
    }

    public Ristorante get(String chiave) {
        if (ristoranti.containsKey(chiave)) {
            return ristoranti.get(chiave);
        } else {
            return null;
        }
    }
    
    public Collection<Ristorante> values(){
        return  ristoranti.values();
    }
    
    public void cercaRistorante() {
        int scelta = -1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserire 1 per cercare un ristorante per locazione");
        System.out.println("Inserire 2 per cercare un ristorante per tipologia di cucina");
        System.out.println("Inserire 3 per cercare un ristorante per fascia di prezzo");
        System.out.println("Inserire 4 per cercare un ristorante in base alla disponibilita' del servizio di delivery");
        System.out.println("Inserire 5 per cercare un ristorante in base alla disponibilita' del servizio di prenotazione online");
        /*System.out.println("Inserire 6 per cercare un ristorante per media del numero di stelle");
        System.out.println("Inserire 7 per cercare un ristorante per combinazione dei precedenti criteri di ricerca")*;*/

        try {
            scelta = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        switch (scelta) {
            case 1:
                cercaRistorantePerLocazione();
                break;
            case 2:
                cercaRistorantePerCucina();
                break;
            case 3:
                cercaRistorantePerPrezzo();
                break;
            case 4:
                cercaRistorantePerDelivery();
                break;
            case 5:
                cercaRistorantePerPrenotazione();
                break;
            /*case 6:
                cercaRistorantePerStelle();
                break;
           /*case 7:
                cercaRistoranteCriteri();
                break;*/
            default:
                System.out.println("Scegliere l'opzione corretta");
                break;
        }

    }

    private void cercaRistorantePerLocazione() {
        System.out.println("Inserire il nome della citta': ");
        String locazione = scanner.next();
        locazione = locazione.toLowerCase();
        /////QKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK
        Collection<Ristorante> ristorantiColl = ristoranti.values();
        List<Ristorante> ristorantiList = new ArrayList<>();
        for (Ristorante ristorante : ristorantiColl) {
            String locazioneRistorante = ristorante.getLocazione().replace("\"", "");
            if (locazioneRistorante.toLowerCase().startsWith(locazione.toLowerCase())) {
                ristorantiList.add(ristorante);
            }
        }
        ristoranteUI.visualizzaRistorante(ristorantiList);
    }

    private void cercaRistorantePerCucina() {
        System.out.println("Inserire la tipologia di cucina del ristorante : ");
        String cucina = scanner.next();
        cucina = cucina.toLowerCase();
        Collection<Ristorante> ristorantiColl = ristoranti.values();
        List<Ristorante> ristorantiList = new ArrayList<>();
        for (Ristorante ristorante : ristorantiColl) {
            String cucinaRistorante = ristorante.getCucina().replace("\"", "");
            if (cucinaRistorante.toLowerCase().startsWith(cucina.toLowerCase())) {
                ristorantiList.add(ristorante);
            }
        }
        ristoranteUI.visualizzaRistorante(ristorantiList);
    }

    private void cercaRistorantePerPrezzo() {
        System.out.println("Visualizzare i ristoranti con un prezzo minore di: ");
        Float prezzoLimite;
        try {
            prezzoLimite = scanner.nextFloat();
        } catch (InputMismatchException e) {
            System.out.println("Inserire un numero valido per il prezzo.");
            return;
        }
        Collection<Ristorante> ristorantiColl = ristoranti.values();
        List<Ristorante> ristorantiList = new ArrayList<>();
        for (Ristorante ristorante : ristorantiColl) {
            String tipologiaRistorante = ristorante.getCucina().replace("\"", "");
            if (ristorante.getPrezzo() < prezzoLimite) {
                ristorantiList.add(ristorante);
            }
        }
        ristoranteUI.visualizzaRistorante(ristorantiList);
    }

    private void cercaRistorantePerDelivery() {
        boolean delivery;

        while (true) {
            System.out.print("Vuoi visualizzare solo i ristoranti con delivery? (si/no): ");
            String risposta = scanner.next().trim().toLowerCase();

            if (risposta.equals("sì") || risposta.equals("si")) {
                delivery = true;
                break;
            } else if (risposta.equals("no")) {
                delivery = false;
                break;
            } else {
                System.out.println("Risposta non valida. Inserire 'si' o 'no'.");
            }
        }

        Collection<Ristorante> ristorantiColl = ristoranti.values();
        List<Ristorante> ristorantiList = new ArrayList<>();

        for (Ristorante ristorante : ristorantiColl) {
            if (ristorante.isDelivery() == delivery) {
                ristorantiList.add(ristorante);
            }
        }

        ristoranteUI.visualizzaRistorante(ristorantiList);
    }

    private void cercaRistorantePerPrenotazione() {
        boolean prenotazione;

        while (true) {
            System.out.print("Vuoi visualizzare solo i ristoranti con prenotazione online? (si/no): ");
            String risposta = scanner.next().trim().toLowerCase();

            if (risposta.equals("sì") || risposta.equals("si")) {
                prenotazione = true;
                break;
            } else if (risposta.equals("no")) {
                prenotazione = false;
                break;
            } else {
                System.out.println("Risposta non valida. Inserire 'si' o 'no'.");
            }
        }

        Collection<Ristorante> ristorantiColl = ristoranti.values();
        List<Ristorante> ristorantiList = new ArrayList<>();

        for (Ristorante ristorante : ristorantiColl) {
            if (ristorante.isPrenotazione() == prenotazione) {
                ristorantiList.add(ristorante);
            }
        }

        ristoranteUI.visualizzaRistorante(ristorantiList);
    }

    /*private void cercaRistorantePerStelle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci il numero esatto di stelle (da 1 a 5): ");
        int numeroStelle;

        try {
            numeroStelle = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Inserire un numero intero valido per le stelle da 1 a 5.");
            return;
        }

        Collection<Ristorante> ristorantiColl = ristoranti.values();
        List<Ristorante> ristorantiList = new ArrayList<>();

        for (Ristorante ristorante : ristorantiColl) {
            if (ristorante.getStelle() == numeroStelle) {
                ristorantiList.add(ristorante);
            }
        }

        visualizzaRistorante(ristorantiList);
    }*/
    
    public boolean ristoranteGiaPossedutoDalGestore(Gestore utente, Ristorante ristorante) {
        HashMap<String, AssGestoreRistoranti> mappa = AssGestoreRistoranti.getRistorantiMap();
        AssGestoreRistoranti gestore = mappa.get(utente.getUsername());
        return gestore != null && gestore.ristoranteInPossessoDaUtente(ristorante);
    }

    public boolean ristoranteHaAltroProprietario(Gestore utente, Ristorante ristorante) {
        return AssGestoreRistoranti.ristoranteInPossessoDaUtenti(ristorante);
    }
}
