/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package menu;

import entita.Dominio;
import repository.generico.Service;

/**
 *
 * @author armuh
 */
public interface ComandiUI<K extends Dominio, V> extends ComandiBaseUI<V>{//CRUD

    public boolean add(K chiave);

    public V get(K chiave);

    public V remove(K chiave);

    public V put(K chiave);
    
    public void visualizza(K chiave);

}
