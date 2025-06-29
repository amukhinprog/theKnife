/**Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package entita.associazioni;

import entita.Associazione;
import entita.dominio.Ristorante;
import java.util.ArrayList;
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
