/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
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
