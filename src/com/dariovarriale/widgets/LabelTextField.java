package com.dariovarriale.widgets;

import com.dariovarriale.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * Rappresenta un elemento composto da una <code>JLabel</code> e una <code>JTextField</code>.
 *
 * @author Davrio Varriale - 145622
 * @version 1.0
 */
public class LabelTextField extends PannelloBorder {

    /**
     * Casella di testo.
     */
    private final JTextField textField;

    /**
     * Metodo costruttore con <code>ActionListener</code>, label e <code>KeyListener</code> custom.
     *
     * @param listener <code>ActionListener</code> per intercettare l'<code>invio</code> dell'utente ed effettuare la richiesta di login.
     * @param label Testo sopra la casella di testo.
     * @param keyListener <code>KeyListener</code> per catturare gli eventi tastiera dell'utente sulla casella.
     */
    public LabelTextField(ActionListener listener, String label, KeyListener keyListener){
        super();
        //Creo e imposto la label.
        Text labelUsername = new Text(label, Constants.fontLabel13);
        labelUsername.setBorder(Constants.compoundBottom5);
        //Creo e imposto la casella di testo.
        textField = new JTextField("", 25);
        textField.setActionCommand("login");
        textField.setBorder(Constants.empty10);
        textField.addActionListener(listener);
        textField.addKeyListener(keyListener);
        //Aggiungo la label e la casella di testo all'oggetto.
        add(labelUsername, BorderLayout.NORTH);
        add(textField, BorderLayout.SOUTH);
    }

    /**
     * Metodo costruttore con <code>ActionListener</code>, label, <code>KeyListener</code> e <code>actionCommand</code> custom.
     *
     * @param listener <code>ActionListener</code> per intercettare l'<code>invio</code> dell'utente.
     * @param label Testo sopra la casella di testo.
     * @param keyListener <code>KeyListener</code> per catturare gli eventi tastiera dell'utente sulla casella.
     * @param actionCommand Custom action command per l'<code>ActionListener</code>.
     */
    public LabelTextField(ActionListener listener, String label, KeyListener keyListener, String actionCommand){
        super();
        //Creo e imposto la label.
        Text labelUsername = new Text(label, Constants.fontLabel13);
        labelUsername.setBorder(Constants.compoundBottom5);
        //Creo e imposto la casella di testo.
        textField = new JTextField("", 25);
        textField.setActionCommand(actionCommand);
        textField.setBorder(Constants.empty10);
        textField.addActionListener(listener);
        textField.addKeyListener(keyListener);
        //Aggiungo la label e la casella di testo all'oggetto.
        add(labelUsername, BorderLayout.NORTH);
        add(textField, BorderLayout.SOUTH);
    }

    /**
     * Metodo costruttore con <code>ActionListener</code>, label, testo e <code>actionCommand</code> custom.
     *
     * @param listener <code>ActionListener</code> per intercettare l'<code>invio</code> dell'utente.
     * @param label Testo sopra la casella di testo.
     * @param text Testo da impostare nella casella.
     * @param actionCommand Custom action command per l'<code>ActionListener</code>.
     */
    public LabelTextField(ActionListener listener, String label, String text, String actionCommand){
        super();
        Text labelUsername = new Text(label, Constants.fontLabel13);
        labelUsername.setBorder(Constants.compoundBottom5);
        //Creo e imposto la casella di testo.
        textField = new JTextField(text, 25);
        textField.setActionCommand(actionCommand);
        textField.setBorder(Constants.empty10);
        textField.addActionListener(listener);
        //Aggiungo la label e la casella di testo all'oggetto.
        add(labelUsername, BorderLayout.NORTH);
        add(textField, BorderLayout.SOUTH);
    }

    /**
     * Ritorna il testo inserito nella casella.
     *
     * @return String
     */
    public String getTextField() {
        return textField.getText();
    }

    /**
     * Richiede il focus sulla casella di testo.
     */
    public void getFieldFocus(){
        textField.requestFocus();
    }

    /**
     * Abilita o disabilita la casella di testo.
     *
     * @param value Valore vero/falso per il pulsante.
     */
    public void setEnabledField(boolean value){
        textField.setEnabled(value);
    }

    /**
     * Imposta il testo all'interno della casella.
     *
     * @param text Stringa di testo da inserire nella casella.
     */
    public void setTextField(String text) {
        textField.setText(text);
    }



}
