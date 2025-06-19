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
