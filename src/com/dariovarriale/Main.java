package com.dariovarriale;

import com.dariovarriale.screens.LastLoggedUser;
import com.dariovarriale.screens.WelcomeScreen;
import com.dariovarriale.utils.Database;

/**
 * Classe <code>Main</code> del Gestore Spedizioni.
 *
 * @author Dario Varriale - 145622
 * @version 1.0
 */
public class Main{

    /**
     * Metodo Main. Viene generata una WelcomeScreen().
     *
     * @param args  Parametri da linea di comando.
     */
    public static void main(String[] args) {
        String lastLoggedUsername = Database.getLog();
        if(lastLoggedUsername != null && !lastLoggedUsername.equals("")){
            new LastLoggedUser(lastLoggedUsername);
            return;
        }
        new WelcomeScreen();
    }
}
