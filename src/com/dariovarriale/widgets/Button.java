package com.dariovarriale.widgets;

import com.dariovarriale.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Rappresenta un oggetto <code>Button</code>: un <code>JButton</code> con un
 * <code>ActionListener</code> per intercettare il click dell'utente, del margine, il cursore
 * con la mano e l'<code>actionCommand</code>.
 *
 * @author Davrio Varriale - 145622
 * @version 1.0
 */
public class Button extends JButton {

    /**
     * Metodo costruttore con <code>ActionListener</code> e testo del bottone custom.
     *
     * @param listener <code>ActionListener</code> per intercettare il click dell'utente.
     * @param text Testo del bottone.
     */
    public Button(ActionListener listener, String text){
        super(text);
        addActionListener(listener);
        setActionCommand("login");
        setMargin(Constants.top10bottom10);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /**
     * Metodo costruttore con <code>ActionListener</code>, testo del bottone e <code>actionCommand</code> custom.
     *
     * @param listener <code>ActionListener</code> per intercettare il click dell'utente.
     * @param text Testo del bottone.
     * @param cmd Custom <code>actionCommand</code>.
     */
    public Button(ActionListener listener, String text, String cmd){
        super(text);
        addActionListener(listener);
        setActionCommand(cmd);
        setMargin(Constants.top10bottom10);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
