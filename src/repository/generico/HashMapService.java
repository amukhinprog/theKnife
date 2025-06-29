package repository.generico;

import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author armuh
 */
public abstract class HashMapService<K, V> implements Service<K, V> {

    protected HashMap<K, V> map = lettura();

    @Override
    public boolean add(V valore) {
        K chiave = getKey(valore);
        if (map.containsKey(chiave)) {
            return false;
        }
        map.put(chiave, valore);
        scrittura(valore);
        return true;
    }

    @Override
    public V get(K chiave) {
        return map.get(chiave);
    }

    @Override
    public boolean remove(K chiave, V elemento) {
        boolean b = map.remove(chiave, elemento);
        sovrascrittura(map);
        return b;
    }

    @Override
    public boolean put(K chiave, V valore) {
        V v;
        if (map.get(chiave).equals(valore)) {
            v = valore;
        } else {
            map.put(chiave, valore);
            v = null;
            sovrascrittura(map);
        }
        return v != null;
    }

    public void set(HashMap<K, V> map) {
        this.map = map;
    }

    public HashMap<K, V> get() {
        return this.map;
    }

    public boolean containsKey(K chiave) {
        return map.containsKey(chiave);
    }

    public Collection<V> values() {
        return map.values();
    }

    protected abstract K getKey(V valore);

    protected abstract HashMap<K, V> lettura();

    protected abstract void scrittura(V valore);

    protected abstract void sovrascrittura(HashMap<K, V> map);
}
