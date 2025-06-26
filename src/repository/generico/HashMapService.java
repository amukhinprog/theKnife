/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.generico;

import repository.generico.Service;
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
            throw new RuntimeException("Valore già presente");
        }
        map.put(chiave, valore);
        return true;
    }

    @Override
    public V get(K chiave) {
        return map.get(chiave);
    }

    @Override
    public V remove(K chiave) {
        return map.remove(chiave);
    }

    @Override
    public V put(V valore) {
        K chiave = getKey(valore);
        return map.put(chiave, valore);
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
