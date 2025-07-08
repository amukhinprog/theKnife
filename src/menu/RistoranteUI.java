/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package menu;

import entita.dominio.Ristorante;
import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import repository.RistoranteService;
import java.util.NoSuchElementException;

/**
 *
 * @author armuh
 */
public class RistoranteUI implements ComandiUISenzaParametri<Ristorante> {

    private Scanner scanner;
    private RistoranteService ristoranteServ;

    public RistoranteUI(Scanner scanner, RistoranteService ristoranteServ) {
        this.scanner = scanner;
        this.ristoranteServ = ristoranteServ;
    }

    public Ristorante contains() {
        String nomeRistorante;
        do {
            System.out.println("Scrivere il nome del ristorante: ");
            nomeRistorante = scanner.next();
            if (nomeRistorante.equals("0")) {
                System.out.println("Uscita richiesta. Operazione annullata.");
                return null;
            }
        } while (ristoranteServ.containsKey(nomeRistorante));
        return ristoranteServ.get(nomeRistorante);
    }

    private Ristorante chiedi() {
        String nome;
        do {
            System.out.println("Nome (o '0' per uscire): ");
            nome = scanner.nextLine();
            if (nome.equals("0")) {
                System.out.println("Uscita richiesta. Operazione annullata.");
                return null;
            }
            while (ristoranteServ.containsKey(nome)) {
                System.out.println("Il nome del ristorante esiste già, riprova (o '0' per uscire): ");
                nome = scanner.nextLine();
                if (nome.equals("0")) {
                    System.out.println("Uscita richiesta. Operazione annullata.");
                    return null;
                }
            }

            try {
                System.out.println("Indirizzo (o '0' per uscire): ");
                String indirizzo = scanner.nextLine();
                if (indirizzo.equals("0")) {
                    System.out.println("Uscita richiesta. Operazione annullata.");
                    return null;
                }

                System.out.println("Locazione (o '0' per uscire): ");
                String locazione = scanner.nextLine();
                if (locazione.equals("0")) {
                    System.out.println("Uscita richiesta. Operazione annullata.");
                    return null;
                }

                float prezzo;
                while (true) {
                    System.out.print("Prezzo (o '0' per uscire): ");
                    String input = scanner.nextLine();
                    if (input.equals("0")) {
                        System.out.println("Uscita richiesta. Operazione annullata.");
                        return null;
                    }
                    try {
                        prezzo = Float.parseFloat(input);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Inserisci un numero valido per il prezzo.");
                    }
                }

                System.out.println("Cucina (o '0' per uscire):");
                String cucina = scanner.nextLine();
                if (cucina.equals("0")) {
                    System.out.println("Uscita richiesta. Operazione annullata.");
                    return null;
                }

                System.out.println("Longitudine (o '0' per uscire): ");
                float longitudine = scanner.nextFloat();
                scanner.nextLine();

                System.out.println("Latitudine (o '0' per uscire): ");
                float latitudine = scanner.nextFloat();
                scanner.nextLine();

                System.out.println("Numero di telefono (o '0' per uscire): ");
                String numeroTelefono = scanner.nextLine();
                if (numeroTelefono.equals("0")) {
                    System.out.println("Uscita richiesta. Operazione annullata.");
                    return null;
                }

                System.out.println("Delivery (o '0' per uscire): ");
                boolean delivery = scanner.nextBoolean();
                scanner.nextLine();

                System.out.println("Url (o '0' per uscire):");
                String url = scanner.nextLine();
                if (url.equals("0")) {
                    System.out.println("Uscita richiesta. Operazione annullata.");
                    return null;
                }

                System.out.println("Website Url (o '0' per uscire):");
                String webSiteUrl = scanner.nextLine();
                if (webSiteUrl.equals("0")) {
                    System.out.println("Uscita richiesta. Operazione annullata.");
                    return null;
                }

                System.out.println("Prenotazione (o '0' per uscire): ");
                boolean prenotazione = scanner.nextBoolean();
                scanner.nextLine();

                System.out.println("Descrizione (o '0' per uscire): ");
                String descrizione = scanner.nextLine();
                if (descrizione.equals("0")) {
                    System.out.println("Uscita richiesta. Operazione annullata.");
                    return null;
                }
//        System.out.println("Stelle: ");
//        short stelle = scanner.nextShort();
                return new Ristorante(nome, indirizzo, locazione, prezzo, cucina, longitudine, latitudine,
                        numeroTelefono, delivery, url, webSiteUrl, prenotazione, descrizione);
            } catch (NoSuchElementException e) {
                System.out.println("Input interrotto. Operazione annullata.");
                return null;
            }
        } while (true);
    }

    public void visualizza(List<Ristorante> ristoranti) {
//        System.out.println(ristoranti);

        for (Ristorante ristorante : ristoranti) {
            System.out.println("Nome: " + ristorante.getNome());
            System.out.println("Locazione: " + ristorante.getLocazione());
            System.out.println("Prezzo: " + ristorante.getPrezzo() + " euro");
            System.out.println("Tipo cucina: " + ristorante.getCucina());
            System.out.println("Servizio delivery: " + ristorante.isDelivery());
            System.out.println("Servizio prenotazione: " + ristorante.isPrenotazione() + "\n\n");
            //System.out.println("Stelle: " + ristorante.getStelle());
        }
    }

    private String chiediNome(Scanner scanner) {
        System.out.println("Inserire il nome del ristorante: ");
        System.out.println("0. Esci");
        String nome = scanner.nextLine();
        if (nome.equals("0")) {
            System.out.println("Uscita richiesta.");
            return null;
        }
        return nome;
    }

    public void cerca() {
        int scelta = -1;
        System.out.println("Inserire 1 per cercare un ristorante per locazione");
        System.out.println("Inserire 2 per cercare un ristorante per tipologia di cucina");
        System.out.println("Inserire 3 per cercare un ristorante per fascia di prezzo");
        System.out.println("Inserire 4 per cercare un ristorante in base alla disponibilita' del servizio di delivery");
        System.out.println("Inserire 5 per cercare un ristorante in base alla disponibilita' del servizio di prenotazione online");
        /*System.out.println("Inserire 6 per cercare un ristorante per media del numero di stelle");
        System.out.println("Inserire 7 per cercare un ristorante per combinazione dei precedenti criteri di ricerca")*;*/

        try {
            String input = scanner.nextLine();
            scelta = Integer.parseInt(input);
        } catch (NumberFormatException | NoSuchElementException e) {
            System.out.println("Input non valido o interrotto. Operazione annullata.");
            return;
        }

        switch (scelta) {
            case 1:
                cercaPerLocazione();
                break;
            case 2:
                cercaPerCucina();
                break;
            case 3:
                cercaPerPrezzo();
                break;
            case 4:
                cercaPerDelivery();
                break;
            case 5:
                cercaPerPrenotazione();
                break;
            /*case 6:
                cercaPerStelle();
                break;
           /*case 7:
                cercaCriteri();
                break;*/
            default:
                System.out.println("Scegliere l'opzione corretta");
                break;
        }

    }

    private void cercaPerLocazione() {
        System.out.println("Inserire il nome della citta': ");
        String locazione = scanner.nextLine();
        if (locazione.equals("0")) {
            System.out.println("Uscita richiesta.");
            return;
        }
        locazione = locazione.toLowerCase();
        /////QKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK
        Collection<Ristorante> ristorantiColl = ristoranteServ.values();
        List<Ristorante> ristorantiList = new ArrayList<>();
        for (Ristorante ristorante : ristorantiColl) {
            String locazioneRistorante = ristorante.getLocazione().replace("\"", "");
            if (locazioneRistorante.toLowerCase().startsWith(locazione.toLowerCase())) {
                ristorantiList.add(ristorante);
            }
        }
        visualizza(ristorantiList);
    }

    private void cercaPerCucina() {
        System.out.println("Inserire la tipologia di cucina del ristorante : ");
        String cucina = scanner.nextLine();
        if (cucina.equals("0")) {
            System.out.println("Uscita richiesta.");
            return; 
        }
        cucina = cucina.toLowerCase();
        Collection<Ristorante> ristorantiColl = ristoranteServ.values();
        List<Ristorante> ristorantiList = new ArrayList<>();
        for (Ristorante ristorante : ristorantiColl) {
            String cucinaRistorante = ristorante.getCucina().replace("\"", "");
            if (cucinaRistorante.toLowerCase().startsWith(cucina.toLowerCase())) {
                ristorantiList.add(ristorante);
            }
        }
        visualizza(ristorantiList);
    }

    private void cercaPerPrezzo() {
        System.out.println("Visualizzare i ristoranti con un prezzo minore di: ");
        Float prezzoLimite;
        try {
            prezzoLimite = scanner.nextFloat();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Inserire un numero valido per il prezzo.");
            return;
        }
        Collection<Ristorante> ristorantiColl = ristoranteServ.values();
        List<Ristorante> ristorantiList = new ArrayList<>();
        for (Ristorante ristorante : ristorantiColl) {
            String tipologiaRistorante = ristorante.getCucina().replace("\"", "");
            if (ristorante.getPrezzo() < prezzoLimite) {
                ristorantiList.add(ristorante);
            }
        }
        visualizza(ristorantiList);
    }

    private void cercaPerDelivery() {
        boolean delivery;

        while (true) {
            System.out.print("Vuoi visualizzare solo i ristoranti con delivery? (si/no): ");
            String risposta = scanner.next().trim().toLowerCase();
            scanner.nextLine();
            
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

        Collection<Ristorante> ristorantiColl = ristoranteServ.values();
        List<Ristorante> ristorantiList = new ArrayList<>();

        for (Ristorante ristorante : ristorantiColl) {
            if (ristorante.isDelivery() == delivery) {
                ristorantiList.add(ristorante);
            }
        }

        visualizza(ristorantiList);
    }

    private void cercaPerPrenotazione() {
        boolean prenotazione;

        while (true) {
            System.out.print("Vuoi visualizzare solo i ristoranti con prenotazione online? (si/no): ");
            String risposta = scanner.next().trim().toLowerCase();
            scanner.nextLine();
            
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

        Collection<Ristorante> ristorantiColl = ristoranteServ.values();
        List<Ristorante> ristorantiList = new ArrayList<>();

        for (Ristorante ristorante : ristorantiColl) {
            if (ristorante.isPrenotazione() == prenotazione) {
                ristorantiList.add(ristorante);
            }
        }

        visualizza(ristorantiList);
    }

    /*private void cercaPerStelle() {
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
    @Override
    public Ristorante add() {
        Ristorante ristorante;
        ristorante = chiedi();
        if (ristorante == null) {
            return null;
        }
        ristoranteServ.add(ristorante);
        return ristorante;
    }

    @Override
    public Ristorante get() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Ristorante remove() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Ristorante put() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void visualizza() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void visualizza(Ristorante valore) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
