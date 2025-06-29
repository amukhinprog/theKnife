package entita.associazioni;

/**
 *
 * @author Nikoro02
 */
import entita.Associazione;
import entita.Dominio;
import java.time.LocalDate;

public class Recensione implements Dominio, Associazione{

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

    public Recensione() {

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
    public void setTesto(String testo){
        this.testo = testo;
    }
}
