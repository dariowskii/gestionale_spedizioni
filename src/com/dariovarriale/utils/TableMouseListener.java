package com.dariovarriale.utils;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Rappresenta il <code>MouseListener</code> per intercettare il click destro
 * dell'utente per cancellare le spedizioni una volta ultimate.
 *
 * @author Dario Varriale - 145622
 * @version 1.0
 */
public class TableMouseListener extends MouseAdapter {

    /**
     * Tabella di riferimento.
     */
    private JTable table;
    /**
     * Vettore delle spedizioni di riferimento.
     */
    private Vector<Spedizione> spedizioni;

    /**
     * Metodo costruttore.
     *
     * @param table Tabella di riferimento.
     * @param spedizioni Vettore delle spedizioni di riferimento.
     */
    public TableMouseListener(JTable table, Vector<Spedizione> spedizioni) {
        this.table = table;
        this.spedizioni = spedizioni;
    }

    /**
     * Metodo per intercettare il click destro sulla riga selezionata della <code>JTable</code>.
     * @param e <code>MouseEvent</code>
     */
    @Override
    public void mousePressed(MouseEvent e) {
        spedizioni.remove(table.getSelectedRow());
    }
}
