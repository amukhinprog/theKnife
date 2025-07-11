/**Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package repository.generico;

import java.util.Collection;
import java.util.HashMap;

/**
 * La classe astratta definisce i metodi dell'interfaccia Service.
 * E' una classe fondamentale poichè la gestione delle 4 operazioni
 * principali è definita in questa classe.
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
        map = lettura();
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
/**
 * Specifica quale chiave ottenere da ogni oggetti. Ogni oggetto ha una chiave diversa
 * per questo bisogna implementare diversamente il metodo.
 * @param valore
 * @return K
 */
    protected abstract K getKey(V valore);
/**
 * Specifica da quale file bisogna leggere le informazioni
 * @return HashMap<K, V>
 */
    protected abstract HashMap<K, V> lettura();
/**
 * Specifica da quale file bisogna scrivere le informazioni
 * @param valore 
 */
    protected abstract void scrittura(V valore);
/**
 * Specifica da quale file bisogna scrivere da capo le informazioni
 * @param map 
 */
    protected abstract void sovrascrittura(HashMap<K, V> map);
}
