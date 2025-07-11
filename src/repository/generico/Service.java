/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package repository.generico;

/**
 * Interfaccia parametrizzata con tipi generici.Utile per realizzare un certo
 * tipo di servizio per ogni classe.Delega a queste classi la loro definizione
 *
 * @author armuh
 * @param <K>
 * @param <V>
 */
public interface Service<K, V> {

    /**
     * Comando base per aggiungere un valore
     *
     * @param valore
     * @return
     */
    public boolean add(V valore);

    /**
     * Comando base per ottenere un valore , data una chiave
     *
     * @param chiave
     * @return V
     */
    public V get(K chiave);

    /**
     * Comando base per rimuovere un elemento, specificando la chiave
     *
     * @param chiave
     * @param elemento
     * @return Boolean
     */
    public boolean remove(K chiave, V elemento);

    /**
     * Comando base per sostituire un elemento, per mezzo della chiave
     *
     * @param chiave
     * @param valore
     * @return Boolean
     */
    public boolean put(K chiave, V valore);
}
