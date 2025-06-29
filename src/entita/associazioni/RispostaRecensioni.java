package entita.associazioni;

import java.time.LocalDate;

/**
 *
 * @author armuh
 */
public class RispostaRecensioni {
    private int ID;
    private int idRif;
    private String username;
    private String testo;
    private LocalDate data;

    public RispostaRecensioni(int ID, int idRif, String username, String testo, LocalDate data) {
        this.ID = ID;
        this.idRif = idRif;
        this.username = username;
        this.testo = testo;
        this.data = data;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdRif() {
        return idRif;
    }

    public void setIdRif(int idRif) {
        this.idRif = idRif;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
}
