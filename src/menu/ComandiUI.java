/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package menu;

import repository.generico.Service;

/**
 *
 * @author armuh
 */
public interface ComandiUI<V> {//CRUD

    public boolean add(V valore);

    public V get(V valore);

    public V remove(V valore);

    public V put(V valore);

    public void visualizza();

    public void visualizza(V valore);

}
