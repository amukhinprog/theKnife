/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entita;
/**
 *
 * @author Utente
 */
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
public class Recensione {
    private int ID;
    private short stelle;
    private String testo;
    private LocalDate data;
    List<String>recensioni= new ArrayList<>();
    Ristorante ristoranteRecensito;

    public Recensione(int ID, short stelle, String testo, LocalDate data, Ristorante ristoranteRecensito) {
        this.ID = ID;
        this.stelle = stelle;
        this.testo = testo;
        this.data = data;
        this.ristoranteRecensito=ristoranteRecensito;
    }
    public int getID(){
        return ID;}
    
    public short getstelle(){
      return stelle;}
    public String gettesto(){
        return testo;
    }
    public LocalDate getdata(){
        return data;
    }
    public Ristorante getristoranteRecensito(){
        return ristoranteRecensito;
    }
    
    public void setID(int ID){
        this.ID=ID;
    }
    public void setstelle(short stelle){
        this.stelle=stelle;
    }
    public void setdata(LocalDate data){
        this.data=data;
    }
    public void setristoranteRecensito(Ristorante ristoranteRecensito){
        this.ristoranteRecensito=ristoranteRecensito;
    }
    public void aggiungiRecensione(String testo,Utente utente){
      
        
        
    }
 
}
