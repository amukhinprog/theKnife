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
