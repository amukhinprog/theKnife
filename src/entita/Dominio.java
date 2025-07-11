/**Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package entita;

/**
 * Interfaccia marker che rappresenta un'entità fondamentale del dominio.
 * Obbliga le classi che la implementano a fornire una logica personalizzata
 * per il metodo equals.
 * @author armuh
 */
public interface Dominio {
/**
 * Confronta questo oggetto con un altro per verificarne l'uguaglianza.
 * @param obj L'oggetto da confrontare.
 * @return true se gli oggetti sono logicamente uguali, false altrimenti.
 */
    @Override
    public abstract boolean equals(Object obj);
}
