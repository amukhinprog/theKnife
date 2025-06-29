package entita.dominio;

import java.time.LocalDate;

/**
 *
 * @author armuh
 */
public class Gestore extends Utente {

    public Gestore(String nome, String cognome, String username, String password, LocalDate data, String luogodomicilio, String ruolo) {
        super(nome, cognome, username, password, data, luogodomicilio, ruolo);
    }

}
