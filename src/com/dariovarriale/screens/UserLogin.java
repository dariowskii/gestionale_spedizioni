package com.dariovarriale.screens;

import com.dariovarriale.utils.Constants;
import com.dariovarriale.widgets.Text;

import javax.swing.*;
import java.awt.*;

/**
 * Pagina di login del cliente.
 * <p>
 * In questa schermata il cliente inserisce lo <strong>username</strong> e la <strong>password</strong>
 * per accedere alla <code>Dashboard</code>.
 *
 * @author Dario Varriale - 145622
 * @version 1.0
 */
public class UserLogin extends LoginScreen {

    /**
     * Metodo costruttore.
     *
     * @param titoloScreen Titolo del <code>JFrame</code>.
     */
    public UserLogin(String titoloScreen) {
        super(titoloScreen);

        //Creo
        Text subText = new Text("Inserisci le tue credenziali per accedere al serivizio.");
        JPanel pannelloLogo = getPannelloLogo();

        //UI Setting
        subText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setBorder(Constants.compoundBottom20);

        //Pannelli
        pannelloLogo.add(subText, BorderLayout.SOUTH);

        pack();
        setSize(700, getHeight());
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
