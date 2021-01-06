package com.dariovarriale.screens;

import com.dariovarriale.utils.Constants;
import com.dariovarriale.widgets.*;
import com.dariovarriale.widgets.Button;
import com.dariovarriale.widgets.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Pagina di benvenuto del Gestore Spedizioni.
 * <p>
 * Si da priorità all'accesso o alla registrazione del cliente, che sarà colui che
 * inserirà le richieste di <code>Spedizione</code> all'interno del sistema.
 * L'amministratore potrà comunque effettuare il login per gestire l'avanzamento delle spedizioni.
 *
 * @author Dario Varriale - 145622
 * @version 1.0
 */
public class WelcomeScreen extends JFrame implements ActionListener, MouseListener {

    /**
     * Metodo costruttore della WelcomeScreen.
     */
    public WelcomeScreen(){
        super("Gestionale spedizioni - Welcome");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //Creo
        Text headerText = new Text("Gestionale Spedizioni", Constants.fontLabel26);
        Text subText = new Text("Con questo software potrai seguire le spedizioni.");
        Text adminLink = new Text("Sei un Admin? Clicca qui.");
        Button loginBtn = new Button(this, "Accedi");
        Button registerBtn = new Button(this,"Registrati", "regis");

        //UI Settings
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setHorizontalAlignment(SwingConstants.CENTER);
        headerText.setBorder(Constants.compoundBottom5);
        subText.setBorder(Constants.compoundBottom20);
        adminLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //ActionListener
        adminLink.addMouseListener(this);

        //Pannelli
        PannelloBorder pannelloLogo = new PannelloBorder();
        PannelloBorder pannelloBtnAccedi = new PannelloBorder();
        PannelloBorder pannelloBtnRegis = new PannelloBorder();
        JPanel pannelloAdmin = new JPanel();

        pannelloLogo.add(headerText, BorderLayout.NORTH);
        pannelloLogo.add(subText, BorderLayout.SOUTH);

        pannelloBtnAccedi.add(loginBtn);
        pannelloBtnAccedi.setBorder(Constants.emptyBottom5);

        pannelloBtnRegis.add(registerBtn);
        pannelloBtnRegis.setBorder(Constants.emptyBottom20);

        pannelloAdmin.add(adminLink);

        //Container
        Container contentView = new Container();

        contentView.add(pannelloLogo);
        contentView.add(pannelloBtnAccedi);
        contentView.add(pannelloBtnRegis);
        contentView.add(pannelloAdmin);

        this.add(contentView);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * ActionEvent per catturare le richieste di login o di registrazione da parte del cliente.
     *
     * @param e Evento e.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch(cmd){
            case "login":
                dispose();
                new UserLogin("Gestionale spedizioni - Accesso Clienti");
                break;
            case "regis":
                dispose();
                new RegistrationScreen();
                break;
            default: break;
        }
    }

    /**
     * Ascoltatore del click sul link per l'accesso amministratore.
     *
     * @param e Evento e.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        dispose();
        new AdminLoginScreen("Gestionale spedizioni - Amministratore");
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
}
