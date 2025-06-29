package entita.associazioni;

import entita.Associazione;
import entita.dominio.Ristorante;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author filod
 */
public class PreferitiCliente  implements Associazione{
    
    private String usernameCliente;
    private List<Ristorante> ristorantiPreferiti = new ArrayList<>();
    
    public PreferitiCliente(String usernameCliente, List<Ristorante> ristorantiPreferiti){
        
        this.usernameCliente = usernameCliente;
        this.ristorantiPreferiti = ristorantiPreferiti;
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PreferitiCliente preferitiCliente = (PreferitiCliente) obj;
        if (this.usernameCliente.equals(preferitiCliente.getUsernameCliente())) {
            return ristorantiPreferiti.containsAll(preferitiCliente.getRistorantiPreferiti());
        } else {
            return false;
        }
    }
    public List<Ristorante> getRistorantiPreferiti() {
        return ristorantiPreferiti;
    }
        
    public void setRistorantiPreferiti(List<Ristorante> ristorantiPreferiti) {
        this.ristorantiPreferiti = ristorantiPreferiti;
    }
    
    public void addRistorantePreferito(Ristorante ristorante) {
        this.ristorantiPreferiti.add(ristorante);
    }
    
    public String getUsernameCliente() {
        return usernameCliente;
    }

    public void setUsernameCliente(String usernameCliente) {
        this.usernameCliente = usernameCliente;
    }
    
}