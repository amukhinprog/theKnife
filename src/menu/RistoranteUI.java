/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package menu;

import entita.associazioni.Recensione;
import entita.dominio.Ristorante;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import repository.RistoranteService;
import java.util.NoSuchElementException;
import repository.RecensioneService;

/**
 * Gestisce l'interfaccia utente per la ricerca e la visualizzazione dei ristoranti,
 * offrendo diversi criteri di filtraggio.
 * @author armuh
 */
public class RistoranteUI implements ComandiUISenzaParametri<Ristorante> {

    private Scanner scanner;
    private RistoranteService ristoranteServ;
    private RecensioneService recensioneServ;

    public RistoranteUI(Scanner scanner, RistoranteService ristoranteServ, RecensioneService recensioneServ) {
        this.scanner = scanner;
        this.ristoranteServ = ristoranteServ;
        this.recensioneServ = recensioneServ;
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
                return new Ristorante(nome, indirizzo, locazione, prezzo, cucina, longitudine, latitudine,
                        numeroTelefono, delivery, url, webSiteUrl, prenotazione, descrizione);
            } catch (NoSuchElementException e) {
                System.out.println("Input interrotto. Operazione annullata.");
                return null;
            }
        } while (true);
    }

    public void visualizza(List<Ristorante> ristoranti) {
        if (!ristoranti.isEmpty()) {
            String[] intestazione = {"Nome", "Indirizzo", "Locazione", "Prezzo",
                "Cucina", "S. delivery", "S. prenotazione"};
            StringBuilder tabella = new StringBuilder();
            tabella.append(String.format("%-30s %-80s %-30s %-10s %-30s %-10s %-15s %n", intestazione[0],
                    intestazione[1], intestazione[2], intestazione[3],
                    intestazione[4], intestazione[5], intestazione[6]));
            for (Ristorante valore : ristoranti) {
                tabella.append(String.format("%-30s %-80s %-30s %-10s %-30s %-10s %-15s %n", valore.getNome(),
                        valore.getIndirizzo(), valore.getLocazione(), valore.getPrezzo(),
                        valore.getCucina(), (valore.isDelivery() ? "si" : "no"), (valore.isPrenotazione() ? "si" : "no")));
            }
            System.out.println(tabella.toString());
        } else {
            System.out.print("Non è presente nessun ristorante");
        }

        System.out.println("\n");
    }

    public void visualizza(HashMap<Ristorante, Float> map) {
        for (Ristorante r : map.keySet()) {
            visualizza(r);
            System.out.println("Media stelle: " + map.get(r) + "\n\n");
        }
    }
/**
 * Mostra un menu all'utente per scegliere un criterio di ricerca per i ristoranti
 * (es. per locazione, cucina, prezzo) e avvia la ricerca corrispondente.
 */
    public void cerca() {
        int scelta;
        System.out.println("Inserire 1 per cercare un ristorante per locazione");
        System.out.println("Inserire 2 per cercare un ristorante per tipologia di cucina");
        System.out.println("Inserire 3 per cercare un ristorante per fascia di prezzo");
        System.out.println("Inserire 4 per cercare un ristorante in base alla disponibilita' del servizio di delivery");
        System.out.println("Inserire 5 per cercare un ristorante in base alla disponibilita' del servizio di prenotazione online");
        System.out.println("Inserire 6 per cercare un ristorante in base alla media del numero di stelle");
        System.out.println("Inserire 7 per cercare un ristorante per combinazione dei precedenti criteri di ricerca");
        try {
            String input = scanner.next();
            scelta = Integer.parseInt(input);
        } catch (NumberFormatException | NoSuchElementException e) {
            System.out.println("Input non valido o interrotto. Operazione annullata.");
            return;
        }
        scanner.nextLine();
        switch (scelta) {
            case 1:
                visualizza(cercaPerLocazione());
                break;
            case 2:
                visualizza(cercaPerCucina());
                break;
            case 3:
                visualizza(cercaPerPrezzo());
                break;
            case 4:
                visualizza(cercaPerDelivery());
                break;
            case 5:
                visualizza(cercaPerPrenotazione());
                break;
            case 6:
                visualizza(cercaPerStelle());
                break;
            case 7:
                visualizza(cercaPerCombinazioni());
                break;
            case 0:
                System.out.println("Uscita");
                break;
            default:
                System.out.println("Scegliere l'opzione corretta");
                break;
        }
    }

    private List<Ristorante> cercaPerLocazione() {
        System.out.println("Inserire il nome della citta': ");
        String locazione = scanner.nextLine();
        if (locazione.equals("0")) {
            System.out.println("Uscita richiesta.");
            return new ArrayList<>();
        }
        locazione = locazione.toLowerCase();
        Collection<Ristorante> ristorantiColl = ristoranteServ.values();
        List<Ristorante> ristorantiList = new ArrayList<>();
        for (Ristorante ristorante : ristorantiColl) {
            String locazioneRistorante = ristorante.getLocazione().replace("\"", "");
            if (locazioneRistorante.toLowerCase().startsWith(locazione.toLowerCase())) {
                ristorantiList.add(ristorante);
            }
        }
        return ristorantiList;
    }

    private List<Ristorante> cercaPerCucina() {
        System.out.println("Inserire la tipologia di cucina del ristorante : ");
        String cucina = scanner.nextLine();
        if (cucina.equals("0")) {
            System.out.println("Uscita richiesta.");
            return new ArrayList<>();
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
        return ristorantiList;
    }

    private List<Ristorante> cercaPerPrezzo() {
        System.out.println("Visualizzare i ristoranti con un prezzo minore di: ");
        Float prezzoLimite;
        try {
            prezzoLimite = scanner.nextFloat();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Inserire un numero valido per il prezzo.");
            return new ArrayList<>();
        }
        Collection<Ristorante> ristorantiColl = ristoranteServ.values();
        List<Ristorante> ristorantiList = new ArrayList<>();
        for (Ristorante ristorante : ristorantiColl) {
            String tipologiaRistorante = ristorante.getCucina().replace("\"", "");
            if (ristorante.getPrezzo() < prezzoLimite) {
                ristorantiList.add(ristorante);
            }
        }
        return ristorantiList;
    }

    private List<Ristorante> cercaPerDelivery() {
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

        return ristorantiList;
    }

    private List<Ristorante> cercaPerPrenotazione() {
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

        return ristorantiList;
    }

    private HashMap<Ristorante, Float> cercaPerStelle() {
        System.out.println("Inserire la media del numero di stelle: ");
        float media;
        try {
            media = scanner.nextFloat();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Inserire un numero valido per la media.");
            return new HashMap<>();
        }
        Collection<Recensione> recensioneColl = recensioneServ.get().values();

        HashMap<Ristorante, Float> recensioniMediaMap = new HashMap<>();
        for (Recensione recensione : recensioneColl) {
            String nomeRistorante = recensione.getRistoranteRecensito();
            Ristorante ristorante = ristoranteServ.get(nomeRistorante);
            if (!recensioniMediaMap.containsKey(ristorante)
                    && recensioneServ.mediaStelle(ristorante) > media - 0.5
                    && recensioneServ.mediaStelle(ristorante) < media + 0.5) {
                recensioniMediaMap.put(ristorante, recensioneServ.mediaStelle(ristorante));
            }
        }
        return recensioniMediaMap;
    }

    private List<Ristorante> cercaPerCombinazioni() {
        int scelta;
        List<Ristorante> ristorantiListProvvisorio = new ArrayList<>();
        HashMap<Ristorante, Float> ristorantiMap = new HashMap<>();
        List<Ristorante> ristorantiList = new ArrayList<>();
        int i = 0;
        boolean cercaPerStelle = false;
        do {
            System.out.println("Inserire 1 per cercare un ristorante per locazione");
            System.out.println("Inserire 2 per cercare un ristorante per tipologia di cucina");
            System.out.println("Inserire 3 per cercare un ristorante per fascia di prezzo");
            System.out.println("Inserire 4 per cercare un ristorante in base alla disponibilita' del servizio di delivery");
            System.out.println("Inserire 5 per cercare un ristorante in base alla disponibilita' del servizio di prenotazione online");
            System.out.println("Inserire 6 per cercare un ristorante in base alla media del numero di stelle");
            System.out.println("Inserire 0 per terminare la ricerca combinata");
            try {
                String input = scanner.next();
                scelta = Integer.parseInt(input);
            } catch (NumberFormatException | NoSuchElementException e) {
                System.out.println("Input non valido o interrotto. Operazione annullata.");
                return new ArrayList<>();
            }
            scanner.nextLine();
            switch (scelta) {
                case 1:
                    ristorantiListProvvisorio = cercaPerLocazione();
                    break;
                case 2:
                    ristorantiListProvvisorio = cercaPerCucina();
                    break;
                case 3:
                    ristorantiListProvvisorio = cercaPerPrezzo();
                    break;
                case 4:
                    ristorantiListProvvisorio = cercaPerDelivery();
                    break;
                case 5:
                    ristorantiListProvvisorio = cercaPerPrenotazione();
                    break;
                case 6:
                    ristorantiMap = cercaPerStelle();
                    cercaPerStelle = true;
                    break;
                case 0:
                    System.out.println("Uscita");
                    break;
                default:
                    System.out.println("Scegliere l'opzione corretta");
                    break;
            }
            if (scelta != 0) {
                if (i == 0) {
                    if (cercaPerStelle) {
                        ristorantiList.addAll(ristorantiMap.keySet());
                    } else {
                        ristorantiList.addAll(ristorantiListProvvisorio);
                    }
                    i++;
                } else {
                    if (cercaPerStelle) {
                        ristorantiList.retainAll(ristorantiMap.keySet());
                    } else {
                        ristorantiList.retainAll(ristorantiListProvvisorio);
                    }
                }
                cercaPerStelle = false;
            }
        } while (scelta != 0 && !ristorantiList.isEmpty());
        return ristorantiList;
    }

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
        List<Ristorante> ristoranteList = new ArrayList<>();
        ristoranteList.add(valore);
    }

}
