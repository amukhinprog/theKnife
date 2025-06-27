/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entita.associazioni;

import entita.Associazione;
import entita.dominio.Ristorante;
import gestioneFile.FileGestoreRistorante;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author armuh
 */
public class AssGestoreRistoranti implements Associazione {

    private String usernameRistoratore;
    private List<Ristorante> ristorantiList = new ArrayList<>();

    public AssGestoreRistoranti(String usernameRistoratore, List<Ristorante> ristorantiList) {
        this.usernameRistoratore = usernameRistoratore;
        this.ristorantiList = ristorantiList;
    }

    public boolean contains(Ristorante r) {
        for (Ristorante rPosseduti : ristorantiList) {
            if (rPosseduti.getNome().equals(r.getNome())) {
                return true;
            }
        }
        return false;
    }

    public List<Ristorante> getRistorantiList() {
        return ristorantiList;
    }

    public void setRistorantiList(List<Ristorante> ristorantiList) {
        this.ristorantiList = ristorantiList;
    }

    public void addRistorantiList(Ristorante ristorante) {
        this.ristorantiList.add(ristorante);
    }

    public String getUsernameRistoratore() {
        return usernameRistoratore;
    }

    public void setUsernameRistoratore(String usernameRistoratore) {
        this.usernameRistoratore = usernameRistoratore;
    }
}
