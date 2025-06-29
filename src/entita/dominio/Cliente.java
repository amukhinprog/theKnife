package entita.dominio;

import java.time.LocalDate;

/**
 *
 * @author armuh
 */
public class Cliente extends Utente {

    public Cliente(String nome, String cognome, String username, String password, LocalDate Data, String luogodomicilio, String ruolo) {
        super(nome, cognome, username, password, Data, luogodomicilio, ruolo);
    }

}
