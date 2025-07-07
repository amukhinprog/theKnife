/**Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package entita.dominio;

import java.time.LocalDate;

/**
 *
 * @author armuh
 */
public class Cliente extends Utente {
//eredita tutte le proprieta e metodi di utente essendo sottoclasse della superclasse Utente.
    public Cliente(String nome, String cognome, String username, String password, LocalDate Data, String luogodomicilio, String ruolo) {
        super(nome, cognome, username, password, Data, luogodomicilio, ruolo);
    }//viene usato super per richiamare il costruttore della superlcasse della sottoclasse
    

}
