package entita;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.*;
import java.io.*;
import gestioneFile.FileRistorante;
//import java.util.ArrayList;

/**
 *
 * @author armuh
 */
public class Ristorante {

    private static FileRistorante fileRistorante = new FileRistorante();
    private static HashMap<String, Ristorante> ristoranti = new FileRistorante().ottieniHashMap();
    private String nome;
    private String indirizzo;
    private String locazione;
    private float prezzo;
    private String cucina;
    private float longitudine;
    private float latitudine;
    private String numeroTelefono;
    private boolean delivery;
    private String url;
    private String webSiteUrl;
    private boolean prenotazione;
    private String descrizione;
    //private short stelle;

    public Ristorante(String nome, String indirizzo, String locazione, float prezzo, String cucina, float longitudine, float latitudine, String numeroTelefono, boolean delivery, String url, String webSiteUrl, boolean prenotazione, String descrizione/*,short stelle*/) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.locazione = locazione;
        this.prezzo = prezzo;
        this.cucina = cucina;
        this.longitudine = longitudine;
        this.latitudine = latitudine;
        this.numeroTelefono = numeroTelefono;
        this.delivery = delivery;
        this.url = url;
        this.webSiteUrl = webSiteUrl;
        this.prenotazione = prenotazione;
        this.descrizione = descrizione;
        //this.stelle = stelle;
    }

    private Ristorante() {

    }

    public static final Ristorante ristoranteVuoto() {
        return new Ristorante();
    }

    public static void cercaRistorante() {
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

    private static void cercaRistorantePerLocazione() {
        Scanner scanner = new Scanner(System.in);
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
        visualizzaRistorante(ristorantiList);
    }

    private static void visualizzaRistorante(List<Ristorante> ristoranti) {
//        System.out.println(ristoranti);

        for (Ristorante ristorante : ristoranti) {
            System.out.println("Locazione: " + ristorante.getLocazione());
            System.out.println("Prezzo: " + ristorante.getPrezzo() + " euro");
            System.out.println("Tipo cucina: " + ristorante.getCucina());
            System.out.println("Servizio delivery: " + ristorante.isDelivery());
            System.out.println("Servizio prenotazione: " + ristorante.isPrenotazione() + "\n\n");
            //System.out.println("Stelle: " + ristorante.getStelle());
        }
    }

    private static void cercaRistorantePerCucina() {
        Scanner scanner = new Scanner(System.in);
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
        visualizzaRistorante(ristorantiList);
    }

    private static void cercaRistorantePerPrezzo() {
        Scanner scanner = new Scanner(System.in);
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
        visualizzaRistorante(ristorantiList);
    }

    private static void cercaRistorantePerDelivery() {
        Scanner scanner = new Scanner(System.in);
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

        visualizzaRistorante(ristorantiList);
    }

    private static void cercaRistorantePerPrenotazione() {
        Scanner scanner = new Scanner(System.in);
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

        visualizzaRistorante(ristorantiList);
    }

    /*private static void cercaRistorantePerStelle() {
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

    public static Ristorante inserisciGenericoRistorante() {
        char scelta = ' ';
        Scanner scanner = new Scanner(System.in);
        Ristorante r = null;
        do {
            System.out.println("Vuole inserire un ristorante di nuova apertura? (s/n)");
            System.out.println("0. Esci");
            scelta = scanner.next().charAt(0);
            switch (scelta) {
                case 's':
                    r = inserisciNuovoRistorante();
                    break;
                case 'n':
                    r = inserisciRistoranteEsistente();
                    break;
                default:
                    System.out.println("Riprovare");
                    break;
            }
        } while (scelta != '0');
        return r;
    }

    private static Ristorante inserisciRistoranteEsistente() {
        Scanner scanner = new Scanner(System.in);
        String nomeRistorante;
        Ristorante r = null;
        do {
            System.out.println("Inserire il nome del ristorante: ");
            System.out.println("0.Esci");
            nomeRistorante = scanner.next();
            r = ristoranti.get(nomeRistorante);
            if (AssGestoreRistoranti.ristoranteInPossesso(r)) {
                System.out.println("Il ristorante inserito ha già un proprietario");
            } else if (ristoranti.containsKey(nomeRistorante)) {
                r = ristoranti.get(nomeRistorante);
            }

        } while (!nomeRistorante.equals("0")
                && AssGestoreRistoranti.ristoranteInPossesso(r)
                && !ristoranti.containsKey(nomeRistorante));

        if (nomeRistorante.equals("0")) {
            return ristoranteVuoto();
        } else {
            return ristoranti.get(nomeRistorante);
        }
    }

    private static Ristorante inserisciNuovoRistorante() {
        Scanner scanner = new Scanner(System.in);
        String nome;
        do {
            System.out.println("Nome: ");
            nome = scanner.next();
        } while (ristoranti.containsKey(nome));

        System.out.println("Indirizzo: ");
        String indirizzo = scanner.next();

        System.out.println("Locazione: ");
        String locazione = scanner.next();

        System.out.println("Prezzo: ");
        float prezzo = scanner.nextFloat();

        System.out.println("Cucina:");
        String cucina = scanner.next();

        System.out.println("Longitudine: ");
        float longitudine = scanner.nextFloat();

        System.out.println("Latitudine: ");
        float latitudine = scanner.nextFloat();

        System.out.println("Numero di telefono: ");
        String numeroTelefono = scanner.next();

        System.out.println("Delivery: ");
        boolean delivery = scanner.nextBoolean();

        System.out.println("Url:");
        String url = scanner.next();

        System.out.println("Website Url:");
        String webSiteUrl = scanner.next();

        System.out.println("Prenotazione: ");
        boolean prenotazione = scanner.nextBoolean();

        System.out.println("Descrizione: ");
        String descrizione = scanner.next();

        System.out.println("Stelle: ");
        short stelle = scanner.nextShort();

        return new Ristorante(nome, indirizzo, locazione, prezzo, cucina, longitudine, latitudine, numeroTelefono, delivery, url, webSiteUrl, prenotazione, descrizione/*, stelle*/);
    }

    public static HashMap<String, Ristorante> getRistoranti() {
        return ristoranti;
    }

    public static void setRistoranti(HashMap<String, Ristorante> ristoranti) {
        Ristorante.ristoranti = ristoranti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getLocazione() {
        return locazione;
    }

    public void setLocazione(String locazione) {
        this.locazione = locazione;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getCucina() {
        return cucina;
    }

    public void setCucina(String cucina) {
        this.cucina = cucina;
    }

    public float getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(float longitudine) {
        this.longitudine = longitudine;
    }

    public float getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(float latitudine) {
        this.latitudine = latitudine;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebSiteUrl() {
        return webSiteUrl;
    }

    public void setWebSiteUrl(String webSiteUrl) {
        this.webSiteUrl = webSiteUrl;
    }

    public boolean isPrenotazione() {
        return prenotazione;
    }

    public void setPrenotazione(boolean prenotazione) {
        this.prenotazione = prenotazione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /*public short getStelle() {
        return stelle;
    }

    public void setStelle(short stelle) {
        this.stelle = stelle;
    }*/

}