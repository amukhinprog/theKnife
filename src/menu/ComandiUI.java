/**Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package menu;

import entita.Dominio;

/**
 *
 * @author armuh
 */
public interface ComandiUI<K extends Dominio, V> extends ComandiBaseUI<V>{

    public boolean add(K chiave);

    public V get(K chiave);

    public boolean remove(K chiave);

    public boolean put(K chiave);
    
    public void visualizza(K chiave);

}
