/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import entita.Recensione;
import entita.Utente;
import gestioneFile.FileRecensioni;
import java.util.HashMap;

/**
 *
 * @author Nikoro02
 */
public class GestoreService {
    private FileRecensioni fileRecensioni= new FileRecensioni();
    private HashMap<Integer, Recensione> recension = fileRecensioni.ottieniHashMap();

   public HashMap<Integer, Recensione> getRecensioni() {
        return recension;
    }
public void setRecensioni (HashMap<Integer, Recensione> recension) {
        this.recension = recension;
    }

}