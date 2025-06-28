/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package menu;

import entita.Dominio;

/**
 *
 * @author armuh
 */
public interface ComandiUI<K extends Dominio, V> extends ComandiBaseUI<V>{

    public boolean add(K chiave);

    public V get(K chiave);

    public boolean remove(K chiave);

    public boolean put(K chiave);
    
    public void visualizza(K chiave);

}
