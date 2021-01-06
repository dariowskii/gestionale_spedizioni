package com.dariovarriale.widgets;

import com.dariovarriale.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * Rappresenta un elemento composto da una <code>JLabel</code> e una <code>JPasswordField</code>.
 *
 * @author Davrio Varriale - 145622
 * @version 1.0
 */
public class LabelPasswordField extends PannelloBorder {

    /**
     * Casella di testo per la password.
     */
    private final JPasswordField password;

    /**
     * Metodo costruttore con <code>ActionListener</code>, label e <code>KeyListener</code> custom.
     *
     * @param listener <code>ActionListener</code> per intercettare l'<code>invio</code> dell'utente ed effettuare la richiesta di login.
     * @param label Testo sopra la casella di testo.
     * @param keyListener <code>KeyListener</code> per catturare gli eventi tastiera dell'utente sulla casella.
     */
    public LabelPasswordField(ActionListener listener, String label, KeyListener keyListener){
        super();
        //Creo e imposto la label.
        Text labePassword = new Text(label, Constants.fontLabel13);
        labePassword.setBorder(Constants.compoundBottom5);
        //Creo e imposto la casella password.
        password = new JPasswordField("", 25);
        password.setActionCommand("login");
        password.setBorder(Constants.empty10);
        password.addActionListener(listener);
        password.addKeyListener(keyListener);
        //Aggiungo i componenti e imposto l'oggetto.
        add(labePassword, BorderLayout.NORTH);
        add(password, BorderLayout.SOUTH);
        setBorder(Constants.emptyBottom10);
    }

    /**
     * Metodo costruttore con <code>ActionListener</code>, label, <code>KeyListener</code> e <code>actionCommand</code> custom.
     *
     * @param listener <code>ActionListener</code> per intercettare l'<code>invio</code> dell'utente.
     * @param label Testo sopra la casella di testo.
     * @param keyListener <code>KeyListener</code> per catturare gli eventi tastiera dell'utente sulla casella.
     * @param actionCommand Custom action command per l'<code>ActionListener</code>.
     */
    public LabelPasswordField(ActionListener listener, String label, KeyListener keyListener, String actionCommand){
        super();
        //Creo e imposto la label.
        Text labePassword = new Text(label, Constants.fontLabel13);
        labePassword.setBorder(Constants.compoundBottom5);
        //Creo e imposto la casella password.
        password = new JPasswordField("", 25);
        password.setActionCommand(actionCommand);
        password.setBorder(Constants.empty10);
        password.addActionListener(listener);
        password.addKeyListener(keyListener);
        //Aggiungo i componenti e imposto l'oggetto.
        add(labePassword, BorderLayout.NORTH);
        add(password, BorderLayout.SOUTH);
        setBorder(Constants.emptyBottom10);
    }

    /**
     * Ritorna la password inserita nella casella.
     *
     * @return String.
     */
    public String getPassword() {
        return String.valueOf(password.getPassword());
    }

    /**
     * Richiede il focus della casella password.
     */
    public void getPasswordFocus(){
        password.requestFocus();
    }

    /**
     * Imposta la password nella casella.
     *
     * @param text Testo da inserire nella casella password.
     */
    public void setPassword(String text) {
        password.setText(text);
    }
}
