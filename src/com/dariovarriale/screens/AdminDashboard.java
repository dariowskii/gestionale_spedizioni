package com.dariovarriale.screens;

import com.dariovarriale.utils.*;
import com.dariovarriale.widgets.PannelloBorder;
import com.dariovarriale.widgets.Text;
import com.dariovarriale.widgets.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * <strong>Dashboard</strong> dell'amministratore dove può gestire l'avanzamento
 * delle spedizioni all'interno del sistema.
 *
 * @author Dario Varriale - 145622
 * @version 1.0
 */
public class AdminDashboard extends Dashboard {

    /**
     * <code>Thread</code> per l'avanzamento delle spedizioni.
     */
    private ThreadSpedizione threadSpedizione;
    /**
     * Controllo per sapere se il thread è attivo o meno.
     */
    private boolean threadActive = false;
    /**
     * Bottone per attivare o fermare il thread.
     */
    private final Button threadButton;
    /**
     * Tabella delle spedizioni all'interno della dashboard.
     */
    private final JTable table;

    /**
     * Metodo costruttore.
     */
    public AdminDashboard(){
        super("Gestionale spedizioni - Dashboard Amministratore");

        //Creo
        Text benvenuto = new Text("Ciao, admin", Constants.fontLabel26);
        Text subText = new Text("Da qui puoi aggiornare le spedizioni dei clienti.");
        JPopupMenu contestMenu = new JPopupMenu();
        JMenuItem cancellaTerminata = new JMenuItem("Cancella dal sistema");
        table = getTableSpedizioni();

        contestMenu.add(cancellaTerminata);
        cancellaTerminata.setActionCommand("deleteSpedizione");
        cancellaTerminata.addMouseListener(new TableMouseListener(table, getSpedizioni()));
        //Imposto il menù contestuale sulle spedizioni ormai ultimate
        setTableModel(new SpedizioniModelTable(getSpedizioni(), true){
            @Override
            public boolean isCellEditable(int row, int column) {
                Spedizione element = getSpedizioni().elementAt(row);
                String stato = element.getStato();
                if((stato.equals("FALLITA") && element.getAssicurata().equals("No")) || (stato.equals("RICEVUTA") || stato.equals("RIMBORSO EROGATO"))){
                    table.setComponentPopupMenu(contestMenu);
                } else table.setComponentPopupMenu(null);
                return false;
            }
        });

        threadButton = new Button(this, "Avvia Spedizioni", "thread");

        //UI Setting
        benvenuto.setBorder(Constants.emptyBottom5);
        benvenuto.setHorizontalAlignment(SwingConstants.LEFT);

        //initTable(tableModel);

        //Pannelli
        PannelloBorder pannelloBenvenuto = getPannelloBenvenuto();
        PannelloBorder containerBenvenuto = getContainerBenvenuto();
        containerBenvenuto.add(benvenuto, BorderLayout.NORTH);
        containerBenvenuto.add(subText, BorderLayout.SOUTH);
        pannelloBenvenuto.add(threadButton, BorderLayout.EAST);

        pack();
        setSize(800, getHeight());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Metodo per intercettare il <strong>logout</strong> dell'amministratore, dove vengono salvate
     * le spedizioni e si viene rimandati alla <code>WelcomeScreen</code>.
     * <p>
     * Inoltre viene intercettata anche la richiesta di avanzamento delle spedizioni.
     *
     * @param e <code>ActionEvent</code> dei pulsanti.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case "exit":
                if(threadSpedizione != null && !threadSpedizione.isInterrupted()) threadSpedizione.interrupt();
                if (Database.saveSpedizioni(this)) {
                    dispose();
                    new WelcomeScreen();
                }
                break;
            case "thread":
                threadActive = !threadActive;
                if(threadActive){
                    if(threadSpedizione == null){
                        threadSpedizione = new ThreadSpedizione(getSpedizioni(), getTableSpedizioni(), threadButton, 2000);
                    }
                    threadSpedizione.start();
                    threadButton.setText("Ferma Spedizioni");
                    break;
                } else {
                    threadSpedizione.interrupt();
                    threadSpedizione = null;
                    threadButton.setText("Avvia Spedizioni");
                }
                break;
            default: break;
        }
    }

    /**
     * Metodo per intercettare la chiusura del <code>JFrame</code>. Vengono salvate in automatico le spedizioni.
     *
     * @param e <code>WindowEvent</code> del JFrame.
     */
    @Override
    public void windowClosing(WindowEvent e) {
        if(threadSpedizione != null && !threadSpedizione.isInterrupted()) threadSpedizione.interrupt();
        if(Database.saveSpedizioni(this)){
            System.out.println("WindowClosing: Salvo Spedizioni!");
            System.exit(0);
        }
    }
}
