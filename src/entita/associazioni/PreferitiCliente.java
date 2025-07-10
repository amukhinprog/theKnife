/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package entita.associazioni;

import entita.Associazione;

/**
 *
 * @author filod
 */
public class PreferitiCliente implements Associazione {

    private String usernameCliente;
    private String ristorantePreferito;

    public PreferitiCliente(String usernameCliente, String ristorantePreferito) {
        this.usernameCliente = usernameCliente;
        this.ristorantePreferito = ristorantePreferito;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PreferitiCliente preferitiCliente = (PreferitiCliente) obj;
        return this.usernameCliente.equals(preferitiCliente.getUsernameCliente())
                && this.ristorantePreferito.equals(preferitiCliente.getRistorantePreferito());
    }

    public String getUsernameCliente() {
        return usernameCliente;
    }

    public void setUsernameCliente(String usernameCliente) {
        this.usernameCliente = usernameCliente;
    }

    public String getRistorantePreferito() {
        return ristorantePreferito;
    }

    public void setRistorantePreferito(String ristorantePreferito) {
        this.ristorantePreferito = ristorantePreferito;
    }

}
