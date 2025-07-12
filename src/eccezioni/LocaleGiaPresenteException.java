/**Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package eccezioni;

/**
 * Eccezione personalizzata lanciata quando si tenta di registrare un ristorante
 * che è già presente nel sistema.
 * @author filod
 */

public class LocaleGiaPresenteException extends RuntimeException {

/**
 * Costruisce una nuova eccezione con il messaggio di errore specificato.
 * @param messaggio Il messaggio che descrive l'errore.
 */
    public LocaleGiaPresenteException(String messaggio) {
        super(messaggio);
    }
}
