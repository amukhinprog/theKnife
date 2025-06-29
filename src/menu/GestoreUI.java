/**Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package menu;

import java.util.Scanner;
import repository.GestoreService;

/**
 *
 * @author armuh
 */
public class GestoreUI {
    GestoreService gestoreServ;
    Scanner scanner;
    public GestoreUI(Scanner scanner, GestoreService gestorServ) {
        this.scanner = scanner;
        this.gestoreServ = gestorServ;
    }
    
}
