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
        locazione = locazione.toLowerCase();
        /////QKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK
        List<List<String>> ristoranti = new ArrayList<>();
        Ristorante[] ristorantiVett = (Ristorante[]) ristoranti.toArray();
        List<Ristorante> ristorantiList = new ArrayList<>();
        for (Ristorante ristorante : ristorantiVett) {
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
        }
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

}
