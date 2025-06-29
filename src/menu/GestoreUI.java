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
