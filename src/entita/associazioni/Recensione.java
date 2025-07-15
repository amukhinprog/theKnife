/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package entita.associazioni;

/**
 * Rappresenta una recensione scritta da un Utente per un Ristorante. Agisce
 * come classe di associazione, collegando un utente e un ristorante e
 * aggiungendo informazioni specifiche come le stelle e il testo.
 *
 * @author Nikoro02
 */
import entita.Associazione;
import entita.Dominio;
import entita.dominio.Ristorante;
import java.time.LocalDate;
import java.util.Objects;

public class Recensione implements Dominio, Associazione {

    private int ID;
    private String username;
    private short stelle;
    private String testo;
    private LocalDate data;
    String ristoranteRecensito;

    public Recensione(int ID, String username, short stelle, String testo, LocalDate data, String ristoranteRecensito) {
        this.ID = ID;
        this.username = username;
        this.stelle = stelle;
        this.testo = testo;
        this.data = data;
        this.ristoranteRecensito = ristoranteRecensito;
    }

    public Recensione(Recensione recensione) {
        this.ID = recensione.getID();
        this.username = recensione.getUsername();
        this.stelle = recensione.getStelle();
        this.testo = recensione.getTesto();
        this.data = recensione.getData();
        this.ristoranteRecensito = recensione.getRistoranteRecensito();
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Recensione recensione = (Recensione) obj;
        return this.ID == recensione.getID() && this.ristoranteRecensito.equals(recensione.ristoranteRecensito)
                && this.testo.equals(recensione.getTesto()) && this.username.equals(recensione.getUsername());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.ID;
        hash = 47 * hash + Objects.hashCode(this.username);
        hash = 47 * hash + Objects.hashCode(this.testo);
        hash = 47 * hash + Objects.hashCode(this.ristoranteRecensito);
        return hash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getID() {
        return ID;
    }

    public short getStelle() {
        return stelle;
    }

    public String getTesto() {
        return testo;
    }

    public LocalDate getData() {
        return data;
    }

    public String getRistoranteRecensito() {
        return ristoranteRecensito;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setStelle(short stelle) {
        this.stelle = stelle;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setRistoranteRecensito(String ristoranteRecensito) {
        this.ristoranteRecensito = ristoranteRecensito;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}
