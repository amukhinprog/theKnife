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

    public Ristorante(String nome, String indirizzo, String locazione, float prezzo, String cucina, float longitudine, float latitudine, String numeroTelefono, boolean delivery, String url, String webSiteUrl, boolean prenotazione, String descrizione) {
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
    }

    

    public static void cercaRistorante() {
        int scelta = -1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Cerca ristorante per locazione");
        try {
            scelta = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        switch (scelta) {
            case 1:
                cercaRistorantePerLocazione();
                break;
            default:
                System.out.println("Input non valido, riprovare");
                break;
        }
    }

    private static void cercaRistorantePerLocazione() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserire il nome della citta': ");
        String locazione = scanner.next();
        List<List<String>> ristoranti = new ArrayList<>();
        int posizioneLocazioneInCsv = 2;
        List<List<String>> ristorantiCorrelati = new ArrayList<>();
        try {
            ristoranti = FileRistorante.letturaCsv(FileRistorante.getPercorsoFile());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        for (int i = 0; i < ristoranti.size(); i++) {
//            System.out.println(ristoranti.get(i).get(posizioneLocazioneInCsv));
            String locazioneRistorante = ristoranti.get(i).get(posizioneLocazioneInCsv).replace("\"", "");
            if (locazioneRistorante.toLowerCase().startsWith(locazione.toLowerCase())) {
                ristorantiCorrelati.add(ristoranti.get(i));
            }
        }
        visualizzaRistorante(ristorantiCorrelati);
    }

    private static void visualizzaRistorante(List<List<String>> ristoranti) {
//        System.out.println(ristoranti);
        int posizioneLocazioneInCsv = 2;
        int posizionePrezzoInCsv = 3;
        int posizioneTipoCucinaInCsv = 4;
        int posizioneDeliveryInCsv = 8;
        int posizionePrenotazioneInCsv = 11;
        for (List<String> ristorante : ristoranti) {
            System.out.println("Locazione: " + ristorante.get(posizioneLocazioneInCsv));
            System.out.println("Prezzo: " + ristorante.get(posizionePrezzoInCsv) + " euro");
            System.out.println("Tipo cucina: " + ristorante.get(posizioneTipoCucinaInCsv));
            System.out.println("Servizio delivery: " + ristorante.get(posizioneDeliveryInCsv));
            System.out.println("Servizio prenotazione: " + ristorante.get(posizionePrenotazioneInCsv) + "\n\n");
        }
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
    
}
