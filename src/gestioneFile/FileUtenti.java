/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioneFile;

/**
 *
 * @author armuh
 */
public class FileUtenti extends GestioneFile{
    private static String percorsoFile = "..\\theKnife\\data\\utenti.csv";

    public static String getPercorsoFile() {
        return percorsoFile;
    }

    private static void setPercorsoFile(String percorsoFile) {
        FileUtenti.percorsoFile = percorsoFile;
    }
    
}
