package entita;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author armuh
 */
public class Utente {

    protected String nome;
    protected String cognome;
    protected String username;
    protected String password;
    protected LocalDate dataNascita;
    protected String luogodomicilio;
    protected String ruolo;

    public Utente(String nome, String cognome, String username, String password, LocalDate Data, String luogodomicilio, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.dataNascita = Data;
        this.luogodomicilio = luogodomicilio;
        this.ruolo = ruolo;
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

    private String getLuogodomicilio() {
        return luogodomicilio;
    }

    protected void setLuogodomicilio(String luogodomicilio) {
        this.luogodomicilio = luogodomicilio;
    }

    public String getRuolo() {
        return ruolo;
    }

    protected void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

}
