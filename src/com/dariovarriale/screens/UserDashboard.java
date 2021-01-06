package com.dariovarriale.screens;

import com.dariovarriale.utils.*;
import com.dariovarriale.widgets.PannelloBorder;
import com.dariovarriale.widgets.Text;
import com.dariovarriale.widgets.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Vector;

/**
 * <strong>Dashboard</strong> del cliente dove può guardare le sue spedizioni e aggiungerne
 * di nuove all'interno del sistema.
 *
 * @author Dario Varriale - 145622
 * @version 1.0
 */
public class UserDashboard extends Dashboard {

    /**
     * Username dell'utente loggato.
     */
    private final String username;
    /**
     * Tabella delle spedizioni.
     */
    private JTable table;

    /**
     * Metodo costruttore della <code>Dashboard</code> cliente.
     *
     * @param username Username dell'utente loggato.
     */
    public UserDashboard(String username){
        super("Gestionale spedizioni - Dashboard");
        this.username = username;

        //Creo
        Text benvenuto = new Text("Ciao, " + username, Constants.fontLabel26);
        Text subText = new Text("Da qui puoi controllare le tue spedizioni.");
        Button addSpedizioneBtn = new Button(this, "Aggiungi spedizione", "addSpedizione");

        JPopupMenu contestMenu = new JPopupMenu();
        JMenuItem richiediRimborso = new JMenuItem("Richiedi rimborso");

        table = getTableSpedizioni();
        Vector<Spedizione> filterSpedizioni = new Vector<>();
        for(Spedizione sped : getSpedizioni()){
            if(sped.getId().split("-")[0].equals(username)){
                filterSpedizioni.add(sped);
            }
        }

        contestMenu.add(richiediRimborso);
        richiediRimborso.setActionCommand("rimborso");
        richiediRimborso.addMouseListener(new TableMouseListener(table, getSpedizioni()){
            @Override
            public void mousePressed(MouseEvent e) {
                int index = table.getSelectedRow();
                Spedizione temp = filterSpedizioni.elementAt(index);
                temp.updateStatoManual("RIMBORSO RICHIESTO");
                filterSpedizioni.set(index, temp);
            }
        });

        //UI Setting
        benvenuto.setBorder(Constants.emptyBottom5);
        benvenuto.setHorizontalAlignment(SwingConstants.LEFT);

        //Aggiungo la possibilità di richiedere il rimborso per le spedizioni assicurate fallite
        setTableModel(new SpedizioniModelTable(filterSpedizioni, false){
            @Override
            public boolean isCellEditable(int row, int column) {
                Spedizione element = filterSpedizioni.elementAt(row);
                String stato = element.getStato();
                if(stato.equals("FALLITA") && element.getAssicurata().equals("Si")){
                    table.setComponentPopupMenu(contestMenu);
                } else table.setComponentPopupMenu(null);
                return false;
            }
        });

        //Pannelli
        PannelloBorder pannelloBenvenuto = getPannelloBenvenuto();
        PannelloBorder containerBenvenuto = getContainerBenvenuto();
        containerBenvenuto.add(benvenuto, BorderLayout.NORTH);
        containerBenvenuto.add(subText, BorderLayout.SOUTH);
        pannelloBenvenuto.add(addSpedizioneBtn, BorderLayout.EAST);

        pack();
        setSize(800, getHeight());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Metodo per intercettare i click dell'utente sul bottone per aggiungere una spedizione
     * e sul pulsante per salvare e uscire dalla <code>Dashboard</code>.
     *
     * @param e <code>ActionEvent</code> per catturare i click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case "exit":
                if(Database.saveSpedizioni(this)){
                    Database.saveLog("");
                    setVisible(false);
                    dispose();
                    new WelcomeScreen();
                    break;
                }
                break;
            case "addSpedizione":
                new AddSpedizioneScreen(this, username, getTableSpedizioni());
                break;
            default: break;
        }
    }

    /**
     * Metodo per intercettare la chiusura della finestra che salva le spedizioni.
     * <p>
     * Non effettuando il logout, viene salvato lo username dell'utente per poter fare poi un accesso diretto
     * alla <code>Dashboard</code> al prossimo avvio del software.
     *
     * @param e <code>WindowEvent</code> per catturare la chiusura del <code>JFrame</code>.
     */
    @Override
    public void windowClosing(WindowEvent e) {
        if(Database.saveSpedizioni(this)){
            System.out.println("WindowClosing: Salvo Spedizioni!");
            Database.saveLog(username);
            System.exit(0);
        }
    }
}
