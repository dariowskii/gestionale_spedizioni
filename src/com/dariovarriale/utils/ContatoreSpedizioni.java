package com.dariovarriale.utils;

import java.io.*;

/**
 * Rappresenta il <strong>contatore globale</strong> delle spedizioni.
 * <p>
 *    Ogni volta che una spedizione viene aggiunta dall'utente il contatore viene icrementato.
 * </p>
 *
 * @author Dario Varriale - 145622
 * @version 1.0
 */
public class ContatoreSpedizioni {

    /**
     * Valore del contatore.
     */
    private int num;

    /**
     * Metodo costruttore.
     */
    public ContatoreSpedizioni(){
        initContatore();
    }

    /**
     * Metodo di inizializzazione del contatore. Controlla se esiste gi&agrave;
     * un valore in memoria e lo assegna al contatore.<br>
     * <strong>In caso negativo il contatore parte da 0.</strong>
     */
    private void initContatore(){
        try{
            FileInputStream out = new FileInputStream("com/dariovarriale/data/contatoreSpedizioni.dat");
            DataInputStream is = new DataInputStream(out);
            this.num = is.readInt();
        } catch (FileNotFoundException e) {
            this.num = 0;
        } catch (IOException e) {
            System.err.println("Errore readInt():");
            e.printStackTrace();
        }
    }

    /**
     * Incrementa il contatore.
     */
    public void inc(){
        this.num++;
    }

    /**
     * Ritorna il valore del contatore.
     *
     * @return int
     */
    public int getNum(){
        return this.num;
    }

    /**
     * Salva il contatore all'interno del sistema.
     */
    public void saveCont(){
        try{
            FileOutputStream out = new FileOutputStream("com/dariovarriale/data/contatoreSpedizioni.dat");
            DataOutputStream os = new DataOutputStream(out);
            os.writeInt(this.num);
        } catch (FileNotFoundException e) {
            System.err.println("Errore FileOutputStream():");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Errore:");
            e.printStackTrace();
        }
    }
}