/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package menu;

/**
 *
 * @author armuh
 * @param <V>
 */
public interface ComandiBaseUI<V> {

    /**
     * Il metodo permette di visualizzare a video le entità
     *
     */
    public void visualizza();

    /**
     * Il metodo permette di visualizzare a video un particolare valore
     * specificato come argomento
     *
     * @param valore
     */
    public void visualizza(V valore);
}
