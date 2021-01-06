package com.dariovarriale.screens;

import com.dariovarriale.utils.Constants;
import com.dariovarriale.utils.Database;
import com.dariovarriale.widgets.*;
import com.dariovarriale.widgets.Button;
import com.dariovarriale.widgets.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Schermata iniziale per l'accesso diretto dell'ultimo utente loggato.
 *
 * @author Dario Varriale - 145622
 * @version 1.0
 */
public class LastLoggedUser extends JFrame implements ActionListener {

    /**
     * Password dell'utente.
     */
    private final LabelPasswordField passwordField;
    /**
     * Username dell'ultimo utente loggato.
     */
    private final String username;

    /**
     * Metodo costruttore.
     *
     * @param username Username dell'ultimo utente loggato.
     */
    public LastLoggedUser(String username){
        super("Accesso diretto per " + username);
        this.username = username;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //Creo
        Text bentornato = new Text("Bentornato, " + username, Constants.fontLabel16);
        Button submitBtn = new Button(this, "Accedi");
        Button cambiaAccount = new Button(this, "Cambia account", "cambiaAccount");
        Text messaggio = new Text("Inserisci la password per accedere direttamente alla tua dashboard.");

        //UI Setting
        bentornato.setHorizontalAlignment(SwingConstants.CENTER);
        bentornato.setBorder(Constants.emptyBottom5);
        messaggio.setHorizontalAlignment(SwingConstants.CENTER);

        //ActionListener
        KeyListener onPressKey = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                submitBtn.setEnabled(!passwordField.getPassword().isBlank());
            }
        };

        //Pannelli
        passwordField = new LabelPasswordField(this,"Password", onPressKey);
        passwordField.setBorder(Constants.emptyBottom10);

        PannelloBorder benvenutoPanel = new PannelloBorder();
        PannelloBorder submitPanel = new PannelloBorder();
        PannelloBorder pannelloCambia = new PannelloBorder();

        benvenutoPanel.add(bentornato, BorderLayout.NORTH);
        benvenutoPanel.add(messaggio, BorderLayout.SOUTH);
        benvenutoPanel.setBorder(Constants.emptyBottom20);

        submitPanel.add(submitBtn);

        pannelloCambia.add(cambiaAccount);

        Container container = new Container();

        container.add(benvenutoPanel);
        container.add(passwordField);
        container.add(submitPanel);
        container.add(pannelloCambia);

        this.add(container);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Metodo per catturare la richiesta di login da parte dell'utente o per cambiare account.
     *
     * @param e <code>ActionListener</code> del bottone
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd){
            case "login":
                if(Database.getAccess(username, passwordField.getPassword())){
                    dispose();
                    new UserDashboard(username);
                } else {
                    JOptionPane.showMessageDialog(this,"Credenziali errate, reindirizzamento alla schermata di benvenuto.", "Errore", JOptionPane.ERROR_MESSAGE);
                    Database.saveLog("");
                    dispose();
                    new WelcomeScreen();
                }
                break;
            case "cambiaAccount":
                Database.saveLog("");
                dispose();
                new WelcomeScreen();
                break;
        }
    }
}
