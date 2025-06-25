/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
