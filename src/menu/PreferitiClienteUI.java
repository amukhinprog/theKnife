/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import entita.Ristorante;
import java.util.ArrayList;
import java.util.List;
import repository.PreferitiClienteService;

/**
 *
 * @author armuh
 */
public class PreferitiClienteUI {

    public List<Ristorante> visualizza(String username) {
        PreferitiClienteService PCS = new PreferitiClienteService();
        if (PCS.getPreferitiMap().containsKey(username)) {
            return PCS.getPreferitiMap().get(username).getRistorantiPreferiti();
        } else {
            return new ArrayList<>();
        }
    }
}
