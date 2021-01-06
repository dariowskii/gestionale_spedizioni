package com.dariovarriale.utils;

import com.dariovarriale.screens.Dashboard;
import java.io.*;

/**
 * Rappresenta la classe che gestisce l'input/output del software, simulando un <code>Database</code>.
 *
 * @author Dario Varriale - 145622
 * @version 1.0
 */
public abstract class Database {

    /**
     * Metodo per inserire un'utente all'interno del sistema.
     *
     * @param user <code>User</code> da inserire nel sistema.
     * @return boolean
     */
    public static boolean insertUser(User user){
        File out;
        FileWriter fw;
        //Creo/Riapro il file degli utenti
        try {
            new File("com/dariovarriale/data").mkdir();
            out = new File("com/dariovarriale/data/user.txt");
            if(!out.exists()){
                out.createNewFile();
            }
            fw = new FileWriter(out, true);
        } catch(IOException e) {
            System.err.println("Errore nella creazione del file");
            e.printStackTrace();
            return false;
        }
        //Provo a scrive sul file e a salvarlo
        try {
            fw.write(user.toString());
            fw.write("\n");
            fw.close();
        } catch(IOException e) {
            System.err.println("Errore nella srittura su file");
            e.printStackTrace();
            return false;
        }
        //Se passa tutti i controlli ritorna vero
        return true;
    }

    /**
     * Metodo per ottenere l'accesso nella schermata di login.
     *
     * @param username Username dell'utente.
     * @param password Password dell'utente.
     * @return boolean
     */
    public static boolean getAccess(String username, String password){
        InputStreamReader in;
        BufferedReader b;
        File f;
        //Provo a leggere nel file degli utenti
        try{
            f = new File("com/dariovarriale/data/user.txt");
            if(!f.exists()) return false;

            in = new InputStreamReader(new FileInputStream(f));
            b = new BufferedReader(in);

            String s;

            do {
                s = b.readLine();
                if(s != null){
                    String[] buffer = s.split(":");
                    if((buffer[0] + ":" + buffer[1]).equals((username + ":" + password)))
                    return true;
                }
            } while (b.ready());

        } catch(IOException e){
            System.err.println("Errore nell'apertura del file");
            e.printStackTrace();
            return false;
        }
        //In caso di errori ritorna negativamente
        return false;
    }

    /**
     * Controlla se all'interno del sistema esiste gi&agrave; un utente con lo stesso username.
     *
     * @param username Username da mettere a confronto.
     * @return boolean
     */
    public static boolean checkUsername(String username){
        File f;
        InputStreamReader in;
        BufferedReader b;
        //Provo a leggere dal file degli utenti
        try{
            f = new File("com/dariovarriale/data/user.txt");
            if(!f.exists()) return false;

            in = new InputStreamReader(new FileInputStream(f));
            b = new BufferedReader(in);

            String s;
            String[] buffer;

            do {
                s = b.readLine();
                if(s == null) return false;

                buffer = s.split(":");
                if(buffer[0].equals(username)){
                    return true;
                }
            } while (b.ready());

        } catch(IOException e){
            System.err.println("Errore nell'apertura del file");
            e.printStackTrace();
            return false;
        }
        //In caso di errori ritorna negativamente
        return false;
    }

    /**
     * Salva le spedizioni all'interno del sistema.
     *
     * @param dashboard Dashboard di riferimento da cui estrarre le spedizioni.
     * @return boolean
     */
    public static boolean saveSpedizioni(Dashboard dashboard){
        File out;
        FileWriter fw;
        //Provo a scrivere il file delle spedizioni
        try {
            out = new File("com/dariovarriale/data/spedizioni.txt");
            fw = new FileWriter(out);
        } catch(IOException e) {
            System.err.println("Errore nella creazione del file");
            e.printStackTrace();
            return false;
        }

        try {
            StringBuilder s = new StringBuilder();
            for(Spedizione sped : dashboard.getSpedizioni()) s.append(sped.toString());

            fw.write(s.toString());
            fw.close();
        } catch(IOException e) {
            System.err.println("Errore nella srittura su file");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Controlla se c'è un utente che non ha effettuato il logout, in modo da permettergli di fare un
     * accesso veloce alla dashboard. In caso positivo ritorna lo username dell'utente.
     *
     * @return String
     */
    public static String getLog(){
        InputStreamReader in;
        BufferedReader b;
        File f;
        //Provo a leggere l'ultimo utente loggato
        try{
            f = new File("com/dariovarriale/data/lastLogged.txt");
            if(!f.exists()) return null;

            in = new InputStreamReader(new FileInputStream(f));
            b = new BufferedReader(in);

            String s = b.readLine();

            if(s != null){
                return s;
            }

        } catch(IOException e){
            System.err.println("Errore nell'apertura del file");
            e.printStackTrace();
            return null;
        }
        //In caso di errori ritorna null
        return null;
    }

    /**
     * Salva lo username dell'ultimo utente che non ha effettuato il logout.
     *
     * @param username Username dell'utente da salvare.
     * @return boolean
     */
    public static boolean saveLog(String username){
        File out;
        FileWriter fw;
        //Provo a scrivere l'utente che non ha effettuato il logout
        try {
            out = new File("com/dariovarriale/data/lastLogged.txt");
            fw = new FileWriter(out);
        } catch(IOException e) {
            System.err.println("Errore nella creazione del file");
            e.printStackTrace();
            return false;
        }
        try {
            fw.write(username);
            fw.close();
        } catch(IOException e) {
            System.err.println("Errore nella srittura su file");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
