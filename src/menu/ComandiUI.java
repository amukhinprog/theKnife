/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package menu;

import entita.Dominio;

/**
 * Interfaccia contenente i comandi che hanno lo scoppo di essere utilizzati
 * come interfaccia per i servizi
 *
 * @author armuh
 * @param <K>
 * @param <V>
 */
public interface ComandiUI<K extends Dominio, V> extends ComandiBaseUI<V> {

    public boolean add(K chiave);

    public V get(K chiave);

    public boolean remove(K chiave);

    public boolean put(K chiave);

    public void visualizza(K chiave);

}
