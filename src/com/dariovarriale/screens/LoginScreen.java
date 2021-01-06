package com.dariovarriale.screens;

import com.dariovarriale.utils.*;
import com.dariovarriale.widgets.*;
import com.dariovarriale.widgets.Button;
import com.dariovarriale.widgets.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Modello astratto per una schermata di login.
 * <p>
 * In questa schermata l'utente fornisce username e password per accedere alla <code>Dashboard</code>.
 *
 * @author Dario Varriale - 145622
 * @version 1.0
 */
public abstract class LoginScreen extends JFrame implements ActionListener {

    /**
     * Username dell'utente.
     */
    private final LabelTextField username;
    /**
     * Password dell'utente.
     */
    private final LabelPasswordField password;
    /**
     * Bottone per mandare la richiesta di login.
     */
    private final Button submitBtn;
    /**
     * Pannello del logo.
     */
    private final PannelloBorder pannelloLogo;

    /**
     * Metodo costruttore.
     *
     * @param titoloScreen Titolo del <code>JFrame</code>.
     */
    public LoginScreen(String titoloScreen){
        super(titoloScreen);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //Creo
        Text headerText = new Text("Gestionale Spedizioni", Constants.fontLabel26);
        submitBtn = new Button(this,"Accedi");
        BackButton backButton = new BackButton(this);

        //UI Setting
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBorder(Constants.compoundBottom5);
        submitBtn.setEnabled(false);

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
                submitBtn.setEnabled(!username.getTextField().isBlank() && !password.getPassword().isBlank());
            }
        };

        //Pannelli
        PannelloFlow pannelloBack = new PannelloFlow(FlowLayout.LEFT);
        pannelloLogo = new PannelloBorder();
        username = new LabelTextField(this, "Username", onPressKey);
        password = new LabelPasswordField(this, "Password", onPressKey);
        PannelloBorder pannelloBtn = new PannelloBorder();

        pannelloBack.add(backButton);
        pannelloBack.setBorder(Constants.emptyBottom10);

        pannelloLogo.add(headerText, BorderLayout.NORTH);
        pannelloLogo.setBorder(Constants.emptyBottom20);

        username.setBorder(Constants.emptyBottom10);
        password.setBorder(Constants.emptyBottom20);

        pannelloBtn.add(submitBtn);

        //Container
        Container contentView = new Container();

        contentView.add(pannelloBack);
        contentView.add(pannelloLogo);
        contentView.add(username);
        contentView.add(password);
        contentView.add(pannelloBtn);

        this.add(contentView);
    }

    /**
     * Imposta il testo all'interno della casella testo username.
     *
     * @param username Testo da inserire.
     */
    public void setUsername(String username) {
        this.username.setTextField(username);
    }

    /**
     * Imposta il testo all'interno della casella testo password.
     *
     * @param password Testo da inserire.
     */
    public void setPassword(String password) {
        this.password.setPassword(password);
    }

    /**
     * Ritorna il testo della casella testo username.
     *
     * @return String
     */
    public String getUsername() {
        return username.getTextField();
    }

    /**
     * Ritorna il testo della casella testo password.
     *
     * @return String
     */
    public String getPassword() {
        return password.getPassword();
    }

    /**
     * Porta il focus dell'utente sulla casella testo username.
     */
    public void getUsernameFocus(){
        username.getFieldFocus();
    }

    /**
     * Ritorna il pannello logo;
     *
     * @return PannelloBorder
     */
    public PannelloBorder getPannelloLogo() {
        return pannelloLogo;
    }

    /**
     * Metodo per intercettare la richiesta di login da parte dell'utente.
     *
     * @param e <code>ActionEvent</code> del bottone.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if(cmd.equals("login")){
            String inputUsername = username.getTextField();
            String inputPassword = password.getPassword();
            //Controllo che non ci siano valori vuoti
            if(inputUsername.isBlank() || inputPassword.isBlank()){
                username.setTextField("");
                password.setPassword("");
                username.getFieldFocus();
                JOptionPane.showMessageDialog(this, "Attenzione, devi riempire correttamente tutti i campi!", "Attenzione", JOptionPane.WARNING_MESSAGE);
                return;
            }
            //Controllo nel sistema se esiste l'utente
            if(Database.getAccess(inputUsername, inputPassword)){
                JOptionPane.showMessageDialog(this, "Bentornato, " + inputUsername, "Accesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new UserDashboard(inputUsername.trim());
            } else {
                username.setTextField("");
                password.setPassword("");
                username.getFieldFocus();
                JOptionPane.showMessageDialog(this, "Username o password non validi!", "Accesso", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
