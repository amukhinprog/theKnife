/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.generico;

/**
 *
 * @author armuh
 */
public interface Service<K, V> {//CRUD

    public boolean add(V valore);

    public V get(K chiave);

    public V remove(K chiave);

    public V put(V valore);
}
