/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package entita.associazioni;

import entita.Associazione;

/**
 *
 * @author armuh
 */
public class AssGestoreRistoranti implements Associazione {

    private String usernameRistoratore;
    private String ristorantePosseduto;

    public AssGestoreRistoranti(String usernameRistoratore, String ristorantePosseduto) {
        this.usernameRistoratore = usernameRistoratore;
        this.ristorantePosseduto = ristorantePosseduto;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AssGestoreRistoranti assGestoreRistoranti = (AssGestoreRistoranti) obj;
        return this.usernameRistoratore.equals(assGestoreRistoranti.getUsernameRistoratore())
                && this.ristorantePosseduto.equals(assGestoreRistoranti.getRistorantePosseduto());
    }

    public String getRistorantePosseduto() {
        return ristorantePosseduto;
    }

    public void setRistorantePosseduto(String ristorantePosseduto) {
        this.ristorantePosseduto = ristorantePosseduto;
    }

    public String getUsernameRistoratore() {
        return usernameRistoratore;
    }

    public void setUsernameRistoratore(String usernameRistoratore) {
        this.usernameRistoratore = usernameRistoratore;
    }
}
