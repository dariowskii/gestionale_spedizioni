package com.dariovarriale.widgets;

import com.dariovarriale.screens.WelcomeScreen;
import com.dariovarriale.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Rappresenta il bottone per tornare alla <code>WelcomeScreen</code>.
 *
 * @author Davrio Varriale - 145622
 * @version 1.0
 */
public class BackButton extends JLabel {

    /**
     * Metodo costruttore del bottone.
     *
     * @param parent Custom <code>JFrame</code> parente.
     */
    public BackButton(JFrame parent){
        super("Indietro");
        setIcon(Constants.arrowLeft);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //Aggiungo un mouse listener per intercettare il click dell'utente.
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parent.dispose();
                new WelcomeScreen();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
