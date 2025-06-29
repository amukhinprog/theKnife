/**Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package repository.generico;

/**
 *
 * @author armuh
 */
public interface Service<K, V> {//CRUD

    public boolean add(V valore);

    public V get(K chiave);

    public boolean remove(K chiave, V elemento);

    public boolean put(K chiave, V valore);
}
