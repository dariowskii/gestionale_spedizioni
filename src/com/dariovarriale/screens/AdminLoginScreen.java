package com.dariovarriale.screens;

import com.dariovarriale.utils.Constants;
import com.dariovarriale.widgets.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Schermata di login riservata esclusivamente all'amministratore.
 *
 * @author Dario Varriale - 145622
 * @version 1.0
 */
public class AdminLoginScreen extends LoginScreen {

    /**
     * Metodo costruttore.
     *
     * @param titoloScreen Titolo del <code>JFrame</code>.
     */
    public AdminLoginScreen(String titoloScreen){
        super(titoloScreen);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //Creo
        Text subText = new Text("Pannello Amministrativo.");
        PannelloBorder pannelloLogo = getPannelloLogo();

        //UI setting
        subText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setBorder(Constants.compoundBottom20);

        //Pannelli
        pannelloLogo.add(subText, BorderLayout.SOUTH);

        pack();
        setSize(700, getHeight());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Metodo per intercettare la richiesta di login dall'amministratore.
     *
     * @param e <code>ActionEvent</code> del bottone.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String inputUsername = getUsername();
        String inputPassword = String.valueOf(getPassword());

        if(cmd.equals("login")){
            //Controllo che non ci siano campi vuoti
            if(inputUsername.isBlank() || inputPassword.isBlank() ){
                setUsername("");
                setPassword("");
                getUsernameFocus();
                JOptionPane.showMessageDialog(this, "Attenzione, devi riempire correttamente tutti i campi!", "Attenzione", JOptionPane.WARNING_MESSAGE);
                return;
            }

            final String adminUsername = "admin";
            final String adminPassword = "password";
            //Cerco il riscontro con le credenziali
            if(inputUsername.equals(adminUsername) && inputPassword.equals(adminPassword)){
                setUsername("");
                setPassword("");
                JOptionPane.showMessageDialog(this, "Bentornato " + adminUsername + ", ti stavo aspettando!", "Accesso consentito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new AdminDashboard();
            } else {
                setUsername("");
                setPassword("");
                getUsernameFocus();
                JOptionPane.showMessageDialog(this, "Errore, username o password non validi!", "Credenziali errate", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
