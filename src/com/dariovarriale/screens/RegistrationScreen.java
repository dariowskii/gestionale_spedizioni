package com.dariovarriale.screens;

import com.dariovarriale.utils.*;
import com.dariovarriale.widgets.*;
import com.dariovarriale.widgets.Button;
import com.dariovarriale.widgets.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Pagina per la registrazione degli utenti all'interno del sistema.
 * <p>
 * In questa schermata l'utente fornisce username, indirizzo, la password e la conferma della password.
 *
 * @author Dario Varriale - 145622
 * @version 1.0
 */
public class RegistrationScreen extends JFrame implements ActionListener {
    /**
     * Username dell'utente.
     */
    private final LabelTextField username;
    /**
     * Indirizzo dell'utente.
     */
    private final LabelTextField indirizzo;
    /**
     * Password dell'utente.
     */
    private final LabelPasswordField password;
    /**
     * Conferma della password.
     */
    private final LabelPasswordField confPass;
    /**
     * Bottone della registrazione.
     */
    private final Button regisBtn;

    /**
     * Metodo costruttore.
     */
    public RegistrationScreen(){
        super("Gestionale spedizioni - Registrazione");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //Creo
        Text headerText = new Text("Gestionale Spedizioni", Constants.fontLabel26);
        Text subText = new Text("Registrati per usufruire del servizio.");
        regisBtn = new Button(this, "Registrati", "regis");
        BackButton backButton = new BackButton(this);

        //UI Setting
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBorder(Constants.compoundBottom5);
        regisBtn.setEnabled(false);

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
                regisBtn.setEnabled(!username.getTextField().isBlank() && !indirizzo.getTextField().isBlank() && !password.getPassword().isBlank() && !confPass.getPassword().isBlank());
            }
        };

        //Pannelli
        PannelloFlow pannelloBack = new PannelloFlow(FlowLayout.LEFT);
        PannelloBorder pannelloLogo = new PannelloBorder();
        username = new LabelTextField(this,"Username", onPressKey, "regis");
        indirizzo = new LabelTextField(this, "Indirizzo", onPressKey, "regis");
        password = new LabelPasswordField(this, "Password", onPressKey, "regis");
        confPass = new LabelPasswordField(this, "Conferma password", onPressKey, "regis");
        PannelloBorder pannelloBtn = new PannelloBorder();

        pannelloBack.add(backButton);
        pannelloBack.setBorder(Constants.emptyBottom10);

        username.setBorder(Constants.emptyBottom10);
        indirizzo.setBorder(Constants.emptyBottom10);
        password.setBorder(Constants.emptyBottom10);
        confPass.setBorder(Constants.emptyBottom20);

        pannelloLogo.add(headerText, BorderLayout.NORTH);
        pannelloLogo.add(subText, BorderLayout.SOUTH);
        pannelloLogo.setBorder(Constants.emptyBottom20);

        pannelloBtn.add(regisBtn);

        //Container
        Container contentView = new Container();

        contentView.add(pannelloBack);
        contentView.add(pannelloLogo);
        contentView.add(username);
        contentView.add(indirizzo);
        contentView.add(password);
        contentView.add(confPass);
        contentView.add(pannelloBtn);

        this.add(contentView);
        pack();
        setSize(700, getHeight());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Metodo per catturare la richiesta di registrazione dall'utente.
     *
     * @param e <code>ActionEvent</code> del bottone.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if(cmd.equals("regis")){
            String inputUsername = username.getTextField().trim();
            String inputIndirizzo = indirizzo.getTextField();
            String inputPassword = String.valueOf(password.getPassword());
            String confPassword = String.valueOf(confPass.getPassword());
            //Controllo che non ci siano campi vuoti
            if(inputUsername.isBlank() || inputIndirizzo.isBlank() || inputPassword.isBlank() || confPassword.isBlank()){
                password.setPassword("");
                confPass.setPassword("");
                JOptionPane.showMessageDialog(this, "Attenzione, devi riempire correttamente tutti i campi!", "Attenzione", JOptionPane.WARNING_MESSAGE);
                return;
            }
            //Controllo che la password sia confermata
            if(!inputPassword.equals(confPassword)){
                password.setPassword("");
                confPass.setPassword("");
                password.getPasswordFocus();
                JOptionPane.showMessageDialog(this, "Attenzione, la password deve essere la stessa!", "Password errata", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Controllo che non ci sia un'utente registrato con lo stesso username
            if(Database.checkUsername(inputUsername)){
                username.setTextField("");
                password.setPassword("");
                confPass.setPassword("");
                username.getFieldFocus();
                JOptionPane.showMessageDialog(this, "Username già esistente!", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //In caso passi tutti i controlli, inserisce l'utente nel sistema
            if(Database.insertUser(new User(inputUsername, inputPassword, inputIndirizzo))){
                JOptionPane.showMessageDialog(this, "Benvenuto, " + inputUsername + "!", "Accesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new UserDashboard(inputUsername);
            } else {
                password.setPassword("");
                confPass.setPassword("");
                password.getPasswordFocus();
                JOptionPane.showMessageDialog(this, "Errore nella registrazione:\nDatabase.insertUser() = false", "Accesso", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
