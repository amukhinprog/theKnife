/**Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package menu;

/**
 *
 * @author armuh
 */
public interface ComandiUISenzaParametri<V> extends ComandiBaseUI<V> {

    public V add();

    public V get();

    public V remove();

    public V put();
}
