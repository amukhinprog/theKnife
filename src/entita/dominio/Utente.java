/**Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package entita.dominio;

import entita.Dominio;
import java.time.LocalDate;

/**
 * Entità raffigurante una persona generica
 * @author armuh
 */
public class Utente implements Dominio {

    protected String nome;
    protected String cognome;
    protected String username;
    protected String password;
    protected LocalDate dataNascita;
    protected String luogoDomicilio;
    protected String ruolo;

    public Utente(String nome, String cognome, String username, String password, LocalDate Data, String luogoDomicilio, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.dataNascita = Data;
        this.luogoDomicilio = luogoDomicilio;
        this.ruolo = ruolo;
    }

    public Utente() {
    }

    /**
     * 
     * @param obj
     * @return Boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Utente utente = (Utente) obj;
        return this.username.equals(utente.getUsername());
    }

    public String getLuogoDomicilio() {
        return luogoDomicilio;
    }

    public void setLuogoDomicilio(String luogoDomicilio) {
        this.luogoDomicilio = luogoDomicilio;
    }

    public String getNome() {
        return nome;
    }

    protected void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    protected void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    protected void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getLuogodomicilio() {
        return luogoDomicilio;
    }

    protected void setLuogodomicilio(String luogoDomicilio) {
        this.luogoDomicilio = luogoDomicilio;
    }

    public String getRuolo() {
        return ruolo;
    }

    protected void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

}
