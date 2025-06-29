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
