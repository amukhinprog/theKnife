/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package entita.dominio;

import entita.Dominio;

/**
 * Entità rappresentante un ristorante del mondo
 *
 * @author armuh
 */
public class Ristorante implements Dominio {

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
    }//il costruttore privato assicura che gli oggetti Ristorante vengano creati solo attraverso il metodo ristoranteVuoto()

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Ristorante ristorante = (Ristorante) obj;
        if (this.nome.equals(ristorante.getNome())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
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
