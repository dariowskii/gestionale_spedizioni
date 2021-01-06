package com.dariovarriale.widgets;

import com.dariovarriale.utils.Constants;

import javax.swing.*;

/**
 * Rappresenta l'icona della dashboard.
 *
 * @author Davrio Varriale - 145622
 * @version 1.0
 */
public class DashboardTitle extends JLabel {

    /**
     * Metodo costruttore.
     */
    public DashboardTitle(){
        super("Dashboard");
        setIcon(Constants.iconDashboard);
        setFont(Constants.fontLabel16);
        setBorder(Constants.emptyBottom20);
    }
}
