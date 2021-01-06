package com.dariovarriale.utils;

import com.dariovarriale.widgets.Button;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

/**
 * Rappresenta il <code>Thread</code> per cambiare gli stati delle spedizioni nell'area amministratore.
 *
 * @author Dario Varriale - 145622
 * @version 1.0
 */
public class ThreadSpedizione extends Thread{

    /**
     * Metodo costruttore del <code>Thread</code>.
     *
     * @param spedizioni Spedizioni da modificare.
     * @param table Tabella di riferimento per aggiornare la UI.
     * @param threadBtn Bottone di riferimento per avviare/fermare il thread.
     * @param sleepTime Valore in millisecondi per lo sleep del thread
     */
    public ThreadSpedizione(Vector<Spedizione> spedizioni, JTable table, Button threadBtn, int sleepTime){
        super(new Runnable() {
            @Override
            public void run() {
                System.out.println("Inizio le spedizioni...");
                //Inizio le spedizioni.
                int lengthSpedizioni = spedizioni.size();
                //Flag per uscire dal loop.
                int exit = 0;
                //Array degli indici del Vector<Spedizione> spedizioni
                ArrayList<Integer> myIndex = new ArrayList<>();
                for (int i = 0; i < lengthSpedizioni; i++) myIndex.add(i);
                //Lo mischio per ottenere un effetto random.
                Collections.shuffle(myIndex);
                //Incomincio il loop.
                while (exit < lengthSpedizioni) {
                    //Ciclo tutte le spedizioni almeno una volta.
                    for (int i = 0; i < lengthSpedizioni; i++) {
                        Spedizione temp = spedizioni.elementAt(myIndex.get(i));
                        if (temp.getStato().equals("FALLITA") || temp.getStato().equals("RICEVUTA") || temp.getStato().equals("RIMBORSO EROGATO")) {
                            exit++;
                            continue;
                        }
                        //Addormento il thread
                        try {
                            sleep(sleepTime);
                        } catch (InterruptedException e) {
                            return;
                        }
                        //Aggiorno lo stato e lo rimpiazzio nel Vector.
                        temp.updateStato();
                        spedizioni.set(myIndex.get(i), temp);
                        //Aggiorno la UI della tabella.
                        table.updateUI();
                    }
                }
                //Concludo le spedizioni.
                System.out.println("Spedizioni concluse!");
                //Resetto lo stato del bottone.
                threadBtn.setText("Spedizioni concluse");
                threadBtn.setEnabled(false);
            }
        });
    }
}
