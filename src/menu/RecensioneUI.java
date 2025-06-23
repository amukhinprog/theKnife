/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import java.util.Scanner;
import repository.RecensioneService;

/**
 *
 * @author armuh
 */
public class RecensioneUI {
    Scanner scanner;
    RecensioneService RS;
    public RecensioneUI(Scanner scanner, RecensioneService RS){
        this.scanner = scanner;
        this.RS = RS;
    }
}
